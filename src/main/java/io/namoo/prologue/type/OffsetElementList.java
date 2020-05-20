/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.
 * @since 2014. 6. 10.
 */

package io.namoo.prologue.type;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * OffsetElementList
 * <pre>
 *   Service 및 Store I/F에서 페이징처리가 필요한 다건조회 시 사용합니다.
 *   오퍼레이션 매개변수를 다음과 같이 설정하고, 반환형은 OffsetList를 사용합니다.
 *
 *   예시 #1. 특정위치에서 최대갯수만큼 조회하기
 *   List&lt;AisUser&gt; findUsers(int offset, int limit);
 *
 *   예시 #2. 조회조건이 있는 경우, offset와 limit 매개변수는 항상 맨뒤에 위치시킵니다.
 *   List&lt;AisUser&gt; findUsersByName(String name, int offset, int limit);
 * </pre>
 *
 * @param <T> obj
 * @author <a href="mailto:hyunohkim@nextree.co.kr">Kim, Hyunoh</a>
 * @since 2015. 7. 29.
 */
public class OffsetElementList<T> implements Iterable<T>, Serializable {
    //
    private List<T> results;
    private int totalCount;

    protected OffsetElementList() {
        //
        this.results = new ArrayList<>();
    }

    public OffsetElementList(int totalCount) {
        //
        this();
        this.totalCount = totalCount;
    }

    public OffsetElementList(List<T> results, int totalCount) {
        //
        this.results = results;
        this.totalCount = totalCount;
    }

    @Override
    public Iterator<T> iterator() {
        //
        return results.iterator();
    }

    public int size() {
        //
        return (results != null) ? results.size() : 0;
    }

    public T get(int index) {
        //
        return (results != null) ? results.get(index) : null;
    }

    public void add(T result) {
        //
        results.add(result);
    }

    public boolean isEmpty() {
        //
        return (results == null || results.isEmpty());
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
