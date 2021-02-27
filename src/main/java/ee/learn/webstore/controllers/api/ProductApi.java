package ee.learn.webstore.controllers.api;

import ee.learn.webstore.dto.ProductDTO;
import ee.learn.webstore.entities.Product;
import ee.learn.webstore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController //the method is going to return JSON
@RequestMapping("/api/product")
public class ProductApi {

    @Autowired
    ProductService service;


    @GetMapping("/all")
    List<ProductDTO> getProducts(){
        return service.findAllDTO();

    }

}
