package dev.ayush.productservice.services;

import dev.ayush.productservice.Dtos.GenericProductDto;
import dev.ayush.productservice.Exceptions.NotFoundException;
import dev.ayush.productservice.models.Category;
import dev.ayush.productservice.models.Price;
import dev.ayush.productservice.models.Product;
import dev.ayush.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("selfproductserviceimpl")
public class SelfProductServiceImpl implements ProductService{

    private ProductRepository productRepository;

    public SelfProductServiceImpl(@Qualifier("productrepository") ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public GenericProductDto convertProductToGenericProductDto(Product product) {
        GenericProductDto g = new GenericProductDto();

        g.setCategory(product.getCategory().toString());
        g.setDescription(product.getDescription());
        g.setTitle(product.getTitle());
        g.setImage(product.getImage());
        g.setPrice(product.getPrice().getPrice());

        return g;
    }

    public Product convertGenericProductDtoToProduct(GenericProductDto g) {
        Product p = new Product();
        p.setTitle(g.getTitle());
        p.setDescription(g.getDescription());
        p.setImage(g.getImage());
        p.setPrice(convertDoubleToPrice(g.getPrice()));
        p.setCategory(convertStringToCategory(g.getCategory()));

        return p;
    }

    public Category convertStringToCategory(String s) {
        Category c = new Category();
        c.setName(s);
        c.setProductsss(new ArrayList<>());
        return c;
    }

    public Price convertDoubleToPrice(double p) {
        Price price = new Price();
        price.setPrice(p);
        price.setCurrency("");

        return price;
    }
    @Override
    public GenericProductDto getProductById(Long id) {
        Optional<Product> product = productRepository.findById(UUID.fromString(String.valueOf(id)));

        if (product.isEmpty()) return new GenericProductDto();
        return convertProductToGenericProductDto(product.get());
    }



    @Override
    public List<GenericProductDto> GetAllProducts() {
       List<Product> products = productRepository.findAll();
       List<GenericProductDto> gen = new ArrayList<>();
       for (Product product : products) {
           gen.add(convertProductToGenericProductDto(product));
       }
       return gen;
     }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        Product p = new Product();
        p.setDescription(product.getDescription());
        p.setImage(product.getImage());
        p.setCategory(convertStringToCategory(product.getCategory()));
        p.setPrice(convertDoubleToPrice(product.getPrice()));
        p.setTitle(product.getTitle());

        productRepository.save(p);
        return convertProductToGenericProductDto(p);
    }

    @Override
    public GenericProductDto deleteProductById(Long id) {
        Optional<Product> op = productRepository.findById(UUID.fromString(String.valueOf(id)));

        if (op.isEmpty()) return null;
        productRepository.delete(op.get());

        GenericProductDto g = convertProductToGenericProductDto(op.get());
        return g;
    }

    @Override
    public GenericProductDto updateProductById(Long id, GenericProductDto product) {

        Product pnew = convertGenericProductDtoToProduct(product);

        Product qwer = productRepository.updateProductById(UUID.fromString(String.valueOf(id)), pnew);

        return convertProductToGenericProductDto(qwer);
    }
}
