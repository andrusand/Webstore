package ee.learn.webstore.controllers;

import ee.learn.webstore.dto.ProductSearchDTO;
import ee.learn.webstore.entities.Product;
import ee.learn.webstore.services.CategoryService;
import ee.learn.webstore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.nio.file.Path;
import java.util.List;

@Controller
@RequestMapping("/product") //al of them have /product/nameOfPage
public class ProductController {

    @Autowired
    ProductService service;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/create") //URL address where we can access it
    String createProductGet(Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.findAll());
            return "createProductGet";
        }


    @PostMapping("/create") //after we save the product, we send it to that address.
    RedirectView createProductPost(Product product) {
        service.createOrUpdate(product);
        return new RedirectView("/product/all");
    }

    @GetMapping("/all") //Spring will provide the model to send to the page. In this case allProducts.html
    String allProducts(Model model){
        List<Product> productList = service.findAll();
        model.addAttribute("products", productList);
        return "allProducts";
    }

    @PostMapping("/search")
    String allProducts (Model model, ProductSearchDTO dto) {
        List<Product> productList = service.findProductByDescriptionContains(dto.getDescription());
        model.addAttribute("products", productList);
        return "allProducts";
    }

    @GetMapping("/search")
    String search(Model model) {
        model.addAttribute("searchDTO", new ProductSearchDTO());
        return "search";
    }

}
