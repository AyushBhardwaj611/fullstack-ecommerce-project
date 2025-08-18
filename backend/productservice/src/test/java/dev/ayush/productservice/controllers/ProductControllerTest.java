/*
package dev.ayush.productservice.controllers;

import dev.ayush.productservice.Dtos.GenericProductDto;
import dev.ayush.productservice.Exceptions.NotFoundException;
import dev.ayush.productservice.ThirdPartyClients.productservices.fakestore.FakeStoreProductServiceClient;
import dev.ayush.productservice.services.ProductService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.web.bind.annotation.PathVariable;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;


    @Autowired
   // @MockitoBean
    private ProductService productService;


    @Captor
    private ArgumentCaptor<Long> idCaptor;

    @Captor
    private ArgumentCaptor<Long> idCaptorFakeStoreServiceClient;


    @Autowired
    private FakeStoreProductServiceClient fakeStoreProductServiceClient;

    @SneakyThrows
    @Test
    void shouldReturnTitleAyushWhenProductIdIs2() {
        GenericProductDto genericProductDto = new GenericProductDto();

        genericProductDto.setTitle("Ayush");

        when(
                productService.getProductById(2L)
        ).thenReturn(genericProductDto);

        GenericProductDto genericProductDto1 = productController.getProductById(2L);
        assertEquals("Ayush",genericProductDto1.getTitle());


    }

    @Test

    void shouldReturnSameProductAsServiceIfExists() throws NotFoundException {
        GenericProductDto genericProductDto = new GenericProductDto();
        when(
                productService.getProductById(anyLong())
        ).thenReturn(genericProductDto);

        assertEquals(genericProductDto, productController
                .getProductById(1234L));
    }

    @Test
    void shouldReturnNullWhenProductDoesnotExist() throws NotFoundException {

        when(
                productService.getProductById(anyLong())
        ).thenReturn(null);

        assertThrows(NotFoundException.class, () -> productController.getProductById(123L));
    }


    @Test
    void returnsSameProductAsServiceWhenProductExists() throws NotFoundException {

        GenericProductDto genericProductDto = new GenericProductDto();

        when(
                productService.getProductById(anyLong())
        ).thenReturn(genericProductDto);

        assertEquals(productService.getProductById(anyLong()).getPrice(),
                        productController.getProductById(anyLong()).getPrice());

        assertThrows(NotFoundException.class,() -> productController.getProductById(123L));


    }

    @Test

    void shouldReturnTitleAyushWhenProductIdIs1() throws NotFoundException {
        GenericProductDto genericProductDto = new GenericProductDto();

        when(
                productController.getProductById(anyLong())
        ).thenReturn(genericProductDto);

        GenericProductDto genericProductDto1 =  productController.getProductById(1L);

        assertEquals("ayush", genericProductDto1);
    }

    @Test
    @DisplayName("1 + 1 = 2")
    void testOnePlusOneEqualsTwo() {

        assertEquals (2, 1 + 1, "yes got it correct");

    }

}*/
