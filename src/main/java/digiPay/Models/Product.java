package digiPay.Models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "products")
public class Product extends AbstractModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull
    private Integer value;

    @NotNull
    private Integer count;

    public Product() {
    }

    public Product(
            String title,
            String description,
            Integer value,
            Integer count
    ) {
        this.title = title;
        this.description = description;
        this.value = value;
        this.count = count;
    }

    public Integer getValue() {
        return value;
    }
}
