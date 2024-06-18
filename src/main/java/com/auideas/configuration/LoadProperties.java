package com.auideas.configuration;



import java.util.Properties;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;

public class LoadProperties {
    public static Properties env = loadProperties();

    private static  Properties loadProperties(){
        Properties properties = new Properties();
        Dotenv dotenv = Dotenv.configure().directory(System.getProperty("user.dir") + "/").filename(".env").load();
        for (DotenvEntry entry : dotenv.entries()) {
            properties.put(entry.getKey(), entry.getValue());
        }
        return properties;
    }

}
    
