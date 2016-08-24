package com.huson.btree;

import com.huson.btree.locator.AddressLocator;

/**
 * Created by chenbaobao on 2016/8/24.
 */
public interface MemeryTreeClientBuilder {
    AddressLocator getAddressLocator();
    void setAddressLocator(AddressLocator var1);
    BTree build();
}
