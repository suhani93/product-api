package project.demo.product.product.entity;

import lombok.Getter;
import project.demo.product.category.entity.CategoryInfo;
import project.demo.product.common.entity.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "t_product_info")
@Getter
public class ProductInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="category_id")
    private CategoryInfo categoryInfo;

    private long sellerId;
    private String name;
    private String imageUrl;
    @Lob
    private String description;
    private String brand;
    private String manufacturer;
    private BigDecimal price;

    @Column(name = "is_deleted")
    private boolean deleted;
}
