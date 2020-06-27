package com.elsevier.page;

import com.codeborne.selenide.SelenideElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import static com.codeborne.selenide.Selenide.$x;

@Component
public class DressesPage {
    @Autowired
    private AddShoppingCartDialog addShoppingCartDialog;
    @Autowired
    private ProductDetail productDetail;
    @Autowired
    private CartPage cartPage;

    private static SelenideElement showDress(final String dressType) {
        return $x("//div[@class='block_content']/ul/li[a[contains(text(),'" + dressType + "')]]");
    }

    private static SelenideElement listButton() {
        return $x("//div[@class='sortPagiBar clearfix']/ul/li[a[text()='List']]");
    }

    private static SelenideElement addToCart(final String productDesc){
        return $x("//ul[@class='product_list row list']//p[contains(text(),'"+productDesc+"')]/../../div[3]//a[span[text()='Add to cart']]");
    }

    private static SelenideElement view(final String productDesc){
        return $x("//ul[@class='product_list row list']//p[contains(text(),'"+productDesc+"')]/../../div[3]//a[span[text()='More']]");
    }

    private static SelenideElement backToSummerDress() {
        return $x("//div[@class='breadcrumb clearfix']/a[@title='Summer Dresses']");
    }

    private static SelenideElement viewCart() {
        return $x("//div[@class='shopping_cart']/a");
    }

    public DressesPage dressDisplay(final String dressType){
        showDress(dressType).click();
        return this;
    }

    public DressesPage displayWithList(){
        listButton().click();
        return this;
    }

    public DressesPage addProductToCart(final String productDesc){
        addToCart(productDesc).click();
        addShoppingCartDialog.closeDialog();
        return this;
    }

    public ProductDetail goToProductDetail(final String productDesc){
        view(productDesc).click();
        return productDetail;
    }

    public DressesPage backToDressPage(){
        backToSummerDress().click();
        return this;
    }

    public CartPage openCart(){
        viewCart().click();
        return cartPage;
    }

}
