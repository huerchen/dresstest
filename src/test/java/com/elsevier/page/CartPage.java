package com.elsevier.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Component
public class CartPage {

    @Autowired
    private AuthenticationPage authenticationPage;

    private static SelenideElement totalPrice(){
        return $(By.id("total_price"));
    }

    private static SelenideElement proceed() {
        return $x("//span[text() ='Proceed to checkout']/..");
    }

    public void checkTotalPrice(final String price){
        totalPrice().shouldHave(text(price));
    }

    public void checkSignIn(){
        proceed().click();
        authenticationPage.checkSignInButton();
    }

}
