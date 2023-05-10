package tn.esprit.Controller;


import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/maps")
@Slf4j
//@CrossOrigin(origins = "http://localhost:4200")
public class MapsController {
    @GetMapping("/geocode")
    public GeocodingResult[] geocodeAddress(@RequestParam("address") String address) throws Exception {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyA-dht1CwgRb5hN-MSL8Lo7xzStLo-BEL0")
                .build();

        log.info("Api key  " + context);
        GeocodingResult[] results = GeocodingApi.geocode(context, address).await();
        return results;
    }
}
