import java.io.File;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public void organise(String directory) {
        File dir = new File(directory);
        // check if directory exists
        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("Directory does not exist");
            return;
        } 
        // list files in directory
        File[] files = dir.listFiles();
        if (files == null) {
            System.out.println("No files in directory");
            return;
        }
        for (File file : files) {
            if (file.isFile()) {
                String fileType = getFileExtension(file);
                Path targetDirectory = Paths.get(directory, fileType);
                try {
                    // create target directory if it doesnt exist
                    Files.createDirectories(targetDirectory);
                    // move file to target directory
                    Files.move(file.toPath(), targetDirectory.resolve(file.getName()));
                } catch (IOException e) {
                    System.out.println("Error moving file: " + file.getName());
                    e.printStackTrace();
                }
            }
        }

}

    
    public static void main(String[] args) {
        String directoryPath = args[0];
        String configPath = args[1];

        organiseAllFiles(directoryPath);
    }