package com.belhard.bookstore.connection;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@RequiredArgsConstructor
public class ConfigurationManager {

    public final String config = "/application.properties";
    private Properties properties = loadProperties();

    private Properties loadProperties() {
        try(InputStream in = ConfigurationManager.class.getResourceAsStream(config);){
            Properties properties = new Properties();
            properties.load(in);
            return properties;
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getProperties(String property) {
        return properties.getProperty(property);
    }
}
