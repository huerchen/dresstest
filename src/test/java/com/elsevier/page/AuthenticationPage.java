package com.elsevier.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;
import static com.codeborne.selenide.Selenide.$;

@Component
public class AuthenticationPage {

    private static SelenideElement signInButton(){
        return $(By.id("SubmitLogin"));
    }

    public void checkSignInButton(){
        signInButton().shouldBe(Condition.visible);
    }


}
