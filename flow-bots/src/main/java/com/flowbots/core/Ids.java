// src/main/java/com/flowbots/core/Ids.java
package com.flowbots.core;

import java.util.concurrent.atomic.AtomicLong;

public final class Ids {
    private static final AtomicLong SEQ = new AtomicLong(1);
    public static String next(String prefix) {
        return prefix + "-" + SEQ.getAndIncrement();
    }
}
