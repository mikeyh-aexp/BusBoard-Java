package training.busboard;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Arrivals implements Comparable<Arrivals> {
    public String $type;
    public String id;
    private String naptanId;
    public String stationName;
    public String lineId;
    public String direction;
    public String bearing;
    public String destinationNaptanID;
    public String destinationName;
    int timeToStation;
    public String currentLocation;
    public String expectedArrival;

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
