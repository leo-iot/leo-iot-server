package at.htl;

import at.htl.entity.Location;
import at.htl.entity.Thing;
import at.htl.repository.LocationRepository;
import at.htl.repository.ThingRepository;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;

public class InitBean {

    @Inject
    LocationRepository locationRepository;

    @Inject
    ThingRepository thingRepository;

    public void init(@Observes StartupEvent startup){
        //initSampleData();
    }

    public void initSampleData(){
        initLocation();
        initThing();
    }

    @Transactional
    public void initLocation(){
        Location l1 = new Location(null, "HTL Leonding");
        Location l2 = new Location(l1, "eg");
        Location l3 = new Location(l2, "e59");
        Location l4 = new Location(l2, "e58");

        locationRepository.persist(l1);
        locationRepository.persist(l2);
        locationRepository.persist(l3);
        locationRepository.persist(l4);
    }

    @Transactional
    public void initThing(){
        Location l1 = locationRepository.findById(3L);
        Location l2 = locationRepository.findById(4L);

        Thing t1 = new Thing(l1, "LeoBox1");
        Thing t2 = new Thing(l2, "LeoBox1");

        thingRepository.persist(t1);
        thingRepository.persist(t2);
    }
}
