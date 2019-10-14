package Anything;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

public class LoginWithExcelData {
	private WebDriver d;
	
  @Test(dataProvider = "credentials")
  public void login(String username, String password) throws InterruptedException {
	  d.findElement(By.partialLinkText("SignIn")).click();
	  d.findElement(By.name("userName")).sendKeys(username);
	  d.findElement(By.name("password")).sendKeys(password);
	  d.findElement(By.name("Login")).click();
	  d.findElement(By.partialLinkText("SignOut")).click();
	  Thread.sleep(2000);
  }

  @DataProvider(name="credentials")
  public Object[][] dp() throws IOException {
    return ExcelUtility.read_excel_data();
  }
  @BeforeTest
  public void beforeTest() {
	  WebDriverManager.chromedriver().setup();
	  d = new ChromeDriver();
	  d.navigate().to("http://10.232.237.143:443/TestMeApp");
	  d.manage().window().maximize();
	  d.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }
  @AfterMethod
  public void afterMethod() throws IOException {
	  File src = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
	  FileUtils.copyFile(src, new File("C:\\Users\\pdc4-training.pdc4\\Desktop\\TestResult\\"+timestamp()+".png"));
  }

  @AfterTest
  public void afterTest() {
  }
  public static String timestamp() {
	  return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
  }

}
