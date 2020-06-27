package com.elsevier.page;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {PageConfig.class})
public class PageConfig {
}
