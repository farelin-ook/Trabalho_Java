package Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Model.BO.ProdutoRN;
import Model.VO.Produto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class PProdutoController implements Initializable{
    
    private ProdutoRN a = new ProdutoRN();
    private Produto prod = new Produto();

    @FXML
    private TextField pesquisa;

    @FXML
    private Button bpesquisa;

    @FXML
    private TableColumn<Produto, Integer> tCod;

    @FXML
    private Button adc;

    @FXML
    private TableView<Produto> tabela;

    @FXML
    private TableColumn<Produto, String> tProd;

    @FXML
    private Button edt;

    @FXML
    private Button voltar;

    @FXML
    private Button rmv;
    
    @FXML
    private TableColumn<Produto, Double> tValor;
    
    @FXML
    private TableColumn<Produto, String> tDesc;
  
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tCod.setCellValueFactory(new PropertyValueFactory<>("Codigo"));
        tProd.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        tDesc.setCellValueFactory(new PropertyValueFactory<>("Descricao"));
        tValor.setCellValueFactory(new PropertyValueFactory<>("Valor"));

        try {
            tabela.setItems(a.chamaTabela());
        } catch(SQLException e){
            e.printStackTrace();
            System.out.println("bah");
        }
    }
    
    @FXML
    void aPesquisar(ActionEvent event){
        try {
            tabela.setItems(a.perquisaTabela(pesquisa.getText()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void aSelecionar(MouseEvent event) {
        int i = tabela.getSelectionModel().getSelectedIndex();

        prod = tabela.getItems().get(i);
    }

    @FXML
    void aRemover(ActionEvent event) throws SQLException {
        a.remover(prod);
    }

    @FXML
    void aAdicionar(ActionEvent event) {
        Main.chamarTela("novoProd");
    }

    @FXML
    void aEditar(ActionEvent event) {
        ProdutoRN.setpSalvar(prod);
        Main.chamarTela("edtProd");
    }

    @FXML
    void aVoltar(ActionEvent event) {
        Main.chamarTela("telaGer");
    }
    
}
