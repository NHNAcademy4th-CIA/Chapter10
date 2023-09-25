package org.nhnacademy.minju;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * .concordance for a document :
 * <문서에 등장하는 단어, 등장하는 줄 번호> 형태
 * 조건 : 단어에 the 제외, 길이가 3 미만인 단어는 제외
 */
public class Exercise6 {
    private static final Logger logger = LoggerFactory.getLogger(Exercise6.class);

    /**
     * .파일을 읽어 조건을 체크하고 TreeMap에 넣는다
     * TreeMap을 다시 파일에 쓴다
     */
    public static void exercise6() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/exercise6.txt"));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/resources/concordance.txt"))) {
            TreeMap<String, TreeSet<Integer>> concordance = new TreeMap<>();
            int lineNumber = 1;
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                StringBuilder sb = new StringBuilder();
                char[] chars = line.toCharArray();
                for (char ch : chars) {
                    // 공백인지 마지막 글자인지 확인
                    if (Character.isWhitespace(ch) || ch == chars[chars.length - 1]) {
                        if (sb.toString().length() >= 3 && !sb.toString().equalsIgnoreCase("the")) {
                            insertItem(sb.toString().toLowerCase(), lineNumber, concordance);
                        }
                        sb.setLength(0);
                    }
                    if (Character.isLetter(ch)) {
                        sb.append(ch);
                    }
                }
                lineNumber++;
            }

            writeConcordance(concordance, bufferedWriter);
            printConcordance(concordance);
        } catch (IOException e) {
            logger.warn(e.getMessage());
        }
    }

    /**
     * .TreeSet에 Integer(줄 number) 삽입
     * @param word 단어
     * @param lineNumber 줄 number
     * @param concordance TreeMap<String, TreeSet<Integer>> => word와 일치하는 단어가 TreeMap 내에 존재하는지 확인,
     *                    존재하면 Set에 lineNumber 추가, 부재 시 새로운 TreeSet을 생성해 lineNumber를 넣고 해당 TreeSet을 concordance에 넣는다.
     */
    private static void insertItem(String word, int lineNumber, TreeMap<String, TreeSet<Integer>> concordance) {
        TreeSet<Integer> lineNumberSet = concordance.get(word);

        if (lineNumberSet == null) {
            // 단어가 들어가있지 않은 상태
            lineNumberSet = new TreeSet<>();
            lineNumberSet.add(lineNumber);
            concordance.put(word, lineNumberSet);
            return;
        }
        lineNumberSet.add(lineNumber);
    }

    /**
     * .파일에 concordance를 적는다. entrySet을 사용하여 key와 value를 한 줄에 적고 newLine()으로 한 줄 띄어 쓴다.
     * @param concordance TreeMap
     * @param bufferedWriter BufferedWriter FileWriter
     * @throws IOException BufferedWriter
     */
    private static void writeConcordance(TreeMap<String, TreeSet<Integer>> concordance, BufferedWriter bufferedWriter)
            throws IOException {
        for (Map.Entry<String, TreeSet<Integer>> entry : concordance.entrySet()) {
            bufferedWriter.write(entry.getKey() + " " + entry.getValue());
            bufferedWriter.newLine();
        }
    }

    private static void printConcordance(TreeMap<String, TreeSet<Integer>> concordance) {
        for (Map.Entry<String, TreeSet<Integer>> entry : concordance.entrySet()) {
            logger.info("{} : {}번째 줄", entry.getKey(), entry.getValue());
        }
    }
}
