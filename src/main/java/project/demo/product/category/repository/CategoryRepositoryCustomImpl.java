package project.demo.product.category.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

public class CategoryRepositoryCustomImpl implements CategoryRepositoryCustom {
    private final JPAQueryFactory query;

    public CategoryRepositoryCustomImpl(JPAQueryFactory query) {
        this.query = query;
    }

}
