package org.nhnacademy.lsj;

import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Problem2 {


    private static final Logger logger = LoggerFactory.getLogger(Problem2.class);

    private static final String regex = "^[+*-]{1}$";

    private static final Scanner sc = new Scanner(System.in);


    public static void problem2() {


        while (true) {

            TreeSet<Integer> set = new TreeSet<>();
            TreeSet<Integer> set2 = new TreeSet<>();

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
                printByCondition(set,set2,false);
            }


        }


        // 합집합 , 교집합 , A-B 구현

    }


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

    public static void printAddAll(TreeSet<Integer> set, TreeSet<Integer> set2) {

        TreeSet<Integer> result = new TreeSet<>();

        set.stream().forEach(x -> result.add(x.intValue()));
        set2.stream().forEach(x -> result.add(x.intValue()));

        printSet(result);
    }

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
