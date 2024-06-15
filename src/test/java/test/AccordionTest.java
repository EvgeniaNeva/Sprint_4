package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pagePackage.ScooterPage;

import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class AccordionTest {
    private final String question;
    private final String answer;
    private final String answerExpect;
    public WebDriver driver;
    ScooterPage scooterPage;

    public AccordionTest(String question, String answer, String answerExpect) {
        this.question = question;
        this.answer = answer;
        this.answerExpect = answerExpect;
    }

    //Параметры вопросов и ответов
    @Parameterized.Parameters
    public static Object[][] getQuestion() {
        return new Object[][]{
                {"0", "0", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"1", "1", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"2", "2", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"3", "3", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"4", "4", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"5", "5", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"6", "6", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"7", "7", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Before
    public void setup() {
        driver = new ChromeDriver();
        scooterPage = new ScooterPage(driver);
        scooterPage.openURL().closeCookies(); //Переход на страницу сервиса заказа самоката

    }

    @After
    public void tearDown() {
        driver.quit(); //Закрытие браузера
    }

    @Test
    public void checkAnswerAndQuestion() {
        scooterPage.clickQuestion(question); //Клик по вопросу с номером
        String result = scooterPage.waitForAnswerAndCheckText(answer); //Текст ответа, кладем в переменную
        assertEquals("Неверный текст ответа на вопрос", answerExpect, result); //Проверка текста ответа
    }
}