package dev.ayush.productservice.repositories;

import dev.ayush.productservice.models.Category;
import dev.ayush.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("productrepository")
public interface ProductRepository
        extends JpaRepository<Product, UUID> {

    Product findByTitleEquals(String title);


    Product findByTitleEqualsAndPrice_Price(String title, double pricePrice);

    List<Product> findAllByPrice_Currency(String priceCurrency);

    @Override
    void delete(Product entity);

    List<Product> findDistinctByTitleLike(String titleRegex);


        @Query(value = CustomQueries.FIND_ALL_BY_TITLE,nativeQuery = true)
        List<Product> findAllByTitle(@Param("title")String ayush);

        /*this is a typical mySql query....we should use this in the worst case
        * also writing them in a customQueries class in the repositories is better approach*/

    @Query("SELECT p from product p where p.price.currency = :currency and p.category = :books")
    List<Product> readAllByTitle(@Param("p.price.currency")String currency,@Param(" p.category") String books);

    // this is an HQL query...it is preferred over the native sql queries..


    @Override
    List<Product> findAll();

   // List<Product> id(UUID id);

    @Query("update product p set p.category = :#{#pro.category}," +
            "p.description = :#{#pro.description}," +
            "p.title = :#{#pro.title}," +
            "p.image = :#{#pro.image}," +
            "p.price = :#{#pro.price} where p.id = :id")
    Product updateProductById(@Param("id") UUID id,@Param("pro") Product pro);

    List<Product> findAllByCategoryIn(List<Category> categories);
}
