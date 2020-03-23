package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private JFXComboBox<String> marqueID;

    @FXML
    private JFXComboBox<String> color1ID;

    @FXML
    private JFXButton confirmBtn;

    @FXML
    private Label color1Err;

    @FXML
    private JFXComboBox<String> modelID;

    @FXML
    private Label portsErr;

    @FXML
    private Label modelErr;

    @FXML
    private Label marqueErr;

    @FXML
    private JFXComboBox<String> portesID;

    @FXML
    private Label portErr;

    @FXML
    private Label yearErr;

    @FXML
    private JFXComboBox<String> kmID;

    @FXML
    private Label kmErr;

    @FXML
    private Label conductTypeErr;

    @FXML
    private Label anneeErr1;

    @FXML
    private JFXComboBox<String> fuelTypeID;

    @FXML
    private Label fuelTypeErr;

    @FXML
    private ToggleGroup typeConduct;

    @FXML
    private JFXComboBox<String> color2ID;

    @FXML
    private ToggleGroup ports1;

    @FXML
    private JFXComboBox<String> yearID;

    @FXML
    private Label color2Err;

    @FXML
    private Label transmissionErr;

    @FXML
    private ToggleGroup transmission;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //SAMPLE DATA
        String[] AudiModels =  {"Q1","A3","A1","Q4","Q5","Q6","Q7","S3"};
        String[] BmwModels =  {"M6","M3","E31"};
        String[] MercedesModels =  {"AMG-GTS","Class A","A45","E245"};
        String[] FordModels =  {"Fiesta","GT","f-50"};


        HashMap<String,String[]> voitures = new HashMap<>();
        voitures.put("AUDI",AudiModels);
        voitures.put("BMW",BmwModels);
        voitures.put("Mercedes",MercedesModels);
        voitures.put("Ford",FordModels);


        //LATER make this as map with the Car's mark as a key and list of models as a value
        String[] extColors =  {"Rouge","Blue","Blanc","Noire","Carbon","Gris"};
        String[] intColors =  {"Beige","Blanc","Noire","Marron"};
        String[] portes =  {"2 Port","3 Port","4 Ports"};
        String[] kms =  {"Moins 10 000","Moins 50 000","Moins 100 000","Plus 150 000"};
        String[] energy =  {"Electricte","Benzin","Gasoil","Gaz","Hydrogen", "Hybrid"};

        //Initialization of UI Components
        //Ceci est une maniere d'ajouter un event handler a un component
        //elle equivalente a ***
        this.marqueID.setOnAction(
                event -> {
                    this.modelID.getItems().removeAll(this.modelID.getItems());
                    this.modelID.getItems().addAll(voitures.get(this.marqueID.getSelectionModel().getSelectedItem()));
                    this.modelID.setDisable(false);
                    marqueErr.setText("");
                }
        );
        initYears();
        yearID.setOnAction(event -> yearErr.setText(""));

        initVoitures(voitures);
        modelID.setOnAction(event -> modelErr.setText(""));

        init(this.color1ID,extColors);
        color1ID.setOnAction(event -> color1Err.setText(""));

        init(this.color2ID,intColors);
        color2ID.setOnAction(event -> color2Err.setText(""));

        init(this.portesID,portes);
        portesID.setOnAction(event -> portErr.setText(""));

        init(this.kmID,kms);
        kmID.setOnAction(event -> kmErr.setText(""));

        init(this.fuelTypeID,energy);
        fuelTypeID.setOnAction(event -> fuelTypeErr.setText(""));

        //the radio buttons
        typeConduct.selectedToggleProperty().addListener(
                (observable, oldValue, newValue) -> {

                        //get the radio button selected here
                        JFXRadioButton rb = (JFXRadioButton)(typeConduct.getSelectedToggle());
                        if(rb != null) {
                            System.out.println(rb.getText());
                            conductTypeErr.setText("");
                        }
                }
        );

        transmission.selectedToggleProperty().addListener(
                (observable, oldValue, newValue) -> {
                    //get the radio button selected here
                    JFXRadioButton rb = (JFXRadioButton)(transmission.getSelectedToggle());
                    if(rb != null) {
                        System.out.println(rb.getText());
                        transmissionErr.setText("");
                    }
                }
        );

    }
    private void initYears(){
        this.yearID.getItems().removeAll(this.yearID.getItems());
        for (int i = 2021; i > 1985; i--) {
            this.yearID.getItems().add(String.valueOf(i));
        }
    }
    private void initVoitures(HashMap<String, String[]> v){
        this.modelID.getItems().removeAll(modelID.getItems());
        this.marqueID.getItems().removeAll(marqueID.getItems());
        for (String mark:v.keySet()) {
            this.marqueID.getItems().add(mark);
        }
    }
    private void init(JFXComboBox<String> box, String[] items){
        box.getItems().removeAll(box.getItems());
        box.getItems().addAll(items);
    }

    //***
    @FXML
    void confirm(ActionEvent event) {
        //let's check if the user selected everything
        if (this.marqueID.getSelectionModel().getSelectedIndex() == -1){
            this.marqueErr.setText("select a mark");
            return;
        }
        if (this.modelID.getSelectionModel().getSelectedIndex() == -1){
            this.modelErr.setText("select a model");
            return;
        }
        if (this.yearID.getSelectionModel().getSelectedIndex() == -1){
            this.yearErr.setText("select a year");
            return;
        }
        if (this.color1ID.getSelectionModel().getSelectedIndex() == -1){
            this.color1Err.setText("select an interior color");
            return;
        }
        if (this.color2ID.getSelectionModel().getSelectedIndex() == -1){
            this.color2Err.setText("select an exterior color");
            return;
        }
        if (this.portesID.getSelectionModel().getSelectedIndex() == -1){
            this.portErr.setText("select how many doors");
            return;
        }
        if (this.kmID.getSelectionModel().getSelectedIndex() == -1){
            this.kmErr.setText("select km");
            return;
        }

        if(this.typeConduct.getSelectedToggle() == null){
            conductTypeErr.setText("must choose a type");
            return;
        }

        if(this.transmission.getSelectedToggle() == null){
            transmissionErr.setText("must choose a transmission type");
            return;
        }

        if (this.fuelTypeID.getSelectionModel().getSelectedIndex() == -1){
            this.fuelTypeErr.setText("select Feul Type");
            return;
        }

        confirmBtn.getParent().setStyle("-fx-background-color: rgba(12,108,244,0.6)");
    }

}
