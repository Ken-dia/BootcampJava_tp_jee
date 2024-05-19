package com.samanecorp.secureapp.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private Properties properties;
    public PropertiesReader(String fileName)  throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
        this.properties = new Properties();
        this.properties.load(is);
    }

    public String getProperty(String name) {
        return this.properties.getProperty(name);
    }
}
