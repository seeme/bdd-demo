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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class BatchNoQuery {

  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;

  @Before
  public void setup(){
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }

  @Given("我已經成功登入")
  public void 我已經成功登入(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
    SigninHelper.landSignInPage(driver);

    List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
    String username = data.get(0).get("username");
    String password = data.get(0).get("password");

    SigninHelper.enterSecret(driver, username, password);
    SigninHelper.clickSignInBtn(driver);
    SigninHelper.verifyLandMainPage(driver, vars, js);
    SigninHelper.verifyManuExist(driver);
  }

  @Given("我在批號查詢頁面")
  public void 我在批號查詢頁面() {
    driver.get("http://140.134.24.157:20083/main.html");
  }
  @When("輸入批號 {string}")
  public void 輸入批號(String batchNo) {
    {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000));
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("batch-no-input")));
    }
    driver.findElement(By.id("batch-no-input")).sendKeys(batchNo);

  }
  @When("我點擊查詢按鈕")
  public void 我點擊查詢按鈕() {
    driver.findElement(By.id("query-btn")).click();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(3000));
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("batchNo")));
  }
  @Then("我會看到批號查詢結果，包含批號 {string}")
  public void 我會看到批號查詢結果_包含批號(String batchNoResult) {
    assertEquals(driver.findElement(By.id("batchNo")).getText(), batchNoResult);
  }
  @Then("目前狀態 {string}")
  public void 目前狀態(String status) {
    assertEquals(driver.findElement(By.id("status")).getText(), status);
  }
  @Then("出現明細按鈕")
  public void 出現明細按鈕() {
    List<WebElement> elements = driver.findElements(By.id("detail-btn"));
    assert(elements.size() > 0);
  }
  @Then("自有部位 {string}")
  public void 自有部位(String selfPosition) {
    assertEquals(driver.findElement(By.id("selfPosition")).getText(), selfPosition);
  }
  @Then("附條件買回部位 {string}")
  public void 附條件買回部位(String buybackPosition) {
    assertEquals(driver.findElement(By.id("buybackPosition")).getText(), buybackPosition);
  }
  @Then("票券類別送存登錄方式 {string}")
  public void 票券類別送存登錄方式(String stokeType) {
    assertEquals(driver.findElement(By.id("stokeType")).getText(), stokeType);
  }
  @Then("發票人 {string}")
  public void 發票人(String issuer) {
    assertEquals(driver.findElement(By.id("issuer")).getText(), issuer);
  }
  @Then("發票日 {string}")
  public void 發票日(String issueDate) {
    assertEquals(driver.findElement(By.id("issueDate")).getText(), issueDate);
  }
  @Then("票載到期日 {string}")
  public void 票載到期日(String dueDate) {
    assertEquals(driver.findElement(By.id("dueDate")).getText(), dueDate);
  }
  @Then("總發行面額 {string}")
  public void 總發行面額(String totalAmount) {
    assertEquals(driver.findElement(By.id("totalAmount")).getText(), totalAmount);
  }

  @Then("不顯示明細按鈕")
  public void 不顯示明細按鈕() {
    WebElement element = driver.findElement(By.id("detail-btn"));
    assertFalse(element.isDisplayed());
  }

  @Then("票卷附條件買回交易 {string}")
  public void 票卷附條件買回交易(String transactions) {
    if ("".equals(transactions.trim())) {
      assertEquals(driver.findElement(By.id("buybackTransactions")).getText(), "");
    } else {
      String[] bTransactions = transactions.split(",");
      for (int i = 1; i <= bTransactions.length; i++) {
        String actual = driver.findElement(By.xpath("//div[@id=\"buybackTransactions\"]/div["
            + i + "]")).getText();
        assertEquals(actual, bTransactions[i - 1].trim());
      }
    }
  }

  @After
  public void tearDown(){
    if (driver != null) {
      driver.quit();
    }
  }
}
