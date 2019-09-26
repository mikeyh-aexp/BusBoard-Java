package training.busboard;

import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.io.Reader;
import java.io.StringReader;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) throws KeyManagementException, NoSuchAlgorithmException {

        System.setProperty("http.proxyHost", "localhost");
        System.setProperty("http.proxyPort", "9090");
        System.setProperty("https.proxyHost", "localhost");
        System.setProperty("https.proxyPort", "9090");

        SSLContext sslcontext = SSLContext.getInstance("TLS");

        sslcontext.init(null, new TrustManager[]{new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] arg0, String arg1) {}
            public void checkServerTrusted(X509Certificate[] arg0, String arg1) {}
            public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
        }}, new java.security.SecureRandom());


        // Set up a scanner input to allow user to input stopcode
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please enter stop code here: ");
        String inputText = userInput.nextLine();
        String userResponse = "https://api.tfl.gov.uk/StopPoint/" + inputText + "/Arrivals";

        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).sslContext(sslcontext).hostnameVerifier((s1, s2) -> true).build();
        String response = client.target(userResponse)
                .request("text/json")
                .get(String.class);

        // ----------------------------

        // Populating a list of arrivals using JSON data
        List<Arrivals> arrivals = client
                .target(userResponse)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<List<Arrivals>>() {});

        // Sorts arrival times into ascending order
        Collections.sort(arrivals);

        // Loops through arrivals array and prints out next 5 buses
        for( int i = 0; i < 5; i++ ) {
            Arrivals busInfo = arrivals.get(i);
            int arrivalTime = arrivals.get(i).getTimeToStation() / 60;
            if( arrivalTime == 0 ) {
                System.out.println("Bus " + busInfo.getLineId() + " is due at " + busInfo.getDestinationName());
            } else if( arrivalTime == 1 ) {
                System.out.println("Bus " + busInfo.getLineId() + " is arriving at " + busInfo.getDestinationName() + " in " + arrivalTime + " minute");
            } else {
                System.out.println("Bus " + busInfo.getLineId() + " is arriving at " + busInfo.getDestinationName() + " in " + arrivalTime + " minutes");
            }
        }



    }



}