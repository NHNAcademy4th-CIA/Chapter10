package org.nhnacademy.minju;

import java.util.HashMap;
import java.util.Scanner;
import org.nhnacademy.minju.textio.TextIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * .symbol table : 키는 함수의 이름, 값은 함수를 나타내는 StandardFunction type 객체
 * 기존의 SimpleInterpreter에 StandardFunction에 해당하는 연산을 추가
 */
public class Exercise7 {

    private static final Logger logger = LoggerFactory.getLogger(Exercise7.class);

    /**
     * Represents a syntax error found in the user's input.
     */
    private static class ParseError extends Exception {
        ParseError(String message) {
            super(message);
        }
    } // end nested class ParseError
    private enum Functions { SIN, COS, TAN, ABS, SQRT, LOG }

    /**
     * An object of this class represents one of the standard functions.
     */
    private static class StandardFunction {

        /**
         * Tells which function this is.
         */
        Functions functionCode;

        /**
         * Constructor creates an object to represent one of
         * the standard functions
         * @param code which function is represented.
         */
        StandardFunction(Functions code) {
            functionCode = code;
        }

        /**
         * Finds the value of this function for the specified
         * parameter value, x.
         */
        double evaluate(double x) {
            // (This uses the "switch expression" syntax)
            switch (functionCode) {
                case SIN:
                    return Math.sin(x);
                case COS:
                    return Math.cos(x);
                case TAN:
                    return Math.tan(x);
                case ABS:
                    return Math.abs(x);
                case SQRT:
                    return Math.sqrt(x);
                default:
                    return Math.log(x);
            }
        }

    } // end class StandardFunction

    /**
     * The symbolTable contains information about the
     * values of variables.  When a variable is assigned
     * a value, it is recorded in the symbol table.
     * The key is the name of the variable, and the
     * value is an object of type Double that contains
     * the value of the variable.  (The wrapper class
     * Double is used, since a HashMap cannot contain
     * objects belonging to the primitive type double.)
     */
    private static HashMap<String,Object> symbolTable;


    /**
     * .symbol table에 sin, cos, tan, sqrt, abs, log를 추가하고
     * 가장 처음 단어가 print면 expression을 계산,
     * let이면 <variable name, expression result>형태로 변수를 symbol table에 저장한다.
     * 조건 : word를 마주하면 variable인지 StandardFunctiond인지 판별해야 한다. => instanceof를 사용
     */
    public static void exercise7() {

        // Create the map that represents symbol table.

        symbolTable = new HashMap<>();

        // To start, add variables named "pi" and "e" to the symbol
        // table.  Their values are the usual mathematical constants.

        initSymbolTable(symbolTable);

        System.out.println("\n\nEnter commands; press return to end.");
        System.out.println("Commands must have the form:\n");
        System.out.println("      print <expression>");
        System.out.println("  or");
        System.out.println("      let <variable> = <expression>");

        while (true) {
            TextIO.put("\n?  ");
            TextIO.skipBlanks();
            if ( TextIO.peek() == '\n' ) {
                break;  // A blank input line ends the while loop and the program.
            }
            try {
                String command = TextIO.getWord();
                if (command.equalsIgnoreCase("print"))
                    doPrintCommand();
                else if (command.equalsIgnoreCase("let"))
                    doLetCommand();
                else
                    throw new ParseError("Command must begin with 'print' or 'let'.");
                TextIO.getln();
            }
            catch (ParseError e) {
                System.out.println("\n*** Error in input:    " + e.getMessage());
                System.out.println("*** Discarding input:  " + TextIO.getln());
            }
        }

        System.out.println("\n\nDone.");

    } // end main()

    private static void initSymbolTable(HashMap<String, Object> symbolTable) {
        symbolTable.put("sin", new StandardFunction(Functions.SIN));
        symbolTable.put("cos", new StandardFunction(Functions.COS));
        symbolTable.put("tan", new StandardFunction(Functions.TAN));
        symbolTable.put("sqrt", new StandardFunction(Functions.SQRT));
        symbolTable.put("abs", new StandardFunction(Functions.ABS));
        symbolTable.put("log", new StandardFunction(Functions.LOG));
    }
    /**
     * Process a command of the form  let <variable> = <expression>.
     * When this method is called, the word "let" has already
     * been read.  Read the variable name and the expression, and
     * store the value of the variable in the symbol table.
     */
    private static void doLetCommand() throws ParseError {
        TextIO.skipBlanks();
        if ( ! Character.isLetter(TextIO.peek()) )
            throw new ParseError("Expected variable name after 'let'.");
        String varName = readWord();  // The name of the variable.
        TextIO.skipBlanks();
        if ( TextIO.peek() != '=' )
            throw new ParseError("Expected '=' operator for 'let' command.");
        TextIO.getChar();
        double val = expressionValue();  // The value of the variable.
        TextIO.skipBlanks();
        if ( TextIO.peek() != '\n' )
            throw new ParseError("Extra data after end of expression.");
        symbolTable.put( varName, val );  // Add to symbol table.
        System.out.println("ok");
    }


    /**
     * Process a command of the form  print <expression>.
     * When this method is called, the word "print" has already
     * been read.  Evaluate the expression and print the value.
     */
    private static void doPrintCommand() throws ParseError {
        double val = expressionValue();
        TextIO.skipBlanks();
        if ( TextIO.peek() != '\n' )
            throw new ParseError("Extra data after end of expression.");
        System.out.println("Value is " + val);
    }


