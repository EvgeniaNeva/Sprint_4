package pagePackage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScooterPage extends BasePage {
    private final By cookieButton = By.id("rcc-confirm-button");
    private final By topOrderButton = By.xpath(".//button[@class='Button_Button__ra12g']");
    private final By downOrderButton = By.xpath(".//div[@class='Home_FinishButton__1_cWm']");

    public ScooterPage(WebDriver driver) {
        super(driver);
    }

    public ScooterPage openURL() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        return this;
    }
    // Клик верхней кнопки Заказать
    public void clickTopOrderButton() {
        driver.findElement(topOrderButton).click();
    }
    // Закрыть окно с куками
    public void closeCookies() {
        driver.findElement(cookieButton).click();
    }
    // Клик нижней кнопки Заказать
    public void clickDownOrderButton() {
        WebElement button = driver.findElement(downOrderButton);
     ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", button);
        button.click();
    }

    // Нажатие на вопрос
    public void clickQuestion(String questionNum) {
        WebElement question = driver.findElement(By.xpath(".//div[@id='accordion__heading-" + questionNum + "']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", question);
        question.click();
    }
    // Проверка ответа
    public String waitForAnswerAndCheckText(String answerNum) {
        WebElement answerForQuestion = driver.findElement(By.xpath(".//div[@id='accordion__panel-" + answerNum + "']/p"));
        return answerForQuestion.getText();
    }
}


