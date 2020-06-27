package com.elsevier.page;

import com.codeborne.selenide.SelenideElement;
import org.springframework.stereotype.Component;
import static com.codeborne.selenide.Selenide.$x;

@Component
public class AddShoppingCartDialog {
    private static SelenideElement closeButton() {
        return $x("//span[@class='cross']");
    }

    private static SelenideElement continueShopping() {
        return $x("//span[@title='Continue shopping']");
    }

    private static SelenideElement proceed() {
        return $x("//a[@title='Proceed to checkout']");
    }

    public void closeDialog(){
        closeButton().click();
    }

}
