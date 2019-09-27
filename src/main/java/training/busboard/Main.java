package training.busboard;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
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
        }}, new SecureRandom());

        // Set up a scanner input to allow user to input stopcode
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please enter post code here: ");
        String inputText = userInput.nextLine();
        String userResponse = "https://api.postcodes.io/postcodes/" + inputText;

        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).sslContext(sslcontext).hostnameVerifier((s1, s2) -> true).build();

        // ----------------------------

        PostcodeWrapper postcode = client
                .target(userResponse)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(PostcodeWrapper.class);

        StoppointWrapper stoppoint = client
                .target("https://api.tfl.gov.uk/StopPoint?stopTypes=NaptanPublicBusCoachTram&lat=" + postcode.result.getLatitude() + "&lon=" + postcode.result.getLongitude() + "&radius=1000")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(StoppointWrapper.class);


        System.out.println("The two nearest stops to the postcode, " + postcode.result.getPostcode() + " are: \n");

        for( int i = 0; i < 5; i++ ) {
            Stoppoint stoppointInfo = stoppoint.stopPoints[i];
            System.out.println( i + 1 + ":- " + stoppointInfo.getCommonName() + " stop going towards " + stoppointInfo.getAdditionalProperties().get(1).value);
        }

        System.out.println("\nEnter the number of the stop you want to see bus times for: ");
        int inputNumber = userInput.nextInt();


        List<Arrivals> arrivals = client
                .target("https://api.tfl.gov.uk/StopPoint/" + stoppoint.stopPoints[inputNumber - 1].getNaptanId() + "/Arrivals")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<List<Arrivals>>() {});
        // Sorts arrival times into ascending order
        Collections.sort(arrivals);


        System.out.println("Bus times for " + stoppoint.stopPoints[inputNumber - 1].getCommonName() + "\n");


//         Loops through arrivals array and prints out next 5 buses
        for( int i = 0; i < 5; i++ ) {
            try {
                Arrivals busInfo = arrivals.get(i);
                int arrivalTime = arrivals.get(i).getTimeToStation() / 60;
                if( arrivalTime == 0 ) {
                    System.out.println("Bus " + busInfo.getLineId() + " is due at " + busInfo.getDestinationName());
                } else if( arrivalTime == 1 ) {
                    System.out.println("Bus " + busInfo.getLineId() + " is arriving at " + busInfo.getDestinationName() + " in " + arrivalTime + " minute");
                } else {
                    System.out.println("Bus " + busInfo.getLineId() + " is arriving at " + busInfo.getDestinationName() + " in " + arrivalTime + " minutes");
                }
            } catch(IndexOutOfBoundsException e) {
                break;
            }
        }



    }



}