package stepdefinitions;

import com.qa.factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.LoginPage;

public class LoginPageSteps {
    private static String title;
    private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

    @Given("user is on login page")
    public void userIsOnLoginPage() {
        DriverFactory.getDriver().get("http://automationpractice.pl/index.php?controller=authentication&back=my-account");
    }

    @When("user gets the title of the page")
    public void userGetsTheTitleOfThePage() {
        title = loginPage.getLoginPageTitle();
        System.out.println("Page title is: " + title);
    }

    @Then("page title should be {string}")
    public void pageTitleShouldBe(String expectedTitleName) {
        Assert.assertTrue(title.contains(expectedTitleName));
    }

    @Then("forgot your password link should be displayed")
    public void forgotYourPasswordLinkShouldBeDisplayed() {
        Assert.assertTrue(loginPage.isForgotPwdLinkExist());
    }

    @When("user enters username {string}")
    public void userEntersUsername(String username) {
        loginPage.enterUserName(username);
    }

    @And("user enters password {string}")
    public void userEntersPassword(String password) {
        loginPage.enterPassword(password);
    }

    @And("user clicks on Login button")
    public void userClicksOnLoginButton() {
        loginPage.clickOnLogin();
    }
}
