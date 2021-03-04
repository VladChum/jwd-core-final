package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.ApplicationProperties;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertyReaderUtil {
    private static final Logger logger = Logger.getLogger(PropertyReaderUtil.class);

    private static final Properties properties = new Properties();
    public static ApplicationProperties applicationProperties;

    private PropertyReaderUtil() {
    }

    /**
     * try-with-resource using FileInputStream
     *
     * @see {https://www.netjstech.com/2017/09/how-to-read-properties-file-in-java.html for an example}
     * <p>
     * as a result - you should populate {@link ApplicationProperties} with corresponding
     * values from property file
     */
    public static void loadProperties() {
        final String propertiesFileName = "src/main/resources/application.properties";
        InputStream iStream = null;
        try {
            iStream = new FileInputStream(propertiesFileName);
            properties.load(iStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (iStream != null) {
                    iStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        readProperties();

        logger.log(Level.INFO, "Properties were write in stream !");
    }

    public static void readProperties() {
        logger.log(Level.INFO ,"Properties are filed !");

        applicationProperties = new ApplicationProperties(
                properties.getProperty("inputRootDir"),
                properties.getProperty("outputRootDir"),
                properties.getProperty("crewFileName"),
                properties.getProperty("missionsFileName"),
                properties.getProperty("planetFileName"),
                properties.getProperty("spaceshipsFileName"),
                Integer.valueOf(properties.getProperty("fileRefreshRate")),
                properties.getProperty("dateTimeFormat"));
    }
}
