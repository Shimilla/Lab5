package Dragon;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CollectionManager {
    private final String dateInit = new Date().toString();
    private List<Dragon> dragons = new ArrayList<>();

    public void addDragon(Dragon dragon) {
        dragons.add(dragon);
    }

    public String getDateInit() {
        return dateInit;
    }

    public void clear () {
        dragons.clear();
    }

    public void remove_by_id (int id) {
        for (Dragon dragon : dragons) {
            if (dragon.getId() == id) {
                dragons.remove(dragon);
            }
        }
    }

    public void remove_at (int index) {
        dragons.remove(index);
    }

    public Dragon getDragon (int index) {
        return dragons.get(index);
    }

    public List<Dragon> getDragons() {
        return dragons;
    }

    public void initDragons(List<Dragon> dragons) {
        this.dragons = dragons;
    }
}
