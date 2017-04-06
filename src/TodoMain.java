import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TodoMain {
  private final static String FILE_NAME = "../data.csv";

  public static void main(String[] args) {
    List<String> todoLines = readLinesFromFile();

    if (args.length == 0) {
      Commands.printUsage();
    }
    else {
      if (args[0].equals("-l") && todoLines.size() != 0) {
        for (int i = 0; i < todoLines.size(); i++) {
          System.out.println(i + 1 + " - " + " " + todoLines.get(i));
        }
      } else if (args[0].equals("-l") && todoLines.size() == 0) {
        System.out.println("No more todos for today, great job!");
      }
      if (args[0].equals("-a")) {
        todoLines.add("[ ] " + args[1]);
      }

      if (args[0].equals("-r")) {
        todoLines.remove(args[1]);
      }

      if (args[0].equals("-c")) {
        todoLines.remove(args[1]);
        todoLines.add("[x] " + args[1]);
      }

      writeToFile(todoLines);
    }
  }

  private static List<String> readLinesFromFile() {
    Path path = Paths.get(FILE_NAME);
    List<String> todoLines;
    try {
      todoLines = Files.readAllLines(path);
    } catch (IOException e) {
      e.printStackTrace();
      todoLines = new ArrayList<>();
    }
    return todoLines;
  }

  private static void writeToFile(List<String> data) {
    Path path = Paths.get(FILE_NAME);
    try {
      Files.write(path, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}





