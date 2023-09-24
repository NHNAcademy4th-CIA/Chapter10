package org.nhnacademy.jungbum;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Quiz2 {

    public Quiz2() {
        new Calc();
    }
}

class Calc {

    private CustomSet a;
    private CustomSet b;
    private char op;
    private final Scanner scanner = new Scanner(System.in);
    private Logger logger = LoggerFactory.getLogger(Quiz2.class);

    public Calc() {
        String line;
        while (!(line = scanner.nextLine()).equals("")) {
            logger.info(line);
            a = new CustomSet();
            b = new CustomSet();
            int count = 0;
            for (char c : line.toCharArray()) {
                if ('[' == c || ']' == c) {
                    count++;
                    continue;
                }
                if ('+' == c || '-' == c || '*' == c) {
                    op = c;
                    continue;
                }
                if (c == ',' || c == ' ') {
                    continue;
                }
                try {
                    int num = Character.getNumericValue(c);
                    numCheck(num);
                    if (count == 1 || count == 2) {
                        a.getSet().add(num);
                    } else {
                        b.getSet().add(num);
                    }
                } catch (IllegalArgumentException e) {
                    logger.warn(e.toString());
                }
            }
            switch (op) {
                case '+':
                    a.addAll(b);
                    logger.info("{}", a.getSet());
                    break;
                case '-':
                    a.removeAll(b);
                    logger.info("{}", a.getSet());
                    break;
                case '*':
                    a.retrainAll(b);
                    logger.info("{}", a.getSet());
                    break;
            }
        }
    }

    public void numCheck(int num) throws IllegalArgumentException {
        if (num == -1 || num == -2)
            throw new IllegalArgumentException("숫자가 아닌 값이 있습니다.");
    }
}

class CustomSet {
    private static TreeSet<Integer> set;

    public CustomSet(int... nums) {
        set = new TreeSet<>();
        for (int num : nums) {
            set.add(num);
        }
    }

    public void addAll(CustomSet set) {
        getSet().addAll(set.getSet());
    }

    public void retrainAll(CustomSet set) {
        getSet().retainAll(set.getSet());
    }

    public void removeAll(CustomSet set) {
        getSet().removeAll(set.getSet());
    }

    public Set<Integer> getSet() {
        return set;
    }
}
