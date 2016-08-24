package com.huson.btree.impl;

import com.huson.btree.BNode;
import com.huson.btree.BTree;
import com.huson.btree.impl.PlusBNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenbaobao on 2016/8/23.
 */
public class PlusBTree implements BTree {
    private BNode bNodeRoot;

    public List<Long> findByPage(Long start, int pageSize) {
        List<Long> retLeafs = new ArrayList<Long>(pageSize);
        BNode node = findLeafByKey(bNodeRoot,start); //从根节点开始搜索
        List<Long> keys =  node.getKeyList();
        //开始在叶节点搜索
        while (true) {
            for (int i = 0; i <= keys.size() - 1; i++) {
                if (keys.get(i) >= start) {
                    //判断该叶节点剩余的个数是否大于或刚好等于需要返回的剩余个数
                    if ((keys.size() - i) >= (pageSize - retLeafs.size())) {
                        retLeafs.addAll(keys.subList(i, i + pageSize - retLeafs.size()));
                        break;
                    }
                } else {
                    //将该叶节点全部的剩余添加到返回数组
                    retLeafs.addAll(keys.subList(i, keys.size()));
                    int restNum = (pageSize - retLeafs.size() - (keys.size() - i));
                    //找到右边节点
                    node = node.findRightSiblings();
                    pageSize = restNum;
                }
            }
        }    }

    public void insert(Long value) {
        //找到叶子层
        BNode leafNode = findLeafByKey(bNodeRoot, value);
        insertOrSplit(leafNode,value);
    }

    private BNode findLeafByKey(BNode tempNode, Long key){
        while (true){
            List<String> sonList = tempNode.getSonList();
            if(sonList == null ){
                //已经搜索到了叶节点
                return tempNode;
            }else{
                //线性比较key值和关键码的大小
                int pos = -1;
                for(int i = 0; i <= tempNode.getKeyNum() -1 ; i++){
                    if(tempNode.getKeyList().get(i) > key){
                        pos = i;
                        break;
                    }
                }
                if(pos == -1){
                    //说明还没找到
                    pos = tempNode.getKeyNum();
                }
                tempNode = tempNode.getSonByPos(pos);
            }
        }
    }

    /**
     * 递归判断是否需要分裂
     * 在tempNode中尝试插入value
     * 返回tempNode的父节点
     */
    private BNode insertOrSplit(BNode tempNode, Long value){
        if(tempNode.getKeyNum() <  tempNode.getM() - 1){//该节点中关键码个数小于m-1，则直接插入即可，不用新增节点
            //找到插入位置
            int pos = -1;
            for(int i = 0 ; i <= tempNode.getKeyNum() - 1 ; i++){
                if(tempNode.getKeyList().get(i) > value){
                    pos = i;
                }
            }
            if(pos == -1){
                pos = tempNode.getKeyNum();
            }
            tempNode.getKeyList().add(pos,value);
            tempNode.setKeyNum(tempNode.getKeyNum()+1);
            tempNode.save();
            return tempNode;
        }
        else{ //若该节点中关键码个数等于m-1，则将引起节点的分裂。
            //以中间关键码为界将节点一分为二
            int millde = tempNode.getKeyNum() / 2;
            //将中间关键码插入到父节点中
            BNode parent = insertOrSplit(tempNode.getParent(),tempNode.getKeyList().get(millde));
            //分裂到右边新增一个兄弟节点,父亲为中间节点
            PlusBNode BPlusNode_split = new PlusBNode();
            BPlusNode_split.init(parent);
            BPlusNode_split.setKeyList(tempNode.getKeyList().subList(millde+1,tempNode.getKeyList().size()));
            BPlusNode_split.setSonList(tempNode.getSonList().subList(millde+1,tempNode.getSonList().size()));
            BPlusNode_split.save();

            //原来的节点分裂之后只有前半段关键码了
            tempNode.setKeyList(tempNode.getKeyList().subList(0,millde));
            tempNode.setSonList(tempNode.getSonList().subList(0,millde));
            tempNode.save();

            return BPlusNode_split;
        }
    }
}
