package dev.ayush.productservice.services;

import dev.ayush.productservice.ThirdPartyClients.productservices.fakestore.FakeStoreProductDto;
import dev.ayush.productservice.Dtos.GenericProductDto;
import dev.ayush.productservice.Exceptions.NotFoundException;
import dev.ayush.productservice.ThirdPartyClients.productservices.fakestore.FakeStoreProductServiceClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Primary      // this annotation is used to mark the bean as primary so even if we
// forget to tell during the dependency injection
// (where we have more than 1 candidate for the object creation)
// then also primary works by default..

//
@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService{

   // private RestTemplateBuilder restTemplateBuilder;

  //  private String specificRequestProductIDUrl = "https://fakestoreapi.com/products/{id}";

  //  private String requestProductBaseUrl = "https://fakestoreapi.com/products";

    // we moved the logic to the FakeStoreProductServiceClient so that this layer only controls the
    // business logic and the work of calling the APIs is done seperatly by the client class

    private FakeStoreProductServiceClient fakeStoreProductServiceClient;

    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient) {
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
    }

    public GenericProductDto convertFakeStoreProductDtoToGenericProductDto(FakeStoreProductDto fakeStoreProductDto){
        GenericProductDto product = new GenericProductDto();

        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImage(fakeStoreProductDto.getImage());
        product.setCategory(fakeStoreProductDto.getCategory());
        product.setId(fakeStoreProductDto.getId());

        return product;
    }

    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {

       return convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductServiceClient.getProductById(id));
    }

    @Override
    public List<GenericProductDto> GetAllProducts() {

        List<GenericProductDto> answer = new ArrayList<>();

        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductServiceClient.GetAllProducts()) {
            answer.add(convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductDto));
        }

        return answer;

    }

    
    public GenericProductDto createProduct(GenericProductDto product){
        return convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductServiceClient.createProduct(product));
    }

    @Override
    public GenericProductDto deleteProductById(Long id) {

        return convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductServiceClient.deleteProductById(id));
    }

    @Override
    public GenericProductDto updateProductById(Long id, GenericProductDto product) {

        return convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductServiceClient.updateProductById(id, product));
    }
}
