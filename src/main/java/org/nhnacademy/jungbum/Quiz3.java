package org.nhnacademy.jungbum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * HashMap구현
 */

public class Quiz3 {
    private Logger logger = LoggerFactory.getLogger(Quiz3.class);

    public Quiz3() {
        Table table = new Table(100);
        table.put("1", "aqsd");

        logger.info("검색 결과{}", table.get("1"));
        logger.info("삭제 요청");
        table.remove("1");

        logger.info("검색 결과 존재하는가? : {}", table.containsKey("1"));
        logger.info("현재 사이즈는? {}", table.size());
    }
}

/***
 * 노드 정의
 */
class Node {
    private String key;
    private String value;
    private Node next;

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public Node getNext() {
        return next;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

/***
 * 노드 테이블 정의
 */
class Table {
    private Logger logger = LoggerFactory.getLogger(Quiz3.class);
    private Node[] table;

    private int count;

    /***
     * 생성자
     * @param init 배열 공간
     */
    public Table(int init) {
        if (init <= 0)
            throw new IllegalArgumentException("테이블 사이즈가 0이하다.");
        table = new Node[init];
    }

    /***
     * 생성
     * @param key 입력할 키
     * @param value 입력할 밸류
     */
    public void put(String key, String value) {

        if (key == null) {
            throw new IllegalArgumentException("키가 비었습니다");
        }


        int bucket = key.hashCode();

        Node list = table[bucket];
        while (list != null) {
            if (list.getKey().equals(key))
                break;
            list = list.getNext();
        }

        if (list != null) {

            list.setValue(value);
        } else {
            resize();
            Node newNode = new Node();
            newNode.setKey(key);
            newNode.setValue(value);
            newNode.setNext(table[bucket]);
            table[bucket] = newNode;
            count++;
        }
    }

    /***
     * 불러오기
     * @param key 조회할 키
     * @return 불러온 밸류
     */
    public String get(String key) {

        int bucket = key.hashCode();

        Node list = table[bucket];
        while (list != null) {

            if (list.getKey().equals(key))
                return list.getValue();
            list = list.getNext();
        }


        return null;
    }


    /***
     * 삭제
     * @param key 삭제할 키
     */
    public void remove(String key) {

        int bucket = key.hashCode();
        if (table[bucket] == null) {
            return;
        }

        if (table[bucket].getKey().equals(key)) {
            table[bucket] = table[bucket].getNext();
            count--;
            return;
        }


        Node prev = table[bucket];
        Node curr = prev.getNext();
        while (curr != null && !curr.getKey().equals(key)) {
            curr = curr.getNext();
            prev = curr;
        }


        if (curr != null) {
            prev.setNext(curr.getNext());
            count--;
        }
    }


    /***
     * 같은게 있는지
     * @param key 조회할키
     * @return 같은게잇다면 true
     */
    public boolean containsKey(String key) {

        int bucket = key.hashCode();
        Node list = table[bucket];
        while (list != null) {
            if (list.getKey().equals(key))
                return true;
            list = list.getNext();
        }

        return false;
    }


    /***
     * size
     * @return table size
     */
    public int size() {
        return count;
    }


    /***
     * 사이즈 재할당
     */
    private void resize() {
        Node[] newtable = new Node[table.length * 2];
        for (int i = 0; i < table.length; i++) {
            Node list = table[i];
            while (list != null) {
                Node next = list.getNext();
                int hash = list.getKey().hashCode();

                list.setNext(newtable[hash]);
                newtable[hash] = list;
                list = next;
            }
        }
        table = newtable;
    }


}