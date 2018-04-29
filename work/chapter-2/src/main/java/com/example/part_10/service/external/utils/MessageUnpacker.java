package com.example.part_10.service.external.utils;

import java.util.Map;

public interface MessageUnpacker {
    boolean supports(String messageType);

    Map<String, Object> unpack(String message);
}
