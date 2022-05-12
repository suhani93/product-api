package project.demo.product.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.demo.product.category.entity.CategoryInfo;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryInfo, String>, CategoryRepositoryCustom {
    List<CategoryInfo> findAllByDeleted(boolean isDeleted);
}
