package com.huson.btree;

import com.huson.btree.impl.PlusBNode;
import com.huson.btree.locator.AddressLocator;

import java.util.List;

/**
 * Created by chenbaobao on 2016/8/23.
 */
public interface BNode {
    public PlusBNode getSonByPos(int pos);
    public void setSonList(List<String> sonList);
    public List<String> getSonList();
    public void setKeyList(List<Long> keyList);
    public List<Long> getKeyList();
    public int getKeyNum();
    public PlusBNode findRightSiblings();
    public  int getM();
    public void setKeyNum(int keyNum);
    public void save();
    public BNode getParent();
    public void init(BNode parent);
    public String getAddress();
    public AddressLocator getAddressLocator();
}
