package Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Model.BO.FuncionarioRN;
import Model.VO.Funcionario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class EditarFuncionarioController implements Initializable {

    private Alert alert = new Alert(AlertType.NONE);
    private FuncionarioRN a = new FuncionarioRN();

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
    private Button bPuxar;    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cSelectCargo.getItems().addAll("Gerente", "Vendedor");
        
    }
    
    @FXML
    void aConfirma(ActionEvent event) {
        Funcionario f = new Funcionario();
        
        
        if(tNome.getText().equals("") || tSenha.getText().equals("") || tId.getText().equals("") || String.valueOf(cSelectCargo.getValue()).equals("")){
            alert.setAlertType(AlertType.ERROR);
            alert.setContentText("Algum campo se encontra nulo");
            alert.show();
        } else {
            alert.setAlertType(AlertType.CONFIRMATION);
            alert.setContentText("Realmente deseja alterar as informações deste Funcionario?");
            alert.showAndWait().ifPresent((resposta) -> {
                if(resposta == ButtonType.OK){
                    f.setCargo(String.valueOf(cSelectCargo.getValue()));
                    f.setNome(tNome.getText());
                    f.setSenha(tSenha.getText());
                    f.setIdFunc(Integer.valueOf(tId.getText()));
                    try {
                        a.editar(f);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @FXML
    void aCancela(ActionEvent event) {
        Main.chamarTela("Funcionario");
    }

    @FXML
    void aPuxar(ActionEvent event) {
        Funcionario f = new Funcionario();
        f = FuncionarioRN.getSalvar();    

        tNome.setText(f.getNome());
        tId.setText(String.valueOf(f.getIdFunc()));
        tSenha.setText(f.getSenha());
        if(f.getCargo().equals("Gerente")){
            cSelectCargo.setValue("Geretente");
        } else {
            cSelectCargo.setValue("Vendedor");
        }
    }
}
