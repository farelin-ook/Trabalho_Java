package Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Model.BO.FuncionarioRN;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class NovoFuncionarioController implements Initializable {
    
    FuncionarioRN a = new FuncionarioRN();
    Alert b = new Alert(AlertType.NONE);

    @FXML
    private TextField tSenha;

    @FXML
    private Button bCancela;

    @FXML
    private TextField tNome;

    @FXML
    private Button bConfirma;

    @FXML
    private ChoiceBox<String> cSelectCargo;

    @FXML
    private TextField tId;

    @FXML
    void aConfirma(ActionEvent event) throws SQLException {
        
        if(tNome.getText().equals("")|| tId.getText().equals("")|| tSenha.getText().equals("")|| String.valueOf(cSelectCargo.getValue()).equals("")){
            b.setAlertType(AlertType.ERROR);
            b.setContentText("Um dos campos esta invalido");
            b.show();
        } else {
             
            b.setAlertType(AlertType.CONFIRMATION);
            b.setContentText("Deseja Adicionar este funcionario?");
            b.showAndWait().ifPresent((resposta) -> {
                if(resposta == ButtonType.OK){
                    try {
                        a.registar(a.validacao(tId.getText()), tNome, tSenha, tId, cSelectCargo);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }    
                }
            });
            
            //a.registar(a.validacao(tId.getText()), tNome, tSenha, tId, cSelectCargo);
            
            
        }
    }

    @FXML
    void aCancela(ActionEvent event) {
        tSenha.setText("");
        tNome.setText("");
        tId.setText("");
        Main.chamarTela("Funcionario");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cSelectCargo.getItems().addAll("Gerente", "Vendedor");
    }

}
