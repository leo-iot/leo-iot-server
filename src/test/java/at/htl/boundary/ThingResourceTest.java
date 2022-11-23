package at.htl.boundary;

import com.intuit.karate.junit5.Karate;
import io.quarkus.test.junit.QuarkusTest;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class ThingResourceTest {

    @Karate.Test
    Karate thing_crud_EndpointTest() {
        return Karate.run("things").relativeTo(getClass());
    }
}
