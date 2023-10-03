package org.nhnacademy.minju;

import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * .음수가 아닌 정수 집합 2개를 입력 받고
 * + => 합집합
 * - => 차집합
 * * => 교집합
 */
public class Exercise2 {

    private static final Logger logger = LoggerFactory.getLogger(Exercise2.class);
    private static final String operationRegex = "[+|*|-]";
    private static final String numericRegex = "[0-9]+";

    /**
     * .입력    [1, 2, 3] + [3,  5,  7]
     * 출력    [1, 2, 3, 5, 7]
     */
    public static void exercise2() {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (!(input = scanner.nextLine()).isBlank()) {
            try {
                calcSet(input.trim());
            } catch (IllegalArgumentException | ArithmeticException e) {
                logger.warn(e.getMessage());
            }
        }
    }

    /**
     * .operationRegex(+, -, *)를 사용하여 operator의 인덱스를 찾는다.
     * 해당 인덱스를 기준으로 split해 문자열을 toSet()으로 set으로 만든다.
     * operator에 따라 set 연산
     * @param input input
     */
    private static void calcSet(String input) {

        TreeSet<Integer> set1;
        TreeSet<Integer> set2;
        char operator;
        int opIndex;

        Pattern pattern = Pattern.compile(operationRegex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            opIndex = matcher.start();
            operator = input.charAt(opIndex);
        } else {
            throw new IllegalArgumentException("부적절한 형식");
        }
        set1 = toSet(input.substring(0, opIndex - 1).replace(" ", "").split(","));
        set2 = toSet(input.substring(opIndex + 1).replace(" ", "").split(","));

        logger.info("{} {} {}", set1, operator, set2);
        switch (operator) {
            case '+':
                // addAll
                set1.addAll(set2);
                break;
            case '*':
                // retainAll
                set1.retainAll(set2);
                break;
            case '-':
                // removeAll
                set1.removeAll(set2);
                break;
            default:
                throw new IllegalArgumentException("no operator");
        }
        logger.info("{} {}", input, set1);
    }

    /**
     * .문자열을 set으로 바꾼다
     * 조건 : [] 위치 확인
     *
     * @param inputSplit "," split
     * @return set
     */
    private static TreeSet<Integer> toSet(String[] inputSplit) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < inputSplit.length; i++) {
            inputSplit[i] = inputSplit[i].trim();
            if ((i != 0 && inputSplit[i].charAt(0) == ('[')
                    || (i != inputSplit.length - 1 && inputSplit[i].charAt(0) == (']')))) {
                throw new IllegalArgumentException("bracket이 부적절한 위치에 있다.");
            }
            if (i == 0) {
                inputSplit[i] = inputSplit[i].substring(1).trim();
            } else if (i == inputSplit.length - 1) {
                inputSplit[i] = inputSplit[i].substring(0, inputSplit[i].length() - 1).trim();
            }
            if (Pattern.matches(numericRegex, inputSplit[i])) {
                set.add(Integer.parseInt(inputSplit[i]));
            } else {
                throw new ArithmeticException("not numeric");
            }
        }
        return set;
    }
}
