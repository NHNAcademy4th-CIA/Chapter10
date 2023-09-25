package org.nhnacademy.jungbum;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * 사전 인덱스 만들기
 */
public class Quiz6 {
    private Logger logger = LoggerFactory.getLogger(Quiz6.class);
    private WordDictionary wordDictionary;
    public Quiz6() {
        wordDictionary = new WordDictionary();
        File note = new File("src/main/resources/book.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(note))) {
            String line;
            int i=1;
            while (((line = br.readLine()) != null)) {
                wordDictionary.wordAdd(line,i++);
            }
            logger.info("{}",wordDictionary.getWordDictionary());
        }catch (IOException e)
        {
            logger.error("{}",e);
        }
    }
}

/***
 * 낱말 사전
 */
class WordDictionary {
    private TreeMap<String,List<Integer>> wordDictionary;
    public WordDictionary(){
        wordDictionary = new TreeMap<>();
    }

    /***
     * 사전에 단어추가
     * @param line 추가 할 라인
     * @param i 추가 할 단어의 위치
     */
    public void wordAdd(String line,int i){
        String[] words =line.split(" ");
        for(String word:words)
        {
            if(word.toLowerCase().contains("the"))
                continue;
            if(word.length()<4)
                continue;
            if(wordDictionary.containsKey(word)) {
                List<Integer> tmp = new ArrayList<>();
                tmp.addAll(wordDictionary.get(word));
                tmp.add(i);
                wordDictionary.put(word,tmp);
                continue;
            }
            wordDictionary.put(word, Arrays.asList(i));
        }
    }

    /***
     * 디렉토리 조회
     * @return
     */
    public TreeMap<String,List<Integer>> getWordDictionary(){
        return wordDictionary;
    }

}

