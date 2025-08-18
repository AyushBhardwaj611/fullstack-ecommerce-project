package dev.ayush.productservice.ThirdPartyClients.productservices;

import dev.ayush.productservice.Dtos.GenericProductDto;
import dev.ayush.productservice.Exceptions.NotFoundException;

import java.util.List;

public interface ThirdPartyProductServiceClient {

    public GenericProductDto getProductById(Long id) throws NotFoundException;

    public List<GenericProductDto> GetAllProducts();

    GenericProductDto createProduct(GenericProductDto product);

    GenericProductDto deleteProductById(Long id);

    GenericProductDto updateProductById(Long id, GenericProductDto product);
}
