package Model.DAO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import Model.VO.Produto;
import Model.VO.Venda;

public interface VendaI {
    public List<Venda> ChamarListaCompleta() throws SQLException;
    public List<Venda> ChamarLista(int cod) throws SQLException;
    public int getFunc(Venda v) throws SQLException;
    public Date getData(Venda v) throws SQLException;
    public void adicionarProd(Produto p, Venda v) throws SQLException;
    public void adiocionarFunc(Venda v) throws SQLException;
    public void adicionarVenda(Venda v) throws SQLException;
    public void mCod(Venda v) throws SQLException;
    public void deletarVenda(int cod) throws SQLException;
}
