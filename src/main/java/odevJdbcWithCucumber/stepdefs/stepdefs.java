package odevJdbcWithCucumber.stepdefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import odevJdbcWithCucumber.base.BaseTest;
import odevJdbcWithCucumber.utils.MyConnection;

import java.util.List;
import java.util.Map;

import static odevJdbcWithCucumber.model.Locators.*;
import static odevJdbcWithCucumber.utils.Utils.getRandom;

public class stepdefs extends BaseTest {

    MyConnection myConnection;
    private int lastInsertId;

    @Given("use the following database connection")
    public void useTheFollowingDatabaseConnection(DataTable table) {
        Map<String, String> map = table.asMap();
        String url = map.get("url");
        String username = map.get("username");
        String password = map.get("password");
        myConnection = new MyConnection(url, username, password);
        //myConnection.setCredentials(url, username, password);

    }


    @And("user create table {string} as follows")
    public void userCreateTableAsFollows(String tableName, DataTable table) {
        List<String> list = table.asList();
        String sql = "CREATE TABLE " + tableName + " (";
        for (String s : list) {
            sql += s + ",";
        }
        sql = sql.substring(0, sql.length() - 1);
        sql += ")";

        myConnection.execute(sql);
    }

    @And("user insert table {string} the following data")
    public void userInsertTableTheFollowingData(String tableName, DataTable table) {
        List<List<String>> lists = table.asLists();

        String fields = "";
        String values = "";

        for (int i = 0; i < lists.size(); i++) {

            fields += lists.get(i).get(0);
            values += "'" + getRandom(lists.get(i).get(1)) + "'";

            if (i != lists.size() - 1) {
                fields += ",";
                values += ",";
            }

        }

        String sql = "INSERT INTO " + tableName + "(" + fields + ") VALUES(" + values +")";

        myConnection.execute(sql);

    }

    @And("user apply the sql {string}")
    public void userApplyTheSql(String sql) {
        myConnection.execute(sql);
    }

    @And("save last insert id")
    public void saveLastInsertId() {
        lastInsertId = Integer.parseInt(myConnection.getTable("SELECT last_insert_id()").get(0).get(0));
    }

    @And("user apply the sql {string} to the last record")
    public void userApplyTheSqlToTheLastRecord(String sql) {
        sql = sql + " WHERE id = " + lastInsertId;
        myConnection.execute(sql);
    }

    @When("user navigate to {string}")
    public void userNavigateTo(String url) {
        driver.get(url);
    }

    @And("user login username as {string}, and password as {string}")
    public void userLoginUsernameAsAndPasswordAs(String username, String password) {

        sendKeys(lUsername, username);
        sendKeys(lPassword, password);
        click(lLoginButton);

    }

    @Then("login should be successful")
    public void loginShouldBeSuccessful() {
        waitForVisibility(getByText("Edit Account"));
    }

    @When("user clicks the link with text {string}")
    public void userClicksTheLinkWithText(String text) {
        click(getByText(text));
    }

    @Then("the text {string} should be visible")
    public void theTextShouldBeVisible(String text) {
        waitForVisibility(getByText(text));
    }

    @Then("the element with attribute {string} should be visible")
    public void theElementWithAttributeShouldBeVisible(String attr) {
        waitForVisibility(getByAttribute(attr));
    }

    @When("user fill the form with last inserted data from table {string}")
    public void userFillTheFormWithLastInsertedDataFromTable(String tableName) {

        String sql = "SELECT * FROM " + tableName + " WHERE id = " + lastInsertId;
        List<List<String>> table = myConnection.getTable(sql);

        for (int i = 0; i < 4; i++) {
            sendKeys(adressFormLocators[i], table.get(0).get(i+1));
        }
    }

    @When("user get sql {string} with last insert id")
    public void userGetSqlWithLastInsertId(String sql) {
        sql += " WHERE id = " + lastInsertId;
        List<List<String>> table = myConnection.getTable(sql);
    }
}

