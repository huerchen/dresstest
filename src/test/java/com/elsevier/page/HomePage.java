package com.elsevier.page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.elsevier.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import static com.codeborne.selenide.Selenide.$x;


@Component
public class HomePage {

    @Autowired
    private Config config;

    @Autowired
    private DressesPage dressesPage;

    private static SelenideElement switchTab(final String tabName) {
        return $x("//div[@id='block_top_menu']/ul/li[a[text()='" + tabName + "']]");
    }

    public HomePage open(){
        Selenide.open(config.baseUrl());
        return this;
    }

    public DressesPage switchToProduct(final String tabName){
        switchTab(tabName).click();
        return dressesPage;
    }

}
