package repo;

import model.Waste;

public interface WasteRepo {

    void addWaste(Waste waste);

    void updateWaste(Waste waste);

    void deleteWaste(Waste waste);

}
