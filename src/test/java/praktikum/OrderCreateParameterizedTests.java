package praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.model.Order;
import praktikum.steps.OrderSteps;


@RunWith(Parameterized.class)
public class OrderCreateParameterizedTests extends AbstractTest{
    private String firstColour;
    private String secondColour;

    @Parameterized.Parameters
    public static Object[][] colour() {
        return new Object[][]{
                {null, null},
                {"BLACK", null},
                {null, "GREY"},
                {"BLACK", "GREY"}
        };
    }

    public OrderCreateParameterizedTests(String firstColour, String secondColour) {
        this.firstColour = firstColour;
        this.secondColour = secondColour;
    }

    @Test
    @DisplayName("Проверяем создание заказа с разными сочетаниями цветов самоката")
    public void shouldCreateOrder() {
        OrderSteps orderSteps = new OrderSteps();
        String[] testColor = {firstColour, secondColour};
        Order order = new Order("Имя",
                "Фамилия",
                "Забугорная, 165",
                4,
                "88002000600",
                2,
                "2024-12-23",
                "Обширный коммаентарий",
                testColor);

        orderSteps
                .createOrder(order)
                .statusCode(201)
                .body("track", Matchers.notNullValue());

    }
}