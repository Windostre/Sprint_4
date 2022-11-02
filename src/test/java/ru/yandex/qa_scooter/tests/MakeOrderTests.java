package ru.yandex.qa_scooter.tests;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import ru.yandex.qa_scooter.helpers.BrowserRules;
import ru.yandex.qa_scooter.pom_pages.HomePage;
import ru.yandex.qa_scooter.pom_pages.OrderPage;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static ru.yandex.qa_scooter.helpers.Constants.*;

public class MakeOrderTests {

   @Rule
   public BrowserRules browserRules = new BrowserRules(FIRE_FOX);

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
        objOrderPage.setDeliveryDate(INDEX); //нет требования макс дню доставки
        objOrderPage.selectRentPeriod(INDEX);
        objOrderPage.selectScooterColor(GREY_COLOR);
        objOrderPage.makeComment("Какой чудесный день");
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
            objOrderPage.setDeliveryDate(INDEX); //нет требования макс дню доставки
            objOrderPage.selectRentPeriod(INDEX);
            objOrderPage.selectScooterColor(BLACK_COLOR);
            objOrderPage.makeComment("Какой чудесный день");
            objOrderPage.clickMakeOrderButton();
            objOrderPage.waitForConfirmationOrderMessage();
            objOrderPage.submitOrder();
            boolean isStatusOrderInfoAvailable = objOrderPage.orderIsMade();
            String orderMessage = objOrderPage.getOrderMessage();

            assertEquals(isStatusOrderInfoAvailable, true);
            assertThat(orderMessage, allOf(startsWith("Заказ оформлен"), containsString("Номер заказа")));
        }

    }
