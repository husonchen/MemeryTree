package com.huson.btree;

import com.huson.btree.impl.MemcachedAddressLocator;
import com.huson.btree.impl.PlusBTree;
import com.huson.btree.locator.AddressLocator;

/**
 * Created by chenbaobao on 2016/8/24.
 */
public class HusonMemeryTreeClientBuilder implements MemeryTreeClientBuilder {
    private AddressLocator addressLocator;
    private BTree bTree;

    public AddressLocator getAddressLocator() {
        return null;
    }

    public void setAddressLocator(AddressLocator var1) {

    }

    public BTree build() {
        if(addressLocator == null)
            this.addressLocator = new MemcachedAddressLocator();
        if(bTree == null){
            bTree = new PlusBTree();
        }

        return null;
    }
}
