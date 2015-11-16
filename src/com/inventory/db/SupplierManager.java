/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.db;

import com.inventory.bean.SupplierInfo;
import com.inventory.db.query.helper.EasyStatement;
import com.inventory.db.repositories.Product;
import com.inventory.db.repositories.User;
import com.inventory.db.repositories.Supplier;
import com.inventory.exceptions.DBSetupException;
import java.sql.Connection;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author nazmul hasan
 */
public class SupplierManager {
    private User user;
    private Supplier supplier;
    private final Logger logger = LoggerFactory.getLogger(EasyStatement.class);
    /**
     * This method will create a new supplier
     * @param supplierInfo, 
     */
    public void createSupplier(SupplierInfo supplierInfo)
    {
        //create a new user
        Connection connection = null;
        try {
            connection = Database.getInstance().getConnection();
            connection.setAutoCommit(false);
            
            user = new User(connection);
            int userId = user.createUser(supplierInfo.getUserInfo()); 
            
            supplierInfo.getUserInfo().setId(userId);
            supplier = new Supplier(connection);
            supplier.createSupplier(supplierInfo);

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
        //add user under a group
        //add address
        //add supplier info
    }
    
    /**
     * This method will return all suppliers
     * 
     */
    public void getAllSuppliers()
    {
    
    }
    
    public void searchSupplier()
    {
    
    }
}
