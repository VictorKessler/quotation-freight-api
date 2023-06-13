package com.victorkessler.quotationfreight.configuration;

import org.testcontainers.containers.DockerComposeContainer;

import java.io.File;

public class DockerComposeContainerWrapper extends DockerComposeContainer<DockerComposeContainerWrapper> {
    public DockerComposeContainerWrapper(final File file) {
        super(file);
    }
}
