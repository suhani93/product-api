package project.demo.product.category.entity;

import lombok.Getter;
import project.demo.product.common.converter.BooleanConverter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_category_info")
@Getter
public class CategoryInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private Long parentId;

    private int orderNumber;

    @Column(name = "is_deleted")
    @Convert(converter = BooleanConverter.class)
    private boolean deleted;

}
