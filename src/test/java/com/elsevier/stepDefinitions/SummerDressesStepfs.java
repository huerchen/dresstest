package com.elsevier.stepDefinitions;

import com.elsevier.config.StepDef;
import com.elsevier.page.CartPage;
import com.elsevier.page.DressesPage;
import com.elsevier.page.HomePage;
import com.elsevier.page.ProductDetail;
import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;

public class SummerDressesStepfs extends StepDef {
    private static final Logger LOGGER = LoggerFactory.getLogger(SummerDressesStepfs.class);

    @Autowired
    private HomePage homePage;
    @Autowired
    private DressesPage dressesPage;
    @Autowired
    private ProductDetail productDetail;
    @Autowired
    private CartPage cartPage;

    @Before
    public void logScenario(final Scenario scenario) {
    LOGGER.info("Run scenario [{}]", scenario.getName());
}

    @Given("^I am on the home page$")
    public void iAmOnTheHomePage()  {
        homePage.open();
    }

    @When("^I go to \"([^\"]*)\" page and select \"([^\"]*)\"$")
    public void iGoToPageAndSelect(final String tabName, final String subTypeDress) {
        homePage.switchToProduct(tabName);
        dressesPage.dressDisplay(subTypeDress).displayWithList();
    }

    @And("^I add \"([^\"]*)\" to cart$")
    public void iAddToCart(final String productName)  {
        dressesPage.addProductToCart(productName);
    }

    @And("^I add product to cart$")
    public void iAddProductToCart(final DataTable products)  {
        List<Map<String, String>> productList = products.asMaps(String.class, String.class);
        for (Map<String, String> rowData : productList) {
            dressesPage.goToProductDetail(rowData.get("description"));
            productDetail.setProductInfo(rowData);
            productDetail.submit();
            dressesPage.backToDressPage();
        }
    }

    @And("^I open cart$")
    public void iOpenCart()  {
        dressesPage.openCart();
    }

    @Then("^I should see total price is \"([^\"]*)\"$")
    public void iShouldSeeTotalPriceIs(final String price)  {
        cartPage.checkTotalPrice(price);
    }

    @Then("^I go to Sign in section when try to check out$")
    public void iGoToSignInSectionWhenTryToCheckOut()  {
        cartPage.checkSignIn();
    }
}
