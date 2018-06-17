package com.example.sfwd.shunfengwaidai.manager;

/**
 * Created by 士昂 on 2018/6/17.
 */

import com.example.sfwd.shunfengwaidai.model.Address;
import com.example.sfwd.shunfengwaidai.model.User;

public class AddressManager {
    private Address address;
    private static AddressManager instance;

    public AddressManager() {

    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    public static AddressManager getInstance() {
        if (instance == null) {
            instance = new AddressManager();
        }
        return instance;
    }
}
