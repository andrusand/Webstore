package ee.learn.webstore.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Getter
@Setter
public class ProductDTO {

    Long Id;
    String name;
    String description;
    Integer price;

}
