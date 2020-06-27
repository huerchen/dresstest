package com.elsevier.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.Arrays;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Configuration.startMaximized;
import static java.lang.System.setProperty;
import static java.util.Objects.requireNonNull;

@Configuration
@Component
@Import({BeanConfig.class})
public class StepDefConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(StepDefConfig.class);
    private final Config config;
    private final Environment environment;

    public StepDefConfig( final Config config, final Environment environment) {
        this.config = requireNonNull(config);
        this.environment = environment;
    }

    @PostConstruct
    public void setupSelenide() {
        LOGGER.info("Active profiles  {}", Arrays.toString(environment.getActiveProfiles()));
        LOGGER.info("Default profiles {}", Arrays.toString(environment.getDefaultProfiles()));
        LOGGER.info("Use {}", config);

        setProperty(config.driverProperty(), config.driverBin().toString());
        setProperty("browser.downloads.dir", config.browserDownloads().toString());

        if (config.browserBinary().isPresent()) {
            setProperty("portable.browser.bin", config.browserBinary().get().toString());
        }
        browser = config.driverType();
        startMaximized = false;
        baseUrl = config.baseUrl().toString();
    }
}
