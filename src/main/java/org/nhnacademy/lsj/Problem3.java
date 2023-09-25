package org.nhnacademy.lsj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 해시테이블 작성하는 클래스.
 */
public class Problem3 {


    private static final Logger logger = LoggerFactory.getLogger(Problem3.class);

    /**
     * 해시테이블로 쓸 배열을 선언해줌 .
     * 후에 put , print , remove , contain 메서드 사용하여 올바른값이 나오는지 test.
     */
    public static void problem3() {

        node[] table = new node[1024];

        put(table, "this", "1");
        put(table, "this", "2");
        put(table, "is", "3");
        put(table, "me", "4");

        print(get(table, "this"));

        logger.info("{}", containKey(table, "this"));

        remove(table, "this");
        print(get(table, "this"));

        logger.info("{}", containKey(table, "this"));


    }

    /**
     * 해시테이블에 원소를 집어넣음.
     *
     * @param table 테이블.
     * @param key   테이블의 index를 결정.
     * @param value 넣을 원소값.
     */
    public static void put(node[] table, String key, String value) {

        int index = key.hashCode() % 1024;

        if (table[index] == null) {
            table[index] = new node(value);
            return;
        }

        node p = table[index];

        while (p.getNext() != null) {
            p = p.getNext();
        }

        p.setNext(new node(value));

    }

    /**
     * 해당하는 index에 노드를 반환하는 get 메서드 .
     *
     * @param table 테이블 .
     * @param key   테이블 index 결정.
     * @return node.
     */
    public static node get(node[] table, String key) {
        return table[key.hashCode() % 1024];
    }

    /**
     * 해당하는 key를 삭제.
     *
     * @param table 테이블.
     * @param key   해시코드 만들 key.
     */
    public static void remove(node[] table, String key) {

        int index = key.hashCode() % 1024;

        table[index] = null;
    }

    /**
     * 해당하는 key값에 노드 존재하는지.
     *
     * @param table 테이블.
     * @param key   키.
     * @return true , false.
     */
    public static boolean containKey(node[] table, String key) {

        int index = key.hashCode() % 1024;

        return table[index] != null;

    }

    /**
     * 해당하는 index에 노드 있는지.
     *
     * @param root 노드의 시작점.
     */
    public static void print(node root) {
        StringBuilder sb = new StringBuilder();
        while (root != null) {
            sb.append(root.getValue()).append(" ");
            root = root.getNext();
        }
        logger.info("{}", sb);
    }

    /**
     * 해당하는 index의 크기 반환.
     *
     * @param table 테이블.
     * @return 크기.
     */
    public static int size(node[] table) {

        int size = 0;

        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                size++;
            }
        }
        return size;
    }

}


class node {
    private String value;
    private node next;

    public node(String value) {
        this.value = value;
        this.next = null;
    }


    public node getNext() {
        return next;
    }

    public void setNext(node next) {
        this.next = next;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
