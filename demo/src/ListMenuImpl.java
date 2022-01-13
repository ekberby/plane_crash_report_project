import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListMenuImpl implements ListMenu{

    private static final String CRASH = "Crash#";
    private static final String DATE = "Date: ";

    @Override
    public void list(Scanner scanner, List<Accident> ol){
        System.out.println("PRESS '1' to list elements with all fields... (you can also give interval, if you enter 1 1 10 " +
                "as a input it will print 1 to 10 elements)");
        System.out.println("PRESS '2' to list specified field only...");
        System.out.println("PRESS 'E' to go back to main menu...");
        scanner.nextLine();
        String choice = scanner.nextLine();
        if (choice.charAt(0) == '1'){
            if(choice.length()==1) // list all elements
                for (int i=0; i< ol.size(); i++){
                    System.out.println("\n");
                    printAccident(ol.get(i), i);
                }
            else{              // list with intervals
                String[] temp0 = choice.split(" ");
                System.out.println(temp0[0] +" "+ temp0[1] +" "+ temp0[2]);
                int a = Integer.parseInt(temp0[1]);
                int b = Integer.parseInt(temp0[2]);
                while (a<=b){
                    printAccident(ol.get(a-1), a-1);
                    a++;
                }
            }
        }
        if (choice.charAt(0) == '2'){
            listSelectedFields(scanner, ol);// list according to given fields
        }
        // go back to main menu
    }

    @Override
    public void listSelectedFields(Scanner scanner, List<Accident> ol){
        System.out.println("\nInput Format: {field1}, {field2}, .... {field n}\n");
        String input = scanner.nextLine();
        String[] fields = input.split("\\s*,\\s*");
        String[] names = {"date", "time", "location","operator","flight","route","type","registration", "cnln", "aboard",
                "fatality", "ground", "survivors", "survivorRate", "summary", "clustId"};
        ArrayList<String> filtered = new ArrayList<>();
        for(String x: fields){
            for(String y: names){
                if(x.equals(y)){
                    filtered.add(x);
                }
            }
        }
        for (Accident accident : ol) {
            String str = accident.toString();
            StringBuilder temp = new StringBuilder();
            for (String z : filtered) {
                int e = str.indexOf(";", str.indexOf(z));
                temp.append(str, str.indexOf(z), e).append("; ");
            }
            System.out.println("[" + temp + "]");
        }
    }

    public void printAccident(Accident accident, int i){
        System.out.println(CRASH+i+":\n"+DATE+accident.getDate()+"\nTime: "+ accident.getTime()+"\nLocation: " +
                accident.getLocation() +"\nOperator: "+accident.getOperator()+
                "\nFlight: "+accident.getFlight()+"\nRoute: "+accident.getRoute()+"\nType: "+ accident.getType()+"\nRegistration: "
                +accident.getRegistration()+"\nCn.ln: "+accident.getCnln()+"\nAboard: "+ accident.getAboard()+"\nFatalities: " +
                accident.getFatality()+"\nGround: "+accident.getGround()+"\nSurvivors: "+ accident.getSurvivors()+"\nSurvival Rate: "+
                accident.getSurvivorRate()+"\nSummary: "+accident.getSummary()+ "\nClust ID: "+accident.getClustId()+
                "\n-------------------------------------------------------------\n");
    }
}
