package dev.ayush.productservice.services;

import dev.ayush.productservice.Dtos.GenericProductDto;
import dev.ayush.productservice.Exceptions.NotFoundException;

import java.util.List;

public interface ProductService {

   public GenericProductDto getProductById(Long id) throws NotFoundException;

   public List<GenericProductDto> GetAllProducts();

   GenericProductDto createProduct(GenericProductDto product);

   GenericProductDto deleteProductById(Long id) throws NotFoundException;

   GenericProductDto updateProductById(Long id, GenericProductDto product);
}
