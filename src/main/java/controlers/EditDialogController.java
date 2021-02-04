package controlers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Waste;
import util.DialogManager;

public class EditDialogController {
    @FXML
    private Button btnOk;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtCodeWaste;
    @FXML
    private TextField txtNameWaste;
    @FXML
    private TextField txtDegreeOfDanger;
    @FXML
    private TextField txtHazardClass;
    @FXML
    private TextField txtKindOfActivity;
    @FXML
    private TextField txtEducationStandard;

    private Waste waste;

    public void setWaste(Waste waste){
        if(waste == null){
            return;
        }
        this.waste = waste;
        txtCodeWaste.setText(String.valueOf(waste.getCodeWaste()));
        txtNameWaste.setText(waste.getNameWaste());
        txtDegreeOfDanger.setText(waste.getDegreeOfDanger());
        txtHazardClass.setText(waste.getHazardClass());
        txtKindOfActivity.setText(waste.getKindOfActivity());
        txtEducationStandard.setText(waste.getEducationStandard());
    }

    public Waste getWaste(){return waste; }

    public void actionSave(ActionEvent actionEvent) {
        if (!checkValues()){
            return;
        }
        waste.setCodeWaste(Integer.parseInt(txtCodeWaste.getText()));
        waste.setNameWaste(txtNameWaste.getText());
        waste.setDegreeOfDanger(txtDegreeOfDanger.getText());
        waste.setHazardClass(txtHazardClass.getText());
        waste.setKindOfActivity(txtKindOfActivity.getText());
        waste.setEducationStandard(txtEducationStandard.getText());
        actionClose(actionEvent);
    }

    public void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    private boolean checkValues() {
        if (txtNameWaste.getText().trim().length()==0 ||
                txtDegreeOfDanger.getText().trim().length()==0 ||
                txtHazardClass.getText().trim().length()==0 ||
                txtKindOfActivity.getText().trim().length()==0 ||
                txtEducationStandard.getText().trim().length()==0){
            DialogManager.showInfoDialog("Ошибка", "Заполните все поля");
            return false;
        }
        return true;
    }
}
