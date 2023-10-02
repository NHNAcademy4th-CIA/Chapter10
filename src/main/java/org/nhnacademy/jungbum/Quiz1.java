package org.nhnacademy.jungbum;


import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * 전화번호부 treemap으로 구현
 */
public class Quiz1 {

    private static final Logger logger = LoggerFactory.getLogger(Quiz1.class);

    public static void main(String[] args) {
        PhoneDirectory phoneBook = new PhoneDirectory();
        logger.info("전화번호부에 추가할 사람을 이름 전화번호 순으로 입력해주세요.(만약 끝내고싶다면 빈칸을 입력해주세요)");
        try {
            phoneBook.addNumber();
        } catch (IllegalArgumentException |NoSuchElementException e) {
            logger.warn(e.toString());
        }
        logger.info("전화번호부에 등록된 모든사람을 조회합니다.");
        phoneBook.print();
    }

}

/***
 * 전화번호부
 */
class PhoneDirectory {
    private static final Logger logger = LoggerFactory.getLogger(Quiz1.class);

    private static Scanner scanner = new Scanner(System.in);

    private TreeMap<String, String> PhoneEntry;

    public PhoneDirectory() {
        PhoneEntry = new TreeMap<String, String>();
    }

//    public String getNumber( String name ) {
//        return PhoneEntry.get(name);
//    }


    /***
     * 전화번호 추가
     * @throws IllegalArgumentException 이름이나 번호가 없는경우
     */
    public void addNumber() throws IllegalArgumentException {
        String value;
        StringTokenizer stringTokenizer;
        while (!(value = scanner.nextLine()).equals("")) {
            stringTokenizer = new StringTokenizer(value);
            String name = stringTokenizer.nextToken();
            String number = stringTokenizer.nextToken();
            if (name == null)
                throw new IllegalArgumentException("이름이 비어 등록이 불가능합니다.");
            if (number == null)
                throw new IllegalArgumentException("번호가 비어 등록이 불가능합니다.");
            PhoneEntry.put(name, number);

        }
    }


    /***
     * 모든사람 출력
     */
    public void print() {
        for (Map.Entry<String, String> entry : PhoneEntry.entrySet())
            logger.info(entry.getKey() + ":  " + entry.getValue());
    }

}