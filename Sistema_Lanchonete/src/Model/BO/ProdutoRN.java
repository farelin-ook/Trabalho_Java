package Model.BO;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.DAO.ProdutosDAO;
import Model.DAO.ProdutosI;
import Model.VO.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ProdutoRN {
    
    private Alert alerta = new Alert(AlertType.NONE);
    private static Produto pSalvar = new Produto();

    public ObservableList<Produto> chamaTabela() throws SQLException{
        ProdutosI prod = new ProdutosDAO();
        List<Produto> lista = new ArrayList<>();

        lista = prod.chamaListaCompleta();

        return FXCollections.observableArrayList(lista);
    }

    public ObservableList<Produto> perquisaTabela(String nome) throws SQLException{
        ProdutosI prod = new ProdutosDAO();
        List<Produto> lista = new ArrayList<>();

        if(nome.equals("")){
            lista = prod.chamaListaCompleta();
        }else{
            lista = prod.chamaLista(nome);

            if(lista.size() == 0){
                try{
                    lista = prod.chamaListaC(Integer.parseInt(nome));
                } catch (RuntimeException e){
                    alerta.setAlertType(AlertType.INFORMATION);
                    alerta.setContentText("Nenhuma correspodencia, favor verificar as informações");
                    alerta.show();
                }                
            }
        }
        return FXCollections.observableArrayList(lista);
    }

    public void remover(Produto p) throws SQLException{
        ProdutosI prod = new ProdutosDAO();

        if(p.equals(null)){
            alerta.setAlertType(AlertType.ERROR);
            alerta.setContentText("Nenhum produto selecionado");
            alerta.show();
        } else {
            alerta.setAlertType(AlertType.CONFIRMATION);
            alerta.setContentText("Realmente deseja deletar este produto do sistema?");
            alerta.showAndWait().ifPresent((resposta) -> {
                if(resposta == ButtonType.OK){
                    try{
                        prod.remover(p.getCodigo());
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public boolean validacao(int cod) throws SQLException{
        ProdutosI prod = new ProdutosDAO();
        List<Produto> lista = new ArrayList<>();

        lista = prod.chamaListaC(cod);

        if(lista.size() == 0){
            return true;
        } else {
            return false;
        }
    }

    public void registrar(boolean validacao, TextField nome, TextField cod, TextField valor, TextArea descricao) throws SQLException{
        ProdutosI prod = new ProdutosDAO();
        Produto p = new Produto();

        if(validacao == true){
            p.setCodigo(Integer.valueOf(cod.getText()));
            p.setNome(nome.getText());
            p.setValor(Double.valueOf(valor.getText()));
            p.setDescricao(descricao.getText());
            prod.adicionar(p);
        } else {
            alerta.setAlertType(AlertType.WARNING);
            alerta.setContentText("Produto não registrado");
        }

    }

    public void editar(Produto p) throws SQLException{
        ProdutosI prod = new ProdutosDAO();

        prod.editar(p);
    }

    public static Produto getpSalvar() {
        return pSalvar;
    }

    public static void setpSalvar(Produto pSalvar) {
        ProdutoRN.pSalvar = pSalvar;
    }
}
