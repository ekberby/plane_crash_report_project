import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchMenuImpl implements SearchMenu {

    @Override
    public void search(Scanner scanner, List<Accident> ol){
        final PrintMenu printMenu= new PrintMenu();
        final FileManager fileManager = new FileManagerImpl();
        Field[] fields;
        Class<? extends Accident> ro = ol.get(0).getClass();
        fields = ro.getDeclaredFields();

        String[] finalInput;
        System.out.println("{Field} {number or text}");
        scanner.nextLine();
        String choicer2 = scanner.nextLine();
        finalInput = choicer2.split(" ");
        List<Accident> ll = new ArrayList<>();
        for (Field z: fields){
            z.setAccessible(true);
            if(z.getName().startsWith(finalInput[0])){
                for(Accident obj: ol){
                    Object value = null;
                    try {
                        value = z.get(obj);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    switch (finalInput[0]) {
                        case "location":
                        case "operator":
                        case "flight":
                        case "route":
                        case "type":
                        case "registration":
                        case "summary":
                        case "clustId":
                        case "cnln":
                            if (value.toString().contains(finalInput[1])) {
                                ll.add(obj);
                            }
                            break;
                        case "date":
                        case "time":
                            if (value.toString().equals(finalInput[1])) {
                                ll.add(obj);
                            }
                            break;
                        case "aboard":
                        case "fatality":
                        case "ground":
                        case "survivors":
                        case "survivorsRate":
                            if ((int) value == Integer.parseInt(finalInput[1])) {
                                ll.add(obj);
                            }
                            break;
                        default:
                            System.out.println("Wrong Command!");
                    }
                }
            }
        }
        if (!ll.isEmpty()){
            printMenu.printListPlus(ll, scanner);          // printing the results with all fields and given fields
            System.out.println("\n\n");
            fileManager.optionalCreateReport(ll, scanner);   // creating a report
        }
        else System.out.println("\n\nUnfortunately, there is not any record on a given inputs!!!\n");
    }
}
