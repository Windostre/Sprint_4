package ru.yandex.qa_scooter.tests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.qa_scooter.helpers.BrowserRules;
import ru.yandex.qa_scooter.pom_pages.HomePage;
import ru.yandex.qa_scooter.pom_pages.OrderPage;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ru.yandex.qa_scooter.helpers.Constants.*;
@RunWith(Parameterized.class)
public class MakeOrderTests {
    private final String firstName;
    private final String secondName;
    private final String address;
    private final String telephone;
    private final String metroStation;
    private final String comment;
    private final String color;

   @Rule
   public BrowserRules browserRules = new BrowserRules(FIRE_FOX);
    public MakeOrderTests(String firstName, String secondName, String address, String telephone, String metroStation, String comment, String color) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.address = address;
        this.telephone = telephone;
        this.metroStation = metroStation;
        this.comment = comment;
        this.color = color;
    }
    @Parameterized.Parameters(name = "Имя = {0}, ответ = {1}, Адрес = {2}, Телефон = {3}, Метро = {4}, Комментарий = {5}, Цвет = {1} ")
    public static Object[][] getDataSet() {
        return new Object[][] {
                {"ли", "ЛУ","улица","+1234567890", "Бульвар Роокоссовского", "Hello, words!", GREY_COLOR},
                {"Ангваснэзомтэка", "Бранденбургская", "Центральный пр. Хорошёвского Серебряного Бора, д5", "+123456789012", "Лихоборы" ,null, BLACK_COLOR}

        };
    }

    @Test
    public void makeOrderHeaderButtonPositive() {
        HomePage objHomePage = new HomePage(browserRules.getDriver());
        objHomePage.goToHomePage();
        objHomePage.clickHeaderOrderButton();
        OrderPage objOrderPage = new OrderPage(browserRules.getDriver());
        objOrderPage.fillClientInfoForm(firstName
                , secondName
                , address
                , telephone
                , metroStation);
        objOrderPage.pressNext();
        objOrderPage.setDeliveryDate(INDEX); //нет требования макс дню доставки
        objOrderPage.selectRentPeriod(INDEX);
        objOrderPage.selectScooterColor(color);
        objOrderPage.makeComment(comment);
        objOrderPage.clickMakeOrderButton();
        objOrderPage.waitForConfirmationOrderMessage();
        objOrderPage.submitOrder();
        boolean isStatusOrderInfoAvailable = objOrderPage.orderIsMade();
        String orderMessage = objOrderPage.getOrderMessage();

        assertTrue(isStatusOrderInfoAvailable);
        assertThat(orderMessage, allOf(startsWith("Заказ оформлен"), containsString("Номер заказа")));
    }

        @Test
        public void makeOrderBottomButtonPositive() {
            HomePage objHomePage = new HomePage(browserRules.getDriver());
            objHomePage.goToHomePage();
            objHomePage.clickBottomOrderButton();
            OrderPage objOrderPage = new OrderPage(browserRules.getDriver());
            objOrderPage.fillClientInfoForm(firstName
                    , secondName
                    , address
                    , telephone
                    , metroStation);
            objOrderPage.pressNext();
            objOrderPage.setDeliveryDate(INDEX); //нет требования макс дню доставки
            objOrderPage.selectRentPeriod(INDEX);
            objOrderPage.selectScooterColor(color);
            objOrderPage.makeComment(comment);
            objOrderPage.clickMakeOrderButton();
            objOrderPage.waitForConfirmationOrderMessage();
            objOrderPage.submitOrder();
            boolean isStatusOrderInfoAvailable = objOrderPage.orderIsMade();
            String orderMessage = objOrderPage.getOrderMessage();

            assertTrue(isStatusOrderInfoAvailable);
            assertThat(orderMessage, allOf(startsWith("Заказ оформлен"), containsString("Номер заказа")));
        }

    }
