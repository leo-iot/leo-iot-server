package at.htl.boundary;

import com.intuit.karate.junit5.Karate;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class LocationResourceTest {

    @Karate.Test
    Karate location_crud_EndpointTest() {
        return Karate.run("locations").relativeTo(getClass());
    }
}
