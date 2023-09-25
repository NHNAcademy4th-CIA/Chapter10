package org.nhnacademy.lsj;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.function.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * T 타입의 객체를 만들고 Predicate를 이용해 테스트하는 프로그램.
 */
public class Problem4 {

    private static final Logger logger = LoggerFactory.getLogger(Problem4.class);

    /**
     * list에 add한 후  -> predicate를 람다표현식으로 나타내 , 결과를 출력.
     */
    public static void problem4() {

        List<Integer> list = new ArrayList<>();


        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);


        Predicates.removeWithCondition(list, integer -> integer % 2 == 0, true);


        logger.info("{}", Predicates.print(list));


        Stack<String> stk = new Stack<>();

        stk.push("3이아닌");
        stk.push("bbb");
        stk.push("ccc");
        stk.push("애들은다");
        stk.push("사랍니다.");
        stk.push("eee");

        Predicates.removeWithCondition(stk, s -> s.length() == 3, false);


        logger.info("{}", Predicates.print(stk));


        logger.info("{}", Predicates.find(list, x -> x == 7));


    }


}

class Predicates {


    public static <T> StringBuilder print(Collection<T> coll) {

        StringBuilder sb = new StringBuilder();

        Iterator iterator = coll.iterator();

        while (iterator.hasNext()) {
            sb.append(iterator.next()).append(" ");
        }

        return sb;

    }

    public static <T> void removeWithCondition(Collection<T> coll, Predicate<T> pred, boolean flag) {

        Iterator<T> iterator = coll.iterator();

        while (iterator.hasNext()) {

            var value = iterator.next();

            if (pred.test(value) == flag) {
                iterator.remove();
            }
        }

    }


    public static <T> int find(Collection<T> coll, Predicate<T> pred) {

        Iterator<T> iterator = coll.iterator();

        int count = 0;

        while (iterator.hasNext()) {

            var value = iterator.next();

            if (pred.test(value)) {
                return count;
            }
            count++;
        }
        return -1;
    }

}

