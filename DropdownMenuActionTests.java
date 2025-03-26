package ui;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static steps.WebFormSteps.openDropdownMenuPage;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class DropdownMenuActionTests {
    WebDriver driver;
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/index.html";

    @BeforeEach
    void start() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
        openDropdownMenuPage((WebElement) driver);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    @Order(1)
    void useLeftClickTest() throws InterruptedException {
        Actions actions = new Actions(driver);
        WebElement dropdownLeftClick = driver.findElement(By.id("my-dropdown-1"));
        actions.click(dropdownLeftClick).perform();
        Thread.sleep(2000);

        WebElement dropdownMenuOption = driver.findElement(By.xpath("//ul[@class = 'dropdown-menu show']//a[text() = 'Action']"));
        assertEquals("Action", dropdownMenuOption.getDomProperty("text"));
        dropdownMenuOption.click();
        Thread.sleep(2000);

        actions.contextClick(dropdownLeftClick).perform();
        assertFalse(dropdownMenuOption.isSelected());
    }

    @Test
    @Order(2)
    void useRightClickTest() throws InterruptedException {
        Actions actions = new Actions(driver);
        WebElement dropdownRightClick = driver.findElement(By.id("my-dropdown-2"));
        actions.contextClick(dropdownRightClick).perform();
        Thread.sleep(2000);

        WebElement dropdownMenuOption = driver.findElement(By.xpath("//ul[@id = 'context-menu-2']//a[text() = 'Action']"));
        assertEquals("Action", dropdownMenuOption.getDomProperty("text"));
        dropdownMenuOption.click();
        Thread.sleep(2000);

        actions.click(dropdownRightClick).perform();
        assertFalse(dropdownMenuOption.isSelected());
    }

    @Test
    @Order(3)
    void useDoubleClickTest() throws InterruptedException {
        Actions actions = new Actions(driver);
        WebElement dropdownDoubleClick = driver.findElement(By.id("my-dropdown-3"));
        actions.doubleClick(dropdownDoubleClick).perform();
        Thread.sleep(2000);

        WebElement dropdownMenuOption = driver.findElement(By.xpath("//ul[@id = 'context-menu-3']//a[text() = 'Action']"));
        assertEquals("Action", dropdownMenuOption.getDomProperty("text"));
        dropdownMenuOption.click();
        Thread.sleep(2000);

        actions.click(dropdownDoubleClick).perform();
        assertFalse(dropdownMenuOption.isSelected());
        Thread.sleep(2000);

        actions.contextClick(dropdownDoubleClick).perform();
        assertFalse(dropdownMenuOption.isSelected());
    }
}
