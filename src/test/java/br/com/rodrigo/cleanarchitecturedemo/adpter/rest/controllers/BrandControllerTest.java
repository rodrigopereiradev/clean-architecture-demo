package br.com.rodrigo.cleanarchitecturedemo.adpter.rest.controllers;

import br.com.rodrigo.cleanarchitecturedemo.adpter.rest.dtos.BrandDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

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

    @Test
    void shouldReturnStatusOkWithFieldsFilledWhenGettingTheBrandById() {
        given()
                .get("/50")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("fantasyName", equalTo("Test"))
                .body("corporateName", equalTo("Test"))
                .body("isActive", equalTo(Boolean.TRUE))
                .body("createdIn", notNullValue());
    }

    @Test
    void shouldReturnBadRequestWithMessageWhenBrandNotFound() {
        given()
                .get("/200")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("description", equalTo("Brand Entity not found."));
    }

    @Test
    void shouldReturnStatusOkWithAtLeastOneBrand() {
        given()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("results", hasSize(greaterThan(0)));
    }

    @Test
    void shouldReturnStatusOkUpdatingBranThatAlreadyExists() {
        var brand = BrandDTO.builder().fantasyName("Test Put").corporateName("Test Put").build();

        given()
                .body(brand)
                .contentType("application/json")
                .put("/51")
                .then()
                .statusCode(HttpStatus.OK.value());

        given()
                .get("/51")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("fantasyName", equalTo("Test Put"))
                .body("corporateName", equalTo("Test Put"))
                .body("updatedIn", notNullValue());
    }

    @Test
    void shouldReturnOkAndActivateInactiveBrand() {

        given().patch("/52/activate").then().statusCode(HttpStatus.OK.value());

        given()
                .get("/52")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("isActive", equalTo(Boolean.TRUE))
                .body("updatedIn", notNullValue());

    }

    @Test
    void shouldReturnOkAndInactivateAnActiveBrand() {

        given().patch("/53/inactivate").then().statusCode(HttpStatus.OK.value());

        given()
                .get("/53")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("isActive", equalTo(Boolean.FALSE))
                .body("updatedIn", notNullValue());

    }
}
