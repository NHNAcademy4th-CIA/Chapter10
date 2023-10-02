package org.nhnacademy.minju;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * .키와 값이 모두 String인 해시 테이블 작성
 */
public class Exercise3 {
    private static final Logger logger = LoggerFactory.getLogger(Exercise3.class);
    private static final int TABLE_SIZE = 1024;

    /**
     * .hash table test
     * put, get, containKey, remove, size
     */
    public static void exercise3() {
        try {

            Node[] hashTable = new Node[TABLE_SIZE];

            String[] keys = {"fruit", "drink", "hello", "language", "time"};
            String[] values = {"banana", "water", "world", "java", "1020"};

            for (int i = 0; i < keys.length; i++) {
                put(hashTable, keys[i], values[i]);
                logger.info("get value : {}", get(hashTable, keys[i]));
            }

            logger.info("hashTable contain drink : {}", containKey(hashTable, "drink"));
            logger.info("hashTable contain a : {}", containKey(hashTable, "a"));
            logger.info("current size : {}", size(hashTable));
            remove(hashTable, "time");
            logger.info("remove hello : {}", size(hashTable));
            remove(hashTable, "drink");
            logger.info("remove hello : {}", size(hashTable));
        } catch (IllegalArgumentException e) {
            logger.warn(e.getMessage());
        }
    }

    private static int hashMethod(Object key) {
        return Math.abs(key.hashCode()) % TABLE_SIZE;
    }

    public static List<String> get(Node[] table, String key) {
        int bucket = hashMethod(key); // index를 가지고 온다
        ArrayList<String> values = new ArrayList<>();
        Node p = table[bucket];
        while (p != null) {
            values.add(p.getValue());
            p = p.getNext();
        }
        return values;
    }

    /**
     * .key와 일치하는 노드를 찾아 그 노드의 다음 노드를 삭제시킬 노드에 이어붙인다
     *
     * @param key   not null
     * @param value value
     */
    public static void put(Node[] table, String key, String value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("null이 들어올 수 없음");
        }

        int bucket = hashMethod(key);
        if (table[bucket] == null) {
            // table[bucket]이 비어있을 때
            table[bucket] = new Node(value);
            return;
        }

        // 다음 노드로 설정하기
        Node p = table[bucket];
        while (p.getNext() != null) {
            p = p.getNext();
        }
        p.setNext(new Node(value));
    }

    public static void remove(Node[] table, String key) {
        int bucket = hashMethod(key);
        if (table[bucket] == null) {
            // linked list가 없음
            return;
        }
        table[bucket] = null;
    }


    public static boolean containKey(Node[] table, String key) {
        int bucket = hashMethod(key);
        return table[bucket] != null;
    }

    public static int size(Node[] table) {
        int size = 0;
        for (int i = 0; i < table.length; i++) {
            size++;
        }
        return size;
    }
}
class Node {
    private String value;
    private Node next;

    public Node(String value) {
        this.value = value;
        this.next = null;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
//class HashTable {
//    private static class ListNode {
//        private String key;
//        private String value;
//        private ListNode next;
//
//        public String getKey() {
//            return key;
//        }
//
//        public void setKey(String key) {
//            this.key = key;
//        }
//
//        public String getValue() {
//            return value;
//        }
//
//        public void setValue(String value) {
//            this.value = value;
//        }
//
//        public ListNode getNext() {
//            return next;
//        }
//
//        public void setNext(ListNode next) {
//            this.next = next;
//        }
//    }
//
//    private ListNode[] table;
//    private int count;
//
//    public HashTable() {
//        table = new ListNode[11]; // 초기 용량(버킷의 수)
//    }
//
//    public HashTable(int initialSize) {
//        if (initialSize <= 0) {
//            throw new IllegalArgumentException("size must bigger than 0");
//        }
//        table = new ListNode[initialSize];
//        count = 0;
//    }
//
//
//    /**
//     * key-value 에서 key를 테이블에 저장할 때 key값을 Hash Method를 이용해 계산을 수행한 후,
//     * 그 결과값을 배열의 인덱스로 사용하여 저장하는 방식이다. 여기서 key값을 계산하는 것이 Hash Method 이다.
//     * hashCode()는 음수
//     **/
//    private int hashMethod(Object key) {
//        return Math.abs(key.hashCode()) % table.length;
//    }
//
//    public String get(String key) {
//        int bucket = hashMethod(key); // index를 가지고 온다
//        ListNode listNode = table[bucket];
//        while (listNode != null) {
//            if (listNode.key.equals(key)) {
//                return listNode.getValue();
//            }
//            listNode = listNode.getNext();
//        }
//        return null;
//    }
//
//    /**
//     * .key와 일치하는 노드를 찾아 그 노드의 다음 노드를 삭제시킬 노드에 이어붙인다
//     *
//     * @param key   not null
//     * @param value value
//     */
//    public void put(String key, String value) {
//        if (key == null || value == null) {
//            throw new IllegalArgumentException("null이 들어올 수 없음");
//        }
//
//        int bucket = hashMethod(key);
//        ListNode listNode = table[bucket];
//        // key 값이 같은 노드를 찾는다.
//        while (listNode != null) {
//            if (listNode.key.equals(key)) {
//                break;
//            }
//            listNode = listNode.getNext();
//        }
//        // listnode가 null이거나 equals(key)
//        if (listNode != null) {
//            listNode.setValue(value);
//            return;
//        }
//        // null -> 새 노드를 만든다
//        ListNode newNode = new ListNode();
//        newNode.setKey(key);
//        newNode.setValue(value);
//        newNode.setNext(table[bucket]);
//        table[bucket] = newNode;
//        count++;
//    }
//
//    public void remove(String key) {
//        int bucket = hashMethod(key);
//        if (table[bucket] == null) {
//            // linked list가 없음
//            return;
//        }
//        if (table[bucket].key.equals(key)) {
//            // 가장 처음에 있다
//            table[bucket] = table[bucket].getNext();
//            count--;
//            return;
//        }
//        // 중간이나 맨 끝
//        ListNode listNode = table[bucket];
//        ListNode isEqualNode = listNode.getNext();
//        while (isEqualNode != null) {
//            if (isEqualNode.getKey().equals(key)) {
//                count--;
//                listNode.setNext(isEqualNode.getNext());
//                return;
//            }
//            listNode = isEqualNode;
//            isEqualNode = isEqualNode.getNext();
//        }
//    }
//
//
//    public boolean containKey(String key) {
//        int bucket = hashMethod(key);
//        ListNode listNode = table[bucket];
//        while (listNode != null) {
//            if (listNode.getKey().equals(key)) {
//                return true;
//            }
//            listNode = listNode.getNext();
//        }
//        return false;
//    }
//
//    public int size() {
//        return count;
//    }
//
//
//}