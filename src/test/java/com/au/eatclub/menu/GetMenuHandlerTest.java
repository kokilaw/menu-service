package com.au.eatclub.menu;

import com.au.eatclub.menu.api.request.InputObject;
import io.quarkus.test.TestTransaction;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;

@TestTransaction
@QuarkusTest
public class GetMenuHandlerTest {

    @Test
    public void testSimpleLambdaSuccess() throws Exception {
        // you test your lambdas by invoking on http://localhost:8081
        // this works in dev mode too

        InputObject in = new InputObject();
        in.setRestaurantId("b30c8aba-7784-4d24-9c7e-5f9c8eeb1153");
        String responseString = given()
                .contentType("application/json")
                .accept("application/json")
                .body(in)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract().body().asPrettyString();
        System.out.println(responseString);
    }

}
