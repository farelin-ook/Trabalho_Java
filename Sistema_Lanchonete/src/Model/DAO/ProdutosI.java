package Model.DAO;

import java.sql.SQLException;
import java.util.List;

import Model.VO.Produto;

public interface ProdutosI {
    public Produto chamar(String nome) throws SQLException;

    public Produto chamarC(int cod) throws SQLException;
    
    public List<Produto> chamaLista(String nome) throws SQLException;
    
    public List<Produto> chamaListaC(int cod) throws SQLException;
    
    public List<Produto> chamaListaCompleta() throws SQLException;
    
    public void adicionar(Produto p) throws SQLException;
    
    public void remover(int cod) throws SQLException;
    
    public void editar(Produto p) throws SQLException;

    public boolean verificar(int codProd) throws SQLException;
}
