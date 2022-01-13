import java.util.List;
import java.util.Scanner;

public interface FileManager {
    List<Accident> readFromFile();
    void optionalCreateReport(List<Accident> list, Scanner scanner);
}
