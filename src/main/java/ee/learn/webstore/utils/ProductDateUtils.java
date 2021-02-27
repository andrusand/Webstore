package ee.learn.webstore.utils;

import ee.learn.webstore.entities.Product;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ProductDateUtils {

    //receive product change date and time and return the product
    public Product updateTime(Product product) {
        product.setUpdatedAt(new Date());
        return product;
    }

}
