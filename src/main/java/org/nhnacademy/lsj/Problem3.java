package org.nhnacademy.lsj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Problem3 {


    private static final Logger logger = LoggerFactory.getLogger(Problem3.class);

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

    public static node get(node[] table, String key) {
        return table[key.hashCode() % 1024];
    }

    public static void remove(node[] table, String key) {

        int index = key.hashCode() % 1024;

        table[index] = null;
    }

    public static boolean containKey(node[] table, String key) {

        int index = key.hashCode() % 1024;

        return table[index] != null;

    }

    public static void print(node root) {
        StringBuilder sb = new StringBuilder();
        while (root != null) {
            sb.append(root.getValue()).append(" ");
            root = root.getNext();
        }
        logger.info("{}", sb);
    }

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
