package org.selenium.pom.factory.abstractFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverManagerAbstract extends DriverManagerAbstract {

    @Override
    protected void startDriver() {
        WebDriverManager.firefoxdriver().cachePath("Drivers").setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }
}
