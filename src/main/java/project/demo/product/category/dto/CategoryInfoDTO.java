package project.demo.product.category.dto;

import lombok.*;
import org.springframework.util.ObjectUtils;
import project.demo.product.category.entity.CategoryInfo;

import java.util.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryInfoDTO {
    private Long id;
    private String name;
    private Long parentId;
    private int orderNumber;
    private TreeSet<CategoryInfoDTO> subCategories;

    public CategoryInfoDTO (CategoryInfo categoryInfo) {
        this.id = categoryInfo.getId();
        this.name = categoryInfo.getName();
        if(!ObjectUtils.isEmpty(categoryInfo.getParentId())){
            this.parentId = categoryInfo.getParentId();
        }
        this.orderNumber = categoryInfo.getOrderNumber();
        this.subCategories = new TreeSet<>(getSubCategoryComparator());
    }

    public static Comparator<CategoryInfoDTO> getSubCategoryComparator() {
        return Comparator.comparing(CategoryInfoDTO::getOrderNumber).thenComparing(CategoryInfoDTO::getName);
    }

    public void addSubcategory(CategoryInfoDTO categoryInfoDTO){
        subCategories.add(categoryInfoDTO);
    }

}
