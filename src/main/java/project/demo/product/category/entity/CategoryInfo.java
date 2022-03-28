package project.demo.product.category.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_category_info")
public class CategoryInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="parent_id")
    private CategoryInfo parent;

    @OneToMany (mappedBy = "parent")
    private List<CategoryInfo> subCategory = new ArrayList<>();

}
