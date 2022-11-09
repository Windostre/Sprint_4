package ru.yandex.qa_scooter.pom_pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static ru.yandex.qa_scooter.helpers.Constants.BLACK_COLOR;
import static ru.yandex.qa_scooter.helpers.Constants.GREY_COLOR;

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
    private final By rentField = By.xpath(".//div[contains(text(),'Срок аренды')] ");

    private final By rentPeriodList = By.className("Dropdown-option");
    //поле даты доставки
    private final By deliveryDate = By.xpath(".//input[contains(@placeholder,'Когда привезти самокат')]");
    //чекбокс выбора цветов
    private final By colorCheckbox = By.xpath(".//input[@type ='checkbox']");
    //чекбокс выбора черный жемчужный
    private final By colourBlack = By.cssSelector("input#black");
    //выбор цвета серая безисходность
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
        driver.findElement(inputMetro).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(inputMetro).sendKeys(Keys.ENTER);
    }

    public void pressNext() {
        driver.findElement(nextButton).click();
    }

    public void setDeliveryDate(int dayFromToday) {
        Calendar calendar = GregorianCalendar.getInstance();

        calendar.add(Calendar.DAY_OF_MONTH, dayFromToday); //менять в случа изменения дня доставки
        Date newDate = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String newDateAsString = dateFormat.format(newDate);
        driver.findElement(deliveryDate).clear();
        driver.findElement(deliveryDate).sendKeys(newDateAsString);
        driver.findElement(deliveryDate).sendKeys(Keys.ENTER);
    }

    public void selectRentPeriod(int index) {
        driver.findElement(rentField).click();
        List<WebElement> elements = driver.findElements(rentPeriodList);
        elements.get(index).click();
    }
    public void selectScooterColor(String colour) {

        if(GREY_COLOR.equals(colour)) {
            driver.findElement(colourGrey).click();
        } else if (BLACK_COLOR.equals(colour)) {
            driver.findElement(colourBlack).click();
        }

    }

    public void makeComment(String comment) {
       if(comment != null) {
           driver.findElement(commentForDelivery).sendKeys(comment);
       } else driver.findElement(commentForDelivery).clear();

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
