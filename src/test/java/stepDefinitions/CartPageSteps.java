package stepDefinitions;

import cucumber.TestContext;
import cucumber.api.java.en.When;
import pageObjects.CartPage;

public class CartPageSteps {

    TestContext testcontext;
    CartPage cartPage;

    public CartPageSteps(TestContext context) {
        testcontext = context;
        cartPage = testcontext.getPageObjectManager().getCartPage();
    }

    @When("^moves to checkout from mini cart$")
    public void moves_to_checkout_from_mini_cart(){
        cartPage.clickOn_Cart();
        cartPage.clickOn_ContinueToCheckout();
    }
}
