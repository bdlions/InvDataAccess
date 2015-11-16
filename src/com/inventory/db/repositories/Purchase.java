/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.db.repositories;

import com.inventory.bean.ProductInfo;
import com.inventory.bean.PurchaseInfo;
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
public class Purchase {
    private Connection connection;
    /***
     * Restrict to call without connection
     */
    private Purchase(){}
    public Purchase(Connection connection) {
        this.connection = connection;
    }
    public void addPurchaseOrder(PurchaseInfo purchaseInfo) throws DBSetupException, SQLException
    {
        try (EasyStatement stmt = new EasyStatement(connection, QueryManager.ADD_PURCHASE_ORDER)) {
            stmt.setString(QueryField.ORDER_NO, purchaseInfo.getOrderNo());
            stmt.setInt(QueryField.SUPPLIER_USER_ID, purchaseInfo.getSupplierUserId());
            stmt.setInt(QueryField.STATUS_ID, purchaseInfo.getStatusId());
            stmt.setInt(QueryField.ORDER_DATE, purchaseInfo.getOrderDate());
            stmt.setInt(QueryField.REQUESTED_SHIP_DATE, purchaseInfo.getRequestShippedDate());
            stmt.setLong(QueryField.DISCOUNT, purchaseInfo.getDiscount());
            stmt.setString(QueryField.REMARKS, purchaseInfo.getRemarks());
            stmt.executeUpdate();
        }
        this.addWarehousePurchasedProductList(purchaseInfo);
        this.addWarehouseStock(purchaseInfo);
        this.addShowroomPurchasedProductList(purchaseInfo);
        this.addShowroomStock(purchaseInfo);
    }
    
    public void addWarehousePurchasedProductList(PurchaseInfo purchaseInfo) throws DBSetupException, SQLException
    {
        //right now we are using loop. later use insert batch
        List<ProductInfo> productList = purchaseInfo.getProductList();
        for(ProductInfo productInfo:productList)
        {
            try (EasyStatement stmt = new EasyStatement(connection, QueryManager.ADD_WAREHOUSE_PURCHASED_PRODUCT_LIST)) {
                stmt.setInt(QueryField.PRODUCT_ID, productInfo.getId());
                stmt.setString(QueryField.ORDER_NO, purchaseInfo.getOrderNo());
                stmt.setLong(QueryField.UNIT_PRICE, productInfo.getUnitPrice());
                stmt.setLong(QueryField.DISCOUNT, productInfo.getDiscount());            
                stmt.executeUpdate();
            }
        }
    }
    
    public void addWarehouseStock(PurchaseInfo purchaseInfo) throws DBSetupException, SQLException
    {
        //right now we are using loop. later use insert batch
        List<ProductInfo> productList = purchaseInfo.getProductList();
        for(ProductInfo productInfo:productList)
        {
            try (EasyStatement stmt = new EasyStatement(connection, QueryManager.ADD_WAREHOUSE_STOCK)) {
                stmt.setString(QueryField.ORDER_NO, purchaseInfo.getOrderNo());
                stmt.setInt(QueryField.PRODUCT_ID, productInfo.getId());
                stmt.setLong(QueryField.STOCK_IN, productInfo.getQuantity());
                stmt.setLong(QueryField.STOCK_OUT, 0);
                //right now transaction category id constant. later update it from config file
                stmt.setInt(QueryField.TRANSACTION_CATEGORY_ID, 1);           
                stmt.executeUpdate();
            }
        }
    }
    
    public void addShowroomPurchasedProductList(PurchaseInfo purchaseInfo) throws DBSetupException, SQLException
    {
        //right now we are using loop. later use insert batch
        List<ProductInfo> productList = purchaseInfo.getProductList();
        for(ProductInfo productInfo:productList)
        {
            try (EasyStatement stmt = new EasyStatement(connection, QueryManager.ADD_SHOWROOM_PURCHASED_PRODUCT_LIST)) {
                stmt.setInt(QueryField.PRODUCT_ID, productInfo.getId());
                stmt.setString(QueryField.ORDER_NO, purchaseInfo.getOrderNo());
                stmt.setLong(QueryField.UNIT_PRICE, productInfo.getUnitPrice());
                stmt.setLong(QueryField.DISCOUNT, productInfo.getDiscount());            
                stmt.executeUpdate();
            }
        }
    }
    
    public void addShowroomStock(PurchaseInfo purchaseInfo) throws DBSetupException, SQLException
    {
        //right now we are using loop. later use insert batch
        List<ProductInfo> productList = purchaseInfo.getProductList();
        for(ProductInfo productInfo:productList)
        {
            try (EasyStatement stmt = new EasyStatement(connection, QueryManager.ADD_SHOWROOM_STOCK)) {
                stmt.setString(QueryField.PURCHASE_ORDER_NO, purchaseInfo.getOrderNo());
                stmt.setString(QueryField.SALE_ORDER_NO, null);
                stmt.setInt(QueryField.PRODUCT_ID, productInfo.getId());
                stmt.setLong(QueryField.STOCK_IN, productInfo.getQuantity());
                stmt.setLong(QueryField.STOCK_OUT, 0);
                //right now transaction category id constant. later update it from config file
                stmt.setInt(QueryField.TRANSACTION_CATEGORY_ID, 1);           
                stmt.executeUpdate();
            }
        }
    }
}
