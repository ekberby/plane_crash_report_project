import java.lang.reflect.Field;
import java.util.*;

public class Main{

    static List<Accident> ol = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static final FileManager fileManager = new FileManagerImpl();
    static final ListMenu listMenu = new ListMenuImpl();
    static final SortMenu sortMenu = new SortMenuImpl();
    static final SearchMenu searchMenu = new SearchMenuImpl();
    static final FilterMenu filterMenu = new FilterMenuImpl();
    public static void main(String[] args){
        ol = fileManager.readFromFile();
        System.out.println("\nWelcome to our project!!!\n");
        System.out.println("\n\t#PRESS 1 to list...\n\t#PRESS 2 to sort...\n\t#PRESS 3 to search...\n\t#PRESS 4 to list " +
                "field names...\n\t#PRESS 5 to filter...\n\t#PRESS 0 to exit...\n");
        try {
            int choice = scanner.nextInt();

            while (choice != 0) {
                switchMenu(choice);
                System.out.println("\n\t#PRESS 1 to list...\n\t#PRESS 2 to sort...\n\t#PRESS 3 to search...\n\t#PRESS 4 to list field names...\n\t#PRESS 5 to filter...\n\t#PRESS 0 to exit...\n");
                choice = scanner.nextInt();
            }
        }
        catch (InputMismatchException e){
            System.out.println("Input mismatch! You cannot enter characters except numbers. Please follow the guides " +
                    "provided to you on the program menu.");
        }
    }

    static void switchMenu(int choice){
        try {
            switch (choice) {
                case 1 -> listMenu.list(scanner, ol);
                case 2 -> sortMenu.sortAnyField(scanner, ol);
                case 3 -> searchMenu.search(scanner, ol);
                case 4 -> columnName();
                case 5 -> filterMenu.filter(scanner, ol);
                default -> System.out.println("Wrong command! Please enter again:");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Your input is not suitable with the program's input range. Please make sure you are " +
                    "following the menu key guides.");
        }
        catch (NumberFormatException e){
            System.out.println("Wrong number format! It may occur because of entering integer instead of float. Please " +
                    "check the list of the fields in .csv file in order to see the specified field's required number formats.");
        }
    }

    static void columnName(){
        Field[] fields;
        Class<? extends Accident> ro = ol.get(0).getClass();
        fields = ro.getDeclaredFields();
        System.out.println(" Field names:\t[" + fields[0].getName() +", "+ fields[1].getName() + ", " + fields[2].getName() +
                ", " + fields[3].getName() + ", " + fields[4].getName() + ", " + fields[5].getName() + ", " + fields[6].getName() +
                ", " + fields[7].getName() + ", " + fields[8].getName() + ", " + fields[9].getName() + ", " + fields[10].getName() +
                ", " + fields[11].getName() + ", " + fields[12].getName() + ", " + fields[13].getName() + ", " + fields[14].getName()
                + ", " + fields[15].getName() + "]");
    }

}
