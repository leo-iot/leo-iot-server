package at.htl;

import at.htl.shared.Module;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

@ApplicationScoped
public class Main {

    @Inject
    Instance<Module> modules;

    public void start(@Observes StartupEvent event) {
        modules.stream()
                .forEach(Module::start);
    }

    public void stop(@Observes ShutdownEvent event) {
        modules.stream()
                .forEach(Module::stop);
    }

}
