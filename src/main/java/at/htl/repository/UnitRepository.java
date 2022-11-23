package at.htl.repository;

import at.htl.entity.Location;
import at.htl.entity.Thing;
import at.htl.entity.Unit;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UnitRepository implements Repository<Unit, Long> {
    public Unit updateUnit(Unit unit) {
        Unit u = findById(unit.id);
        if(u == null){
            return null;
        }
        u.symbol = unit.symbol;
        return u;
    }
}
