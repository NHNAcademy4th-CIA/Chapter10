package org.nhnacademy.lsj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 특정 단어가 나왔을때 그 단어가 나온 줄 번호를 저장하는 프로그램.
 */
public class Problem6 {

    private static final Logger logger = LoggerFactory.getLogger(Problem6.class);

    /**
     * 파일 읽어옴 -> 읽어온거 한줄씩 읽어 -> 한줄에서 공백기준으로 단어 나눠 -> 그거 기준에 따라 map에 넣어줘.
     */
    public static void problem6() {

        Scanner sc = new Scanner(System.in);

        TreeMap<String, TreeSet<Integer>> m = new TreeMap<>();

        logger.info("읽어올 파일 이름을 입력해주세요");

        String fileName = sc.nextLine();

        BufferedWriter bufferedWriter = null;

        try (BufferedReader bf = new BufferedReader(new FileReader(
                new File("src/main/resources/" + fileName + ".txt")))) {


            bufferedWriter = new BufferedWriter(new FileWriter("src/main/resources/concordance.txt"));


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

        try {
            printBook(m, bufferedWriter);
        } catch (IOException e) {
            logger.warn("쓰지마세용");
        }

    }

    /**
     * 책 전체 출력 .
     *
     * @param m map , 단어와 그 단어가 나온 줄 번호를 set으로 가짐.
     */
    public static void printBook(TreeMap<String, TreeSet<Integer>> m, BufferedWriter bufferedWriter)
            throws IOException {

        StringBuilder sb = new StringBuilder();
        for (var v : m.entrySet()) {

            sb.append(v.getKey()).append(" ");

            Iterator iterator = v.getValue().iterator();

            while (iterator.hasNext()) {
                sb.append(iterator.next()).append(" ");
            }

            logger.info("{}", sb); // write 쓰기

            bufferedWriter.write(sb.toString());

            sb.setLength(0);

        }
    }

}

