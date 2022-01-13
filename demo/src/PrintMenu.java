import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrintMenu {
    public void printListPlus(List<Accident> ll1, Scanner scanner){
        Field[] fields;
        Class<? extends Accident> ro = ll1.get(0).getClass();
        fields = ro.getDeclaredFields();
        if(ll1.isEmpty()){
            System.out.println("we couldn't find any record:(");
        }
        else{
            System.out.println("We have found "+ ll1.size() +" Accidents!"+"\nPRESS 1 to list the accidents with all fields...\nPRESS 2 to list on specific fields...");
            int choicer4 = scanner.nextInt();
            if (choicer4 == 1){
                for (int i= 0; i< ll1.size(); i++){
                    System.out.println("CRASH#"+i+":\n"+"Date: "+ll1.get(i).getDate()+"\nTime: "+ ll1.get(i).getTime()+
                            "\nLocation: " + ll1.get(i).getLocation() +"\nOperator: "+ll1.get(i).getOperator()+ "\nFlight: "
                            +ll1.get(i).getFlight()+"\nRoute: "+ll1.get(i).getRoute()+"\nType: "+ ll1.get(i).getType()+
                            "\nRegistration: " +ll1.get(i).getRegistration()+"\nCn.ln: "+ll1.get(i).getCnln()+"\nAboard: "+
                            ll1.get(i).getAboard()+"\nFatalities: " + ll1.get(i).getFatality()+"\nGround: "+ll1.get(i).getGround()+
                            "\nSurvivors: "+ ll1.get(i).getSurvivors()+"\nSurvival Rate: "+ ll1.get(i).getSurvivorRate()+
                            "\nSummary: "+ll1.get(i).getSummary()+ "\nClust ID: "+ll1.get(i).getClustId()+
                            "\n-------------------------------------------------------------\n");
                }
            }
            else if (choicer4 == 2){
                System.out.println("enter specific fields:\n");
                List<Field> ff = new ArrayList<>();
                scanner.nextLine();

                String str1 = scanner.nextLine();
                String[] nameOfFields = str1.split("\\s*,\\s*");
                for(Field field: fields){
                    field.setAccessible(true);
                    for(String name: nameOfFields){
                        if (field.getName().equals(name)){
                            ff.add(field);
                        }
                    }
                }
                StringBuilder str10= new StringBuilder();
                for(Accident stringObj: ll1){
                    str1 = stringObj.toString();
                    for(Field field: ff){
                        field.setAccessible(true);
                        if (str1.contains(field.getName())){
                            String temp1 = "";
                            int e = str1.indexOf(";", str1.indexOf(field.getName()) );
                            temp1 += str1.substring(str1.indexOf(field.getName()), e) + "; ";
                            str10.append(temp1);
                        }
                    }
                    System.out.println("[" + str10 + "]");
                    str10 = new StringBuilder();
                }
            }
        }
    }
}
