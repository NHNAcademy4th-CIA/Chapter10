package org.nhnacademy.jungbum;


import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Quiz1 {

    private static final Logger logger = LoggerFactory.getLogger(Quiz1.class);

    public static void main(String[] args) {
        PhoneDirectory phoneBook = new PhoneDirectory();
        logger.info("전화번호부에 추가할 사람을 이름 전화번호 순으로 입력해주세요.(만약 끝내고싶다면 빈칸을 입력해주세요)");
        try {
            phoneBook.addNumber();
        }catch (IllegalArgumentException e)
        {
            logger.warn(e.toString());
        }
        logger.info("전화번호부에 등록된 모든사람을 조회합니다.");
        phoneBook.print();
    }

}
class PhoneDirectory {
    private static Scanner scanner = new Scanner(System.in);

    private TreeMap<String,String> PhoneEntry;
    public PhoneDirectory() {
        PhoneEntry = new TreeMap<String,String>();
    }

    public String getNumber( String name ) {
        return PhoneEntry.get(name);
    }

    public void addNumber() {
        String value;
        StringTokenizer stringTokenizer;
        while (!(value = scanner.nextLine()).equals(""))
        {
            stringTokenizer = new StringTokenizer(value);
            String name = stringTokenizer.nextToken();
            String number = stringTokenizer.nextToken();
            if (name == null)
                throw new IllegalArgumentException("이름이 비어 등록이 불가능합니다.");
            if (number == null)
                throw new IllegalArgumentException("번호가 비어 등록이 불가능합니다.");
            PhoneEntry.put(name,number);

        }
    }


    public void print() {
        for ( Map.Entry<String,String> entry : PhoneEntry.entrySet() )
            System.out.println( entry.getKey() + ":  " + entry.getValue() );
    }

}