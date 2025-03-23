package categoriser;

import java.io.*;
import java.util.Properties;

public class PropertiesFileCategoriser implements FileCategoriser {
    // hashmap class for managing extension mapping
    private final Properties extensionMap = new Properties();

    // constructor to initialise extension map
    public PropertiesFileCategoriser(String configPath) {
        try {
            // file input stream for config
            InputStream input = new FileInputStream(configPath);
            prop.load(input);
        } catch (IOException e) {
            // io error with config path
            throw new RuntimeException("Failed to load config file with error: " e.getMessage());
        }
    }

    // categorises files based on the extension name
    @Override
    public String categorise(File file) {
        // get file name and extension
        String fileName = file.getName();
        int dotIndex = name.lastIndexOf(".");
        // extension not found
        if (dotIndex == -1) {
            return null;
        }
        String fileExtension = fileName.substring(dotIndex + 1);
        if (extensionMap.containsKey(fileExtension)) {
            return prop.getProperty(ext);
        }
        // extension mapping not found
        return null;
    }
}