package controlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Waste;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import repo.impl.WasteRepoImpl;
import util.DialogManager;
import java.io.IOException;

public class MainController {
    private Stage mainStage;
    WasteRepoImpl wasteRepoImpl = new WasteRepoImpl();

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private TableView<Waste> tableWasted;

    @FXML
    private TableColumn<Waste, Integer> columnCodeWaste;
    @FXML
    private TableColumn<Waste, String> columnNameWaste;
    @FXML
    private TableColumn<Waste, String> columnDegreeOfDanger;
    @FXML
    private TableColumn<Waste, String> columnHazardClass;
    @FXML
    private TableColumn<Waste, String> columnKindOfActivity;
    @FXML
    private TableColumn<Waste, String> columnEducationStandard;

    private Parent fxmlEdit;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private EditDialogController editDialogController;
    private Stage editDialogStage;

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @FXML
    private void initialize() throws IOException, InvalidFormatException {
        columnCodeWaste.setCellValueFactory(new PropertyValueFactory<Waste, Integer>("codeWaste"));
        columnNameWaste.setCellValueFactory(new PropertyValueFactory<Waste, String>("nameWaste"));
        columnDegreeOfDanger.setCellValueFactory(new PropertyValueFactory<Waste, String>("degreeOfDanger"));
        columnHazardClass.setCellValueFactory(new PropertyValueFactory<Waste, String>("hazardClass"));
        columnKindOfActivity.setCellValueFactory(new PropertyValueFactory<Waste, String>("kindOfActivity"));
        columnEducationStandard.setCellValueFactory(new PropertyValueFactory<Waste, String>("educationStandard"));
        initListeners();
        fillData();
        initLoader();
    }

    private void fillData() throws IOException {
        wasteRepoImpl.getInfoExcel();
        tableWasted.setItems(wasteRepoImpl.getWasteList());
    }


    private void initLoader() {
            try {
                fxmlLoader.setLocation(getClass().getResource("../fxml/edit.fxml"));
                fxmlEdit = fxmlLoader.load();
                editDialogController = fxmlLoader.getController();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    private void initListeners() {
        tableWasted.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    editDialogController.setWaste((Waste)tableWasted.getSelectionModel().getSelectedItem());
                    showDialog();
                }
            }
        });
    }

    private void showDialog() {
        if (editDialogStage==null) {
            editDialogStage = new Stage();
            editDialogStage.setTitle("Редактирование записи");
            editDialogStage.setMinHeight(150);
            editDialogStage.setMinWidth(300);
            editDialogStage.setResizable(false);
            editDialogStage.setScene(new Scene(fxmlEdit));
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(mainStage);
        }

        editDialogStage.showAndWait();

    }

    public void actionButtonPressed(ActionEvent actionEvent) throws IOException {
        Object source = actionEvent.getSource();

        if (!(source instanceof Button)) {
            return;
        }

        Waste selectedWaste =  tableWasted.getSelectionModel().getSelectedItem();

        Button clickedButton = (Button) source;

        switch (clickedButton.getId()) {
            case "btnAdd":
                editDialogController.setWaste(new Waste());
                showDialog();
                wasteRepoImpl.addWaste(editDialogController.getWaste());
                break;

            case "btnEdit":
                if (!wasteIsSelected(selectedWaste)){
                    return;
                }
                editDialogController.setWaste((Waste) tableWasted.getSelectionModel().getSelectedItem());
                showDialog();
                break;

            case "btnDelete":
                if (!wasteIsSelected(selectedWaste)){
                    return;
                }
                wasteRepoImpl.deleteWaste((Waste) tableWasted.getSelectionModel().getSelectedItem());
                break;

            case "btnSave":
                wasteRepoImpl.saveInfoExcel(tableWasted.getItems());
        }
    }

    private boolean wasteIsSelected(Waste selectedWaste) {
        if(selectedWaste == null){
            DialogManager.showInfoDialog("Ошибка", "Выберите отход");
            return false;
        }
        return true;
    }
}
