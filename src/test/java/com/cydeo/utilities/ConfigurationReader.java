package com.cydeo.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {
    //1-create a properties object
    //make it "static" so we are limiting access to the object
    //"static" to make sure its created and loaded before everything else
    public static Properties properties= new Properties();
    static {
        try {
            //2-open a file with FileInputStream(open file)
            FileInputStream file = new FileInputStream("configuration.properties");

            //3-load "properties" object with the "file".(load properties)
            properties.load(file);
            file.close();
        } catch (IOException e) {

            System.out.println("File not found with given path");
            e.printStackTrace();
        }
    }
    //create utility method to use the object to read
    //4-use the "properties" object to read from the file(read properties)

    public static String getProperties(String keyword){
        return properties.getProperty(keyword);
    }
}

