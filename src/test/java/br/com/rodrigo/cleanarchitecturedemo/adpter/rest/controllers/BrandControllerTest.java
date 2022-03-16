package br.com.rodrigo.cleanarchitecturedemo.adpter.rest.controllers;

import br.com.rodrigo.cleanarchitecturedemo.adpter.rest.dtos.BrandDTO;
import br.com.rodrigo.cleanarchitecturedemo.domain.models.Brand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BrandControllerTest {

    @LocalServerPort
    private int localServerPort;

    @BeforeEach
    void setUp() {
        basePath = "/api/v1/brands";
        port = localServerPort;
    }

    @Test
    void shouldReturnCreatedWhenCreatingBrand() {

        var brand = BrandDTO.builder().fantasyName("Test").corporateName("Test").build();

        given().body(brand).contentType("application/json").post().then().statusCode(HttpStatus.CREATED.value());

    }
}
