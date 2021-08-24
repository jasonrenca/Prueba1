package solotodotest;

import java.nio.file.Paths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CertificacionSoloTodo {
	private WebDriver driver;
	private String url;
	private WebDriverWait wait;

	@AfterTest
	public void finPrueba() {
		// driver.close();
	}

	@BeforeTest
	public void preparacionPruebas() {
		String rutaChromeDriver = Paths
				.get(System.getProperty("user.dir"), "\\src\\test\\resources\\webdriver\\chromedriver.exe").toString();
		System.setProperty("webdriver.chrome.driver", rutaChromeDriver);
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 10);
		url = "https://www.solotodo.cl/";
	}

	@Test
	public void CP001_Login_Incorrecto() {
		driver.get(url);
		driver.manage().window().maximize();
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//body/div[@id='__next']/div[2]/div[1]/nav[1]/div[1]/ul[2]/li[2]/a[1]/i[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//body/div[@id='__next']/div[2]/div[1]/nav[1]/div[1]/ul[2]/li[2]/a[1]/i[1]")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Iniciar sesión')]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#exampleInputEmail1")))
				.sendKeys("jacson.renca@tsoftlatam.com");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#password"))).sendKeys("123456789");
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//body/div[@id='__next']/div[2]/div[2]/div[1]/div[1]/div[3]/div[1]/form[1]/input[1]")))
				.click();
		String mensaje = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(
						"//div[contains(text(),'No puede iniciar sesión con las credenciales proporcionadas.')]")))
				.getText();
		Assert.assertEquals(mensaje, "No puede iniciar sesión con las credenciales proporcionadas.");

	}

	@Test
	public void CP002_Buscar_Seccion_Tecnologia_Celulares_Iphones() throws InterruptedException {
		driver.get(url);
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//a[contains(text(),'Tecnología')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'Celulares')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='search']")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#search")).sendKeys("Iphone");
		Thread.sleep(7000);

		/*
		 * Este caso de prueba, se realizó con Thread, debido a que se requiere validar
		 * que aparezca la sección de celulares con marca Iphone.
		 */
	}

	@Test
	public void CP003_Enviar_Correo_Bajo_Precio() {
		driver.get(url);
		driver.manage().window().maximize();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Tecnología')]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Notebooks')]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='search']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#search"))).sendKeys("Acer Aspire Nitro 5");
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Acer Aspire Nitro 5 AN515-54-70KK [NH.Q96AA.001]"))).click();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//button[contains(text(),'¡Avísame cuando baje de precio!')]")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
				"body.modal-open:nth-child(2) div.modal.fade.show div.modal-dialog div.modal-content form:nth-child(1) div.modal-body > input.form-control.mt-3")))
				.sendKeys("prueba@gmail.com");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Suscribirse')]")))
				.click();
	}

	@Test
	public void CP004_Detalle_Grafico_Producto() {
		driver.get(url);
		driver.manage().window().maximize();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Tecnología')]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Celulares')]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='search']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#search"))).sendKeys("Iphone 12 Pro Max");
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//a[contains(text(),'Apple iPhone 12 Pro Max (128 GB / Graphite) + Appl')]"))).click();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//button[contains(text(),'Ver gráfico de precios')]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Ver detalle')]")))
				.click();
		String mensaje = wait
				.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//h5[contains(text(),'Precio histórico por tienda')]")))
				.getText();
		Assert.assertEquals(mensaje, "Precio histórico por tienda");

	}

	@Test
	public void CP005_Exportar_Excel_Cotizacion() {
		driver.get(url);
		driver.manage().window().maximize();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Comentar')]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Exportar a Excel')]")))
				.click();

	}

}
