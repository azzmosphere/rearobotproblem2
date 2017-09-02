package au.azzmosphere.integration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class test1 {

    public static void main(String args[]) {
        WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:8080/");
        driver.findElement(By.id("connect")).click();

        driver.close();
        driver.quit();
    }
}
