package mover;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileMover {
    private StandardCopyOption moveOption;

    public FileMover(String moveOptionFlag) {
        // move flag option enums
        switch(moveOptionFlag) {
            case "-a":
                this.moveOption = StandardCopyOption.ATOMIC_MOVE;
                break;
            case "-c":
                this.moveOption = StandardCopyOption.COPY_ATTRIBUTES;
                break;
            case "-r":
                this.moveOption = StandardCopyOption.REPLACE_EXISTING;
                break;
            default:
                // move flag not found
                throw new IllegalArgumentException("Invalid move flag: " + moveOptionFlag);
        }
          
    }
    // dev notes: possible expansion to include custom destination directory paths?
    private Path resolvePath(String directoryCategory) {
        String home = System.getProperty("user.home");
        return Path.of(home, directoryCategory);
    }

    public void moveFile(File file, String directoryCategory) {
        // generate home path to store new directories
        Path targetDirectory = resolvePath(directoryCategory);
        try {
            // check existence of moved to directory, create if none found
            if (!Files.exists(targetDirectory)) {
                Files.createDirectories(targetDirectory);
            }
            // move file with specific flags
            Path targetPath = targetDirectory.resolve(file.getName());
            Files.move(file.toPath(), targetPath, this.moveOption);
            System.out.println("Moved: " + file.getName() + " -> " + targetDirectory.getFileName());
        } catch (IOException e) {
            System.out.println("Failed to move: " + file.getName() + " due to " + e.getMessage());
        }
    }
}
