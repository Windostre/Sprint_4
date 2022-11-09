package ru.yandex.qa_scooter.tests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.qa_scooter.helpers.BrowserRules;
import ru.yandex.qa_scooter.pom_pages.HomePage;

import static org.junit.Assert.assertEquals;
import static ru.yandex.qa_scooter.helpers.Constants.FIRE_FOX;

@RunWith(Parameterized.class)
public class AnswerTextMatchesTests {
    @Rule
    public BrowserRules browserRules = new BrowserRules(FIRE_FOX);

    private final int questionNumber;
    private final String expectedAnswer;
    private WebDriver driver;

    public AnswerTextMatchesTests(int questionNumber, String expectedAnswer) {
        this.questionNumber = questionNumber;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters(name = "номер вопроса = {0}, ответ = {1} ")
    public static Object[][] getDataSet() {
        return new Object[][]{
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},

        };
    }

    @Test
    public void importantQuestionHasVisibleText() {
        HomePage objHomePage = new HomePage(browserRules.getDriver());
        objHomePage.goToHomePage();
        objHomePage.waitForFAQListLoad();
        objHomePage.clickQuestionOfFAQ(questionNumber);
        String actualAnswer = objHomePage.getAnswerOfFAQ(questionNumber);
        assertEquals(actualAnswer, expectedAnswer);
        
    }
}
