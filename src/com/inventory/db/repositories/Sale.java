/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.db.repositories;

import com.inventory.bean.ProductInfo;
import com.inventory.bean.SaleInfo;
import com.inventory.db.query.QueryField;
import com.inventory.db.query.QueryManager;
import com.inventory.db.query.helper.EasyStatement;
import com.inventory.exceptions.DBSetupException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author nazmul hasan
 */
public class Sale {
    private Connection connection;
    /***
     * Restrict to call without connection
     */
    private Sale(){}
    public Sale(Connection connection) {
        this.connection = connection;
    }
    
    public void addSaleOrder(SaleInfo saleInfo) throws DBSetupException, SQLException
    {
        try (EasyStatement stmt = new EasyStatement(connection, QueryManager.ADD_SALE_ORDER)) {
            stmt.setString(QueryField.ORDER_NO, saleInfo.getOrderNo());            
            stmt.setInt(QueryField.CUSTOMER_USER_ID, saleInfo.getCustomerUserId());            
            stmt.setInt(QueryField.STATUS_ID, saleInfo.getStatusId());            
            stmt.setInt(QueryField.SALE_DATE, saleInfo.getSaleDate());            
            stmt.setLong(QueryField.DISCOUNT, saleInfo.getDiscount());
            stmt.setString(QueryField.REMARKS, saleInfo.getRemarks());
            stmt.executeUpdate();
        }
        this.addSaleOrderProductList(saleInfo);
        this.addShowroomStock(saleInfo);
    }
    
    public void addSaleOrderProductList(SaleInfo saleInfo) throws DBSetupException, SQLException
    {
        //right now we are using loop. later use insert batch
        List<ProductInfo> productList = saleInfo.getProductList();
        for(ProductInfo productInfo:productList)
        {
            try (EasyStatement stmt = new EasyStatement(connection, QueryManager.ADD_SALE_ORDER_PRODUCT_LIST)) {
                stmt.setInt(QueryField.PRODUCT_ID, productInfo.getId());
                stmt.setString(QueryField.SALE_ORDER_NO, saleInfo.getOrderNo());
                stmt.setString(QueryField.PURCHASE_ORDER_NO, productInfo.getPurchaseOrderNo());
                stmt.setLong(QueryField.UNIT_PRICE, productInfo.getUnitPrice());
                stmt.setLong(QueryField.DISCOUNT, productInfo.getDiscount());            
                stmt.executeUpdate();
            }
        }
    }
    
    public void addShowroomStock(SaleInfo saleInfo) throws DBSetupException, SQLException
    {
    //right now we are using loop. later use insert batch
        List<ProductInfo> productList = saleInfo.getProductList();
        for(ProductInfo productInfo:productList)
        {
            try (EasyStatement stmt = new EasyStatement(connection, QueryManager.ADD_SHOWROOM_STOCK)) {
                stmt.setString(QueryField.PURCHASE_ORDER_NO, productInfo.getPurchaseOrderNo());
                stmt.setString(QueryField.SALE_ORDER_NO, saleInfo.getOrderNo());
                stmt.setInt(QueryField.PRODUCT_ID, productInfo.getId());
                stmt.setLong(QueryField.STOCK_OUT, productInfo.getQuantity());
                stmt.setLong(QueryField.STOCK_IN, 0);
                //right now transaction category id constant. later update it from config file
                stmt.setInt(QueryField.TRANSACTION_CATEGORY_ID, 5);           
                stmt.executeUpdate();
            }
        }
    }
}