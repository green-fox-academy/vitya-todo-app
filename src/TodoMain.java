import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TodoMain {

  public static void main(String[] args) {
    Commands help = new Commands();
    System.out.println(help.printUsage());
  }

}
