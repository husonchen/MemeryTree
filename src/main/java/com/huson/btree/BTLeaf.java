package com.huson.btree;

/**
 * Created by chenxiaochen on 2016/7/28.
 * B树叶节点，保存具体的信息
 */
public class BTLeaf {
    private Long key;
    private String value;

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
