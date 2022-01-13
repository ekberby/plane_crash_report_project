import java.util.List;
import java.util.Scanner;

public interface ListMenu {

    void list(Scanner scanner, List<Accident> ol);

    void listSelectedFields(Scanner scanner, List<Accident> ol);
}
