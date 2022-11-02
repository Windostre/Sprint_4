package ru.yandex.qa_scooter.tests;

import org.junit.Rule;
import org.junit.Test;
import ru.yandex.qa_scooter.helpers.BrowserRules;
import ru.yandex.qa_scooter.pom_pages.HomePage;
import ru.yandex.qa_scooter.pom_pages.OrderPage;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class MakeOrderTests {

   @Rule
   public BrowserRules browserRules = new BrowserRules(FIRE_FOX);

    private static final String CHROME = "chrome";
    private static final String FIRE_FOX = "ff";

    @Test
    public void makeOrderHeaderButtonPositive() {
        HomePage objHomePage = new HomePage(browserRules.getDriver());
        objHomePage.goToHomePage();
        objHomePage.clickHeaderOrderButton();
        OrderPage objOrderPage = new OrderPage(browserRules.getDriver());
        objOrderPage.fillClientInfoForm("Терри"
                , "Праттчет"
                , " Елисеевский переулок дом 2,. корп. 15"
                , "+12345678900"
                , "Сокол");
        objOrderPage.pressNext();
        objOrderPage.setDeliveryDate(2);
        objOrderPage.fillRentInfoForm("Какой чудесный день");
        objOrderPage.clickMakeOrderButton();
        objOrderPage.waitForConfirmationOrderMessage();
        objOrderPage.submitOrder();
        boolean isStatusOrderInfoAvailable = objOrderPage.orderIsMade();
        String orderMessage = objOrderPage.getOrderMessage();

        assertEquals(isStatusOrderInfoAvailable, true);
        assertThat(orderMessage, allOf(startsWith("Заказ оформлен"), containsString("Номер заказа")));
    }

        @Test
        public void makeOrderBottomButtonPositive() {
            HomePage objHomePage = new HomePage(browserRules.getDriver());
            objHomePage.goToHomePage();
            objHomePage.clickBottomOrderButton();
            OrderPage objOrderPage = new OrderPage(browserRules.getDriver());
            objOrderPage.fillClientInfoForm("Терри"
                    , "Праттчет"
                    , " Елисеевский переулок дом 2,. корп. 15"
                    , "+12345678900"
                    , "Сокол");
            objOrderPage.pressNext();
            objOrderPage.setDeliveryDate(0);
            objOrderPage.fillRentInfoForm("Какой чудесный день");
            objOrderPage.clickMakeOrderButton();
            objOrderPage.waitForConfirmationOrderMessage();
            objOrderPage.submitOrder();
            boolean isStatusOrderInfoAvailable = objOrderPage.orderIsMade();
            String orderMessage = objOrderPage.getOrderMessage();

            assertEquals(isStatusOrderInfoAvailable, true);
            assertThat(orderMessage, allOf(startsWith("Заказ оформлен"), containsString("Номер заказа")));
        }

    }
