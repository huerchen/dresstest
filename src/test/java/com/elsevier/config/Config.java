package com.elsevier.config;

import com.google.common.base.MoreObjects;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Optional;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static java.nio.file.Files.createDirectories;
import static java.nio.file.Files.exists;
import static java.nio.file.Files.isExecutable;
import static java.nio.file.Files.isRegularFile;
import static org.apache.commons.lang3.StringUtils.trimToNull;

public class Config {

    private final String driverType;
    private final String driverProperty;
    private final Path driverBin;
    private final Path browserBinary;
    private final Path browserDownloads;
    private final URL baseUrl;

    Config(final Builder builder) {
        driverType = builder.driverType;
        driverProperty = builder.driverProperty;
        driverBin = builder.driverBin;
        browserBinary = builder.browserBinary;
        browserDownloads = builder.browserDownloads;

        baseUrl = builder.baseUrl;

    }

    public String driverType() {
        return driverType;
    }

    public String driverProperty() {
        return driverProperty;
    }

    public Path driverBin() {
        return driverBin;
    }

    public Optional<Path> browserBinary() {
        return Optional.ofNullable(browserBinary);
    }

    public Path browserDownloads() {
        return browserDownloads;
    }

    public URL baseUrl() {
        return baseUrl;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("driverType", driverType)
                .add("driverProperty", driverProperty)
                .add("driverBin", driverBin)
                .add("browserBinary", browserBinary)
                .add("browserDownloads", browserDownloads)
                .add("arBaseUrl", baseUrl)
                .toString();
    }
    static class Builder {

        private String driverType;
        private String driverProperty;
        private Path driverBin;
        private Path browserBinary;
        private Path browserDownloads;
        private URL baseUrl;

        Builder baseUrl(final URL baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }
        Builder driverType(final String driverType) {
            this.driverType = checkNotNull(trimToNull(driverType));
            return this;
        }

        Builder driverProperty(final String driverProperty) {
            this.driverProperty = checkNotNull(trimToNull(driverProperty));
            return this;
        }

        Builder driverBin(final Path driverBin) {
            checkFile(driverBin);

            this.driverBin = driverBin.toAbsolutePath();
            return this;
        }

        Builder browserBinary(final Path browserBinaryPath) {
            if (browserBinaryPath != null) {
                browserBinary = browserBinaryPath.toAbsolutePath();

                checkState(
                        isExecutable(browserBinary),
                        "Browser binary does not exist or is not executable [" + browserBinary + "]");
            }
            return this;
        }

        Builder browserDownloadsDir(final Path browserDownloadsPath) {
            browserDownloads = browserDownloadsPath.toAbsolutePath();
            try {
                createDirectories(browserDownloads);
            } catch (IOException e) {
                throw new IllegalStateException(e.getMessage(), e);
            }
            return this;
        }

        Config build() {
            return new Config(this);
        }

        private static void checkFile(final Path file) {
            checkState(exists(file), "Could not find path [" + file + "]");
            checkState(isRegularFile(file), "Path [" + file + "] is not a file");
        }
    }
}
