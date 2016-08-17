/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inventory.db;

import com.inventory.bean.ProductCategoryInfo;
import com.inventory.bean.ProductInfo;
import com.inventory.bean.ProductTypeInfo;
import com.inventory.bean.UOMInfo;
import com.inventory.db.manager.ProductManager;
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
public class ProductManagerTest {

    final static ProductManager productManager = new ProductManager();

    public ProductManagerTest() {

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

    // @Test
    public void createProductTest() {
        ProductInfo productInfo1 = new ProductInfo();
        productInfo1.setName("a1");
        productInfo1.setCode("b1");
        productInfo1.setLength("c1");
        productInfo1.setWidth("d1");
        productInfo1.setHeight("e1");
        productInfo1.setWeight("f1");
        productInfo1.setUnitPrice(100);
        productManager.createProduct(productInfo1);
    }
    // @Test

    public void getProductListTest() {
        List<ProductInfo> productInfoList = productManager.getAllProducts();
        System.out.println(productInfoList);
    }

    // @Test
    public void getAllProductCategoriesTest() {
        List<ProductCategoryInfo> productCategoryInfoList = productManager.getAllProductCategories();
        System.out.println(productCategoryInfoList);
    }

    // @Test
    public void getAllProductTypesTest() {
        List<ProductTypeInfo> productTypeInfoList = productManager.getAllProductTypes();
        System.out.println(productTypeInfoList);
    }
    
     //@Test

    public void getAllUOMs() {
        List<UOMInfo> uOMInfoList = productManager.getAllUOMs();
        System.out.println(uOMInfoList);
    }

}
