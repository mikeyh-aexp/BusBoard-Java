package training.busboard;

public class Client {
    String naptanID;   //this is the stopCode
    String route;
    String destination;
    Integer timeToStation;

    Client(String naptanID, String route, String destination, int timeToStation) {
        this.naptanID = naptanID;
        this.route = route;
        this.destination = destination;
        this.timeToStation = timeToStation;
    }

}
