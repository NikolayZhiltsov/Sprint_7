package praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import praktikum.model.Courier;
import praktikum.steps.CourierSteps;

public class CourierCreateTests extends AbstractTest{
    private CourierSteps courierSteps = new CourierSteps();
    private Courier courier;


    @Before
    public void setUp() {
        courier = new Courier();
        courier.setLogin(RandomStringUtils.randomAlphabetic(10));
        courier.setPassword(RandomStringUtils.randomAlphabetic(10));
        courier.setFirstName(RandomStringUtils.randomAlphabetic(10));
    }


    @Test
    @DisplayName("Проверяем статус-код ответа успешного создания курьера")
    public void shouldReturnStatusCode201() {
        courierSteps
                .createCourier(courier)
                .statusCode(201);
    }

    @Test
    @DisplayName("Проверяем тело ответа успешного создания курьера")
    public void shouldReturnOkTrue() {
        courierSteps
                .createCourier(courier)
                .body("ok", Matchers.is(true));
    }

    @Test
    @DisplayName("Проверяем статус-код ответа повторного использования логина")
    public void shouldReturnStatusCode409() {
        courierSteps
                .createCourier(courier);
        courierSteps
                .createCourier(courier)
                .statusCode(409);
    }

    @Test
    @DisplayName("Проверяем тело ответа повторного использования логина")
    public void shouldReturnMessageLoginIsAlreadyExists() {
        courierSteps
                .createCourier(courier);
        courierSteps
                .createCourier(courier)
                .body("code", Matchers.is(409), "message", Matchers.is("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    @DisplayName("Проверяем статус-код ответа о недостающем обязательном поле")
    public void shouldReturnStatusCode400() {
        courier.setPassword("");
        courierSteps
                .createCourier(courier)
                .statusCode(400);
    }

    @Test
    @DisplayName("Проверяем тело ответа о недостающем обязательном поле")
    public void shouldReturnMessageFieldIsEmpty() {
        courier.setLogin("");
        courierSteps
                .createCourier(courier)
                .body("message", Matchers.is("Недостаточно данных для создания учетной записи"));
    }

    @After
    public void tearDown() {
        Integer id = courierSteps.loginCourier(courier)
                .extract().body().path("id");
        if (id != null) {
            courier.setId(id);
            courierSteps.deleteCourier(courier);
        }
    }
}