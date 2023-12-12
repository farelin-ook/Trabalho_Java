package Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Model.BO.ProdutoRN;
import Model.VO.Produto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class EditarProdutoController implements Initializable{

    private Alert a = new Alert(AlertType.NONE);
    private ProdutoRN b = new ProdutoRN();

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
    private Button bPuxar;

    @FXML
    private TextField tCodigo;

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    @FXML
    void aConfirma(ActionEvent event) {
        Produto p = new Produto();

        if(tCodigo.getText().equals("")||tValor.getText().equals("")||tNome.getText().equals("")||tDesc.getText().equals("")){
            a.setAlertType(AlertType.ERROR);
            a.setContentText("Um dos campos se encontra vazio");
            a.show();        
        } else {
            a.setAlertType(AlertType.CONFIRMATION);
            a.setContentText("Deseja realmente alterar as informações deste produto?");
            a.showAndWait().ifPresent(resposta -> {
                if(resposta == ButtonType.OK){
                    p.setCodigo(Integer.valueOf(tCodigo.getText()));
                    p.setNome(tNome.getText());
                    p.setValor(Double.valueOf(tValor.getText()));
                    p.setDescricao(tDesc.getText());

                    try {
                        b.editar(p);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @FXML
    void aCancela(ActionEvent event) {
        tCodigo.setText("");
        tNome.setText("");
        tValor.setText("");
        tDesc.setText("");
        Main.chamarTela("Produtos");
    }

    @FXML
    void aPuxarInfo(ActionEvent event) {
        tCodigo.setText(String.valueOf(ProdutoRN.getpSalvar().getCodigo()));
        tNome.setText(ProdutoRN.getpSalvar().getNome());
        tDesc.setText(ProdutoRN.getpSalvar().getDescricao());
        tValor.setText(String.valueOf(ProdutoRN.getpSalvar().getValor()));

    }
    
}
