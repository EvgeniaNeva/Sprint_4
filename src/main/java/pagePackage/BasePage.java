package pagePackage;

import org.openqa.selenium.WebDriver;

public class BasePage {
    // Драйвер для страницы заказа самоката
    public final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
}
