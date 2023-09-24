package org.nhnacademy.lsj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Problem6 {

    private static final Logger logger = LoggerFactory.getLogger(Problem6.class);

    public static void problem6() {

        Scanner sc = new Scanner(System.in);

        TreeMap<String, TreeSet<Integer>> m = new TreeMap<>();

        logger.info("읽어올 파일 이름을 입력해주세요");

        String fileName = sc.nextLine();

        try (BufferedReader bf = new BufferedReader(new FileReader(
                new File("src/main/resources/" + fileName + ".txt")))) {

            String line;
            int index = 1;

            while ((line = bf.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(line);
                while (stk.hasMoreTokens()) {
                    String temp = stk.nextToken();

                    if (temp.equals("the") || temp.length() < 3) {
                        continue;
                    }

                    TreeSet<Integer> reference = m.get(temp);

                    if (reference == null) {
                        TreeSet<Integer> firstRef = new TreeSet<>();
                        firstRef.add(index);
                        m.put(temp, firstRef);
                        continue;
                    }

                    reference.add(index);
                }
                index++;

            }


        } catch (FileNotFoundException e) {
            logger.warn("파일을 찾을 수 없습니다.");
            problem6();
        } catch (IOException e) {
            logger.warn("입출력이 올바르지 않습니다.");
            problem6();
        }

        printBook(m);

    }

    public static void printBook(TreeMap<String, TreeSet<Integer>> m) {

        StringBuilder sb = new StringBuilder();
        for (var v : m.entrySet()) {

            sb.append(v.getKey()).append(" ");

            Iterator iterator = v.getValue().iterator();

            while (iterator.hasNext()) {
                sb.append(iterator.next()).append(" ");
            }

            logger.info("{}", sb);

            sb.setLength(0);

        }
    }

}

