package dev.ayush.productservice.controllers;

import dev.ayush.productservice.Dtos.ExceptionDto;
import dev.ayush.productservice.Dtos.GenericProductDto;
import dev.ayush.productservice.Exceptions.NotFoundException;
import dev.ayush.productservice.models.Product;
import dev.ayush.productservice.security.TokenValidator;
import dev.ayush.productservice.services.FakeStoreProductService;
import dev.ayush.productservice.services.ProductService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    // @Autowired
    // feild injection
    private ProductService productService;
    private TokenValidator tokenValidator;
    // constructer injection is the best practice as it makes the code more readable
    // spring automatically uses autowired annotation on the constrocter injection

    public ProductController(@Qualifier("FakeStoreProductService") ProductService productService,
                             TokenValidator tokenValidator) {
        this.productService = productService;
        this.tokenValidator = tokenValidator;
    }

    //setter injection
    // @Autowired
    // public void setProductService(ProductService productService) {
    // this.productService = productService;
    //


@GetMapping
public ResponseEntity<List<GenericProductDto>> getAllProducts(){

    List<GenericProductDto> genericProductDtos = productService.GetAllProducts();

    if (genericProductDtos.isEmpty()) {
        return new ResponseEntity<>(
                genericProductDtos,
                HttpStatus.NOT_FOUND
        );
    }

   /* return new ResponseEntity<>(
            genericProductDtos,
            HttpStatus.OK
    );

    we did this directly but this would make the test flaky when we will edit the list
    after fetching because due to pass by value the same list will be edited and
    it will pass the test case so we get all the data into a new list so that
    the object reference changes and the test case fails if any editing is done*/

    List<GenericProductDto> finProductsList = new ArrayList<>();

    for (GenericProductDto g : genericProductDtos) {
        finProductsList.add(g);
    }

   // finProductsList.remove(finProductsList.get(0));

    return new ResponseEntity<>(
            finProductsList,
            HttpStatus.OK
    );

}

@GetMapping("/{id}")
public GenericProductDto getProductById(@PathVariable("id") Long id) throws NotFoundException {
    GenericProductDto genericProductDto = productService.getProductById(id);

    if (genericProductDto == null) throw new NotFoundException("product doesnt exists");

    return genericProductDto;
}


@DeleteMapping ( "/{id}")
public GenericProductDto deleteProductById(@PathVariable("id") Long id) throws NotFoundException {
    return productService.deleteProductById(id);
}

@PostMapping
public GenericProductDto createProduct(@NonNull @RequestBody GenericProductDto product){
        return productService.createProduct(product);
}

@PatchMapping("{id}")
public GenericProductDto updateProductById(@PathVariable("id") Long id,@RequestBody GenericProductDto product){
        return productService.updateProductById(id, product);
}

}
