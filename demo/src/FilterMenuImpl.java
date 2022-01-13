import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FilterMenuImpl implements FilterMenu {
    final FileManager fileManager = new FileManagerImpl();

    @Override
    public void filter(Scanner scanner, List<Accident> ol){
        final PrintMenu printMenu = new PrintMenu();
        System.out.println("Enter your fields names with specific keywords:\n\ne.g. : {field1}, {keyword1}, {value1}, {field2}, {keyword2}, {value2} .... {field n}, {keyword n}, {value n}\nNOTE! use only this example format, and don't forget keywords!");
        scanner.nextLine();
        String str = scanner.nextLine();
        String[] finalInput = str.split("\\s*,\\s*");
        List<String> inputFields = new ArrayList<>();
        List<String> keywords = new ArrayList<>();
        List<String> values = new ArrayList<>();
        List <Accident> ll1;


        for(int i = 0; i<finalInput.length-2; i+=3){
            inputFields.add(finalInput[i]);
            keywords.add(finalInput[i+1]);
            values.add(finalInput[i+2]);
        }
        System.out.println("\n\n");
        ll1=ol;
        for(int i = 0; i<inputFields.size(); i++){ // MAIN FILTERING MAIN FILTERING MAIN FILTERING
            ll1 = mainFilter(ll1, inputFields.get(i), keywords.get(i), values.get(i), ol);
        }
        if (!ll1.isEmpty()){
            printMenu.printListPlus(ll1, scanner);   // printing on the console regarding given fields or all fields
            System.out.println("\n\n");
            fileManager.optionalCreateReport(ll1, scanner); // report creator
        }
        else System.out.println("there is not any matched record :(");
    }

    @Override
    public List<Accident> mainFilter(List<Accident> ll1, String inputField, String keyword, String value, List<Accident> ol){
        Field[] fields;
        Class<? extends Accident> ro = ol.get(0).getClass();
        fields = ro.getDeclaredFields();
        List<Accident> newList1 = new ArrayList<>();

        for (Accident accident : ll1) {
            for (Field z : fields) {
                z.setAccessible(true);
                if (z.getName().equals(inputField)) {
                    Object objectValue = null;
                    try {
                        objectValue = z.get(accident);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    switch (inputField) {
                        case "location", "operator", "flight", "route", "type", "registration", "summary", "clustId", "cnln" -> {
                            if (keyword.equals("ct") && objectValue.toString().contains(value)) {
                                newList1.add(accident);
                            }
                            if (keyword.equals("eq") && objectValue.toString().equals(value)) {
                                newList1.add(accident);
                            }
                            if (keyword.equals("sw") && objectValue.toString().startsWith(value)) {
                                newList1.add(accident);
                            }
                            if (keyword.equals("ew") && objectValue.toString().endsWith(value)) {
                                newList1.add(accident);
                            }
                        }
                        case "fatality", "aboard", "survivors", "ground" -> {
                            if (keyword.equals("eq") && (int) objectValue == Integer.parseInt(value)) {
                                newList1.add(accident);
                            }
                            if (keyword.equals("gt") && (int) objectValue > Integer.parseInt(value)) {
                                newList1.add(accident);
                            }
                            if (keyword.equals("lt") && (int) objectValue < Integer.parseInt(value)) {
                                newList1.add(accident);
                            }
                            if (keyword.equals("ge") && (int) objectValue >= Integer.parseInt(value)) {
                                newList1.add(accident);
                            }
                            if (keyword.equals("le") && (int) objectValue <= Integer.parseInt(value)) {
                                newList1.add(accident);
                            }
                            if ((keyword.equals("bw"))) {
                                String[] range = value.split(" ");
                                if (((Integer.parseInt(range[0]) < (int) objectValue) && ((int) objectValue < Integer.parseInt(range[1])))) {
                                    newList1.add(accident);
                                }
                            }
                        }
                        case "survivorRate" -> {
                            if (keyword.equals("eq") && (double) objectValue == Double.parseDouble(value)) {
                                newList1.add(accident);
                            }
                            if (keyword.equals("gt") && (double) objectValue > Double.parseDouble(value)) {
                                newList1.add(accident);
                            }
                            if (keyword.equals("lt") && (double) objectValue < Double.parseDouble(value)) {
                                newList1.add(accident);
                            }
                            if (keyword.equals("ge") && (double) objectValue >= Double.parseDouble(value)) {
                                newList1.add(accident);
                            }
                            if (keyword.equals("le") && (double) objectValue <= Double.parseDouble(value)) {
                                newList1.add(accident);
                            }
                        }
                        case "date", "time" -> {
                            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                            SimpleDateFormat tf = new SimpleDateFormat("HH:mm");
                            if (keyword.equals("eq") && objectValue.toString().equals(value)) {
                                newList1.add(accident);
                            }
                            if (keyword.equals("gt")) {
                                if (inputField.equals("date")) {
                                    Date date1 = null;
                                    Date date2 = null;
                                    try {
                                        date1 = df.parse(value);
                                        date2 = df.parse(objectValue.toString());
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }

                                    if ((date1.compareTo(date2) < 0)) {
                                        newList1.add(accident);
                                    }
                                } else if (inputField.equals("time")) {
                                    String str1 = value.trim();
                                    if (!Objects.equals(objectValue.toString(), "")) {
                                        Date date1 = null;
                                        Date date2 = null;
                                        try {
                                            date1 = tf.parse(str1);
                                            date2 = tf.parse(objectValue.toString());
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                        if ((date1.compareTo(date2) < 0)) {
                                            newList1.add(accident);
                                        }
                                    }
                                }
                                else{
                                    System.out.println("Wrong Input");
                                }
                            }
                            if (keyword.equals("lt")) {
                                if (inputField.equals("date")) {
                                    Date date1 = null;
                                    Date date2 = null;
                                    try {
                                        date1 = df.parse(value);
                                        date2 = df.parse(objectValue.toString());
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }

                                    if ((date1.compareTo(date2) > 0)) {
                                        newList1.add(accident);
                                    }
                                } else if (inputField.equals("time") && !Objects.equals(objectValue.toString(), "")) {
                                    Date date1 = null;
                                    Date date2 = null;
                                    try {
                                        date1 = tf.parse(value);
                                        date2 = tf.parse(objectValue.toString());
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }

                                    if ((date1.compareTo(date2) > 0)) {
                                        newList1.add(accident);
                                    }
                                }
                                else{
                                    System.out.println("Wrong Input");
                                }
                            }
                            if (keyword.equals("ge")) {
                                if (inputField.equals("date")) {
                                    Date date1 = null;
                                    Date date2 = null;
                                    try {
                                        date1 = df.parse(value);
                                        date2 = df.parse(objectValue.toString());
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    if (((date1.compareTo(date2) == 0) || date1.compareTo(date2) < 0)) {
                                        newList1.add(accident);
                                    }
                                } else if (inputField.equals("time") && !Objects.equals(objectValue.toString(), "")) {
                                    Date date1 = null;
                                    Date date2 = null;
                                    try {
                                        date1 = tf.parse(value);
                                        date2 = tf.parse(objectValue.toString());
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    if ((date1.compareTo(date2) < 0 || date1.compareTo(date2) == 0)) {
                                        newList1.add(accident);
                                    }
                                }
                                else{
                                    System.out.println("Wrong Input");
                                }
                            }
                            if (keyword.equals("le")) {
                                if (inputField.equals("date")) {
                                    Date date1 = null;
                                    Date date2 = null;
                                    try {
                                        date1 = df.parse(value);
                                        date2 = df.parse(objectValue.toString());
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }

                                    if ((date1.compareTo(date2) > 0 || (date1.compareTo(date2) == 0))) {
                                        newList1.add(accident);
                                    }
                                }
                                if (inputField.equals("time") && !Objects.equals(objectValue.toString(), "")) {
                                    Date date1 = null;
                                    Date date2 = null;
                                    try {
                                        date1 = df.parse(value);
                                        date2 = df.parse(objectValue.toString().trim());
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    if ((date1.compareTo(date2) > 0) || (date1.compareTo(date2) == 0)) {
                                        newList1.add(accident);
                                    }
                                }
                            }
                            if (keyword.equals("Y")) {
                                Date date2 = null;
                                try {
                                    date2 = df.parse(objectValue.toString());
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                Calendar cal2 = Calendar.getInstance();
                                cal2.setTime(date2);
                                if (cal2.get(Calendar.YEAR) == Integer.parseInt(value)) {
                                    newList1.add(accident);
                                }
                            }
                            if (keyword.equals("M")) {
                                Date date2 = null;
                                try {
                                    date2 = df.parse(objectValue.toString());
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                Calendar cal2 = Calendar.getInstance();
                                cal2.setTime(date2);
                                if (cal2.get(Calendar.MONTH) == Integer.parseInt(value) - 1) {
                                    newList1.add(accident);
                                }
                            }
                            if (keyword.equals("D")) {
                                Date date2 = null;
                                try {
                                    date2 = df.parse(objectValue.toString());
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                Calendar cal2 = Calendar.getInstance();
                                cal2.setTime(date2);
                                if (cal2.get(Calendar.DAY_OF_MONTH) == Integer.parseInt(value)) {
                                    newList1.add(accident);
                                }
                            }
                            if (keyword.equals("bw")) {
                                Date date1 = null;
                                Date date2 = null;
                                Date date3 = null;
                                if (inputField.equals("date")) {
                                    String[] range = value.split(" ");
                                    try {
                                        date1 = df.parse(objectValue.toString());
                                        date2 = df.parse(range[0]);
                                        date3 = df.parse(range[1]);
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }

                                    if ((date2.compareTo(date1) < 0 && (date3.compareTo(date1) > 0))) {
                                        newList1.add(accident);
                                    }
                                }
                                if (inputField.equals("time")) {
                                    String[] range = value.split(" ");
                                    if (!Objects.equals(objectValue.toString(), "")) {
                                        try {
                                            date1 = df.parse(objectValue.toString());
                                            date2 = df.parse(range[0]);
                                            date3 = df.parse(range[1]);
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                        if ((date2.compareTo(date1) < 0 && (date3.compareTo(date1) > 0))) {
                                            newList1.add(accident);
                                        }
                                    }
                                }
                            }
                        }
                        default -> System.out.println("Wrong Command!");
                    }
                }
            }
        }
        return newList1;
    }
}
