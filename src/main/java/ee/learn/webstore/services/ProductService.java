package ee.learn.webstore.services;

import ee.learn.webstore.dto.ProductDTO;
import ee.learn.webstore.entities.Category;
import ee.learn.webstore.entities.Product;
import ee.learn.webstore.repositories.ProductRepository;
import ee.learn.webstore.utils.ProductDateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

//CRUD creation - create, read, update, delete

@Service //bean to use for the product service
@RequiredArgsConstructor // creates constructor with one argument that you are forced to initialize.
public class ProductService implements GenericService<Product> {

    final private ProductRepository repository;
    final private ProductDateUtils dateUtils;


    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Product read(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    @Transactional //all happens or none of them happen
    public void createOrUpdate(Product object) {
        dateUtils.updateTime(object);
        repository.save(object);

    }

    @Override
    public void delete(Product object) {
        repository.delete(object);

    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);

    }

    public List<Product> findProductsNameLike (String value) {
        List<Product> productList = repository.findProductByNameContains(value);
        productList.forEach(product -> {
            Category emptyCategory = new Category();
            emptyCategory.setName("No Category");
            if (product.getCategory() == null){
                product.setCategory(emptyCategory);
            }
        });
        return productList;
    }

    public List<Product> getAllWithPricesLessThanValue (Integer value) {
        List<Product> productList = repository.getAllWithPricesLessThanValue(value);
        productList.forEach(product -> {
            Category emptyCategory = new Category();
            emptyCategory.setName("No Category");
            if (product.getCategory() == null){
                product.setCategory(emptyCategory);
            }
        });
        return productList;
    }

    public List<Product> findProductByDescriptionContains (String value) {
        List<Product> productList = repository.findProductByDescriptionContains(value);
        productList.forEach(product -> {
            Category emptyCategory = new Category();
            emptyCategory.setName("No Category");
            if (product.getCategory() == null){
                product.setCategory(emptyCategory);
            }
        });
        return productList;
    }

    //Return product by keyword

    public List<Product> findByKeyword (String keyword) {
        List<Product> productList = repository.findByKeyword(keyword);
        productList.forEach(product -> {
            Category emptyCategory = new Category();
            emptyCategory.setName("No Category");
            if (product.getCategory() == null){
                product.setCategory(emptyCategory);
            }
        });
        return productList;
    }

    public List<ProductDTO> findAllDTO() {
        List<ProductDTO> productDTOS = new ArrayList<>();
        List<Product> productList = repository.findAll();
        for (Product product : productList){
            ProductDTO productDTO = new ProductDTO();
            productDTO.setDescription(product.getDescription());
            productDTO.setName(product.getName());
            productDTO.setPrice(product.getPrice());
            productDTO.setId(product.getId());
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }
}
