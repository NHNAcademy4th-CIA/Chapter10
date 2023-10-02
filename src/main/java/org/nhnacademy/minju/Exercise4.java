package org.nhnacademy.minju;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * .predicate object를 제네릭 메서드로 테스트
 */
public class Exercise4 {
    private static final Logger logger = LoggerFactory.getLogger(Exercise4.class);
    private static Random random;

    /**
     * .Predicates 테스트
     */
    public static void exercise4() {
        Collection<Integer> coll = makeColl();
        Predicate<Integer> pred = num -> num % 2 == 0;

        Predicates.remove(coll, pred);
        logger.info("remove : {}", coll);

        coll = makeColl();
        Predicates.retain(coll, pred);
        logger.info("retain : {}", coll);

        coll = makeColl();
        logger.info("collect : {}", Predicates.collect(coll, pred));

        coll = makeColl();
        logger.info("find(return index) : {}", Predicates.find(new ArrayList<>(coll), pred));
    }

    private static Collection<Integer> makeColl() {
        Collection<Integer> coll = new ArrayList<>();
        random = new Random();
        for (int i = 0; i < 10; i++) {
            coll.add(random.nextInt(100));
        }
        logger.info("coll : {}", coll);
        return coll;
    }
}

class Predicates {
    //    Remove every object, obj, from coll for which pred.test(obj)
    //    is true.  (This does the same thing as coll.removeIf(pred).)
    public static <T> void remove(Collection<T> coll, Predicate<T> pred) {
        Iterator<T> iterator = coll.iterator();
        while (iterator.hasNext()) {
            T item = iterator.next();
            if (pred.test(item)) {
                iterator.remove();
            }
        }
    }

    // Remove every object, obj, from coll for which
    // pred.test(obj) is false.  (That is, retain the
    // objects for which the predicate is true.)
    public static <T> void retain(Collection<T> coll, Predicate<T> pred) {
        Iterator<T> iterator = coll.iterator();
        while (iterator.hasNext()) {
            T item = iterator.next();
            if (!pred.test(item)) {
                iterator.remove();
            }
        }
    }

    // Return a List that contains all the objects, obj,
    // from the collection, coll, such that pred.test(obj)
    // is true.
    public static <T> List<T> collect(Collection<T> coll, Predicate<T> pred) {
        List<T> list = new ArrayList<>();
        for (T t : coll) {
            if (pred.test(t)) {
                list.add(t);
            }
        }
        return list;
    }

    // Return the index of the first item in list
    // for which the predicate is true, if any.
    // If there is no such item, return -1.
    public static <T> int find(ArrayList<T> list, Predicate<T> pred) {
        for (int i = 0; i < list.size(); i++) {
            T t = list.get(i);
            if (pred.test(t)) {
                return i;
            }
        }
        return -1;
    }

}