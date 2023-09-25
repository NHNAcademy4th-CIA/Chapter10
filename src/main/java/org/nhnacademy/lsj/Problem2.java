package org.nhnacademy.lsj;

import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 집합 2개가 있을때 집합의 교집합 , 합집합 ,  차지합 구하는 문제.
 */
public class Problem2 {


    private static final Logger logger = LoggerFactory.getLogger(Problem2.class);

    private static final String regex = "^[+*-]{1}$";

    private static final Scanner sc = new Scanner(System.in);


    /**
     * set , set2 집합 2개 만들기 -> 연산자 받기(연산자가 차집합,여집합,합집합 결정).
     * -> 그거에 따른 결과 print.
     */
    public static void problem2() {


        while (true) {

            TreeSet<Integer> set;

            TreeSet<Integer> set2;

            logger.info("첫 번째 집합을 만듭니다");
            set = creatTreeSet();

            String operator = setOperator();

            logger.info("두 번째 집합을 만듭니다");
            set2 = creatTreeSet();

            if (operator.equals("+")) {
                printAddAll(set, set2);
            } else if (operator.equals("*")) {
                printByCondition(set, set2, true);
            } else if (operator.equals("-")) {
                printByCondition(set, set2, false);
            }


        }


        // 합집합 , 교집합 , A-B 구현

    }


    /**
     * 교집합과 , 차집합은 거의 동일한 논리 구조를 가짐.
     * 교집합 = 집합1의 원소가 집합2에 있는 것 .
     * 차집합 = 집합1의 원소가 집합2에 없는 것.
     * 따라서 하나의 메서드로 Procedure Abstraction 함.
     *
     * @param set  집합1.
     * @param set2 집합2.
     * @param flag condition.
     */
    public static void printByCondition(TreeSet<Integer> set, TreeSet<Integer> set2,
                                        boolean flag) {

        TreeSet<Integer> result = new TreeSet<>();

        Iterator iterator = set.iterator();
        Iterator iterator2 = set2.iterator();


        set.stream().filter(x -> set2.contains(x) == flag)
                .forEach(x -> result.add(x.intValue()));
        // true면 합집합 , false 면 차집합
        printSet(result);
    }

    /**
     * 합집합  출력하는 메서드 .
     *
     * @param set  집합1.
     * @param set2 집합2.
     */
    public static void printAddAll(TreeSet<Integer> set, TreeSet<Integer> set2) {

        TreeSet<Integer> result = new TreeSet<>();

        set.stream().forEach(x -> result.add(x.intValue()));
        set2.stream().forEach(x -> result.add(x.intValue()));

        printSet(result);
    }

    /**
     * 집합을 출력하는 메서드를 분리함.
     *
     * @param result 출력할 집합.
     */
    public static void printSet(TreeSet<Integer> result) {
        Iterator iterator = result.iterator();

        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        sb.append("[");

        while (iterator.hasNext()) {
            if (flag) {
                sb.append(", ");
            }
            sb.append(iterator.next());
            flag = true;
        }
        sb.append("]");

        logger.info("{}", sb);
    }

    /**
     * 연산자가 올바른지 판별하고 잘못된 경우 입력 재귀적으로 받음.
     *
     * @return 올바른 연산자.
     */
    public static String setOperator() {

        logger.info("연산자를 입력해 주세요");

        String str = sc.nextLine();

        try {
            if (!Pattern.matches(regex, str)) {
                logger.warn("올바른 연산자가 아닙니다");
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            setOperator();
        }
        return str;
    }

    /**
     * 집합을 양식에 맞게 입력받아 생성함 .
     * 양식 : 시작은 [ 끝은 ]   정수들은 , 로 구분.
     *
     * @return 집합.
     */
    public static TreeSet<Integer> creatTreeSet() {

        TreeSet<Integer> set = new TreeSet<>();
        logger.info("다음의 양식으로 입력해 주세요 [정수 , 정수 , 정수] 정수는 , 를 이용해 구분합니다");


        String str = sc.nextLine();

        try {

            if (!(str.charAt(0) == '[' && str.charAt(str.length() - 1) == ']')) {
                logger.warn("입력 양식이 올바르지 않습니다.");
                throw new IllegalArgumentException();
            }
            str = str.substring(1, str.length() - 1);
            str = str.replace(" ", ""); // 공백제거

            StringTokenizer st = new StringTokenizer(str, ","); // , 기준으로 나눔

            while (st.hasMoreTokens()) {
                set.add(Integer.valueOf(st.nextToken()));
            }

        } catch (NumberFormatException e) {
            logger.warn("올바른 정수 입력이 아닙니다");
            creatTreeSet();
        } catch (IllegalArgumentException e) {
            creatTreeSet();
        }

        return set;

    }

}
