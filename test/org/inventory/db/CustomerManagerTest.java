/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inventory.db;

import com.inventory.bean.AddressCategoryInfo;
import com.inventory.bean.AddressInfo;
import com.inventory.bean.AddressTypeInfo;
import com.inventory.bean.CustomerInfo;
import com.inventory.bean.ProfileInfo;
import com.inventory.db.manager.CustomerManager;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author sampanit
 */
public class CustomerManagerTest {

    /**
     *
     */
    final static CustomerManager customerManager = new CustomerManager();

    public CustomerManagerTest() {

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test

    public void createCustomerTest() {

        //  CustomerManager customerManager = new CustomerManager();
        ProfileInfo userInfo = new ProfileInfo();
        userInfo.setFirstName("customerFn1");
        userInfo.setLastName("customerln1");
        userInfo.setEmail("customeremail1");
        userInfo.setPhone("customerphone1");
        userInfo.setFax("customerfax1");
        userInfo.setWebsite("customerwebsite1");
        AddressInfo addressInfo = new AddressInfo();
        addressInfo.setAddress("customerniketon");
        addressInfo.setCity("customerdhaka");
        addressInfo.setState("customerdhaka");
        addressInfo.setZip("1507");
        AddressTypeInfo addressTypeInfo = new AddressTypeInfo();
        addressTypeInfo.setId(1);
        addressTypeInfo.setTitle("Commercial");
        AddressCategoryInfo addressCategoryInfo = new AddressCategoryInfo();
        addressCategoryInfo.setId(1);
        addressCategoryInfo.setTitle("Business");
        
        addressInfo.setAddressTypeInfo(addressTypeInfo);
        addressInfo.setAddressCategoryInfo(addressCategoryInfo);
        List<AddressInfo> addresses = new ArrayList<>();
        addresses.add(addressInfo);
        userInfo.setAddresses(addresses);
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setProfileInfo(userInfo);
        customerManager.createCustomer(customerInfo);
    }
 // @Test
    public void getAllCustomersTest() {
      List<CustomerInfo> customerInfoList =  customerManager.getAllCustomers();
      System.out.println(customerInfoList);
    }

}
