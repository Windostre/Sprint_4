package ru.yandex.qa_scooter.tests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.qa_scooter.helpers.BrowserRules;
import ru.yandex.qa_scooter.pom_pages.HomePage;

import static org.junit.Assert.assertTrue;
import static ru.yandex.qa_scooter.helpers.Constants.FIRE_FOX;

@RunWith(Parameterized.class)
public class AnswerTextExistsTests {
    @Rule
    public BrowserRules browserRules = new BrowserRules(FIRE_FOX);


    private final int questionNumber;

    public AnswerTextExistsTests(int questionNumber) {
        this.questionNumber = questionNumber;

    }

    @Parameterized.Parameters(name = "номер вопроса = {0}")
    public static Object[][] getDataSet() {
        return new Object[][]{
                {0},
                {1},
                {2},
                {3},
                {4},
                {5},
                {6},
                {7},

        };
    }

    @Test
    public void importantQuestionHasVisibleText() {
        HomePage objHomePage = new HomePage(browserRules.getDriver());
        objHomePage.goToHomePage();
        objHomePage.waitForFAQListLoad();
        objHomePage.clickQuestionOfFAQ(questionNumber);
        String text = objHomePage.getAnswerOfFAQ(questionNumber);

        boolean hasAnswer = !text.isBlank();
        assertTrue(hasAnswer);


    }
}
