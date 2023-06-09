package basicSelenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Date;

public class CRUDItemTest {
    static ChromeDriver chrome;
    String itemName = "New Item"+new Date().getTime();;;
    String prevItem = "";

    @BeforeAll
    public static void openBrowser(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/driver/chromedriver.exe");
        chrome = new ChromeDriver();

        // implicit wait --> tiempo de espera generico para todos los controles
        chrome.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        chrome.manage().window().maximize();
        chrome.get("http://todo.ly/");

        // click login button
        chrome.findElement(By.xpath("//img[@src='/Images/design/pagelogin.png']")).click();
        // type email in email txtbox
        chrome.findElement(By.id("ctl00_MainContent_LoginControl1_TextBoxEmail")).sendKeys("vanetejerina314@gmail.com");
        // type pwd in password txtbox
        chrome.findElement(By.id("ctl00_MainContent_LoginControl1_TextBoxPassword")).sendKeys("12345");
        // click login button
        chrome.findElement(By.id("ctl00_MainContent_LoginControl1_ButtonLogin")).click();
        chrome.findElement(By.xpath("//td[text()='Home']")).click();
    }

    @AfterAll
    public static void closeBrowser(){
        chrome.quit();
    }

//    @Test
//    public void verifyCreateItem() throws InterruptedException {
//        //Crear un Item
//        chrome.findElement(By.id("NewItemContentInput")).sendKeys(itemName);
//        chrome.findElement(By.id("NewItemAddButton")).click();
//        Thread.sleep(2000);
//        // verification 1
//        Assertions.assertEquals(1, chrome.findElements(By.xpath("(//div[text()=\"" + itemName + "\"])[contains(@class, \"ItemContentDiv\")][last()]")).size(), "ERROR, the item was not created");
//
//    }
//
//    @Test
//    public void verifyEditItem() throws InterruptedException {
//        // Editar un Item
//        prevItem = itemName;
//        itemName = "Item Edited";
//        chrome.findElement(By.xpath("(//div[text()=\""+prevItem+"\"])[contains(@class, \"ItemContentDiv\")][last()]"))
//                .click();
//        chrome.findElement(By.id("ItemEditTextbox")).clear();
//        chrome.findElement(By.id("ItemEditTextbox")).sendKeys(itemName);
////        chrome.findElement(By.id("ItemEditTextbox")).submit();
//        chrome.findElement(By.id("ItemEditSubmit")).click();
//        Thread.sleep(2000);
//
//        // verification 1
//        Assertions.assertEquals(1, chrome.findElements(By.xpath("(//div[text()=\"" + itemName + "\"])[contains(@class, \"ItemContentDiv\")][last()]"))
//                .size(), "ERROR, the item was not edited");
//
////        if(chrome.findElements(By.xpath("(//div[text()=\"" + itemName + "\"])[contains(@class, \"ItemContentDiv\")][last()]"))
////                .size() == 0){
////            itemName = prevItem;
////        }
//
//    }
//
//    @Test
//    public void verifyDeleteItem() throws InterruptedException {
//        // Eliminar un Item
////        itemName = "Item Edited";
//        chrome.findElement(By.xpath("(//div[text()=\""+itemName+"\"])[contains(@class, \"ItemContentDiv\")][last()]"))
//                .click();
//        chrome.findElement(By.xpath("(//div[@class=\"ItemIndicator\"]//img[contains(@style,\"inline\")])[last()]")).click();
//        chrome.findElement(By.xpath("//li[@class=\"delete separator\"]//a[@href=\"#delete\"]")).click();
//        Thread.sleep(2000);
//
//        // verification 1
//
//        Assertions.assertEquals(0, chrome.findElements(By.xpath("(//div[text()=\"" + itemName + "\"])[contains(@class, \"ItemContentDiv\")][last()]"))
//                .size(), "ERROR, the item " + itemName + " was not deleted");
//    }

    @Test
    public void verifyCRUDItem() throws InterruptedException {
        //Crear un Item
        chrome.findElement(By.id("NewItemContentInput")).sendKeys(itemName);
        chrome.findElement(By.id("NewItemAddButton")).click();
        Thread.sleep(2000);
        // verification 1
        Assertions.assertEquals(1, chrome.findElements(By.xpath("(//div[text()=\"" + itemName + "\"])[contains(@class, \"ItemContentDiv\")][last()]")).size(), "ERROR, the item was not created");


        // Editar un Item
        prevItem = itemName;
        itemName = "Item Edited";
        chrome.findElement(By.xpath("(//div[text()=\"" + prevItem + "\"])[contains(@class, \"ItemContentDiv\")][last()]"))
                .click();
        chrome.findElement(By.id("ItemEditTextbox")).clear();
        chrome.findElement(By.id("ItemEditTextbox")).sendKeys(itemName);
//        chrome.findElement(By.id("ItemEditTextbox")).submit();
        chrome.findElement(By.id("ItemEditSubmit")).click();
        Thread.sleep(2000);

        // verification 1


        if (chrome.findElements(By.xpath("(//div[text()=\"" + itemName + "\"])[contains(@class, \"ItemContentDiv\")][last()]"))
                .size() == 0) {
            itemName = prevItem;
        } else {
            //Para que pueda pasar al delete y no se quede en el edit
            Assertions.assertEquals(1, chrome.findElements(By.xpath("(//div[text()=\"" + itemName + "\"])[contains(@class, \"ItemContentDiv\")][last()]"))
                    .size(), "ERROR, the item was not edited");
        }
        // Eliminar un Item
//        itemName = "Item Edited";
        chrome.findElement(By.xpath("(//div[text()=\"" + itemName + "\"])[contains(@class, \"ItemContentDiv\")][last()]"))
                .click();
        chrome.findElement(By.xpath("(//div[@class=\"ItemIndicator\"]//img[contains(@style,\"inline\")])[last()]")).click();
        chrome.findElement(By.xpath("//li[@class=\"delete separator\"]//a[@href=\"#delete\"]")).click();
        Thread.sleep(2000);

        // verification 1

        Assertions.assertEquals(0, chrome.findElements(By.xpath("(//div[text()=\"" + itemName + "\"])[contains(@class, \"ItemContentDiv\")][last()]"))
                .size(), "ERROR, the item " + itemName + " was not deleted");
    }
}
