package D_BaiTapCoBan;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class File {

    private static final Logger logger = MyLogger.getLogger();

    static Path path = Paths.get("input.txt");

    // Search and filter file
    public static void searchAndFilterFile() {
        logger.log(Level.INFO, "Start search and filter file");
        try {
            Files.walkFileTree(path, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (attrs.isRegularFile() && file.toString().endsWith(".txt")) {
                        System.out.println("Có file: " + file);
                        logger.log(Level.INFO, "Detect file input.txt");
                    } else {
                        System.out.println("Không có file");
                        logger.log(Level.INFO, "No detect file input.txt");
                    }
                    return super.visitFile(file, attrs);
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    System.out.println("Không có file");
                    return super.visitFileFailed(file, exc);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Error don't find a file input.txt");
        }
    }

    // Read file
    public static void readFile() {
        try {
            BufferedReader bufferedReader = Files.newBufferedReader(path);
            String readLine = bufferedReader.readLine();
            int[] numbers = Arrays
                    .stream(readLine.split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            Arrays.stream(numbers).forEach(n -> System.out.print(n + " "));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Write file
    public static void writeFile() {
        Path wpath = Paths.get("abc.txt");
        try {
            String message = "Hello World";
            BufferedReader reader = new BufferedReader(new FileReader(wpath.toFile()));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n").append(message).append(",");
            }
            reader.close();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(wpath.toFile()));

            bufferedWriter.write(builder.toString());
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Read attributes in file
    public static void readAttributesFile() {
        try {

            // Read attributes file
            BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
            System.out.println("creationTime: " + attributes.creationTime().toInstant().atZone(ZoneId.systemDefault()));
            System.out.println("lastAccessTime: " + attributes.lastAccessTime().toInstant().atZone(ZoneId.systemDefault()));
            System.out.println("lastModifiedTime: " + attributes.lastModifiedTime().toInstant().atZone(ZoneId.systemDefault()));
            System.out.println("size: " + attributes.size());

            // Copy file
            Path pathTarget = Paths.get("copy.txt");
            Files.copy(path, pathTarget, StandardCopyOption.REPLACE_EXISTING);


        } catch (IOException e) {
            e.getMessage();
        }
    }
}
