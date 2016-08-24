package com.huson.btree;

import java.util.List;

/**
 * Created by chenbaobao on 2016/8/23.
 */
public interface BTree {
    public List<Long> findByPage(Long start, int pageSize);
    public void insert(Long value);
}
