package orwashere.speedment;

import com.speedment.Manager;
import com.speedment.Speedment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import orwashere.speedment.dao.sampledb.sampledb.sampledb.contacts.Contacts;

import java.util.stream.Collectors;

/**
 * Created by fdirlikl on 10/27/2015.
 */
@Component
public class Service {

    @Autowired
    Speedment speedment;

    public String message() {
        Manager<Contacts> manager = speedment.managerOf(Contacts.class);
        String contacts = manager.stream()
                .map(Contacts::toJson)
                .collect(Collectors.joining(", "));
        return contacts;
    }

}