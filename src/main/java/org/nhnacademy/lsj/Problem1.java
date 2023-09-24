package org.nhnacademy.lsj;

import java.util.Map;
import java.util.TreeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Problem1 {

    public static void problem1() {

        PhoneEntry phoneEntry = new PhoneEntry();

        phoneEntry.add("a", "1");
        phoneEntry.add("c", "2");
        phoneEntry.add("b", "3");

        phoneEntry.print();

    }


}


class PhoneEntry {

    Logger logger = LoggerFactory.getLogger(PhoneEntry.class);
    private Map<String, String> m;

    PhoneEntry() {
        m = new TreeMap<>();
    }

    public void add(String name, String phoneNum) {
        m.put(name, phoneNum);
    }

    public void print() {

        for (var v : m.entrySet()) {
            logger.info("이름 : {} , 번호 : {} ",v.getKey(),v.getValue());
        }

    }

}


