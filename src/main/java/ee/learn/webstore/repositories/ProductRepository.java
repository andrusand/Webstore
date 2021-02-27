package ee.learn.webstore.repositories;

import ee.learn.webstore.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    //spring queries, searching by name
    List<Product> findProductByNameContains(String name);

    List<Product> findProductByDescriptionContains(String name);

    //or using completely custom built
    @Query("select p from Product p where p.price < :value")
    List<Product> getAllWithPricesLessThanValue (@Param("value") Integer price);

    @Query(value = "select * from Product p where p.name like %:keyword% or p.description like %:keyword%", nativeQuery = true)
    List<Product> findByKeyword(@Param("keyword") String keyword);
}
