//package org.nhnacademy.jungbum;
//
//public class Quiz7 {
//}
//
//
//private enum Functions { SIN, COS, TAN, ABS, SQRT, LOG }
//
///**
// * An object of this class represents one of the standard functions.
// */
//private static class StandardFunction {
//
//    /**
//     * Tells which function this is.
//     */
//    Functions functionCode;
//
//    /**
//     * Constructor creates an object to represent one of
//     * the standard functions
//     * @param code which function is represented.
//     */
//    StandardFunction(Functions code) {
//        functionCode = code;
//    }
//
//    /**
//     * Finds the value of this function for the specified
//     * parameter value, x.
//     */
//    double evaluate(double x) {
//        // (This uses the "switch expression" syntax)
//        return switch(functionCode) {
//            case SIN -> Math.sin(x);
//            case COS -> Math.cos(x);
//            case TAN -> Math.tan(x);
//            case ABS -> Math.abs(x);
//            case SQRT -> Math.sqrt(x);
//            default -> Math.log(x);
//        };
//    }
//
//} // end class StandardFunction