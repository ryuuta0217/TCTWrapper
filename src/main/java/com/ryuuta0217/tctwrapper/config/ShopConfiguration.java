package com.ryuuta0217.tctwrapper.config;

import org.bukkit.configuration.ConfigurationSection;

public class ShopConfiguration<T> {
    private final ConfigurationSection conf;
    private final String configKey;
    private final T value;

    public ShopConfiguration(ConfigurationSection shopConfigurationSection, String configKey, T value) {
        this.conf = shopConfigurationSection;
        this.configKey = configKey;
        this.value = value;
    }

    public T get() {
        return value;
    }

    public void set(T newValue) {
        conf.set(configKey, newValue);
    }
}