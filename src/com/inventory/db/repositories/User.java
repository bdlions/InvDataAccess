/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.db.repositories;

import com.inventory.bean.AddressInfo;
import com.inventory.bean.UserInfo;
import com.inventory.db.query.QueryField;
import com.inventory.db.query.QueryManager;
import com.inventory.db.query.helper.EasyStatement;
import com.inventory.exceptions.DBSetupException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

/**
 *
 * @author nazmul hasan
 */
public class User {
    private Connection connection;
    /***
     * Restrict to call without connection
     */
    private User(){}
    public User(Connection connection) {
        this.connection = connection;
    }
    
    public int createUser(UserInfo userInfo) throws DBSetupException, SQLException
    {
        //right now random int is used. later get last inserted id
        Random random = new Random();
        int userId = random.nextInt(10000000) + 1;
        userInfo.setId(userId);
        
        try (EasyStatement stmt = new EasyStatement(connection, QueryManager.CREATE_USER)) {
            stmt.setInt(QueryField.ID, userId);
            stmt.setString(QueryField.FIRST_NAME, userInfo.getFirstName());
            stmt.setString(QueryField.LAST_NAME, userInfo.getLastName());
            stmt.setString(QueryField.EMAIL, userInfo.getEmail());
            stmt.setString(QueryField.PHONE, userInfo.getPhone());
            stmt.setString(QueryField.FAX, userInfo.getFax());
            stmt.setString(QueryField.WEBSITE, userInfo.getWebsite());
            stmt.executeUpdate();
        }
        this.addUserToGroup(userInfo);
        return userId;
    }
    
    public void addUserToGroup(UserInfo userInfo) throws DBSetupException, SQLException
    {
        try (EasyStatement stmt = new EasyStatement(connection, QueryManager.ADD_USER_TO_GROUP)) {
            stmt.setInt(QueryField.USER_ID, userInfo.getId());
            stmt.setInt(QueryField.GROUP_ID, userInfo.getGroupId());
            stmt.executeUpdate();
        }
        this.addUserAddresses(userInfo);
    }
    
    public void addUserAddresses(UserInfo userInfo) throws DBSetupException, SQLException
    {
        //right now we are using loop. later use insert batch
        List<AddressInfo> addresses = userInfo.getAddresses();
        for (AddressInfo address : addresses) {
            try (EasyStatement stmt = new EasyStatement(connection, QueryManager.ADD_USER_ADDRESS)) {
                stmt.setInt(QueryField.USER_ID, userInfo.getId());
                stmt.setInt(QueryField.ADDRESS_TYPE_ID, address.getAddressTypeId());
                stmt.setString(QueryField.ADDRESS, address.getAddress());
                stmt.setString(QueryField.CITY, address.getCity());
                stmt.setString(QueryField.STATE, address.getState());
                stmt.setString(QueryField.ZIP, address.getZip());
                stmt.executeUpdate();
            }
        }
    }
}
