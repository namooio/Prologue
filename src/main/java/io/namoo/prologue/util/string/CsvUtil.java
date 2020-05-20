/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.
 * @since 2014. 6. 10.
 */

package io.namoo.prologue.util.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvUtil {
    //
    private static final char CSV_SPERATOR = ',';

    public static String toCsv(List<String> list) {
        //
        if (list == null || list.size() <= 0) return null;
        return StringUtil.join(list, CSV_SPERATOR);
    }

    public static List<String> toList(String commaSperatedValues) {
        //
        List<String> results = new ArrayList<>();
        if (commaSperatedValues != null && !commaSperatedValues.isEmpty()) {
            results.addAll(Arrays.asList(StringUtil.split(commaSperatedValues, CSV_SPERATOR)));
        }
        return results;
    }
}
