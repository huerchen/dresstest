package com.elsevier.config;

import com.elsevier.page.PageConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Configuration
@Import({PropertiesConfig.class, PageConfig.class})
@Component
public class BeanConfig {

}
