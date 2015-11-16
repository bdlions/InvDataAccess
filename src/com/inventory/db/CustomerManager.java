/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.db;

import com.inventory.bean.CustomerInfo;
import com.inventory.db.query.helper.EasyStatement;
import com.inventory.db.repositories.Customer;
import com.inventory.db.repositories.Supplier;
import com.inventory.db.repositories.User;
import com.inventory.exceptions.DBSetupException;
import java.sql.Connection;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author nazmul hasan
 */
public class CustomerManager {
    private User user;
    private Customer customer;
    private final Logger logger = LoggerFactory.getLogger(EasyStatement.class);
    public void createCustomer(CustomerInfo customerInfo)
    {
        //create a new user
        Connection connection = null;
        try {
            connection = Database.getInstance().getConnection();
            connection.setAutoCommit(false);
            
            //right now group id constant. Later update it from configuraiton file
            customerInfo.getUserInfo().setGroupId(2);
            
            user = new User(connection);
            int userId = user.createUser(customerInfo.getUserInfo()); 
            
            customerInfo.getUserInfo().setId(userId);
            customer = new Customer(connection);
            customer.createCustomer(customerInfo);

            connection.commit();
            connection.close();
        } catch (SQLException ex) {
            try {
                if(connection != null){
                    connection.rollback();
                    connection.close();
                }
            } catch (SQLException ex1) {
                logger.error(ex1.getMessage());
            }
        } catch (DBSetupException ex) {
            logger.error(ex.getMessage());
        }
    }
}
