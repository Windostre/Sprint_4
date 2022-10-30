package ru.yandex.qa_scooter.pom_pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Описание страницы заказа
 */
public class OrderPage {
    private WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }
    //Данные клиента
    //Форма заказа
    private  By deliveryForm = By.xpath("//div[contains(text(),'Для кого самокат')]");

    //имя на форме заказа
    private  By clientFirstName = By.xpath(".//input[contains(@placeholder,'Имя')]");
    //фамилия на форме заказа
    private  By clientSecondName = By.xpath(".//input[contains(@placeholder,'Фамилия')]");
    //адрес доставки
    private  By clientAddress = By.xpath(".//input[contains(@placeholder,'Адрес')]");
    //метро текст
    private  By inputMetro = By.xpath(".//input[contains(@placeholder,'Станция метро')]");
    //метро клик
    private  By listMetroStations = By.className("select-search__select");
    //телефон для связи
    private  By telNumber = By.xpath(".//input[contains(@placeholder,'Телефон')]");
    //кнопка далее
    private  By nextButton = By.xpath(".//div[contains(@class,'Order_NextButton')]/button");

    //Данные по аренде
    //поле периодя
    private final By rentPeriod = By.className("Dropdown-control");
    //списко доступных периодов
    private final By rentDay = By.xpath(".//div[@class='Dropdown-option' and text()='сутки']");
    //поле даты доставки
    private final By deliveryDate = By.xpath(".//input[contains(@placeholder,'Когда привезти самокат')]");
    //выбор цвета черный жемчужный
    private final By colourBlack = By.cssSelector("input#black");
    //выбор цвета черный жемчужный
    private final By colourGrey = By.cssSelector("input#grey");
    //комментарий
    private final By commentForDelivery = By.xpath(".//input[contains(@placeholder,'Комментарий')]");
    //кнопка Заказать
    private final By makeOrderButton = By.xpath(".//div[contains(@class,'Order_Buttons')]/button[text()='Заказать']");
    //Форма подтверждения заказа
    private final By orderSubmitForm = By.xpath(".//div[contains(@class,'Order_ModalHeader')]");
    //Кнопка подтверждения заказа
    private final By submitOrderButton = By.xpath(".//div[contains(@class,'Order_Buttons')]/button[text()='Да']");
    //Окно с номером заказа
    //Информация о статусе заказа (номер и статус сливаются в один текст - грустно)
    private final By statusOrderInfo = By.xpath(".//div[contains(@class,'Order_ModalHeader')]");
    //Кнопка Посмотреть татус
    private final By orderStatusButton = By.xpath(".//div[contains(@class, 'Order_NextButton')]/button[text()='Посмотреть статус']");


    //Заполение формы Для Кого Заказ
    public void fillClientInfoForm(String firstName
            ,String secondName
            ,String address
            ,String telephone
            ,String metroStation) {
        driver.findElement(clientFirstName).clear();
        driver.findElement(clientFirstName).sendKeys(firstName);
        driver.findElement(clientSecondName).clear();
        driver.findElement(clientSecondName).sendKeys(secondName);
        driver.findElement(clientAddress).clear();
        driver.findElement(clientAddress).sendKeys(address);
        driver.findElement(telNumber).clear();
        driver.findElement(telNumber).sendKeys(telephone);
        driver.findElement(inputMetro).click();
        driver.findElement(inputMetro).sendKeys(metroStation);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        driver.findElement(inputMetro).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(inputMetro).sendKeys(Keys.ENTER);
    }

    public void pressNext() {
        driver.findElement(nextButton).click();
    }

    public void fillRentInfoForm(String comment) {
        //установка текущей даты
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String data = df.format(new Date());
        driver.findElement(deliveryDate).clear();
        driver.findElement(deliveryDate).sendKeys(data);
        driver.findElement(deliveryDate).sendKeys(Keys.ENTER);
        //выбор срок аренды
        driver.findElement(rentPeriod).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(rentDay).click();
        driver.findElement(colourGrey).click();
        driver.findElement(commentForDelivery).sendKeys(comment);

    }

    public void clickMakeOrderButton() {
        driver.findElement(makeOrderButton).click();
    }
    public void waitForConfirmationOrderMessage(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(orderSubmitForm));
    }

    public void submitOrder() {
        driver.findElement(submitOrderButton).click();
    }

    public boolean orderIsMade(){
        return driver.findElement(statusOrderInfo).isEnabled();
    }

    public String getOrderMessage() {
        if(!orderIsMade()) {
           return null;
        }
        return driver.findElement(statusOrderInfo).getText();
    }
}
