import java.util.List;
import java.util.Scanner;

public interface FilterMenu {
    void filter(Scanner scanner, List<Accident> ol);

    List<Accident> mainFilter(List<Accident> ll1, String inputField, String keyword, String value, List<Accident> ol);
}
