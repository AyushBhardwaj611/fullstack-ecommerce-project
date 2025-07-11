package dev.ayush.productservice.services;

import dev.ayush.productservice.Dtos.GetProductTitlesRequestDto;
import dev.ayush.productservice.models.Category;
import dev.ayush.productservice.models.Product;
import dev.ayush.productservice.repositories.CategoryRepository;
import dev.ayush.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Primary
@Service("CategoryServiceImpl")
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    

    public CategoryServiceImpl (CategoryRepository categoryRepository,
                                ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Category getCategory(String uuid) {
       Optional<Category> categoryOptional = categoryRepository.findById(UUID.fromString(uuid));

       if (categoryOptional.isEmpty()) return null;

       Category category = categoryOptional.get();

        List<Product> products= category.getProductsss();

       return category;
    }


   public List<String> getProductTitles(List<String> uuid) {

        List<UUID> uuids = new ArrayList<>();

        for (String u: uuid) {
            uuids.add(UUID.fromString(u));
        }

        /*
        this is a bad code practice as it will lead to N + 1 problem
        write query instead

        List<Category> categories= categoryRepository.findAllById(uuids);

       List<String> titles = new ArrayList<>();


       categories.forEach(
               category -> {
                   category.getProductsss().forEach(
                           product -> {
                               titles.add(product.getTitle());
                           }
                   );
               }
       );
*/
       List<Category> categories= categoryRepository.findAllById(uuids);

        List<Product> products = productRepository.findAllByCategoryIn(categories);

       List<String> titles = new ArrayList<>();

       for (Product product : products) {
           titles.add(product.getTitle());
       }

       return titles;
   }
}

