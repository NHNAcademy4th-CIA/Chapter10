package org.nhnacademy.jungbum;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * predicate 이해하기
 */
public class Quiz4 {
    Logger logger = LoggerFactory.getLogger(Quiz4.class);

    public Quiz4() {
        Collection<Integer> coll;

        List<Integer> result;

        Predicate<Integer> pred = i -> (i % 2 == 0);

        coll = makeSet();
        logger.info("Set: {}", coll);

        Predicates.remove(coll, pred);
        logger.info("Remove: {}", coll);

        coll = makeSet();
        Predicates.retain(coll, pred);
        logger.info("Retain: {}", coll);


        coll = makeSet();
        result = Predicates.collect(coll, pred);
        logger.info("Collect: {}", result);


        ArrayList<Integer> list = new ArrayList<>();
        int i = Predicates.find(list, pred);
        logger.info("find: {}", i);


        pred = n -> (n > 100);
        logger.info("predicate 변경");
        coll = makeSet();
        logger.info("Set: {}", coll);

        Predicates.remove(coll, pred);
        logger.info("Remove: {}", coll);

        coll = makeSet();
        Predicates.retain(coll, pred);
        logger.info("Retain: {}", coll);

        coll = makeSet();
        result = Predicates.collect(coll, pred);
        logger.info("Collect: {}", result);

        list = new ArrayList<Integer>(coll);
        i = Predicates.find(list, pred);
        logger.info("find: {}", i);

    }

    /***
     * 콜렉션 생성하기
     * @return 생성된 콜렉션
     */
    private Collection<Integer> makeSet() {
        Collection<Integer> set = new TreeSet<>();
        set.add(Integer.valueOf(32));
        set.add(Integer.valueOf(17));
        set.add(Integer.valueOf(142));
        set.add(Integer.valueOf(56));
        set.add(Integer.valueOf(100));
        return set;
    }

}

/***
 * predicate 함수형 인터페이스
 * @param <T> 타입
 */
interface Predicate<T> {
    public boolean test(T obj);
}

/***
 * predicate 적용
 */
class Predicates {

    /****
     * 삭제
     * @param coll 콜렉션
     * @param pred predicate
     * @param <T> 타입
     */
    public static <T> void remove(Collection<T> coll, Predicate<T> pred) {
        Iterator<T> iter = coll.iterator();
        while (iter.hasNext()) {
            T item = iter.next();
            if (pred.test(item))
                iter.remove();
        }
    }

    /****
     * 삭제한거
     * @param coll 콜렉션
     * @param pred predicate
     * @param <T> 타입
     */
    public static <T> void retain(Collection<T> coll, Predicate<T> pred) {
        Iterator<T> iter = coll.iterator();
        while (iter.hasNext()) {
            T item = iter.next();
            if (!pred.test(item))
                iter.remove();
        }
    }

    /***
     * 복제
     * @param coll 콜렉션
     * @param pred predicate
     * @return 복제된 콜렉션
     * @param <T> 타입
     */
    public static <T> List<T> collect(Collection<T> coll, Predicate<T> pred) {
        List<T> list = new ArrayList<T>();
        for (T item : coll) {
            if (pred.test(item))
                list.add(item);
        }
        return list;
    }

    /***
     * 찾기
     * @param list 리스트
     * @param pred predicate
     * @return 찾은값
     * @param <T> 타입
     */
    public static <T> int find(ArrayList<T> list, Predicate<T> pred) {
        //
        for (int i = 0; i < list.size(); i++) {
            T item = list.get(i);
            if (pred.test(item))
                return i;
        }
        return -1;
    }
}
