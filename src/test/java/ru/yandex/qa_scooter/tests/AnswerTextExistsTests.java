package ru.yandex.qa_scooter.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.qa_scooter.pom_pages.HomePage;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class AnswerTextExistsTests {

    private final int questionNumber;
    private final boolean hasText;
    private WebDriver driver;

    public AnswerTextExistsTests(int questionNumber, boolean hasText) {
        this.questionNumber = questionNumber;
        this.hasText = hasText;
    }

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Webdrivers\\chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "C:\\Webdrivers\\geckodriver.exe");
        //driver = new ChromeDriver();
        driver = new FirefoxDriver();

    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Parameterized.Parameters(name = "номер вопроса = {0}, Наличие текста в ответе = {1} ")
    public static Object[][] getDataSet() {
        return new Object[][] {
                {0, true},
                {1, true},
                {2, true},
                {3, true},
                {4, true},
                {5, true},
                {6, true},
                {7, true},

        };
    }
    @Test
    public void importantQuestionHasVisibleText() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        HomePage objHomePage = new HomePage(driver);
        objHomePage.goToHomePage();
        objHomePage.scrollDown();
        objHomePage.waitForFAQListLoad();
        objHomePage.clickQuestionOfFAQ(questionNumber);
        String text =  objHomePage.getAnswerOfFAQ(questionNumber);
        boolean actual = !text.isBlank();
        assertEquals(actual,hasText);


    }
}
