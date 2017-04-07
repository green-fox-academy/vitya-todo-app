import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TodoMain {
  private final static String FILE_NAME = "../../../data.csv";

  public static void main(String[] args) {
    List<String> todoLines = readLinesFromFile();

    if (args.length == 0) {
      Commands.printUsage();
    } else if (args[0].equals("-l") && todoLines.size() != 0) {
      for (int i = 0; i < todoLines.size(); i++) {
        System.out.println(i + 1 + " - " + todoLines.get(i));
        writeToFile(todoLines);
      }
    } else if (args[0].equals("-l") && todoLines.size() == 0) {
      System.out.println("You have no todos yet.");

    } else if (args[0].equals("-a") && args.length == 1) {
      System.out.println("ERROR : Unable to add: no task provided");

    } else if (args[0].equals("-a")) {
      todoLines.add("[ ] " + args[1]);
      writeToFile(todoLines);

    } else if (args[0].equals("-r") && args.length == 1) {
      System.out.println("ERROR : Unable to remove: no removable task provided");

    } else if (args[0].equals("-r") && (Integer.parseInt(args[1]) > (todoLines.size()))) {
      System.out.println("ERROR : Unable to remove: task is not existing.");

    } else if (args[0].equals("-r")) {
      todoLines.remove(Integer.parseInt(args[1]) - 1);
      writeToFile(todoLines);

    } else if (args[0].equals("-c") && args.length == 1) {
      System.out.println("ERROR : Unable to  check: no index provided.");


    } else if (args[0].equals("-c")) {
      boolean isContains = false;
      for (int i = 0; i < todoLines.size(); i++) {
        String tempName = todoLines.get(i);
        if (tempName.equals("[ ] " + args[1])) {
          todoLines.remove(i);
          todoLines.add(i, "[x] " + args[1]);
          writeToFile(todoLines);
          isContains = true;
        } else if (tempName.equals("[x] " + args[1])) {
          todoLines.remove(i);
          todoLines.add(i, "[ ] " + args[1]);
          writeToFile(todoLines);
          isContains = true;
        }
      }
     if (isContains == false){
       System.out.println("ERROR : Unable to  check: task is not existing");
     }
    } else {
      System.out.println("ERROR : Unsupported argument , please type a valid argument.");
    }
    writeToFile(todoLines);
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