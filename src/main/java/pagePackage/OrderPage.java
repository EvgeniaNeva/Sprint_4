package pagePackage;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage extends BasePage {

    //Поле для Имени
    private final By nameField = By.xpath(".//input[@placeholder='* Имя']");

    // Поле для Фамилии
    private final By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");

    // Поле для Адреса
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    // Поле для Станции метро
    private final By metroStationField = By.xpath(".//div[@Class='select-search']");

    // Поле для Номера телефона
    private final By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    // Кнопка Далее
    private final By nextButton = By.xpath(".//button[text()='Далее']");

    // Поле выбора Даты
    private final By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

    // Выбор срока аренды
    private final By dropDownInRentInput = By.xpath(".//span[@class='Dropdown-arrow']");

    // Комментарий для курьера
    private final By commentForCourier = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    // Кнопка Заказать
    private final By orderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[2]");

    // Кнопка Да
    private final By yesButton = By.xpath(".//button[text()='Да']");

    // Заказ оформлен
    private final By orderStatusInfo = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ']");


    public OrderPage(WebDriver driver) {
        super(driver);
    }

    // Метод ввода Данных о заказчике

    public void setCustomerData(String name, String surname, String address, String metroStation, String phoneNumber) {
        //driver.findElement(nameInput).click();
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(surnameField).sendKeys(surname);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(metroStationField).click();
        WebElement station = driver.findElement(By.xpath(".//div[text()='" + metroStation + "']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", station);
        station.click();
        driver.findElement(phoneField).sendKeys(phoneNumber);
    }

    // Метод для клика на Кнопку "Далее"
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    // Метод ввода Данных о заказе

    public void setRentData(String desiredDate, String Period, String colour, String comment) {
        driver.findElement(dateField).sendKeys(desiredDate);
        driver.findElement(dateField).click();
        driver.findElement(dropDownInRentInput).click();
        WebElement rentPeriod = driver.findElement(By.xpath(".//div[@class='Dropdown-menu']/div[" + Period + "]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", rentPeriod);
        rentPeriod.click();
        WebElement scooterColour = driver.findElement(By.xpath(".//label[@for='" + colour + "']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scooterColour);
        scooterColour.click();
        driver.findElement(commentForCourier).sendKeys(comment);
    }

    // Метод для клика на Кнопку "Заказать"

    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    // Метод для клика на Кнопку "Да"
    public void clickYesButton() {
        driver.findElement(yesButton).click();
    }

    // Метод проверки, что Заказ оформлен
    public void checkOrderStatus(String expect) {
        Duration duration = Duration.ofSeconds(5);
        try {
            new WebDriverWait(driver, duration).until(driver -> (driver.findElement(orderStatusInfo).getText().contains(expect)));
        } catch (TimeoutException e) {
            System.out.println("Ошибка!!!");
        } finally {
            new WebDriverWait(driver, duration).until(driver -> (driver.findElement(orderStatusInfo).getText().contains(expect)));
        }
    }
}
