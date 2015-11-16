/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.bean;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nazmul
 */
public class PurchaseInfo {
    private int id;
    private String orderNo;
    private int supplierUserId;
    private int statusId;
    private int orderDate;
    private int requestShippedDate;
    private long discount;
    private String remarks;
    private List<ProductInfo> productList;
    public PurchaseInfo()
    {
        productList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getSupplierUserId() {
        return supplierUserId;
    }

    public void setSupplierUserId(int supplierUserId) {
        this.supplierUserId = supplierUserId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(int orderDate) {
        this.orderDate = orderDate;
    }

    public int getRequestShippedDate() {
        return requestShippedDate;
    }

    public void setRequestShippedDate(int requestShippedDate) {
        this.requestShippedDate = requestShippedDate;
    }

    public long getDiscount() {
        return discount;
    }

    public void setDiscount(long discount) {
        this.discount = discount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<ProductInfo> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductInfo> productList) {
        this.productList = productList;
    }
    
}
