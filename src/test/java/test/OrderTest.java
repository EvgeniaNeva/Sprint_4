package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pagePackage.ScooterPage;
import pagePackage.OrderPage;


@RunWith(Parameterized.class)
public class OrderTest {
    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;
    private final String desiredDate;
    private final String rentPeriod;
    private final String colour;
    private final String comment;
    private final String expect;
    public WebDriver driver;
    public ScooterPage scooterPage;
    public OrderPage orderPage;

    //Конструктор класса
    public OrderTest(String name, String surname, String address,
                     String metroStation, String phoneNumber, String desiredDate,
                     String rentPeriod, String colour, String comment, String expect) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.desiredDate = desiredDate;
        this.rentPeriod = rentPeriod;
        this.colour = colour;
        this.comment = comment;
        this.expect = expect;
    }

    @Parameterized.Parameters //Параметры для теста
    public static Object[][] getOrder() {
        return new Object[][]{
                {"Маруся", "Иванова", "улица Елецкая", "Домодедовская", "12345678910", "16.06.2024", "1", "black", "Позвоните за час", "Заказ оформлен"},
                {"Олег", "Яблочкин", "улица Ленина", "Алексеевская", "99876543211", "17.06.2024", "2", "grey", "Оставьте у подъезда", "Заказ оформлен"},
        };
    }

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        scooterPage = new ScooterPage(driver);
        orderPage = new OrderPage(driver);
        scooterPage.openURL();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test //Кейс с использованием кнопки "Заказать", которая находится вверху страницы
    public void makeOrderAndCheckResultByTopButton() {
        scooterPage.clickTopOrderButton();
        orderPage.setCustomerData(name, surname, address, metroStation, phoneNumber);
        orderPage.clickNextButton();
        orderPage.setRentData(desiredDate, rentPeriod, colour, comment);
        orderPage.clickOrderButton();
        orderPage.clickYesButton();
        orderPage.checkOrderStatus(expect);
    }

    @Test //Кейс с использованием кнопки "Заказать", которая находится внизу страницы
    public void makeOrderAndCheckResultByDownButton() {
        scooterPage.clickDownOrderButton();
        orderPage.setCustomerData(name, surname, address, metroStation, phoneNumber);
        orderPage.clickNextButton();
        orderPage.setRentData(desiredDate, rentPeriod, colour, comment);
        orderPage.clickOrderButton();
        orderPage.clickYesButton();
        orderPage.checkOrderStatus(expect);
    }
}
