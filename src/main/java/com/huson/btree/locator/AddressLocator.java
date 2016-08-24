package com.huson.btree.locator;

import com.huson.btree.impl.PlusBNode;

/**
 * Created by huson.chen on 2016/8/22.
 */
public interface AddressLocator {

    public PlusBNode getBtnodeByAddress(String address);

    public String setBtnodeByAddress(String address,PlusBNode BPlusNode);
}
