package ru.yandex.qa_scooter.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.qa_scooter.helpers.BrowserRules;
import ru.yandex.qa_scooter.pom_pages.HomePage;
import ru.yandex.qa_scooter.pom_pages.OrderPage;

import java.time.Duration;

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
        objOrderPage.fillRentInfoForm("Какой чудесный день");
        objOrderPage.clickMakeOrderButton();
        objOrderPage.waitForConfirmationOrderMessage();
        objOrderPage.submitOrder();
        boolean isStatusOrderInfoAvailabel = objOrderPage.orderIsMade();
        String orderMessage = objOrderPage.getOrderMessage();

        assertEquals(isStatusOrderInfoAvailabel, true);
        assertThat(orderMessage, allOf(startsWith("Заказ оформлен"), containsString("Номер заказа")));
    }

        @Test
        public void makeOrderBotomButtonPositive() {
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
            objOrderPage.fillRentInfoForm("Какой чудесный день");
            objOrderPage.clickMakeOrderButton();
            objOrderPage.waitForConfirmationOrderMessage();
            objOrderPage.submitOrder();
            boolean isStatusOrderInfoAvailabel = objOrderPage.orderIsMade();
            String orderMessage = objOrderPage.getOrderMessage();

            assertEquals(isStatusOrderInfoAvailabel, true);
            assertThat(orderMessage, allOf(startsWith("Заказ оформлен"), containsString("Номер заказа")));
        }

    }
