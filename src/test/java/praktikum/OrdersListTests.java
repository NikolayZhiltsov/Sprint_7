package praktikum;


import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.Matchers;
import org.junit.Test;
import praktikum.steps.OrderSteps;

public class OrdersListTests extends AbstractTest{
    private OrderSteps orderSteps = new OrderSteps();

    @Test
    @DisplayName("Проверяем, что возвращаемый список заказов не пустой")
    public void shouldReturnNotEmptyOrdersList() {
        orderSteps
                .getOrdersList()
                .statusCode(200)
                .body("orders", Matchers.not(Matchers.empty()));
    }
}
