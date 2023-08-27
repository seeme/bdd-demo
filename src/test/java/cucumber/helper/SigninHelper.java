package cucumber.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class SigninHelper {

  private static final String SIGNIN_PAGE_URL = "http://140.134.24.157:20083/signin.html";
  private static final String MAIN_PAGE_URL = "http://140.134.24.157:20083/main.html";

  public static void landSignInPage(WebDriver webDriver) {
    webDriver.get(SIGNIN_PAGE_URL);
  }

  public static void enterSecret(WebDriver webDriver, String username, String password) {
    webDriver.findElement(By.id("username")).sendKeys(username);
    webDriver.findElement(By.id("password")).sendKeys(password);
  }

  public static void clickSignInBtn(WebDriver webDriver) {
    webDriver.findElement(By.id("sign-in-btn")).click();
  }

  public static void verifyLandMainPage(WebDriver webDriver,
                                        Map<String, Object> vars,
                                        JavascriptExecutor js)
      throws InterruptedException {
    Thread.sleep(1000);
    vars = new HashMap<String, Object>();
    vars.put("url", js.executeScript("return window.location.href;"));
    assertEquals(vars.get("url").toString(), MAIN_PAGE_URL);
  }

  public static void verifyManuExist(WebDriver webDriver) {
    WebElement elm = webDriver.findElement(By.id("navbarDropdownMenuLink"));
    assertThat(webDriver.findElement(By.id("navbarDropdownMenuLink")).getText(), is("初級市場"));
  }

}
