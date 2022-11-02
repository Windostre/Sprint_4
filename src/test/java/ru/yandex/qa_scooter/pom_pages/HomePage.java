package ru.yandex.qa_scooter.pom_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Описание главной страницы приложение Самокат
 */
public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private By headerOrderButton = By.xpath(".//button[contains(@class, 'Button_Button') and (text()='Заказать')][1]");
    private By bottomOrderButton = By.xpath(".//button[contains(@class, 'Button_Middle')]");
    private By questionList = By.className("accordion__heading"); // был accordion__button
    private By answerList = By.className("accordion__panel");

    public void scrollDown() {
        ((JavascriptExecutor)driver)
                .executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("accordion__heading-7")));
    }

    public void waitForFAQListLoad() {
        ((JavascriptExecutor)driver)
                .executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("accordion__heading-7")));
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(questionList));
    }

    public void clickQuestionOfFAQ(int questionNumber) {
        List<WebElement> elements = driver.findElements(questionList);
       WebElement element = elements.get(questionNumber);
       element.click();
    }

    public String getAnswerOfFAQ(int questionNumber) {
        List<WebElement> elements = driver.findElements(answerList);
        return elements.get(questionNumber).getText();
    }

    public void goToHomePage() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    public void clickHeaderOrderButton(){
        driver.findElement(headerOrderButton).click();
    }
    public void clickBottomOrderButton(){
        ((JavascriptExecutor)driver)
                .executeScript("arguments[0].scrollIntoView();", driver.findElement(bottomOrderButton));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(bottomOrderButton).click();
    }




}
