package training.busboard;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Arrivals implements Comparable<Arrivals> {
    String $type;
    String id;
    private String naptanId;
    String stationName;
    String lineId;
    String direction;
    String bearing;
    String destinationNaptanID;
    String destinationName;
    int timeToStation;
    String currentLocation;
    String expectedArrival;

    public String get$type() {
        return $type;
    }

    public String getId() {
        return id;
    }

    public String getStationName() {
        return stationName;
    }

    public String getLineId() {
        return lineId;
    }

    public String getDirection() {
        return direction;
    }

    public String getBearing() {
        return bearing;
    }

    public String getDestinationNaptanID() {
        return destinationNaptanID;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public int getTimeToStation() {
        return timeToStation;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public String getExpectedArrival() {
        return expectedArrival;
    }

    public String getNaptanId() {
        return naptanId;
    }

    @Override
    public int compareTo(Arrivals a) {
        return this.timeToStation - a.getTimeToStation();
    }
}
