package com.automator.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;

public class ExitoAutomationTest {
    private WebDriver driver;
    private Actions actions;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\brayan.useche\\eclipse-workspace\\Automator\\chromedriver.exe");
        System.setProperty("webdriver.chrome.logfile", "C:\\Users\\brayan.useche\\eclipse-workspace\\Automator\\chromedriver.log");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        actions = new Actions(driver);
    }

    @Test
    public void testSeleccionarCategoriaYSubcategoria() {
        driver.get("https://www.exito.com/");

        // Cerrar posible banner emergente
        try {
            WebElement closeButton = driver.findElement(By.className("mod-close"));
            if (closeButton.isDisplayed()) {
                closeButton.click();
            }
        } catch (NoSuchElementException ignored) {
        }

        // Hover sobre la categoría "Dormitorio"
        WebElement categoriaDormitorio = driver.findElement(By.xpath("//span[contains(text(), 'Dormitorio')]"));
        actions.moveToElement(categoriaDormitorio).perform();

        // Hacer clic en la subcategoría "Cabeceros"
        WebElement subcategoriaCabeceros = driver.findElement(By.xpath("//a[contains(text(), 'Cabeceros')]"));
        subcategoriaCabeceros.click();

        // Realizar una búsqueda de productos
        WebElement searchBox = driver.findElement(By.id("downshift-1-input"));
        searchBox.sendKeys("cabecero"); // Cambia "cabecero" por el nombre del producto a buscar
        searchBox.sendKeys(Keys.ENTER);

        // Hacer clic en el primer producto listado
        WebElement primerProducto = driver.findElement(By.cssSelector(".jsx-2682922312.product-list.product-item"));
        primerProducto.click();

        // Agregar el producto al carrito
        WebElement botonAgregarCarrito = driver.findElement(By.cssSelector(".jsx-1205251538.add-to-cart-btn"));
        botonAgregarCarrito.click();

    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
