package ui;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WebFormActionsTests {
    WebDriver driver;
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";
    private static final String BIG_TEXT = "Lorem ipsum dolor sit amet consectetur adipiscing elit habitant metus, " +
            "tincidunt maecenas posuere sollicitudin augue duis bibendum mauris eu, et dignissim magna ad nascetur suspendisse quis nunc. " +
            "Fames est ligula molestie aliquam pretium bibendum nullam, sociosqu maecenas mus etiam consequat ornare leo, sem mattis " +
            "varius luctus litora senectus. Parturient quis tristique erat natoque tortor nascetur, primis augue vivamus habitasse " +
            "senectus porta leo, aenean potenti ante a nam.";

    @BeforeEach
    void start() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
        openWebFormPage(driver);
    }

    private void openWebFormPage(WebDriver driver) {
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
    void webFormTitleTextTest() {
        WebElement webFormTitleText = driver.findElement(By.xpath("//h1[@class = 'display-6']"));

        assertEquals("Web form", webFormTitleText.getText());
    }

    @Test
    @Order(5)
    void textInputTest() throws InterruptedException {
        WebElement textInputField = driver.findElement(By.id("my-text-id"));
        WebElement textInputText = driver.findElement(By.xpath("//label[@class='form-label w-100' and normalize-space(text()) = 'Text input']"));
        textInputField.sendKeys("test string");
        Thread.sleep(2000);

        assertEquals("test string", textInputField.getAttribute("value"));
        assertEquals("Text input", textInputText.getText());
    }

    @Test
    @Order(6)
    void textInputClearTest() throws InterruptedException {
        WebElement textInputField = driver.findElement(By.id("my-text-id"));
        textInputField.sendKeys("test string");
        Thread.sleep(2000);
        textInputField.clear();
        Thread.sleep(2000);

        assertEquals("", textInputField.getAttribute("value"));
    }

    @Test
    @Order(7)
    void passwordInputTest() throws InterruptedException {
        WebElement passwordInputField = driver.findElement(By.name("my-password"));
        WebElement passwordInputText = driver.findElement(By.xpath("//label[@class='form-label w-100' and normalize-space(text()) = 'Password']"));
        passwordInputField.sendKeys("Qwerty123");
        Thread.sleep(2000);

        assertEquals("Qwerty123", passwordInputField.getAttribute("value"));
        assertEquals("Password", passwordInputText.getText());
    }

    @Test
    @Order(8)
    void passwordInputClearTest() throws InterruptedException {
        WebElement passwordInputField = driver.findElement(By.name("my-password"));
        passwordInputField.sendKeys("Qwerty123");
        Thread.sleep(2000);
        passwordInputField.clear();
        Thread.sleep(2000);

        assertEquals("", passwordInputField.getAttribute("value"));
    }

    @Test
    @Order(9)
    void textAreaFieldTest() throws InterruptedException {
        WebElement textAreaInputField = driver.findElement(By.name("my-textarea"));
        WebElement textAreaInputText = driver.findElement(By.xpath("//label[@class='form-label w-100' and normalize-space(text()) = 'Textarea']"));
        textAreaInputField.sendKeys(BIG_TEXT);
        Thread.sleep(2000);

        assertEquals(BIG_TEXT, textAreaInputField.getAttribute("value"));
        assertEquals("Textarea", textAreaInputText.getText());
    }

    @Test
    @Order(10)
    void textAreaFieldClearTest() throws InterruptedException {
        WebElement textAreaInputField = driver.findElement(By.name("my-textarea"));
        textAreaInputField.sendKeys(BIG_TEXT);
        Thread.sleep(2000);
        textAreaInputField.clear();
        Thread.sleep(2000);

        assertEquals("", textAreaInputField.getAttribute("value"));
    }

    @Test
    @Order(11)
    void disabledInputFieldTest() {
        WebElement disabledInputField = driver.findElement(By.name("my-disabled"));
        WebElement disabledInputText = driver.findElement(By.xpath("//label[@class='form-label w-100' and normalize-space(text()) = 'Disabled input']"));

        assertFalse(disabledInputField.isEnabled());
        assertThrows(ElementNotInteractableException.class, () -> disabledInputField.sendKeys("test string"));
        assertEquals("Disabled input", disabledInputField.findElement(By.xpath("..")).getText());
        assertEquals("Disabled input", disabledInputField.getDomAttribute("placeholder"));
        assertEquals("Disabled input", disabledInputText.getText());

        Rectangle rec = disabledInputField.getRect();
        System.out.printf("Dimension %s, Height %s, Width %s, Point %s, X: %s, Y: %s\n", rec.getDimension(), rec.getHeight(),
                rec.getWidth(), rec.getPoint(), rec.getX(), rec.getY());

        //get css values
        System.out.println(disabledInputField.getCssValue("background-color"));
        System.out.println(disabledInputField.getCssValue("opacity"));
        System.out.println(disabledInputField.getCssValue("font-size"));
        System.out.println(disabledInputField.getCssValue("color"));

        System.out.println(disabledInputField.getText());
        System.out.println(disabledInputField.findElement(By.xpath("..")).getText());
        System.out.println(disabledInputField.findElement(By.xpath("..")).getAttribute("innerText"));
        System.out.println(disabledInputField.findElement(By.xpath("..")).getAttribute("textContent"));
    }

    @Test
    @Order(12)
    void readonlyInputFieldTest() {
        WebElement readonlyInputField = driver.findElement(By.name("my-readonly"));
        WebElement readonlyInputText = driver.findElement(By.xpath("//label[@class='form-label w-100' and normalize-space(text()) = 'Readonly input']"));

        assertTrue(readonlyInputField.isEnabled());
        assertEquals("Readonly input", readonlyInputField.findElement(By.xpath("..")).getText());
        assertEquals("Readonly input", readonlyInputField.getDomAttribute("value"));
        readonlyInputField.sendKeys("test string");
        assertNotEquals("test string", readonlyInputField.findElement(By.xpath("..")).getText());
        assertEquals("Readonly input", readonlyInputText.getText());

        System.out.println(readonlyInputField.getText());
        System.out.println(readonlyInputField.findElement(By.xpath("..")).getText());
        System.out.println(readonlyInputField.findElement(By.xpath("..")).getAttribute("innerText"));
        System.out.println(readonlyInputField.findElement(By.xpath("..")).getAttribute("textContent"));
    }

    @Test
    @Order(13)
    void dropdownSelectTest() throws InterruptedException {
        WebElement dropdownSelectMenu = driver.findElement(By.name("my-select"));
        WebElement dropdownSelectText = driver.findElement(By.xpath("//label[@class='form-label w-100' and normalize-space(text()) = 'Dropdown (select)']"));

        Select select = new Select(dropdownSelectMenu);
        assertEquals("Open this select menu", select.getFirstSelectedOption().getText());
        Thread.sleep(2000);
        select.selectByIndex(1);
        assertEquals("One", select.getFirstSelectedOption().getText());
        assertTrue(select.getFirstSelectedOption().isSelected());
        Thread.sleep(2000);
        select.selectByValue("2");
        assertEquals("Two", select.getFirstSelectedOption().getText());
        assertTrue(select.getFirstSelectedOption().isSelected());
        Thread.sleep(2000);
        select.selectByVisibleText("Three");
        assertEquals("Three", select.getFirstSelectedOption().getText());
        assertTrue(select.getFirstSelectedOption().isSelected());
        Thread.sleep(2000);

        assertEquals("Dropdown (select)", dropdownSelectText.getText().split("\n")[0].trim());

        List<WebElement> selectedOptions = select.getAllSelectedOptions();
        for (WebElement selectedOption : selectedOptions) {
            System.out.println("Selected option: " + selectedOption.getText());
        }
        Thread.sleep(2000);

        List<WebElement> options = select.getOptions();
        for (WebElement option : options) {
            System.out.printf("Available Option: %s isSelected = %s%n", option.getText(), option.isSelected());
        }
        Thread.sleep(2000);

        if (select.isMultiple()) {
            select.deselectByIndex(1);
            select.selectByValue("1");
            select.deselectByVisibleText("One");
            select.deselectAll();
        } else {
            System.out.println("You may only deselect all options of a multi-select");
        }
        Thread.sleep(2000);
    }

    @Test
    @Order(14)
    void dropdownDataListTest() throws InterruptedException {
        WebElement dropdownDataList = driver.findElement(By.name("my-datalist"));
        WebElement dropdownDataListText = driver.findElement(By.xpath("//label[@class='form-label w-100' and normalize-space(text()) = 'Dropdown (datalist)']"));

        assertEquals("Dropdown (datalist)", dropdownDataListText.getText());

        dropdownDataList.sendKeys("San Francisco");
        assertEquals("San Francisco", dropdownDataList.getAttribute("value"));
        Thread.sleep(2000);
        dropdownDataList.clear();
        Thread.sleep(2000);

        dropdownDataList.sendKeys("New York");
        assertEquals("New York", dropdownDataList.getAttribute("value"));
        Thread.sleep(2000);
        dropdownDataList.clear();
        Thread.sleep(2000);

        dropdownDataList.sendKeys("Seattle");
        assertEquals("Seattle", dropdownDataList.getAttribute("value"));
        Thread.sleep(2000);
        dropdownDataList.clear();
        Thread.sleep(2000);

        dropdownDataList.sendKeys("Los Angeles");
        assertEquals("Los Angeles", dropdownDataList.getAttribute("value"));
        Thread.sleep(2000);
        dropdownDataList.clear();
        Thread.sleep(2000);

        dropdownDataList.sendKeys("Chicago");
        assertEquals("Chicago", dropdownDataList.getAttribute("value"));
        Thread.sleep(2000);
        dropdownDataList.clear();
        Thread.sleep(2000);

        assertEquals("Type to search...", dropdownDataList.getDomAttribute("placeholder"));
        Thread.sleep(2000);
    }

    @Test
    @Order(15)
    void fileInputTest() throws InterruptedException {
        WebElement fileInputButton = driver.findElement(By.name("my-file"));
        WebElement fileInputText = driver.findElement(By.xpath("//label[@class='form-label w-100' and normalize-space(text()) = 'File input']"));

        assertEquals("File input", fileInputText.getText());

        String selectFile = System.getProperty("user.dir") + "/src/test/resources/STE In Banner.jpg";
        fileInputButton.sendKeys(selectFile);
        Thread.sleep(2000);

        WebElement submit = driver.findElement(By.xpath("//button[text()='Submit']"));
        submit.click();
        Thread.sleep(3000);
        assertThat(driver.getCurrentUrl()).contains("STE+In+Banner");

        WebElement formSubmittedText = driver.findElement(By.xpath("//h1[@class = 'display-6']"));
        assertEquals("Form submitted", formSubmittedText.getText());

        WebElement receivedText = driver.findElement(By.xpath("//p[@class = 'lead']"));
        assertEquals("Received!", receivedText.getText());
    }

    @Test
    @Order(16)
    void checkedCheckboxTest() throws InterruptedException {
        WebElement checkedCheckbox = driver.findElement(By.id("my-check-1"));
        WebElement checkedCheckboxText = driver.findElement(By.xpath("//label[@class = 'form-check-label w-100'][normalize-space(.) = 'Checked checkbox']"));

        assertEquals("Checked checkbox", checkedCheckboxText.getText());

        assertTrue(checkedCheckbox.isSelected());
        Thread.sleep(2000);
        checkedCheckbox.click();
        Thread.sleep(2000);
        assertFalse(checkedCheckbox.isSelected());
    }

    @Test
    @Order(17)
    void defaultCheckboxTest() throws InterruptedException {
        WebElement checkedCheckbox = driver.findElement(By.id("my-check-2"));
        WebElement defaultCheckboxText = driver.findElement(By.xpath("//label[@class = 'form-check-label w-100'][normalize-space(.) = 'Default checkbox']"));

        assertEquals("Default checkbox", defaultCheckboxText.getText());

        assertFalse(checkedCheckbox.isSelected());
        Thread.sleep(2000);
        checkedCheckbox.click();
        Thread.sleep(2000);
        assertTrue(checkedCheckbox.isSelected());
    }

    @Test
    @Order(18)
    void radioButtonsTest() throws InterruptedException {
        WebElement checkedRadioButton = driver.findElement(By.id("my-radio-1"));
        WebElement defaultRadioButton = driver.findElement(By.id("my-radio-2"));
        WebElement checkedRadioText = driver.findElement(By.xpath("//label[@class = 'form-check-label w-100'][normalize-space(.) = 'Checked radio']"));
        WebElement defaultRadioText = driver.findElement(By.xpath("//label[@class = 'form-check-label w-100'][normalize-space(.) = 'Default radio']"));

        assertEquals("Checked radio", checkedRadioText.getText());
        assertEquals("Default radio", defaultRadioText.getText());

        assertTrue(checkedRadioButton.isSelected());
        assertFalse(defaultRadioButton.isSelected());
        Thread.sleep(2000);
        defaultRadioButton.click();
        Thread.sleep(2000);
        assertFalse(checkedRadioButton.isSelected());
        assertTrue(defaultRadioButton.isSelected());
    }

    @Test
    @Order(19)
    void colorPickerTest() throws InterruptedException {
        WebElement colorPicker = driver.findElement(By.name("my-colors"));
        WebElement colorPickerText = driver.findElement(By.xpath("//label[@class = 'form-label w-100' and normalize-space(text()) = 'Color picker']"));

        assertEquals("Color picker", colorPickerText.getText());

        JavascriptExecutor js = (JavascriptExecutor) driver;

        String initColor = colorPicker.getAttribute("value");
        System.out.println("The initial color is " + initColor);

        Color green = new Color(0, 255, 0, 1);

        String script = String.format("arguments[0].setAttribute('value', '%s');", green.asHex());
        Thread.sleep(3000);
        js.executeScript(script, colorPicker);
        String finalColor = colorPicker.getAttribute("value");
        System.out.println("The initial color is " + finalColor);
        assertThat(finalColor).isNotEqualTo(initColor);
        assertThat(Color.fromString(finalColor)).isEqualTo(green);
        assertEquals("#00ff00", colorPicker.getAttribute("value"));
    }

    @Test
    @Order(20)
    void datePickerTest() throws InterruptedException {
        WebElement dateBox = driver.findElement(By.name("my-date"));
        WebElement datePickerText = driver.findElement(By.xpath("//label[@class = 'form-label w-100' and normalize-space(text()) = 'Date picker']"));

        dateBox.click();
        Thread.sleep(2000);
        WebElement dateLocator = driver.findElement(By.xpath("//td[@class = 'day' and text() = 25]"));
        dateLocator.click();
//        dateBox.sendKeys("03/24/2025");
        dateBox.sendKeys(Keys.ESCAPE);
        Thread.sleep(2000);

        assertEquals("03/25/2025", dateBox.getAttribute("value"));
        assertEquals("Date picker", datePickerText.getText());
    }

    @Test
    @Order(21)
    void exampleRangeTest() throws InterruptedException {
        WebElement rangeElement = driver.findElement(By.name("my-range"));
        WebElement exampleRangeText = driver.findElement(By.xpath("//label[@class = 'form-label w-100' and normalize-space(text()) = 'Example range']"));

        assertEquals("Example range", exampleRangeText.getText());

        assertEquals("5", rangeElement.getDomProperty("value"));
        Thread.sleep(2000);

        for (int min = 0; min <= 10 ; min++) {
            rangeElement.sendKeys(Keys.ARROW_RIGHT);
        }
        Thread.sleep(2000);

        assertEquals("10", rangeElement.getDomProperty("value"));

        for (int min = 10; min >= 0 ; min--) {
            rangeElement.sendKeys(Keys.ARROW_LEFT);
        }
        Thread.sleep(2000);

        assertEquals("0", rangeElement.getDomProperty("value"));
    }

    @Test
    @Order(22)
    void actionAPIMouseExampleRangeTest() throws InterruptedException {
        WebElement rangeElement = driver.findElement(By.name("my-range"));
        WebElement exampleRangeText = driver.findElement(By.xpath("//label[@class='form-label w-100' and normalize-space(text()) = 'Example range']"));
        int width = rangeElement.getSize().getWidth();
        int x = rangeElement.getLocation().getX();
        int y = rangeElement.getLocation().getY();
        int i;
        for (i = 5; i <= 10; i++) {
            new Actions(driver)
                    .moveToElement(rangeElement)
                    .clickAndHold()
                    .moveToLocation(x + width / 10 * i, y)
                    .release()
                    .perform();

        }
//        rangeElement.click();
//
//        Actions actions = new Actions(driver);
//        actions.clickAndHold(rangeElement)
//                .moveByOffset(60, 0).release().perform();
        Thread.sleep(2000);

        assertEquals("Example range", exampleRangeText.getText());
        assertEquals(String.valueOf(i - 1), rangeElement.getDomProperty("value"));
    }

    @Test
    @Order(23)
    void returnToIndexLinkTest() throws InterruptedException {
        WebElement returnToIndexLink = driver.findElement(By.xpath("//a[@href = './index.html']"));
        returnToIndexLink.click();

        assertEquals("https://bonigarcia.dev/selenium-webdriver-java/index.html", driver.getCurrentUrl());
        Thread.sleep(2000);
    }

    @Test
    @Order(24)
    void boniGarciaLinkTest() throws InterruptedException {
        WebElement boniGarciaLink = driver.findElement(By.xpath("//a[@href = 'https://bonigarcia.dev/']"));
        boniGarciaLink.click();

        assertEquals("https://bonigarcia.dev/", driver.getCurrentUrl());
        Thread.sleep(2000);
    }

    @Test
    @Order(25)
    void copyrightTextTest() throws InterruptedException {
        WebElement copyrightText = driver.findElement(By.xpath("//span[@class = 'text-muted' and normalize-space(text()) = 'Copyright © 2021-2025']"));

        assertEquals("Copyright © 2021-2025 Boni García", copyrightText.getText());
        Thread.sleep(2000);
    }

    @Test
    @Order(26)
    void submitButtonTest() throws InterruptedException {
        WebElement submitButton = driver.findElement(By.tagName("button"));
        WebElement submitButtonText = driver.findElement(By.xpath("//button[text() = 'Submit']"));
        assertEquals("Submit", submitButtonText.getText());

        submitButton.click();
        Thread.sleep(3000);

        assertEquals("https://bonigarcia.dev/selenium-webdriver-java/submitted-form.html?my-text=&my-password=&my-textarea=&my-readonly=Readonly+input&my-select=Open+this+select+menu&my-datalist=&my-file=&my-check=on&my-radio=on&my-colors=%23563d7c&my-date=&my-range=5&my-hidden=", driver.getCurrentUrl());

        WebElement formSubmittedText = driver.findElement(By.xpath("//h1[@class = 'display-6']"));

        assertEquals("Form submitted", formSubmittedText.getText());
    }
}