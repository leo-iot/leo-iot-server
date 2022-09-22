package at.htl.boundary;

import com.intuit.karate.junit5.Karate;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class LocationResourceTest {

    @Karate.Test
    Karate option_crud_in_productEndpointTest() {
        return Karate.run("locations").relativeTo(getClass());
    }
}
