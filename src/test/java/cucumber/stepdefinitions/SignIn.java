package cucumber.stepdefinitions;

import cucumber.helper.SigninHelper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class SignIn {

  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;

  @Before
  public void setup(){
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }

  @Given("我在登入頁面")
  public void 我在登入頁面() {
    SigninHelper.landSignInPage(driver);
  }
  @When("我輸入以下有效的帳號資訊:")
  public void 我輸入以下有效的帳號資訊(io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
    String username = data.get(0).get("username");
    String password = data.get(0).get("password");

    SigninHelper.enterSecret(driver, username, password);
  }
  @When("我點擊登入按鈕")
  public void 我點擊登入按鈕() {
    SigninHelper.clickSignInBtn(driver);
  }
  @Then("我應該被導向到甘丹查主頁面")
  public void 我應該被導向到甘丹查主頁面() throws InterruptedException {
    SigninHelper.verifyLandMainPage(driver, vars, js);
  }
  @Then("主頁面上有一個初級市場下搭選單")
  public void 主頁面上有一個初級市場下搭選單() {
    SigninHelper.verifyManuExist(driver);
  }

  @When("我輸入以下無效的帳號資訊:")
  public void 我輸入以下無效的帳號資訊(io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
    String username = data.get(0).get("username");
    String password = data.get(0).get("password");

    driver.findElement(By.id("username")).sendKeys(username);
    driver.findElement(By.id("password")).sendKeys(password);
  }
  @Then("我應該看到一則「密碼錯誤，請重新登入」錯誤訊息")
  public void 我應該看到一則_密碼錯誤_請重新登入_錯誤訊息() throws InterruptedException {
    Thread.sleep(1000);
    assertEquals(driver.findElement(By.id("error-msg")).getText(), "密碼錯誤，請重新登入");
  }
  @Then("我應該保持在登入頁面上")
  public void 我應該保持在登入頁面上() {
    vars.put("url", js.executeScript("return window.location.href;"));
    assertEquals(vars.get("url").toString(), "http://140.134.24.157:20083/signin.html");
  }

  @After
  public void tearDown(){
    if (driver != null) {
      driver.quit();
    }
  }
}
