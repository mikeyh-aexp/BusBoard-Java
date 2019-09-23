package training.busboard;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Scanner;

public class Main {

    private static void createUserInput(String APIString) {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter a stop code below: ");
        String userInput = in.nextLine();
        APIString += userInput +"/Arrivals";
        System.out.println(APIString);
    }

    public static void main(String args[]) {

//        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
//        String name = client.target("https://api.tfl.gov.uk/StopPoint/490008660N").request(MediaType.TEXT_PLAIN).get(String.class);
        String APIString = "https://api.tfl.gov.uk/StopPoint/";

        createUserInput(APIString);

    }

}	
