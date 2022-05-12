package project.demo.product.category.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import project.demo.product.category.dto.CategoryInfoDTO;
import project.demo.product.category.entity.CategoryInfo;
import project.demo.product.category.repository.CategoryRepository;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static project.demo.product.category.dto.CategoryInfoDTO.getSubCategoryComparator;


@RequiredArgsConstructor
@Service
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public TreeSet<CategoryInfoDTO> getCategories() {
        List<CategoryInfo> all = categoryRepository.findAllByDeleted(false);
        Map<Long, CategoryInfoDTO> list = all.stream().map(categoryInfo -> {
            return new CategoryInfoDTO(categoryInfo);
        }).collect(Collectors.toMap(CategoryInfoDTO::getId, Function.identity()));

        TreeSet<CategoryInfoDTO> result = new TreeSet<>(getSubCategoryComparator());

        list.forEach((aLong, categoryInfoDTO) -> {
            if(ObjectUtils.isEmpty(categoryInfoDTO.getParentId())){
               result.add(categoryInfoDTO);
            } else {
                list.get(categoryInfoDTO.getParentId()).addSubcategory(categoryInfoDTO);
            }
        });

        return result;
    }


}
