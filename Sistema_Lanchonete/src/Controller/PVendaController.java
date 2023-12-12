package Controller;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Model.BO.VendaRN;
import Model.VO.Venda;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class PVendaController implements Initializable{
    
    private VendaRN a = new VendaRN();
    private Venda vend = new Venda();

    @FXML
    private TextField pesquisa;

    @FXML
    private Button bpesquisa;

    @FXML
    private Button adc;

    @FXML
    private TableView<Venda> tabela;

    @FXML
    private Button edt;

    @FXML
    private TableColumn<Venda, Integer> tVenda;

    @FXML
    private Button voltar;

    @FXML
    private Button rmv;

    @FXML
    private TableColumn<Venda, Double> tValor;

    @FXML
    private TableColumn<Venda, Date> tData;

    @FXML
    private TableColumn<Venda, Integer> tId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tData.setCellValueFactory(new PropertyValueFactory<>("dataVenda"));
        tId.setCellValueFactory(new PropertyValueFactory<>("idVendedor"));
        tValor.setCellValueFactory(new PropertyValueFactory<>("valorVenda"));
        tVenda.setCellValueFactory(new PropertyValueFactory<>("cod"));

        try {
            tabela.setItems(a.chamaTabela());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void cSelect(MouseEvent event) {
        int i = tabela.getSelectionModel().getSelectedIndex();

        vend = tabela.getItems().get(i);
    }

    @FXML
    void aPesquisar(ActionEvent event) {
        
        if(pesquisa.getText().equals("")){
            try {
                tabela.setItems(a.chamaTabela());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else{

            try{
                tabela.setItems(a.pesquisaTabela(Integer.valueOf(pesquisa.getText())));
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    void aRemover(ActionEvent event) throws SQLException {
        a.remover(vend);
    }

    @FXML
    void aAdicioinar(ActionEvent event) {
        Main.chamarTela("lancaVenda");
    }
    
    @FXML
    void aVoltar(ActionEvent event) {
        Main.chamarTela("telaGer");
    }

}
