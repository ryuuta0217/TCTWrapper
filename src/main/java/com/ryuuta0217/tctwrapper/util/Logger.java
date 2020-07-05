package com.ryuuta0217.tctwrapper.util;

import org.bukkit.plugin.Plugin;

import java.util.function.Supplier;

public class Logger {
    protected static java.util.logging.Logger pluginLogger;

    public static void init(Plugin pluginInstance) {
        pluginLogger = pluginInstance.getLogger();
    }

    public static void info(String msg) {
        pluginLogger.info(msg);
    }

    public static void info(Supplier<String> msgSupplier) {
        pluginLogger.info(msgSupplier);
    }

    public static void warn(String msg) {
        pluginLogger.warning(msg);
    }

    public static void warn(Supplier<String> msgSupplier) {
        pluginLogger.warning(msgSupplier);
    }
}
