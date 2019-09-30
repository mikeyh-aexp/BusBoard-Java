package training.busboard.web;

import training.busboard.Arrivals;

import java.util.List;

public class BusInfo {
    private final String postcode;
    private final List<Arrivals> arrivals;


    public BusInfo(String postcode, List<Arrivals> arrivals) {
        this.postcode = postcode;
        this.arrivals = arrivals;
    }

    public String getPostcode() {
        return postcode;
    }

    public List<Arrivals> getArrivals() {
        return arrivals;
    }

}
