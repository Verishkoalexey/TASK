package repo.impl;


import model.Waste;
import repo.WasteRepo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.ExcelDateReader;

import java.io.IOException;

public class WasteRepoImpl implements WasteRepo {

    private ObservableList<Waste> wasteList = FXCollections.observableArrayList();

    @Override
    public void addWaste(Waste waste) {
        wasteList.add(waste);
    }

    @Override
    public void updateWaste(Waste waste) {

    }

    @Override
    public void deleteWaste(Waste waste) {
        wasteList.remove(waste);
    }

    public ObservableList<Waste> getWasteList() {
        return wasteList;
    }

    public ObservableList<Waste> getInfoExcel() throws IOException {
        wasteList = ExcelDateReader.readFromExcel("Пример справочников.xlsx");
        return wasteList;
    }

    public void saveInfoExcel(ObservableList<Waste> wasteList) throws IOException {
        ExcelDateReader.writeIntoExcel("Пример справочников.xlsx",wasteList);
    }
}
