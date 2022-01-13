import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("ALL")
public class SortMenuImpl implements SortMenu{

    @Override
    public void sortAnyField(Scanner scanner, List<Accident> ol) {
        System.out.println("To sort entities based on a field\nEnter following:\n\t{field name} {ASC|DESC}\n\tPRESS 'E' " +
                "to go back to main menu\n");
        String input;
        String field;
        String order;
        scanner.nextLine();
        input = scanner.nextLine();
        input = input.toUpperCase();
        field = input.split(" ")[0].trim();
        order = input.split(" ")[1].trim();
        Comparator<Accident> accidentComparator;
        switch (field) {
            case "DATE" -> {
                accidentComparator = Comparator.comparing(Accident::getDate);
                switchOrder(order, ol, accidentComparator);
            }
            case "TIME" -> {
                accidentComparator = Comparator.comparing(Accident::getTime);
                switchOrder(order, ol, accidentComparator);
            }
            case "lOCATION" -> {
                accidentComparator = Comparator.comparing(Accident::getLocation);
                switchOrder(order, ol, accidentComparator);
            }
            case "OPERATOR" -> {
                accidentComparator = Comparator.comparing(Accident::getOperator);
                switchOrder(order, ol, accidentComparator);
            }
            case "FLIGHT" -> {
                accidentComparator = Comparator.comparing(Accident::getFlight);
                switchOrder(order, ol, accidentComparator);
            }
            case "ROUTE" -> {
                accidentComparator = Comparator.comparing(Accident::getRoute);
                switchOrder(order, ol, accidentComparator);
            }
            case "TYPE" -> {
                accidentComparator = Comparator.comparing(Accident::getType);
                switchOrder(order, ol, accidentComparator);
            }
            case "REGISTRATION" -> {
                accidentComparator = Comparator.comparing(Accident::getRegistration);
                switchOrder(order, ol, accidentComparator);
            }
            case "CNLN" -> {
                accidentComparator = Comparator.comparing(Accident::getCnln);
                switchOrder(order, ol, accidentComparator);
            }
            case "ABOARD" -> {
                accidentComparator = Comparator.comparing(Accident::getAboard);
                switchOrder(order, ol, accidentComparator);
            }
            case "FATALITY" -> {
                accidentComparator = Comparator.comparing(Accident::getFatality);
                switchOrder(order, ol, accidentComparator);
            }
            case "GROUND" -> {
                accidentComparator = Comparator.comparing(Accident::getGround);
                switchOrder(order, ol, accidentComparator);
            }
            case "SURVIVORS" -> {
                accidentComparator = Comparator.comparing(Accident::getSurvivors);switchOrder(order, ol, accidentComparator);
            }
            case "SURVIVORRATE" -> {
                accidentComparator = Comparator.comparing(Accident::getSurvivorRate);
                switchOrder(order, ol, accidentComparator);
            }
            case "SUMMARY" -> {
                accidentComparator = Comparator.comparing(Accident::getSummary);
                switchOrder(order, ol, accidentComparator);
            }
            case "CLUSTID" -> {
                accidentComparator = Comparator.comparing(Accident::getClustId);
                switchOrder(order, ol, accidentComparator);
            }
            default -> System.out.println("Wrong Command!");
        }
        System.out.println("""
                The list sorted successfully! Please Press List button in the main menu to see the sorted list according to given list...
                
                """);
    }

    public void switchOrder(String order, List<Accident> crashList, Comparator<Accident> accidentComparator){
        switch (order) {
            case "ASC" -> crashList.sort(accidentComparator);
            case "DESC" -> crashList.sort(accidentComparator.reversed());
            default -> System.out.println("Wrong Command!");
        }
    }
}
