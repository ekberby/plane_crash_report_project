import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManagerImpl implements FileManager {
    @Override
    public List<Accident> readFromFile() {
        List<Accident> ol = new ArrayList<>();
        String[] arrForAccidents;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("Large_Passenger_Plane_Crashes_1933_to_2009.csv"));
            String line;
            int cnt = 0;
            while ((line = br.readLine()) != null) {
                if (cnt == 1) {
                     arrForAccidents = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                     assignAccidentAsAccident(arrForAccidents, ol);
                } else {
                    cnt++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return ol;
    }

    public static void assignAccidentAsAccident(String[] arrForAccidents, List<Accident> ol){
        Accident ac = new Accident(arrForAccidents[0],arrForAccidents[1],arrForAccidents[2],arrForAccidents[3],arrForAccidents[4]
                ,arrForAccidents[5],arrForAccidents[6],arrForAccidents[7],arrForAccidents[8],Integer.parseInt(arrForAccidents[9]),
                Integer.parseInt(arrForAccidents[10]), Integer.parseInt(arrForAccidents[11]), Integer.parseInt(arrForAccidents[12]),
                Double.parseDouble(arrForAccidents[13]), arrForAccidents[14], arrForAccidents[15]);
        ol.add(ac);
    }

    public void optionalCreateReport(List<Accident> list, Scanner scanner){
        String column = "Date; Time; Location; Operator; Flight; Route; Type; Registration; Cn.ln; Aboard; Fatalities; " +
                "Ground; Survivors; Survival Rate; Summary; Clust ID\n";
        System.out.println("Do you want to create a report?\n\n\t#PRESS 1 for yes...\n\t#PRESS 2 for no...");
        int choicer = scanner.nextInt();
        if (choicer == 1){
            try(BufferedWriter bw = new BufferedWriter(new FileWriter("report.csv"))){
                bw.write(column);
                for (Accident A: list){
                    bw.write(A.getDate()+";"+A.getTime()+";"+A.getLocation()+";"+A.getOperator()+";"+A.getFlight()+";"+
                            A.getRoute()+";"+A.getType()+";"+A.getRegistration()+";"+A.getCnln()+";"+A.getAboard()+";"+
                            A.getFatality()+";"+A.getGround()+";"+A.getSurvivors()+";"+A.getSurvivorRate()+";"+A.getSummary()+";"+
                            A.getClustId()+"\n");
                }
            }catch(Exception e){
                System.out.println("wawd");
            }
            System.out.println("Report created successfully!!!");
        }
        else System.out.println("Report is not created!");

    }
}
