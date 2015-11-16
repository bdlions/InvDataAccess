/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.db.repositories;

import com.inventory.bean.CustomerInfo;
import com.inventory.db.query.QueryField;
import com.inventory.db.query.QueryManager;
import com.inventory.db.query.helper.EasyStatement;
import com.inventory.exceptions.DBSetupException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author nazmul hasan
 */
public class Customer {
    private Connection connection;
    /***
     * Restrict to call without connection
     */
    private Customer(){}
    public Customer(Connection connection) {
        this.connection = connection;
    }
    public void createCustomer(CustomerInfo customerInfo) throws DBSetupException, SQLException
    {
        try (EasyStatement stmt = new EasyStatement(connection, QueryManager.CREATE_CUSTOMER)) {
            stmt.setInt(QueryField.USER_ID, customerInfo.getUserInfo().getId());
            stmt.executeUpdate();
        }
    }
}
