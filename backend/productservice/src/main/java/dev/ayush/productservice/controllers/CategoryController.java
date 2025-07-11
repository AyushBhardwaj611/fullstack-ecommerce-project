package dev.ayush.productservice.controllers;

import dev.ayush.productservice.Dtos.GetProductTitlesRequestDto;
import dev.ayush.productservice.Dtos.ProductDto;

import dev.ayush.productservice.models.Product;
import dev.ayush.productservice.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
        private CategoryService categoryService;

        public CategoryController(CategoryService categoryService) {
            this.categoryService = categoryService;
        }

        @GetMapping("/{uuid}")
    public List<ProductDto> getCategory(@PathVariable("uuid") String uuid) {


            List<Product> products =  categoryService.getCategory(uuid).getProductsss();

            List<ProductDto> productDtos = new ArrayList<>();

            for (Product product : products) {
                ProductDto pro = new ProductDto();

                pro.setTitle(product.getTitle());
                pro.setImage(product.getImage());
                pro.setDescription(product.getDescription());
                pro.setPrice(product.getPrice());
                productDtos.add(pro);
            }

            return productDtos;
    }
    @GetMapping("/titles/")
    public List<String> getProductTitles(@RequestBody GetProductTitlesRequestDto requestDto) {

        List<String> uuids = requestDto.getUuids();
            return categoryService.getProductTitles(uuids);
    }
}
