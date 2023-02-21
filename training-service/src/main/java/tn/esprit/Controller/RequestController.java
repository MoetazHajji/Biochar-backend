package tn.esprit.Controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.Entity.Request;
import tn.esprit.Interface.IRequestService;

import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
@RequestMapping("request")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class RequestController {
    final IRequestService requestService;


    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.POST})
    @ResponseStatus(HttpStatus.CREATED)
    public Request add_request(@QueryParam("id") Long training_id, @RequestBody Request r)
    {
        return requestService.add_request(training_id,r);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public void delete_request(@PathVariable("id") Long id)
    {
        requestService.delete_request(id);
    }

    @GetMapping
    public List<Request> getAll_request()
    {

        return requestService.getAll_request();
    }

}
