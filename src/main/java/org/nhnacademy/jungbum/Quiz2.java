package org.nhnacademy.jungbum;

import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * 집합 - 합집합, 교집합, 차집합 만들기
 */
public class Quiz2 {

    public Quiz2() {
        new Calc();
    }
}

/***
 * 집합 계산기
 */
class Calc {

    private CustomSet a;
    private CustomSet b;
    private String op;
    private final Scanner scanner = new Scanner(System.in);
    private Logger logger = LoggerFactory.getLogger(Quiz2.class);

    /***
     * 글자제외하고 추가
     */
    public Calc() {
        String line;
        while (!(line = scanner.nextLine()).equals("")) {
            logger.info(line);
            a = new CustomSet();
            b = new CustomSet();
            int count = 0;
            StringTokenizer stringTokenizer = new StringTokenizer(line);
            while (stringTokenizer.hasMoreTokens()) {
                String numString = stringTokenizer.nextToken();
                System.out.println(numString);
                if (numString.equals("[") || numString.equals("]")) {
                    count++;
                    continue;
                }
                if (numString.equals("+") || numString.equals("-") || numString.equals("*")) {
                    op = numString;
                    continue;
                }
                try {
                    int num = Integer.parseInt(numString.split(",")[0]);
                    numCheck(num);
                    if (count == 1 || count == 2) {
                        System.out.println("1 "+num);
                        a.getSet().add(num);
                    } else {
                        System.out.println("2 "+num);

                        b.getSet().add(num);
                    }
                } catch (IllegalArgumentException e) {
                    logger.warn(e.toString());
                }
            }
            logger.info("{}",a.getSet());
            logger.info("{}",b.getSet());
            switch (op) {
                case "+":
                    a.addAll(b);
                    logger.info("{}", a.getSet());
                    break;
                case "-":
                    a.removeAll(b);
                    logger.info("{}", a.getSet());
                    break;
                case "*":
                    a.retrainAll(b);
                    logger.info("{}", a.getSet());
                    break;
            }
        }
    }

    /***
     * 숫자가 아닐때 예외처리
     * @param num 숫자후보
     * @throws IllegalArgumentException 숫자가아닐때
     */
    public void numCheck(int num) throws IllegalArgumentException {
        if (num == -1 || num == -2)
            throw new IllegalArgumentException("숫자가 아닌 값이 있습니다.");
    }
}

/***
 * 사용자 정의 커스텀 셋
 */
class CustomSet {
    private TreeSet<Integer> set;

    public CustomSet() {
        set = new TreeSet<>();
    }

    /***
     * 합집합
     * @param set 집합
     */
    public void addAll(CustomSet set) {
        getSet().addAll(set.getSet());
    }

    /***
     * 교집합
     * @param set 집합
     */
    public void retrainAll(CustomSet set) {
        getSet().retainAll(set.getSet());
    }

    /***
     * 차집합
     * @param set 집합
     */
    public void removeAll(CustomSet set) {
        getSet().removeAll(set.getSet());
    }

    /***
     * 집합 읽기
     * @return 집합값
     */
    public Set<Integer> getSet() {
        return set;
    }

}
