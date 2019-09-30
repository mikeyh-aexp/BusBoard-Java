package training.busboard.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import training.busboard.Arrivals;

import java.util.ArrayList;
import java.util.List;

@Controller
@EnableAutoConfiguration
public class Website {

    @RequestMapping("/")
    ModelAndView home() {
        return new ModelAndView("index");
    }

    @RequestMapping("/busInfo")
    ModelAndView busInfo(@RequestParam("postcode") String postcode) {
        List<Arrivals> arrivals = new ArrayList<>();
        Arrivals arrival = new Arrivals();
        arrival.destinationName = "Mackie Avenue";
        arrival.direction = "Towards Churchill Square";
        arrival.expectedArrival = "13.06pm";
        arrivals.add(arrival);

        return new ModelAndView("info", "busInfo", new BusInfo(postcode, arrivals));
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Website.class, args);
    }

}