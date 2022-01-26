package br.com.rodrigo.cleanarchitecturedemo.adpter.rest.controllers;

import br.com.rodrigo.cleanarchitecturedemo.adpter.rest.dtos.ProductDTO;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

    @LocalServerPort
    private int localServerPort;

    @BeforeEach
    void setup() {
        RestAssured.basePath = "/api/v1/products";
        RestAssured.port = localServerPort;
    }

    @Test
    void shouldReturnStatusCreatedWhenCreatingProduct() {
         var product = ProductDTO.builder()
                .name("Test")
                .description("Test")
                .brand("Test")
                .quantity(100)
                .value(new BigDecimal("400.90"))
                .build();

         given()
                 .body(product)
                 .contentType("application/json")
                 .post()
                 .then()
                 .statusCode(HttpStatus.CREATED.value());

    }

    @Test
    void shouldReturnStatusOkWhenGettingTheProdcutById() {

        given().
                get("/50").
                then()
                .statusCode(HttpStatus.OK.value());

    }

}