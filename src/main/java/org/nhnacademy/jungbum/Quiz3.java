package org.nhnacademy.jungbum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Quiz3 {
    public static void main(String[] args) {
        Table table = new Table(2);
        String key;
        String value;
    }
}

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

class Table {
    private Logger logger = LoggerFactory.getLogger(Quiz3.class);
    private Node[] table;

    private int count;

    public Table() {
        table = new Node[10];
    }


    public Table(int initialSize) {
        if (initialSize <= 0)
            throw new IllegalArgumentException("테이블 사이즈가 0이하다.");
        table = new Node[initialSize];
    }

    void dump() {
        for (int i = 0; i < table.length; i++) {

            logger.info("{} :", i);
            Node list = table[i];
            while (list != null) {
                logger.info("  ({},{})", list.getKey(), list.getValue());
                list = list.getNext();
            }
            System.out.println();
        }
    }

    public void put(String key, String value) {

        if (key == null) {
            throw new IllegalArgumentException("키가 비었습니다");
        }


        int bucket = hash(key);

        Node list = table[bucket];
        while (list != null) {
            if (list.getKey().equals(key))
                break;
            list = list.getNext();
        }

        if (list != null) {

            list.setValue(value);
        } else {

            if (count >= 0.75 * table.length) {

                resize();
                bucket = hash(key);
            }
            Node newNode = new Node();
            newNode.setKey(key);
            newNode.setValue(value);
            newNode.setNext(table[bucket]);
            table[bucket] = newNode;
            count++;
        }
    }


    public String get(String key) {

        int bucket = hash(key);

        Node list = table[bucket];
        while (list != null) {

            if (list.getKey().equals(key))
                return list.getValue();
            list = list.getNext();
        }


        return null;
    }


    public void remove(String key) {

        int bucket = hash(key);
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


    public boolean containsKey(String key) {

        int bucket = hash(key);
        Node list = table[bucket];
        while (list != null) {
            if (list.getKey().equals(key))
                return true;
            list = list.getNext();
        }

        return false;
    }


    public int size() {
        return count;
    }


    private int hash(Object key) {
        return (Math.abs(key.hashCode())) % table.length;
    }


    private void resize() {
        Node[] newtable = new Node[table.length * 2];
        for (int i = 0; i < table.length; i++) {
            Node list = table[i];
            while (list != null) {
                Node next = list.getNext();
                int hash = (Math.abs(list.getKey().hashCode())) % newtable.length;

                list.setNext(newtable[hash]);
                newtable[hash] = list;
                list = next;
            }
        }
        table = newtable;
    }


}