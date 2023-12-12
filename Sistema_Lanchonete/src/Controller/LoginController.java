package Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Model.BO.FuncionarioRN;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements Initializable{
    
    private FuncionarioRN a = new FuncionarioRN();

    @FXML
    private PasswordField senha;

    @FXML
    private Label erro;

    @FXML
    private Button entrar;

    @FXML
    private TextField usuario;

    @FXML
    void acaoBotao(ActionEvent event) throws SQLException {
        
        a.Login(usuario, senha, erro);
        usuario.setText("");
        senha.setText("");
        
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

}
