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
            this.extensionMap.load(input);
        } catch (IOException e) {
            // io error with config path
            throw new RuntimeException("Failed to load config file with error: " + e.getMessage());
        }
    }

    // categorises files based on the extension name
    @Override
    public String categorise(File file) {
        // get file name and extension
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf(".");
        // extension not found
        if (dotIndex == -1) {
            return null;
        }
        String fileExtension = fileName.substring(dotIndex + 1);
        if (this.extensionMap.containsKey(fileExtension)) {
            return this.extensionMap.getProperty(fileExtension);
        }
        // extension mapping not found
        return null;
    }
}