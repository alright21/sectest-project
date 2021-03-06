package po;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends MenuComponent{


	@FindBy (id = "addProductModalBtn")
	WebElement addProductModalBtn;
	@FindBy (id = "productImage")
	WebElement productImage;
	@FindBy (id = "productName")
	WebElement productName;
	@FindBy (id = "quantity")
	WebElement quantity;
	@FindBy (id = "rate")
	WebElement rate;
	@FindBy (id = "brandName")
	WebElement brandName;
	@FindBy (id = "categoryName")
	WebElement categoryName;
	@FindBy (id = "productStatus")
	WebElement productStatus;
	@FindBy (id = "createProductBtn")
	WebElement createProductBtn;
	@FindBy (xpath = "//div[@class='modal-footer']//button[1]")
	WebElement closeBtn;


	// relative product buttons
	@FindBy (xpath = "//tr[3]//td[8]//div[1]//button[1]")
	WebElement actionBtn;
	@FindBy (xpath = "//div[@class='btn-group open']//a[@id='removeProductModalBtn']")
	WebElement removeBtn;
	@FindBy (id = "removeProductBtn")
	WebElement removeProductBtn;

	
	//edit product
	@FindBy(xpath = "//div[@class='btn-group open']//a[@id='editProductModalBtn']")
	WebElement editProductModalBtn;
	
	@FindBy(xpath = "//a[contains(text(),'Product Info')]")
	WebElement productInfo;	
	@FindBy(id = "editProductName")
	WebElement editProductName;
	@FindBy(id="editQuantity")
	WebElement editQuantity;
	@FindBy(id = "editRate")
	WebElement editRate;
	
	@FindBy(id = "editProductBtn")
	WebElement editProductBtn;
	
	


	public ProductPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}



	public void addProduct(String name, String quantity, String rate, String brand, String category, String status) {


		
		wait = new WebDriverWait(driver, 2);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("addProductModalBtn")));
		addProductModalBtn.click();
		productName.sendKeys(name);
		String absPath = "";
		try {
			absPath = Paths.get(getClass().getClassLoader().getResource("macbookpro.png").toURI()).toFile().getAbsolutePath();
			System.out.println(absPath);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		productImage.sendKeys(absPath);
		this.quantity.sendKeys(quantity);
		this.rate.sendKeys(rate);


		js.executeScript("arguments[0].scrollIntoView(true);", productStatus);
		Select brandSelect = new Select(brandName);		
		brandSelect.selectByVisibleText(brand);

		Select categoriesSelect = new Select(categoryName);
		
		categoriesSelect.selectByVisibleText(category);

		Select statusSelect = new Select(productStatus);
		statusSelect.selectByVisibleText(status);

		createProductBtn.click();

	
		for(int i=0; i < 3; ++i) {
			clickAlert();
			
		}
		
		
		closeBtn.click();


	}

	public void removeProduct() {

		actionBtn.click();
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].scrollIntoView(true);", removeBtn);
		removeBtn.click();
		removeProductBtn.click();
	}



	public void editProduct(String name, String quantity, String rate, String brand, String category,
			String status) {
		
		driver.findElement(By.xpath("//tr[@class='odd']//button[@class='btn btn-default dropdown-toggle'][contains(text(),'Action')]")).click();
		
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].scrollIntoView(true);", editProductModalBtn);
		editProductModalBtn.click();
		
		productInfo.click();
		

		editQuantity.sendKeys(quantity);
		
		editProductBtn.click();
		
		clickAlert();

		
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/form[1]/div[8]/button[1]")).click();
		
		
		
		
		
	}


}
