package com.elsevier.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import java.net.URL;
import java.nio.file.Path;

@Configuration
@PropertySource({"classpath:application.properties"})
public class PropertiesConfig {
    private static final String NO_DESTROY_METHOD = "";

    @Value("${baseUrl}")
    private URL rootUrl;

    @Value("${driver.bin.path}")
    private Path driverBin;

    @Value("${driver.type}")
    private String driverType;

    @Value("${driver.type.property}")
    private String driverProperty;

    @Value("${portable.browser.bin}")
    private Path browserBinary;

    @Value("${browser.downloads.dir}")
    private Path browserDownloads;

    @Bean(destroyMethod = NO_DESTROY_METHOD)
    public Config config() {
        return new Config.Builder()
                .driverType(driverType)
                .driverProperty(driverProperty)
                .driverBin(driverBin)
                .browserBinary(browserBinary)
                .browserDownloadsDir(browserDownloads)
                .baseUrl(rootUrl)
                .build();
    }
}
