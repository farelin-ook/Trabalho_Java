package Model.BO;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Controller.Main;
import Model.DAO.ProdutosDAO;
import Model.DAO.ProdutosI;
import Model.DAO.VendaDAO;
import Model.DAO.VendaI;
import Model.VO.Produto;
import Model.VO.Venda;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class VendaRN {
    
    private static Venda v = new Venda();
    Alert alert = new Alert(AlertType.NONE);
    Alert alert2 = new Alert(AlertType.NONE);
    private static List<Produto> l = new ArrayList<>();

    public ObservableList<Venda> chamaTabela() throws SQLException{
        VendaI vend = new VendaDAO();
        v.setValorVenda(0);
        List<Venda> lista = new ArrayList<>();
        
        lista = vend.ChamarListaCompleta();
        
        return FXCollections.observableArrayList(lista);
    }

    public ObservableList<Produto> prodVenda(int codProd) throws SQLException {
        ProdutosI prod = new ProdutosDAO();
        Produto p = new Produto();
        
        if(prod.verificar(codProd) == false){
            alert.setAlertType(AlertType.ERROR);
            alert.setContentText("Codigo invalido");
            alert.show();
            
            return FXCollections.observableArrayList(VendaRN.l);
        } else{
            
            p = prod.chamarC(codProd);
            VendaRN.l.add(p);
            v.setValorVenda(v.getValorVenda() + p.getValor());
            return FXCollections.observableArrayList(VendaRN.l);
        }

    }

    public ObservableList<Venda> pesquisaTabela(int cod) throws SQLException{
        VendaI vend = new VendaDAO();
        List<Venda> lista = new ArrayList<>();
        try{
            lista = vend.ChamarLista(cod);
        } catch (RuntimeException e){
            alert.setAlertType(AlertType.INFORMATION);
            alert.setContentText("Nenhuma correspodencia, favor verificar as informações");
            alert.show();
        }   
        return FXCollections.observableArrayList(lista);
    }

    public void finalizar(double valorCompra, double pagamento, TableView<Produto> tab, TextField pag, TextField prec) throws SQLException{
        Venda v = new Venda();
        VendaI vend = new VendaDAO();
        if(pagamento >= valorCompra){
            alert.setAlertType(AlertType.CONFIRMATION);
            alert.setContentText("Gostaria de Finalizar a venda?");
            alert.showAndWait().ifPresent((resposta) -> {
                if(resposta == ButtonType.OK){
                    try {
                        vend.mCod(v);
                        v.setValorVenda(valorCompra);
                        v.setIdVendedor(FuncionarioRN.getOperador().getIdFunc());
                        v.setDataVenda(Date.valueOf(LocalDate.now()));
                        vend.adicionarVenda(v);
                        vend.adiocionarFunc(v);
                        for(int i = 0; i < l.size(); i++){
                            vend.adicionarProd(l.get(i), v);
                        }
                        if(pagamento == valorCompra){
                            alert2.setAlertType(AlertType.INFORMATION);
                            alert2.setContentText("Venda registrada, Sem troco");
                            alert2.show();
                        } else {
                            alert2.setAlertType(AlertType.INFORMATION);
                            alert2.setContentText("Venda registrada, Troco: R$" + (pagamento - valorCompra));
                            alert2.show();
                        }

                        zerarL();
                        tab.setItems(null);
                        pag.setText("");
                        prec.setText("");
                        
                        if(FuncionarioRN.getOperador().getCargo().equals("Gerente")){
                            Main.chamarTela("Vendas");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            alert.setAlertType(AlertType.INFORMATION);
            alert.setContentText("O saldo não é o suficiente");
            alert.show();
        }
    }

    public void remover(Venda v) throws SQLException{
        VendaI vend = new VendaDAO();

        if(v.equals(null)){
            alert.setAlertType(AlertType.ERROR);
            alert.setContentText("Nenhum produto selecionado");
            alert.show();
        } else {
            alert.setAlertType(AlertType.CONFIRMATION);
            alert.setContentText("Realmente deseja deletar este produto do sistema?");
            alert.showAndWait().ifPresent((resposta) -> {
                if(resposta == ButtonType.OK){
                    try{
                        vend.deletarVenda(v.getCod());
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void zerarL(){
        List<Produto> lista = new ArrayList<>();

        VendaRN.l = lista;
    }

    public static Venda getV() {
        return v;
    }

    public static void setV(Venda v) {
        VendaRN.v = v;
    }

    public static List<Produto> getL() {
        return l;
    }

    public static void setL(List<Produto> l) {
        VendaRN.l = l;
    }

    
}
