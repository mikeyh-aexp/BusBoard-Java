package training.busboard;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Postcodes {
    String postcode;
    double latitude;
    double longitude;

    public String getPostcode() {
        return postcode;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}