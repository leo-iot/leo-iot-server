package at.htl.control;

import at.htl.entity.Measurement;
import at.htl.repository.MeasurementRepository;
import at.htl.repository.MessageRepository;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

public class InitBean {

    @Inject
    EntityManager em;

    @Inject
    MessageRepository messageRepository;

    @Inject
    MeasurementRepository measurementRepository;

    @Transactional
    void onStart(@Observes StartupEvent ev) {
//        System.out.println("processing");
//        messageRepository.processingMessage(
//                "leo-iot/og/107/noise/",
//                "{\"timestamp\":1634634132,\"value\":639.00}"
//        );
//
//        measurementRepository
//                .listAll()
//                .stream()
//                .map(Measurement::getValue)
//                .forEach(System.out::println);

    }
}
