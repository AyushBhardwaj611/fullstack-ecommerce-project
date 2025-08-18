/*
package dev.ayush.productservice.controllers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.ayush.productservice.Dtos.GenericProductDto;
import dev.ayush.productservice.Exceptions.NotFoundException;
import dev.ayush.productservice.services.ProductService;
import org.apiguardian.api.API;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



*/
/*in the spring boot tests the whole spring boot server was starting thereby taking
a lot of time during the testing phase. because all the unnecesasary beans were also initialised
...so here we come up with the webMVC tests where an end to end testing(sort of functional testing)
is done such that the application would be presented in frint of the customer. *//*


*/
/*here we autowire the MockMvc dependency which means that only the necessary beans related
to the productcontroller class will be injected and not the whole application.
also the webMvc test handles the logic on the web layerrather than the whole application server
thereby making testing more robust and lightweight.

here we are testing the REST APIs of the fakeStore API*//*


@WebMvcTest(ProductController.class)
public class ProductControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Captor
    private ArgumentCaptor<Long> idCaptor;

    @MockitoBean
    @Qualifier("FakeStoreProductService")
    private ProductService productService;

    @Autowired
    private ProductController productController;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllProductsReturnsEmptyListWhenNoProducts() throws Exception {

        when(productService.GetAllProducts())
                .thenReturn(new ArrayList<>());

        mockMvc.perform(get("/api/v1/products"))
                .andExpect(status().is(404))
                .andExpect(content().string("[]"));
    }

    @Test
    void getAllProductsReturnsListWhenProductsExist() throws Exception {
        List<GenericProductDto> products = new ArrayList<>();

        products.add(new GenericProductDto());
        products.add(new GenericProductDto());
        products.add(new GenericProductDto());

        when(productService.GetAllProducts())
                .thenReturn(products);


        mockMvc.perform(get("/api/v1/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(products)));
    }

    @Test
    void CreateProductWhenCreateANewProduct() throws Exception{
        //we import the hamcrest/ assertJ dependency for matching here for JSON processing

        GenericProductDto productToCreate = new GenericProductDto();
        productToCreate.setPrice(1000000L);
        productToCreate.setTitle("iphone 15 pro");
        productToCreate.setDescription("latest applem device");
        productToCreate.setImage("some url : ");
        productToCreate.setCategory("apple devices");

        GenericProductDto expectedProduct = new GenericProductDto();
        expectedProduct.setId(101L);
        expectedProduct.setPrice(1000000L);
        expectedProduct.setTitle("iphone 15 pro");
        expectedProduct.setDescription("latest applem device");
        expectedProduct.setImage("some url : ");
        expectedProduct.setCategory("apple devices");

        when(productService.createProduct(any()))
                .thenReturn(expectedProduct);

        mockMvc.perform(
                post(URI.create("/api/v1/products"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productToCreate))
        )
                .andExpect(status().isOk())
                .andExpect(
                        content().string(objectMapper.writeValueAsString(expectedProduct))
                )
                .andExpect(jsonPath("$.student.name", is("ayush")))
                .andExpect(jsonPath("$.length()", is(2)));

    }

    @Test
    void productControllerCallsProductServiceBySameProductId() throws NotFoundException {
        when(productService.getProductById(anyLong()))
                .thenReturn(new GenericProductDto());

        // check the internal implementation using the argument captor
        // captor is something that listens to the argument on which it is put
        // and later can be used for verofication purposses.
        //this is generally used in end to end testing that is to check whether the correct
        //is is fetched by the database on the method invokation

        Long id = 101L;

        productController.getProductById(id);
        
        verify(productService).getProductById(idCaptor.capture());

        assertEquals(id, idCaptor.getValue());
    }

}
*/
