package br.com.rodrigo.cleanarchitecturedemo.adpter.rest.controllers;

import br.com.rodrigo.cleanarchitecturedemo.adpter.rest.dtos.BrandDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.hasItem;

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
    void shouldReturnCreatedStatusWhenCreatingBrand() {

        var brand = BrandDTO.builder().fantasyName("Test").corporateName("Test").build();

        given()
                .body(brand)
                .contentType("application/json")
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value());

    }

    @Test
    void shouldReturnBadRequestWithMessageWhenCreatingBrandWithoutFantasyName() {
        var brand = BrandDTO.builder().fantasyName(null).corporateName("Test").build();
        given()
                .body(brand)
                .contentType("application/json")
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("descriptions", hasItem("Fantasy name is mandatory."));
    }

    @Test
    void shouldReturnBadRequestWithMessageWhenCreatingBrandWithoutCorporateName() {
        var brand = BrandDTO.builder().fantasyName("Test").corporateName(null).build();
        given()
                .body(brand)
                .contentType("application/json")
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("descriptions", hasItem("Corporate name is mandatory."));
    }
}
