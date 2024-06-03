package praktikum.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import praktikum.model.Courier;

import static io.restassured.RestAssured.*;

public class CourierSteps {
    private static final String COURIER_CREATE = "/api/v1/courier";
    private static final String COURIER_LOGIN = "/api/v1/courier/login";
    private static final String COURIER_DELETE = "/api/v1/courier/{id}";

    @Step("Шаг создания курьера")
    public ValidatableResponse createCourier(Courier courier) {
        return given()
                .body(courier)
                .when()
                .post(COURIER_CREATE)
                .then();
    }

    @Step("Шаг логина курьера в систему")
    public ValidatableResponse loginCourier(Courier courier) {
        return given()
                .body(courier)
                .when()
                .post(COURIER_LOGIN)
                .then();
    }

    @Step("Шаг удаления курьера")
    public ValidatableResponse deleteCourier(Courier courier) {
        return given()
                .pathParam("id", courier.getId())
                .when()
                .delete(COURIER_DELETE)
                .then();
    }
}
