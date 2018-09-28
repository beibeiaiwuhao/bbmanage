package com.beibei.bbmanage.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorUtils {

    public static <T> List<T> copyIterator(Iterator<T> iter) {
        List<T> copy = new ArrayList<T>();
        while (iter.hasNext())
            copy.add(iter.next());
        return copy;
    }

}
