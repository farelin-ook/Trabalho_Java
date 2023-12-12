package Model.DAO;

import Model.VO.Funcionario;
import java.sql.SQLException;
import java.util.List;

public interface FuncionarioI {
    public boolean Login(String nome, String senha) throws SQLException;
    
    public Funcionario chamar(String nome)throws SQLException;
    
    public Funcionario chamarI(int id)throws SQLException;
    
    public List<Funcionario> chamaLista(String nome) throws SQLException;
    
    public List<Funcionario> chamaListaI(int id) throws SQLException;
    
    public List<Funcionario> chamaListaCompleta() throws SQLException;
    //public int maiorId() throws SQLException;
    public void adicionar(Funcionario f) throws SQLException;

    public void remover(int id)throws SQLException;
    
    public void editar(Funcionario f)throws SQLException;
}
