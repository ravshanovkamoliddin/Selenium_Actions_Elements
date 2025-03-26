package ui;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.*;
import static steps.WebFormSteps.openNavigationPage;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NavigationActionsTests {
    WebDriver driver;
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/index.html";

    @BeforeEach
    void start() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
        openNavigationPage((WebElement) driver);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    @Order(1)
    void headingTextTest() {
        WebElement headingText = driver.findElement(By.className("display-4"));

        assertEquals("Hands-On Selenium WebDriver with Java", headingText.getText());
    }

    @Test
    @Order(2)
    void titleTextTest() {
        WebElement titleText = driver.findElement(By.tagName("h5"));

        assertEquals("Practice site", titleText.getText());
    }

    @Test
    @Order(3)
    void imageIconTest() throws InterruptedException {
        WebElement imageIcon = driver.findElement(By.xpath("//img[@src = 'img/hands-on-icon.png']"));

        int width = imageIcon.getSize().getWidth();
        int hight = imageIcon.getSize().getHeight();

        System.out.println(width +">>>" + hight);

        assertTrue(imageIcon.isDisplayed());
        assertEquals(80, width);
        Thread.sleep(2000);

        imageIcon.click();
        String currentUrl = driver.getCurrentUrl();
        assertEquals("https://github.com/bonigarcia/selenium-webdriver-java", currentUrl);
        Thread.sleep(2000);
    }

    @Test
    @Order(4)
    void navigationTitleTextTest() {
        WebElement navigationTitleText = driver.findElement(By.xpath("//h1[@class = 'display-6']"));

        assertEquals("Navigation example", navigationTitleText.getText());
    }

    @Test
    @Order(5)
    void navigationTextTest() throws InterruptedException {
        WebElement navigationText = driver.findElement(By.xpath("//p[@class = 'lead' and normalize-space(text()) = 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.']"));

        assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                navigationText.getText());
        Thread.sleep(2000);
    }

    @Test
    @Order(6)
    void backToIndexLinkTest() throws InterruptedException {
        WebElement backToIndexLink = driver.findElement(By.xpath("//a[@href = 'index.html']"));
        backToIndexLink.click();
        Thread.sleep(2000);

        assertEquals("https://bonigarcia.dev/selenium-webdriver-java/index.html", driver.getCurrentUrl());
    }

    @Test
    @Order(7)
    void previousButtonTest() throws InterruptedException {
        WebElement previousButton = driver.findElement(By.xpath("//a[@class = 'page-link' and text() = 'Previous']"));
        WebElement currentTextPage = driver.findElement(By.xpath("//p[@class = 'lead']"));

        assertTrue(previousButton.isDisplayed());
        assertTrue(previousButton.isEnabled());
        assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                currentTextPage.getText());
        Thread.sleep(2000);
    }

    @Test
    @Order(8)
    void firstButtonTest() throws InterruptedException {
        WebElement firstButton = driver.findElement(By.xpath("//a[@href = 'navigation1.html']"));
        WebElement firstText = driver.findElement(By.xpath("//p[@class = 'lead']"));

        assertTrue(firstButton.isDisplayed());
        assertTrue(firstButton.isEnabled());
        assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                firstText.getText());
        Thread.sleep(2000);
    }

    @Test
    @Order(9)
    void secondButtonTest() throws InterruptedException {
        WebElement secondButton = driver.findElement(By.xpath("//a[@href = 'navigation2.html']"));

        new Actions(driver).click(secondButton).perform();
        Thread.sleep(2000);
        assertEquals("https://bonigarcia.dev/selenium-webdriver-java/navigation2.html", driver.getCurrentUrl());

        WebElement secondPageText = driver.findElement(By.xpath("//p[@class = 'lead']"));
        assertEquals("Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.",
                secondPageText.getText());
    }

    @Test
    @Order(10)
    void thirdButtonTest() throws InterruptedException {
        WebElement thirdButton = driver.findElement(By.xpath("//a[@href = 'navigation3.html']"));

        new Actions(driver).click(thirdButton).perform();
        Thread.sleep(2000);
        assertEquals("https://bonigarcia.dev/selenium-webdriver-java/navigation3.html", driver.getCurrentUrl());

        WebElement thirdPageText = driver.findElement(By.xpath("//p[@class = 'lead']"));
        assertEquals("Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                thirdPageText.getText());
    }

    @Test
    @Order(11)
    void nextButtonNavigationTest() throws InterruptedException {
        WebElement nextButtonExist = driver.findElement(By.xpath("//a[@class = 'page-link' and text() = 'Next']"));
        WebElement firstText = driver.findElement(By.xpath("//p[@class = 'lead']"));
        assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                firstText.getText());
        Thread.sleep(2000);

        assertTrue(nextButtonExist.isDisplayed());
        assertTrue(nextButtonExist.isEnabled());
        Thread.sleep(2000);

        Actions actions = new Actions(driver);
        WebElement nextButton1 = driver.findElement(By.xpath("//a[@class = 'page-link' and text() = 'Next']"));
        actions.click(nextButton1).perform();
        assertEquals("https://bonigarcia.dev/selenium-webdriver-java/navigation2.html", driver.getCurrentUrl());
        WebElement secondPageText = driver.findElement(By.xpath("//p[@class = 'lead']"));
        assertEquals("Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.",
                secondPageText.getText());
        Thread.sleep(2000);

        WebElement nextButton2 = driver.findElement(By.xpath("//a[@class = 'page-link' and text() = 'Next']"));

        actions.click(nextButton2).perform();
        assertEquals("https://bonigarcia.dev/selenium-webdriver-java/navigation3.html", driver.getCurrentUrl());
        WebElement thirdPageText = driver.findElement(By.xpath("//p[@class = 'lead']"));
        assertEquals("Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                thirdPageText.getText());
        Thread.sleep(2000);
    }

    @Test
    @Order(12)
    void previousButtonNavigationTest() throws InterruptedException {
        driver.findElement(By.xpath("//a[@href = 'navigation3.html']")).click();
        WebElement thirdPageText = driver.findElement(By.xpath("//p[@class = 'lead']"));
        assertEquals("Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                thirdPageText.getText());

        WebElement previousButtonExist = driver.findElement(By.xpath("//a[@class = 'page-link' and text() = 'Previous']"));

        assertTrue(previousButtonExist.isDisplayed());
        assertTrue(previousButtonExist.isEnabled());
        Thread.sleep(2000);

        Actions actions = new Actions(driver);
        WebElement previousButton1 = driver.findElement(By.xpath("//a[@class = 'page-link' and text() = 'Previous']"));
        actions.click(previousButton1).perform();
        assertEquals("https://bonigarcia.dev/selenium-webdriver-java/navigation2.html", driver.getCurrentUrl());
        WebElement secondPageText = driver.findElement(By.xpath("//p[@class = 'lead']"));
        assertEquals("Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.",
                secondPageText.getText());
        Thread.sleep(2000);

        WebElement previousButton2 = driver.findElement(By.xpath("//a[@class = 'page-link' and text() = 'Previous']"));

        actions.click(previousButton2).perform();
        assertEquals("https://bonigarcia.dev/selenium-webdriver-java/navigation1.html", driver.getCurrentUrl());
        WebElement firstPageText = driver.findElement(By.xpath("//p[@class = 'lead']"));
        assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                firstPageText.getText());
        Thread.sleep(2000);
    }
}