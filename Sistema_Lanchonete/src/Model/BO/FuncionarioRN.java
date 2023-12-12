package Model.BO;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Controller.Main;
import Model.DAO.FuncionarioDAO;
import Model.DAO.FuncionarioI;
import Model.VO.Funcionario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class FuncionarioRN {
 
    private static Funcionario Operador = new Funcionario();
    private static Funcionario salvar = new Funcionario();
    
    private Alert alert = new Alert(AlertType.NONE);
    
    public FuncionarioRN(){}
    
    public void Login(TextField nome, PasswordField senha, Label a) throws SQLException{
        FuncionarioI func = new FuncionarioDAO();
        if(func.Login(nome.getText(), senha.getText()) == true){
            
            a.setText(" ");
            
            Operador = func.chamar(nome.getText());
            
            if(Operador.getCargo().equals("Gerente")){
                Main.chamarTela("telaGer");
            } else {
                Main.chamarTela("lancaVenda");
            }
            
        } else {
            a.setText("Usuario ou senha incorreta");
        }
        
    }
    
    public ObservableList<Funcionario> chamaTabela() throws SQLException{
        FuncionarioI func = new FuncionarioDAO();
        List<Funcionario> lista =  new ArrayList<>();
        
        lista = func.chamaListaCompleta();
        return FXCollections.observableArrayList(lista);
        
    }
    
    public ObservableList<Funcionario> pesquisaTabela(String nome) throws SQLException{
        FuncionarioI func = new FuncionarioDAO();
        List<Funcionario> lista =  new ArrayList<>();
        
        
        if(nome.equals("")){
            
            lista = func.chamaListaCompleta();
            
        }else{
            lista = func.chamaLista(nome);
            // caso não haja correspondencia pelo nome ele testa pesquisar pelo id
            if(lista.size() == 0){

                lista = func.chamaListaI(Integer.valueOf(nome));
                        
            }
        }
        return FXCollections.observableArrayList(lista);
        
    }
    
    public boolean validacao(String id) throws SQLException{
        FuncionarioI func = new FuncionarioDAO();
        List<Funcionario> lista =  new ArrayList<>();

        lista = func.chamaListaI(Integer.valueOf(id));

        if(lista.size() == 0){
            return true;
        } else {
            return false;
        }
        
    }
    
    public void registar(boolean validacao, TextField nome, TextField senha, TextField id, ChoiceBox cargo) throws SQLException{
        FuncionarioI func = new FuncionarioDAO();
        Funcionario f = new Funcionario();
        
        if(validacao == true){
            f.setCargo(String.valueOf(cargo.getValue()));
            f.setIdFunc(Integer.valueOf(id.getText()));
            f.setNome(nome.getText());
            f.setSenha(senha.getText());
            func.adicionar(f);
        }else{
            alert.setAlertType(AlertType.WARNING);
            alert.setContentText("Funcionario não regitrado");
            
        }
    }

    public void remover(Funcionario f) throws SQLException{
        FuncionarioI func = new FuncionarioDAO();
        
        if(f.equals(null)){
            alert.setAlertType(AlertType.ERROR);
            alert.setContentText("nunhum funcionario selecionado");
            alert.show();
        } else {
            alert.setAlertType(AlertType.CONFIRMATION);
            alert.setContentText("Realmente deseja remover este funcionario do sistema?");
            alert.showAndWait().ifPresent((e) -> {
                if(e == ButtonType.OK){
                    try{
                        func.remover(f.getIdFunc());
                    }catch(SQLException r){
                        r.printStackTrace();
                    }
                }
            });
        }
    }

    public void editar(Funcionario f) throws SQLException{
        FuncionarioI func = new FuncionarioDAO();
        
        func.editar(f);
    }
    
    public static Funcionario getSalvar() {
        return salvar;
    }

    public static void setSalvar(Funcionario salvar) {
        FuncionarioRN.salvar = salvar;
    }
    
    public static Funcionario getOperador() {
        return Operador;
    }
}