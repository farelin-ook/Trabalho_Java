package Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Model.BO.FuncionarioRN;
import Model.BO.VendaRN;
import Model.VO.Produto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class LancarVendaController implements Initializable {

    VendaRN a = new VendaRN();
    Alert alert = new Alert(AlertType.NONE);

    @FXML
    private Button bFinalizar;

    @FXML
    private TextField tPagamento;

    @FXML
    private TableView<Produto> tabela;

    @FXML
    private TextField tPrecoTotal;

    @FXML
    private Button bAdcProd;

    @FXML
    private TableColumn<Produto, Double> tPreco;

    @FXML
    private Button bVoltar;

    @FXML
    private TableColumn<Produto, String> tProduto;

    @FXML
    private TextField CodProd;
    
    @FXML
    private TextField tOperador;
    
    @FXML
    private TableColumn<Produto, Integer> tCodigo;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tCodigo.setCellValueFactory(new PropertyValueFactory<>("Codigo"));
        tProduto.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        tPreco.setCellValueFactory(new PropertyValueFactory<>("Valor"));
    }

    @FXML
    void aAdicionar(ActionEvent event) {
        tOperador.setText(FuncionarioRN.getOperador().getNome());

        if(CodProd.getText().equals(null)){
            alert.setAlertType(AlertType.ERROR);
            alert.setContentText("Campo invalido");
            alert.show();
        } else {
            try {
                tabela.setItems(a.prodVenda(Integer.valueOf(CodProd.getText())));
                tPrecoTotal.setText(String.valueOf(VendaRN.getV().getValorVenda()));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            CodProd.setText(null);
        }

    }


    @FXML
    void aVoltar(ActionEvent event) {
        tabela.setItems(null);
        a.zerarL();
        tOperador.setText("");
        tPrecoTotal.setText("");
        tPagamento.setText("");
        if(FuncionarioRN.getOperador().getCargo().equals("Gerente")){
            Main.chamarTela("Vendas");
        } else {
            Main.chamarTela("Login");
        }
    }

    @FXML
    void aFinalizar(ActionEvent event) throws SQLException {
        a.finalizar(Double.valueOf(tPrecoTotal.getText()), Double.valueOf(tPagamento.getText()), tabela, tPagamento, tPrecoTotal);
        
    }

    
}
