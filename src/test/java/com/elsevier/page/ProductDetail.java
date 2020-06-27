package com.elsevier.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Map;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Component
public class ProductDetail {

    @Autowired
    private AddShoppingCartDialog addShoppingCartDialog;

    private static SelenideElement submitButton() {
        return $x("//button[@name='Submit']");
    }

    private static SelenideElement inputNum() {
        return $x("//input[@id='quantity_wanted']");
    }

    private static SelenideElement sizeDropdown() {
        return $(By.id("group_1"));
    }

    private static SelenideElement colorPick(final String color) {
        return $x("//ul[@id='color_to_pick_list']//a[@title='"+color+"']");
    }

    public ProductDetail selectSize(final String size){
        sizeDropdown().selectOptionContainingText(size);
        return this;
    }

    public ProductDetail submit(){
        submitButton().click();
        addShoppingCartDialog.closeDialog();
        return this;
    }

    public ProductDetail setColor(final String color){
        colorPick(color).click();
        return this;
    }

    public ProductDetail inputQuantity(final String num){
        inputNum().shouldBe(visible).setValue(num);
        return this;
    }

    public ProductDetail setProductInfo(final Map<String, String> rowData){
       inputQuantity(rowData.get("quantity")).setColor(rowData.get("color")).selectSize(rowData.get("size"));
        return this;
    }

}
