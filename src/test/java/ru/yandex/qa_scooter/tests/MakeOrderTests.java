package ru.yandex.qa_scooter.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.qa_scooter.pom_pages.HomePage;
import ru.yandex.qa_scooter.pom_pages.OrderPage;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class MakeOrderTests {
    private WebDriver driver;
    @Before
    public void setUp() {
       // System.setProperty("webdriver.chrome.driver", "C:\\Webdrivers\\chromedriver.exe");
       // System.setProperty("webdriver.gecko.driver", "C:\\Webdrivers\\geckodriver.exe");
        //driver = new ChromeDriver();
        driver = new FirefoxDriver();

    }
   @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void makeOrderHeaderButtonPositive() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        HomePage objHomePage = new HomePage(driver);
        objHomePage.goToHomePage();
        objHomePage.clickHeaderOrderButton();
        OrderPage objOrderPage = new OrderPage(driver);
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
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            HomePage objHomePage = new HomePage(driver);
            objHomePage.goToHomePage();
            objHomePage.clickBottomOrderButton();
            OrderPage objOrderPage = new OrderPage(driver);
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
