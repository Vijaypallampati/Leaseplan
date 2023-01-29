package com.auto.net.helpers;

import com.auto.net.config.GuiProps;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Slf4j
public class GuiElement {
    private final WebDriver driver;
    private final int EXPLICIT_WAIT;
    private final int IMPLICIT_WAIT;
    private final WebDriverWait wait;

    public GuiElement(WebDriver driver, GuiProps guiProps) {
        this.driver = driver;
        this.EXPLICIT_WAIT = guiProps.getExplicitWait();
        this.IMPLICIT_WAIT = guiProps.getImplicitWait();
        wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
    }


    private ExpectedCondition<WebElement> visibilityOfElementLocated(final By by) throws NoSuchElementException {
        return webDriver -> {

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new NoSuchElementException("No Such Element Exception :" + e.getMessage());
            }
            assert webDriver != null;
            WebElement element = webDriver.findElement(by);
            return element.isDisplayed() ? element : null;
        };
    }


    private WebElement waitForExpectedElement(final By by) {
        try {
            return wait.until(visibilityOfElementLocated(by));
        } catch (NoSuchElementException e) {
            Assert.fail("Fail to waitForExpectedElement : " + by + e.getMessage());
            return null;
        }
    }

    public WebElement visibilityOfElement(WebElement element) {
        try {
            return wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf((element)));
        } catch (Exception e) {
            Assert.fail("Fail to visibilityOfElement : " + e.getMessage());
            return null;
        }
    }

    public WebElement clickableElement(WebElement element) {
        try {
            return wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            Assert.fail("Fail to clickableElement : " + e.getMessage());
            return null;
        }
    }

    public void waitForElementEnabled(final WebElement element) {
        try {
            wait.until((ExpectedCondition<Boolean>) driver -> element.isEnabled());
        } catch (Exception e) {
            Assert.fail("Fail to waitForElementEnabled : " + e.getMessage());
        }
    }

    public void selectItemFromDropdownByVisibleText(WebElement dropdownElement, String value) {
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(value);
    }

}
