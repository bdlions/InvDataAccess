/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.db.repositories;

import com.inventory.bean.ProductInfo;
import com.inventory.db.query.QueryField;
import com.inventory.db.query.QueryManager;
import com.inventory.db.query.helper.EasyStatement;
import com.inventory.exceptions.DBSetupException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nazmul hasan
 */
public class Product {
    private Connection connection;
    /***
     * Restrict to call without connection
     */
    private Product(){}
    public Product(Connection connection) {
        this.connection = connection;
    }
    
    public void createProduct(ProductInfo productInfo)throws DBSetupException, SQLException
    {
        
        try (EasyStatement stmt = new EasyStatement(connection, QueryManager.CREATE_PRODUCT)) {
            stmt.setString(QueryField.NAME, productInfo.getName());
            stmt.setString(QueryField.CODE, productInfo.getCode());
            stmt.setString(QueryField.LENGTH, productInfo.getLength());
            stmt.setString(QueryField.WIDTH, productInfo.getWidth());
            stmt.setString(QueryField.HEIGHT, productInfo.getHeight());
            stmt.setString(QueryField.WEIGHT, productInfo.getWeight());
            stmt.executeUpdate();
        }
    }
    
    public List<ProductInfo> getAllProducts()throws DBSetupException, SQLException
    {
        List<ProductInfo> productList = new ArrayList<>();
        try (EasyStatement stmt = new EasyStatement(connection, QueryManager.GET_ALL_PRODUCTS)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                ProductInfo productInfo = new ProductInfo();
                productInfo.setId(rs.getInt(QueryField.ID));
                productInfo.setName(rs.getString(QueryField.NAME));
                productInfo.setCode(rs.getString(QueryField.CODE));
                productInfo.setLength(rs.getString(QueryField.LENGTH));
                productInfo.setWidth(rs.getString(QueryField.WIDTH));
                productInfo.setHeight(rs.getString(QueryField.HEIGHT));
                productInfo.setWeight(rs.getString(QueryField.WEIGHT));
                productList.add(productInfo);
            }
        }
        return productList;
    }
}
