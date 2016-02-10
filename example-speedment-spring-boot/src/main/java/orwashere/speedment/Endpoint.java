package orwashere.speedment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by fdirlikl on 10/27/2015.
 */
@Component
@Path("/contacts")
public class Endpoint {

    private Service service;

    @Autowired
    public Endpoint(Service service) {
        this.service = service;
    }

    @GET
    public String message() {
        return this.service.message();
    }

}