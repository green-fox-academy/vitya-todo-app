import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TodoMain {
  private final static String FILE_NAME = "data.csv";

  public static void main(String[] args) {
    Commands.printUsage();

    if (args[0].equals("-l") && args.length == 2) {
      String type = args[1];
    }
  }
  

  private static void writeToFile(List<String> data) {
    Path path = Paths.get(FILE_NAME);
    try {
      Files.write(path, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static List<String> readLinesFromFile() {
    Path path = Paths.get(FILE_NAME);
    List<String> rawLines;

    try {
      rawLines = Files.readAllLines(path);
    } catch (IOException e) {
      e.printStackTrace();
      rawLines = new ArrayList<>();
    }

    return rawLines;
  }
}
}
