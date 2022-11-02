package ru.yandex.qa_scooter.helpers;

import org.junit.After;
import org.junit.Before;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.Objects;

public class BrowserRules extends ExternalResource {
    private WebDriver driver;
    private String browser;

    public BrowserRules(String browser) {
        this.browser = browser;
    }

    public WebDriver getDriver() {
        return driver;
    }

    @Override
    protected void before() throws Throwable {

        if("chrome".equals(browser)) {
            driver = new ChromeDriver();
        } else if ("ff".equals(browser)) {
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Override
    protected void after() {
        driver.quit();
    }
}
