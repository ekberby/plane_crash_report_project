public class Accident {
    private String date;
    private String time;
    private String location;
    private String operator;
    private String route;
    private String type;
    private String registration;
    private String cnln;
    private String flight;
    private int aboard;
    private int fatality;
    private int ground;
    private int survivors;
    private double survivorRate;
    private String summary;
    private String clustId;

    public Accident(String date, String time,String location,String operator,String flight,String route,String type,
                    String registration,String cnln,int aboard,int fatality, int ground,int survivors,Double survivorRate,
                    String summary,String clustId){
        this.date=date;this.location=location;this.operator=operator;this.flight=flight;
        this.route=route;this.type=type;this.registration=registration;this.cnln=cnln;
        this.aboard=aboard;this.fatality=fatality;this.ground=ground;this.survivors=survivors;
        this.survivorRate=survivorRate;this.summary=summary;this.clustId=clustId;
        if (time.length()==4){
            this.time="0"+time;
        }
        else{
            this.time=time;
        }
    }

    public String toString(){
        if (time.length()==4){
            time = "0" + time;
            return "[date: " + date +"; time: "+  time +"; location: "+ location +"; operator: "+ operator +"; flight: "+
                    flight +"; route: "+ route +"; type: "+ type +"; registration: "+ registration +"; cnln: "+ cnln +
                    "; aboard: "+ aboard +"; fatality: "+ fatality +"; ground: "+ ground+"; survivors: "+ survivors +
                    "; survivorRate: "+survivorRate +"; summary: "+ summary +"; clustId: "+ clustId +";]";
        }
        return "[date: " + date +"; time: "+ time +"; location: "+ location +"; operator: "+ operator +"; flight: "+ flight +
                "; route: "+ route +"; type: "+ type +"; registration: "+ registration +"; cnln: "+ cnln +"; aboard: "+
                aboard +"; fatality: "+ fatality +"; ground: "+ ground+"; survivors: "+ survivors +"; survivorRate: "+
                survivorRate +"; summary: "+ summary +"; clustId: "+ clustId +";]";
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public String getOperator() {
        return operator;
    }

    public String getRoute() {
        return route;
    }

    public String getType() {
        return type;
    }

    public String getRegistration() {
        return registration;
    }

    public String getCnln() {
        return cnln;
    }

    public String getFlight() {
        return flight;
    }

    public int getAboard() {
        return aboard;
    }

    public int getFatality() {
        return fatality;
    }

    public int getGround() {
        return ground;
    }

    public int getSurvivors() {
        return survivors;
    }

    public double getSurvivorRate() {
        return survivorRate;
    }

    public String getSummary() {
        return summary;
    }

    public String getClustId() {
        return clustId;
    }
}
