package com.huson.btree;

import com.huson.btree.impl.PlusBNode;
import com.huson.btree.locator.AddressLocator;

import java.util.List;

/**
 * Created by chenbaobao on 2016/8/23.
 */
public interface BNode {
    PlusBNode getSonByPos(int pos);
    void setSonList(List<String> sonList);
    List<String> getSonList();
    void setKeyList(List<Long> keyList);
    List<Long> getKeyList();
    int getKeyNum();
    PlusBNode findRightSiblings();
    int getM();
    void setKeyNum(int keyNum);
    void save();
    BNode getParent();
    void init(BNode parent);
    String getAddress();
    AddressLocator getAddressLocator();
}