    /**
     * Read an expression from the current line of input and return its value.
     */
    private static double expressionValue() throws ParseError {
        TextIO.skipBlanks();
        boolean negative;  // True if there is a leading minus sign.
        negative = false;
        if (TextIO.peek() == '-') {
            TextIO.getAnyChar();
            negative = true;
        }
        double val;  // Value of the expression.
        val = termValue();  // An expression must start with a term.
        if (negative)
            val = -val; // Apply the leading minus sign
        TextIO.skipBlanks();
        while ( TextIO.peek() == '+' || TextIO.peek() == '-' ) {
            // Read the next term and add it to or subtract it from
            // the value of previous terms in the expression.
            char op = TextIO.getAnyChar();
            double nextVal = termValue();
            if (op == '+')
                val += nextVal;
            else
                val -= nextVal;
            TextIO.skipBlanks();
        }
        return val;
    } // end expressionValue()


    /**
     * Read a term from the current line of input and return its value.
     */
    private static double termValue() throws ParseError {
        TextIO.skipBlanks();
        double val;  // The value of the term.
        val = factorValue();  // A term must start with a factor.
        TextIO.skipBlanks();
        while ( TextIO.peek() == '*' || TextIO.peek() == '/' ) {
            // Read the next factor, and multiply or divide
            // the value-so-far by the value of this factor.
            char op = TextIO.getAnyChar();
            double nextVal = factorValue();
            if (op == '*')
                val *= nextVal;
            else
                val /= nextVal;
            TextIO.skipBlanks();
        }
        return val;
    } // end termValue()


    /**
     * Read a factor from the current line of input and return its value.
     * (Note:  The exponentiation operator, "^", is right associative.  That is,
     * a^b^c means a^(b^c), not (a^b)^c.  The BNF definition of primary takes
     * this into account.
     */
    private static double factorValue() throws ParseError {
        TextIO.skipBlanks();
        double val;  // Value of the factor.
        val = primaryValue();  // A factor must start with a primary.
        TextIO.skipBlanks();
        if ( TextIO.peek() == '^' ) {
            // Read the next factor, and exponentiate
            // the value by the value of that factor.
            TextIO.getChar();
            double nextVal = factorValue();
            val = Math.pow(val,nextVal);
            if (Double.isNaN(val))
                throw new ParseError("Illegal values for ^ operator.");
            TextIO.skipBlanks();
        }
        return val;
    } // end factorValue()


    /**
     *  Read a primary from the current line of input and
     *  return its value.  A primary must be a number,
     *  a variable, or an expression enclosed in parentheses.
     *  수정 : function 이름과 variable 이름을 구분해야 한다.
     *  -> 객체가 null이 아니고 Double이면 단어는 변수, StandardFunction 타입이면 단어는 함수
     */
    private static double primaryValue() throws ParseError {
        TextIO.skipBlanks();
        char ch = TextIO.peek();
        if ( Character.isDigit(ch) ) {
            // The factor is a number.  Read it and
            // return its value.
            return TextIO.getDouble();
        }
        else if ( Character.isLetter(ch) ) {
            // The factor is a variable.  Read its name and
            // look up its value in the symbol table.  If the
            // variable is not in the symbol table, an error
            // occurs.  (Note that the values in the symbol
            // table are objects of type Double.)
            String name = readWord();
            Object val = symbolTable.get(name);
            if (val == null)
                throw new ParseError("Unknown variable \"" + name + "\"");
            // 단어는 변수
            if (val instanceof Double) {
                return (Double) val;
            }
            // 단어는 함수
            StandardFunction standardFunction = (StandardFunction) val; // ex. sin, cos etc
//            TextIO.skipBlanks();
            if (TextIO.peek() != '(') {
                throw new ParseError("( 없음");
            }
            TextIO.getChar(); // remove (
            double expressionVal = expressionValue();
            TextIO.skipBlanks();
            if (TextIO.peek() != ')') {
                throw new ParseError(") 부재");
            }
            TextIO.getChar(); // remove )
            return standardFunction.evaluate(expressionVal);
        }
        else if ( ch == '(' ) {
            // The factor is an expression in parentheses.
            // Return the value of the expression.
            TextIO.getAnyChar();  // Read the "("
            double val = expressionValue();
            TextIO.skipBlanks();
            if ( TextIO.peek() != ')' )
                throw new ParseError("Missing right parenthesis.");
            TextIO.getAnyChar();  // Read the ")"
            return val;
        }
        else if ( ch == '\n' )
            throw new ParseError("End-of-line encountered in the middle of an expression.");
        else if ( ch == ')' )
            throw new ParseError("Extra right parenthesis.");
        else if ( ch == '+' || ch == '-' || ch == '*' || ch == '/')
            throw new ParseError("Misplaced operator.");
        else
            throw new ParseError("Unexpected character \"" + ch + "\" encountered.");
    }  // end primaryValue()


    /**
     *  Reads a word from input.  A word is any sequence of
     *  letters and digits, starting with a letter.  When
     *  this subroutine is called, it should already be
     *  known that the next character in the input is
     *  a letter.
     */
    private static String readWord() {
        String word = "";  // The word.
        char ch = TextIO.peek();
        while (Character.isLetter(ch) || Character.isDigit(ch)) {
            word += TextIO.getChar(); // Add the character to the word.
            ch = TextIO.peek();
        }
        return word;
    }
}
