package com.huson.btree.impl;

import com.huson.btree.BNode;
import com.huson.btree.locator.AddressLocator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenxiaochen on 2016/7/28.
 * 每个节点至多有m棵子树。
 * 除根节点外，其他每个分支节点至少有[m/2]棵子树
 * 有j个孩子的非叶节点有j-1个关键码，关键码按递增次序排列
 * 所有的叶节点都在同一层上
 * 所有的叶子结点中包含了全部关键字的信息
 * keyList[i] <= sonList[i]的全部
 */
public class PlusBNode implements BNode {
    private String address; //自己的地址
    private int keyNum; //关键字个数
    private String parentAddress;//父节点地址
    private List<Long> keyList;//关键字数组，长度为keyNum:key[0]...key[keyNum-1]
    private List<String> sonList;//子树地址数组长度为keyNum，son[0]...son[keyNum-1]
    private int m = 0; //m阶b+树
    private AddressLocator addressLocator;

    public void init(BNode parent){
        this.m = parent.getM();
        this.keyNum = 0;
        this.parentAddress = parent.getAddress();
        this.keyList = new ArrayList<Long>();
        this.sonList = new ArrayList<String>();
        this.addressLocator = parent.getAddressLocator();
    }

    public int getKeyNum() {
        return keyNum;
    }

    public void setKeyNum(int keyNum) {
        this.keyNum = keyNum;
    }

    public PlusBNode getParent() {
        return addressLocator.getBtnodeByAddress(parentAddress);
    }

    public void setParent(PlusBNode parent) {
        this.parentAddress = parent.getAddress();
    }

    public PlusBNode getSonByPos(int pos) {
        return addressLocator.getBtnodeByAddress(sonList.get(pos));
    }

    public void setSonByPos(PlusBNode son, int pos) {
        this.sonList.set(pos,son.getAddress());
    }

    public List<Long> getKeyList() {
        return keyList;
    }


    public  int getM() {
        return m;
    }

    public  void setM(int m) {
        this.m = m;
    }

    public PlusBNode findRightSiblings(){
        Long nowBigest = keyList.get(keyNum - 1);
        //找到父节点
        PlusBNode prtNode = this.getParent();
        //找到自己的位置
        int pos = -1;
        for(int i = 0 ; i <= prtNode.getKeyNum() - 1 ; i ++){
            if(keyList.get(i) > nowBigest){
                pos = i;
                break;
            }
        }
        if(pos == -1){
            //说明还没找到，说明是父节点最右的子儿子，需要找到父节点右边的元素的最左边孩子
            //找到父亲的父亲节点
            PlusBNode parentRight = prtNode.findRightSiblings();
            return parentRight.getSonByPos(0);
        }
        return prtNode.getSonByPos(0);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 更新保存到地址里面
     */
    public void save(){
        addressLocator.setBtnodeByAddress(this.address,this);
    }

    public void setKeyList(List<Long> keyList) {
        this.keyList = keyList;
    }

    public List<String> getSonList() {
        return sonList;
    }

    public void setSonList(List<String> sonList) {
        this.sonList = sonList;
    }

    public AddressLocator getAddressLocator() {
        return addressLocator;
    }

    public void setAddressLocator(AddressLocator addressLocator) {
        this.addressLocator = addressLocator;
    }
}
