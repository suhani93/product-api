package project.demo.product.product.entity;

import project.demo.product.common.entity.CreatedBaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "t_product_stock")
public class ProductStock extends CreatedBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long productInfoId;
    private int quantity;
}
