package dev.ayush.productservice;


import dev.ayush.productservice.controllers.CategoryController;
import dev.ayush.productservice.models.Category;
import dev.ayush.productservice.models.Price;
import dev.ayush.productservice.models.Product;
import dev.ayush.productservice.repositories.CategoryRepository;
import dev.ayush.productservice.repositories.PriceRepository;
import dev.ayush.productservice.repositories.ProductRepository;
import dev.ayush.productservice.services.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class ProductserviceApplication  { //implements CommandLineRunner {

    /*private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    private PriceRepository priceRepository;

    public ProductserviceApplication(*//*@Qualifier("categoryrepository")*//* CategoryRepository categoryRepository,
                                     *//*@Qualifier("productrepository") *//*ProductRepository productRepository,
                                     *//*@Qualifier("pricerepository")*//* PriceRepository priceRepository) {

        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.priceRepository = priceRepository;

    }*/

    public static void main(String[] args) {
        SpringApplication.run(ProductserviceApplication.class, args);
    }
/*
    @Transactional
    @Override
    public void run(String... args) throws Exception {
        Price price = new Price("ruppee",15000);
        Price savedPrice = priceRepository.save(price);

        Category category = new Category("tv", new ArrayList<>());

        Category savedCategory = categoryRepository.save(category);

        Product product = new Product();
        product.setCategory(savedCategory);
        product.setImage("qwer");
        product.setTitle("samsung");
        product.setDescription("colorful visions");
        product.setPrice(savedPrice);
        productRepository.save(product);


        System.out.println("fetching uuid e766cdc5-432e-4c55-b688-9669c314f88e");
        Thread.sleep(1000);
        Optional<Category> categoryOptional = categoryRepository.findById(UUID.fromString("e766cdc5-432e-4c55-b688-9669c314f88e"));

        if (categoryOptional.isEmpty()) {
            System.out.println("category dont exist");
            return;
        }

        Category category = categoryOptional.get();
        System.out.println("fetching products for category");

        Thread.sleep(1000);

        List<Product> products = category.getProductsss();

        for (Product product : products) {
            System.out.println(product);
        }
    }*/
}
