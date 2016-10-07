package org.testobject.appium.testng;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testobject.rest.api.appium.common.TestObjectCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

@Listeners({ TestObjectTestNGTestResultWatcher.class })
public class AppiumDriverCalculatorWatcherTestTestNG implements TestObjectWatcherProvider {

    private AppiumDriver driver;

    @BeforeMethod
    public void beforeTest() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("testobject_api_key", "7CDE94EFFE3E4EF4A773DB2728688C53");
        capabilities.setCapability(TestObjectCapabilities.TESTOBJECT_DEVICE, "Motorola_Moto_E_2nd_gen_real");

        driver = new AndroidDriver(new URL("https://app.testobject.com:443/api/appium/wd/hub"), capabilities);

    }

    @Test
    public void twoPlusTwoOperation() {

        MobileElement buttonTwo = (MobileElement)(driver.findElement(By.id("net.ludeke.calculator:id/digit2")));
        MobileElement buttonPlus = (MobileElement)(driver.findElement(By.id("net.ludeke.calculator:id/plus")));
        MobileElement buttonEquals = (MobileElement)(driver.findElement(By.id("net.ludeke.calculator:id/equal")));
        MobileElement resultField = (MobileElement)(driver.findElement(By.xpath("//android.widget.EditText[1]")));

        buttonTwo.click();
        buttonPlus.click();
        buttonTwo.click();
        buttonEquals.click();

        (new WebDriverWait(driver, 30)).until(ExpectedConditions.textToBePresentInElement(resultField, "4"));

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Override
    public AppiumDriver getDriver() {
        return this.driver;
    }

    @Override
    public URL getApiEndpoint() { return TestObjectCapabilities.TESTOBJECT_API_ENDPOINT; }

    @Override
    public boolean getIsLocalTest() { return false; }
}
