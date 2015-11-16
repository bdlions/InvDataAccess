/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.db;

import com.inventory.bean.ProductInfo;
import com.inventory.db.query.helper.EasyStatement;
import com.inventory.db.repositories.Product;
import com.inventory.exceptions.DBSetupException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author nazmul hasan
 */
public class ProductManager {
    private Product product;
    private final Logger logger = LoggerFactory.getLogger(EasyStatement.class);
    public void createProduct(ProductInfo productInfo)
    {
        Connection connection = null;
        try {
            connection = Database.getInstance().getConnection();
            
            product = new Product(connection);
            product.createProduct(productInfo);
            

            connection.close();
        } catch (SQLException ex) {
            try {
                if(connection != null){
                    connection.close();
                }
            } catch (SQLException ex1) {
                logger.error(ex1.getMessage());
            }
        } catch (DBSetupException ex) {
            logger.error(ex.getMessage());
        }
    }
    
    public List<ProductInfo> getAllProducts()
    {
        List<ProductInfo> productList = new ArrayList<>(); 
        Connection connection = null;
        try {
            connection = Database.getInstance().getConnection();
            
            product = new Product(connection);
            productList = product.getAllProducts();

            connection.close();
        } catch (SQLException ex) {
            try {
                if(connection != null){
                    connection.close();
                }
            } catch (SQLException ex1) {
                logger.error(ex1.getMessage());
            }
        } catch (DBSetupException ex) {
            logger.error(ex.getMessage());
        }
        return productList;
    }
}
