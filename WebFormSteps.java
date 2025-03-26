package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebFormSteps {
    public static void openWebFormPge(WebDriver driver) throws InterruptedException {
        WebElement webFormButton = driver.findElement(By.xpath("//a[@href = 'web-form.html']"));
        Thread.sleep(2000);
        webFormButton.click();
    }

    public static void openNavigationPage(WebElement driver) throws InterruptedException {
          WebElement navigationButton = driver.findElement(By.xpath("//a[@href = 'navigation1.html']"));
          Thread.sleep(2000);
          navigationButton.click();
    }

    public static void openDropdownMenuPage(WebElement driver) throws InterruptedException {
        WebElement dropdownMenuButton = driver.findElement(By.xpath("//a[@href = 'dropdown-menu.html']"));
        Thread.sleep(2000);
        dropdownMenuButton.click();
    }
    public static void openDragAndDropPage(WebDriver driver) throws InterruptedException {
        WebElement dragAndDropButton = driver.findElement(By.xpath("//a[@href = 'drag-and-drop.html']"));
        Thread.sleep(2000);
        dragAndDropButton.click();
    }
}
