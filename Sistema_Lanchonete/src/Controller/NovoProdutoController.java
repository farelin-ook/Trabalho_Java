package Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Model.BO.ProdutoRN;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class NovoProdutoController implements Initializable {

    private ProdutoRN a = new ProdutoRN();
    private Alert b = new Alert(AlertType.NONE);

    @FXML
    private Button bCancela;

    @FXML
    private TextArea tDesc;

    @FXML
    private TextField tNome;

    @FXML
    private TextField tValor;

    @FXML
    private Button bConfirma;

    @FXML
    private TextField tCodigo;

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    @FXML
    void aConfirma(ActionEvent event) {
        if(tCodigo.getText().equals("")||tNome.getText().equals("")||tValor.getText().equals("")||tDesc.getText().equals("")){
            b.setAlertType(AlertType.ERROR);
            b.setContentText("Um dos campos esta invalido");
            b.show();
        } else {
            b.setAlertType(AlertType.CONFIRMATION);
            b.setContentText("Deseja adicionar este novo produto?");
            b.showAndWait().ifPresent((resposta) -> {
                if(resposta ==  ButtonType.OK){
                    try{
                        a.registrar(a.validacao(Integer.valueOf(tCodigo.getText())), tNome, tCodigo, tValor, tDesc);
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @FXML
    void aCancela(ActionEvent event) {
        Main.chamarTela("Produtos");
    }
}
