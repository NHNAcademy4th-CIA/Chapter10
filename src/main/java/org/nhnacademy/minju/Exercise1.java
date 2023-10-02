package org.nhnacademy.minju;

import java.util.TreeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * .phoneDirectory를 TreeMap으로 작성
 */
public class Exercise1 {
    /**
     * name, number를 갖는 TreeMap
     */
    private TreeMap<String, String> data;
    private static final Logger logger = LoggerFactory.getLogger(Exercise1.class);


    /**
     * TreeMap을 초기화
     */
    public Exercise1() {
        data = new TreeMap<>();
    }


    /**
     * find는 매개변수 이름과 data 안에 있는 이름이 같으면 그에 대한 인덱스를 반환하는 메서드
     * getNumber(String name) : 해당 인덱스를 받아 인덱스의 number를 반환
     */

    /**
     * 주어진 이름에 대해 phone number를 리턴
     *
     * @param name name
     * @return phone number
     */
    public String getNumber(String name) {
        return data.get(name);
    }

    /**
     * TreeMap에 <name, number> 넣기
     */
    public void putNumber(String name, String number) {
        if (name == null || number == null) {
            throw new IllegalArgumentException("name and number cannot be null");
        }
        data.put(name, number);
    }

    public static void exercise1() {
        Exercise1 treeMapTest = new Exercise1();
        treeMapTest.putNumber("abc", "010-1234-5678");
        treeMapTest.putNumber("dde", "010-4321-5678");
        treeMapTest.putNumber("keq", "010-9876-5678");

        logger.info("{}", treeMapTest.getNumber("abc"));
    }
}
