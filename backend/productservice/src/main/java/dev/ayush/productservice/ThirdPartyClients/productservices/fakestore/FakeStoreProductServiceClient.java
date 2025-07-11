package dev.ayush.productservice.ThirdPartyClients.productservices.fakestore;

import dev.ayush.productservice.Dtos.GenericProductDto;
import dev.ayush.productservice.Exceptions.NotFoundException;
import dev.ayush.productservice.configs.RestTemplateConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import lombok.Builder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
public class FakeStoreProductServiceClient // implements ThirdPartyProductServiceClient --> once implemented then remove this{
{

    private  String requestProductBaseUrl;

    private String  specificRequestProductIDUrl;

    private RestTemplate restTemplate;

    @Autowired
    public FakeStoreProductServiceClient(RestTemplate restTemplate,
                                         @Value("${fakeStore.Api.paths.product}") String productPathApiUrl,
                                         @Value("${fakeStore.Api.Url}") String BasicApiUrl) {

        this.restTemplate= restTemplate;
        this.requestProductBaseUrl = BasicApiUrl;
        this.specificRequestProductIDUrl = BasicApiUrl + productPathApiUrl + "/{id}";
    }


/*    public GenericProductDto convertFakeStoreProductDtoToGenericProductDto(FakeStoreProductDto fakeStoreProductDto){
        GenericProductDto product = new GenericProductDto();

        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImage(fakeStoreProductDto.getImage());
        product.setCategory(fakeStoreProductDto.getCategory());
        product.setId(fakeStoreProductDto.getId());

        return product;
    }
    no more required as we will return fakeStoreDto object which was returned by the client here
    we will use this method in the service class to convert the response
    */


    public FakeStoreProductDto getProductById(Long id) throws NotFoundException {


     //   RestTemplate restTemplate = restTemplateBuilder.build();
        System.out.println("in product service");

        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.getForEntity(specificRequestProductIDUrl, FakeStoreProductDto.class, id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        if (fakeStoreProductDto == null) {
            throw new NotFoundException("Resource with id: " + id + " not found");
        }
        else if (fakeStoreProductDto.getId() != id) {
            throw new NotFoundException("Resource with id: " + id + " not found");
        }

        return fakeStoreProductDto;
    }


    public List<FakeStoreProductDto> GetAllProducts() {
    //    RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto[]> response =
                restTemplate.getForEntity(requestProductBaseUrl, FakeStoreProductDto[].class);

        return Arrays.stream(response.getBody()).toList();
    }


    public FakeStoreProductDto createProduct(GenericProductDto product) {
      //  RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.postForEntity(requestProductBaseUrl, product, FakeStoreProductDto.class);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return fakeStoreProductDto;
    }


    public FakeStoreProductDto deleteProductById(Long id) {
      //  RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDto.class);

        ResponseEntity<FakeStoreProductDto>  response = restTemplate.execute(specificRequestProductIDUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return fakeStoreProductDto;
    }


    public FakeStoreProductDto updateProductById(Long id, GenericProductDto product) {
     //   RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.execute(specificRequestProductIDUrl, HttpMethod.PATCH, requestCallback, responseExtractor, id);



        return response.getBody();
    }
}
