package Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Model.BO.FuncionarioRN;

import Model.VO.Funcionario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class PFuncionarioController implements Initializable{
    
    private FuncionarioRN a = new FuncionarioRN();
    private Funcionario func = new Funcionario();

    @FXML
    private TextField pesquisa;

    @FXML
    private Button bpesquisa;

    @FXML
    private Button adc;

    @FXML
    private TableView<Funcionario> tabela;

    @FXML
    private Button edt;

    @FXML
    private TableColumn<Funcionario, String> tCargo;

    @FXML
    private Button voltar;

    @FXML
    private Button rmv;

    @FXML
    private TableColumn<Funcionario, String> tFunc;

    @FXML
    private TableColumn<Funcionario, Integer> tId;

    @FXML
    private TableColumn<Funcionario, String> tSenha;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tFunc.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        tSenha.setCellValueFactory(new PropertyValueFactory<>("Senha"));
        tId.setCellValueFactory(new PropertyValueFactory<>("IdFunc"));
        tCargo.setCellValueFactory(new PropertyValueFactory<>("Cargo"));
        
        try {
            tabela.setItems(a.chamaTabela());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("bah");   
        }

    }
    @FXML
    void aSelecionar(MouseEvent event) {
        int i = tabela.getSelectionModel().getSelectedIndex();
        func = tabela.getItems().get(i);
    }

    @FXML
    void aPesquisar(ActionEvent event){
        try {
            tabela.setItems(a.pesquisaTabela(pesquisa.getText()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void aRemover(ActionEvent event) throws SQLException {
        a.remover(func);
    }

    @FXML
    void aEditar(ActionEvent event) {
        FuncionarioRN.setSalvar(func);
        Main.chamarTela("edtFunc");
    }

    @FXML
    void aAdicionar(ActionEvent event) {
        Main.chamarTela("nFunc");
    }
    
    @FXML
    void aVoltar(ActionEvent event) {
        Main.chamarTela("telaGer");
    }
}
