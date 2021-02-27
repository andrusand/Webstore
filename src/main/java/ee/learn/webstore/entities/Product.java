package ee.learn.webstore.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
public class Product {

    @GeneratedValue //Hibernate creates an automatic  value
    @Id
    Long Id;
    @Length(max = 12, message = "Product name bigger than expected!")
    String name;
    String description;
    Date updatedAt;
    Integer price;


    @ManyToOne
    @JoinColumn(name = "product_category")
    Category category;
}
