package br.com.rodrigo.cleanarchitecturedemo.adpter.rest.controllers;

import br.com.rodrigo.cleanarchitecturedemo.adpter.rest.dtos.ProductDTO;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

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
        ProductDTO product = getProductDTO();

        given().body(product)
                 .contentType("application/json")
                 .post()
                 .then()
                 .statusCode(HttpStatus.CREATED.value());

    }

    @Test
    void shouldReturnStatusOkWithFilledFieldsWhenGettingTheProductById() {
        given().
                get("/50").
                then()
                .statusCode(HttpStatus.OK.value())
                .body("name", equalTo("Controle Xbox 360"))
                .body("description", equalTo("Cor branca."))
                .body("brand", notNullValue())
                .body("quantity", equalTo(20))
                .body("value", equalTo(99.99F))
                .body("isActive", equalTo(Boolean.TRUE))
                .body("createdIn", notNullValue());
    }

    @Test
    void shouldReturnBadRequestWithMessageWhenGettingTheProductThatNotExists() {
        given().
                get("/1000").
                then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("description", equalTo("Product Entity not found."));
    }

    @Test
    void shouldReturnBadRequestWithMessageWhenCreatingProductWithNullName() {
        var product = getProductDTO();
        product.setName(null);

        given().body(product)
                .contentType("application/json")
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("descriptions", hasItem("The product's name is mandatory."));

    }

    @Test
    void shouldReturnBadRequestWithMessageWhenCreatingProductWithEmptyName() {
        var product = ProductDTO.builder().name("").build();
        given().body(product)
                .contentType("application/json")
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("descriptions", hasItem("The product's name is mandatory."));

    }

    @Test
    void shouldReturnBadRequestWithMessageWhenCreatingProductWithNullBrand() {
        var product = getProductDTO();
        product.setIdBrand(null);
        given().body(product)
                .contentType("application/json")
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("descriptions", hasItem("The product's brand id is mandatory."));

    }

    @Test
    void shouldReturnBadRequestWithMessageWhenCreatingProductWithNullQuantity() {
        var product = getProductDTO();
        product.setQuantity(null);

        given().body(product)
                .contentType("application/json")
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("descriptions", hasItem("The product's quantity is mandatory."));
    }

    @Test
    void shouldReturnBadRequestWithMessageWhenCreatingProductWithNullValue() {
        var product = getProductDTO();
        product.setValue(null);

        given().body(product)
                .contentType("application/json")
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("descriptions", hasItem("The product's value is mandatory."));
    }

    @Test
    void shouldReturnBadRequestWithMessageWhenCreatingProductWithNegativeValue() {
        var product = getProductDTO();
        product.setValue(new BigDecimal("-1"));

        given().body(product)
                .contentType("application/json")
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("descriptions", hasItem("The product's value needs to be highest or equal 0.0"));

    }

    @Test
    void shouldReturnBadRequestWithMessageWhenCreatingProductWithValueWithMoreThanTwoDecimals() {
        var product = getProductDTO();
        product.setValue(new BigDecimal("1.003"));

        given().body(product)
                .contentType("application/json")
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("descriptions", hasItem("Value needs to be a decimal (17,2)"));

    }

    @Test
    void shouldReturnBadRequestWithMessageWhenCreatingProductWithValueMoreThanSixteenIntegers() {
        var product = getProductDTO();
        product.setValue(new BigDecimal("12345678901234567.00"));

        given().body(product)
                .contentType("application/json")
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("descriptions", hasItem("Value needs to be a decimal (17,2)"));

    }

    @Test
    void shouldReturnOkWithProductActivatedWhenActivatingProduct() {

        given().patch("/51/activate").then().statusCode(HttpStatus.OK.value());

        given().get("/51").then().body("isActive", equalTo(Boolean.TRUE));

    }

    @Test
    void shouldReturnOkWithNewQuantityWhenIncreasingProductQuantity() {

        given().patch("/51/increases/15").then().statusCode(HttpStatus.OK.value());

        given().get("/51").then().body("quantity", equalTo(35));

    }

    @Test
    void shouldReturnOkWithNewQuantityWhenDecreasingProductQuantity() {

        given().patch("/52/decreases/15").then().statusCode(HttpStatus.OK.value());

        given().get("/52").then().body("quantity", equalTo(5));

    }

    @Test
    void shouldReturnOkWhitNoEmptyListWhenGettingAllProducts() {

        given().get().then().body("results", hasSize(greaterThan(0)));

    }

    @Test
    void shouldReturnOkWithProductInactivatedWhenInactivatingProduct() {

        given().patch("/52/inactivate").then().statusCode(HttpStatus.OK.value());

        given().get("/52").then().body("isActive", equalTo(Boolean.FALSE));

    }

    private ProductDTO getProductDTO() {
        return ProductDTO.builder()
                .name("Test")
                .description("Test")
                .idBrand(50L)
                .quantity(100)
                .value(new BigDecimal("400.90"))
                .build();
    }


}
