package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TelaGerenteController implements Initializable{

    
    @FXML
    private Button bSair;

    @FXML
    private static Label mensagem;

    @FXML
    private Button bFuncionarios;
    
    @FXML
    private Button bProduto;

    @FXML
    private Button bVendas;

    @FXML
    void acessarVendas(ActionEvent event) {
        Main.chamarTela("Vendas");
    }

    @FXML
    void AcessarFuncionarios(ActionEvent event) {
        Main.chamarTela("Funcionario");
    }

    @FXML
    void AcessarProdutos(ActionEvent event) {
        Main.chamarTela("Produtos");
    }
    
    @FXML
    void Sair(ActionEvent event) {
        Main.chamarTela("Login");
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    
    }
    
}
