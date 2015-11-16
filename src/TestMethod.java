
import com.inventory.bean.AddressInfo;
import com.inventory.bean.ProductInfo;
import com.inventory.bean.SupplierInfo;
import com.inventory.bean.UserInfo;
import com.inventory.db.Database;
import com.inventory.db.ProductManager;
import com.inventory.db.SupplierManager;
import com.inventory.exceptions.DBSetupException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nazmul
 */
public class TestMethod {
    public static void main(String args[]) throws DBSetupException, SQLException
    {
          Database.getInstance();
//        ProductInfo productInfo = new ProductInfo();
//        productInfo.setName("a3");
//        productInfo.setCode("b3");
//        productInfo.setLength("c3");
//        productInfo.setWidth("d3");
//        productInfo.setHeight("e3");
//        productInfo.setWeight("f3");
//        
//        ProductManager productManager = new ProductManager();
////        productManager.createProduct(productInfo);
////        
//        List<ProductInfo> productList = productManager.getAllProducts();
          UserInfo userInfo = new UserInfo();
          userInfo.setFirstName("fn2");
          userInfo.setLastName("ln2");
          userInfo.setEmail("email2");
          userInfo.setPhone("phone2");
          userInfo.setFax("fax2");
          userInfo.setWebsite("website2");
          
          userInfo.setGroupId(1);
          
          AddressInfo addressInfo = new AddressInfo();
          addressInfo.setAddress("niketon");
          addressInfo.setCity("dhaka");
          addressInfo.setState("dhaka");
          addressInfo.setZip("1207");
          
          List<AddressInfo> addresses = new ArrayList<>();
          addresses.add(addressInfo);
          userInfo.setAddresses(addresses);
          
          SupplierInfo supplierInfo = new SupplierInfo();
          supplierInfo.setUserInfo(userInfo);
          supplierInfo.setRemarks("remarks2");
          
          SupplierManager supplierManager = new SupplierManager();
          supplierManager.createSupplier(supplierInfo);
    }
}
