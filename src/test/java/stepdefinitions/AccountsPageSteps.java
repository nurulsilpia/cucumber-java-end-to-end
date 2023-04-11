package stepdefinitions;

import com.qa.factory.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pages.AccountsPage;
import pages.LoginPage;

import java.util.List;
import java.util.Map;

public class AccountsPageSteps {

    private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
    private AccountsPage accountsPage;

    @Given("user has already logged in to application")
    public void userHasAlreadyLoggedInToApplication(DataTable credTable) {
        List<Map<String, String>> credList = credTable.asMaps();
        String userName = credList.get(0).get("username");
        String password = credList.get(0).get("password");

        DriverFactory.getDriver().get("http://automationpractice.pl/index.php?controller=authentication&back=my-account");
        accountsPage = loginPage.doLogin(userName, password);
    }

    @Given("user is on Accounts page")
    public void userIsOnAccountsPage() {
        String title = accountsPage.getAccountsPageTitle();
        System.out.println("Accounts Page title is: " + title);
    }

    @Then("user gets accounts section")
    public void userGetsAccountsSection(DataTable sectionsTable) {
        List<String> expAccountSectionsList = sectionsTable.asList();

        System.out.println("Expected accounts section list: " + expAccountSectionsList);

        List<String> actualAccountSectionsList = accountsPage.getAccountsSectionsList();
        System.out.println("Actual accounts section list: " + actualAccountSectionsList);

        Assert.assertTrue(expAccountSectionsList.containsAll(actualAccountSectionsList));
    }

    @And("accounts section count should be {int}")
    public void accountsSectionCountShouldBe(int expectedSectionCount) {
        Assert.assertTrue(accountsPage.getAccountsSectionCount() == expectedSectionCount);
    }
}
