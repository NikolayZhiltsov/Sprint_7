package praktikum.steps;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import praktikum.model.Order;

import static io.restassured.RestAssured.*;

public class OrderSteps {
    private static final String ORDERS_LIST = "api/v1/orders";
    private static final String ORDER_CREATE = "/api/v1/orders";

    @Step("Шаг запроса списка заказов")
    public ValidatableResponse getOrdersList() {
        return given()
                .when()
                .get(ORDERS_LIST)
                .then();
    }

    @Step
    @DisplayName("Шаг создания заказа")
    public ValidatableResponse createOrder(Order order) {
        return given()
                .body(order)
                .when()
                .post(ORDER_CREATE)
                .then();
    }
}
