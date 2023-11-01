import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Files;

public class ListDirectories {

    public static void main(String [] args) {

        Path directoryPath = FileSystems.getDefault().getPath(args[0]);

        try {
            DirectoryStream<Path> directoryStream = Files.newDirectoryStream(directoryPath);

            for (Path directoryEntry: directoryStream) {
                System.out.println(directoryEntry.toString());
            }
        } catch (IOException ioException) {
            System.out.println(ioException);
        }
    }
}
