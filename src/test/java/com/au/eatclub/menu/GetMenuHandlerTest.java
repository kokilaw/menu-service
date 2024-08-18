package com.au.eatclub.menu;

import com.au.eatclub.menu.api.request.InputObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.TestTransaction;
import jakarta.inject.Inject;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class GetMenuHandlerTest {

    @Inject
    ObjectMapper objectMapper;

    @TestTransaction
    @Test
    public void testSimpleLambdaSuccess() throws IOException, JSONException {

        String expectedResponse = readFromFile("expected-responses/expected-full-menu-1.json");

        InputObject in = new InputObject();
        in.setRestaurantId("b30c8aba-7784-4d24-9c7e-5f9c8eeb1153");
        String actualResponse = given()
                .contentType("application/json")
                .accept("application/json")
                .body(in)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract().body().asPrettyString();
        JSONAssert.assertEquals(expectedResponse, actualResponse, false);
    }

    public String readFromFile(String path) throws IOException {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(path);
        assert resourceAsStream != null;
        return IOUtils.toString(resourceAsStream, StandardCharsets.UTF_8);
    }

}
