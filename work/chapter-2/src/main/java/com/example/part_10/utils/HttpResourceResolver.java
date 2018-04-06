/**
 * Copyright (C) Zoomdata, Inc. 2012-2018. All rights reserved.
 */
package com.example.part_10.utils;

import java.net.URI;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class HttpResourceResolver {
    private static final Logger logger = Logger.getLogger("http-server");

    public static String resourcePath(String fileName) {
        try {
            URI resourceUri = ClassLoader.getSystemResource(fileName).toURI();
            String location = Paths.get(resourceUri).toAbsolutePath().toString();
            logger.info("File [" + fileName + "] location: " + location);
            return location;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
