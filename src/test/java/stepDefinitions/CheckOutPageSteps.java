package stepDefinitions;

import cucumber.TestContext;
import cucumber.api.java.en.When;
import org.junit.Test;
import pageObjects.CheckOutPage;

public class CheckOutPageSteps {

    TestContext testContext;
    CheckOutPage checkOutPage;

    public CheckOutPageSteps(TestContext context){
        testContext = context;
        checkOutPage = testContext.getPageObjectManager().getCheckOutPage();
    }

    @When("^enter personal details on checkout page$")
    public void enter_personal_details_on_checkout_page() throws InterruptedException {
        checkOutPage.fill_PersonalDetails();
    }

    @When("^select same delivery address$")
    public void select_same_delivery_address() throws InterruptedException{
        checkOutPage.check_ShipToDifferentAddress(false);
    }

    @When("^select payment method as check payment$")
    public void select_payment_method_as_payment(){
        checkOutPage.select_PaymentMethod("CheckPayment");
    }

    @When("^place the order$")
    public void place_the_order() throws InterruptedException {
        checkOutPage.check_TermsAndCondition(true);
        checkOutPage.clickOn_PlaceOrder();
//        testContext.getWebDriverManager().closeDriver();
    }
}
