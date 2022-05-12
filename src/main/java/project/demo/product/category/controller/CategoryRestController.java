package project.demo.product.category.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.demo.product.category.dto.CategoryInfoDTO;
import project.demo.product.category.service.CategoryService;
import project.demo.product.common.response.ResponseUtil;

import java.util.TreeSet;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/categories",produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class CategoryRestController {

    private final CategoryService categoryService;

    @GetMapping("")
    public ResponseUtil.ResponseSuccessData<TreeSet<CategoryInfoDTO>> getCategories(){
        TreeSet<CategoryInfoDTO> categories = categoryService.getCategories();
        return ResponseUtil.success(categories);
    }
}
