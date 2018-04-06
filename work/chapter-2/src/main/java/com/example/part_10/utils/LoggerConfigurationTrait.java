/**
 * Copyright (C) Zoomdata, Inc. 2012-2018. All rights reserved.
 */
package com.example.part_10.utils;

import com.example.part_10.Part10CryptoPlatform;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;

public class LoggerConfigurationTrait {
    // Configure logger
    static {
        InputStream stream = Part10CryptoPlatform.class
                .getClassLoader()
                .getResourceAsStream("logging.properties");
        try {
            LogManager.getLogManager().readConfiguration(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
