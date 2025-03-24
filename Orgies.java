import java.io.File;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import categoriser.FileCategoriser;
import categoriser.PropertiesFileCategoriser;
import mover.FileMover;

import java.io.IOException;

public class Orgies {
    private final FileCategoriser categoriser;
    private final FileMover mover;
    private String directoryPath;

    public Orgies(String directoryPath, String configPath, Optional<String> moveFlag) {
        this.categoriser = new PropertiesFileCategoriser(configPath);
        this.mover = new FileMover(moveFlag.orElse("-r"));
        this.directoryPath = directoryPath;
    }

    public void sortDirectory() {
        File sortDirectory = new File(this.directoryPath);
        // check if directory exists
        if (!sortDirectory.exists() || !sortDirectory.isDirectory()) {
            System.out.println("Directory does not exist");
            return;
        } 
        // list files in directory
        File[] files = sortDirectory.listFiles();
        for (File file : files) {
            String directoryCategory = categoriser.categorise(file);

            if (directoryCategory == null) {
                continue;
            }
            
            Path targetDirectory = Paths.get(directoryCategory);
            this.mover.moveFile(file, targetDirectory);
        }
    }
        
    public static void main(String[] args) {
        // check argument length
        if (args.length < 2) {
            throw new RuntimeException("Invalid number of arguments, usage: java Orgies <directory_path> <config_path> [optional]<move_option_flag>");
        }
        // get arguments
        String directoryPath = args[0];
        String configPath = args[1];
        Optional<String> moveFlag = (args.length >= 3) ? Optional.of(args[2]) : Optional.empty();
        // utilise client to sort directory
        Orgies orgies = new Orgies(directoryPath, configPath, moveFlag);
        orgies.sortDirectory();
    }
}
