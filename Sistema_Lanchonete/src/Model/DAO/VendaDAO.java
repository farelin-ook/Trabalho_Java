package Model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import Model.VO.Produto;
import Model.VO.Venda;

public class VendaDAO implements VendaI{

    private Connection connect;

    public VendaDAO() throws SQLException{
        connect = ConnectionJDBC.getConnection();
    }
     
    public int getFunc(Venda v) throws SQLException{
        String sql = "SELECT * FROM funcVenda where venda = ?";
        PreparedStatement a = connect.prepareStatement(sql);
        a.setInt(1, v.getCod());
        ResultSet b = a.executeQuery();
        Venda vi = new Venda();
        while(b.next()){
            vi.setCod(b.getInt("venda"));
            vi.setIdVendedor(b.getInt("funcionario"));
        }

        a.close();
        b.close();

        return vi.getIdVendedor();

    } 
    public Date getData(Venda v) throws SQLException{
        String sql = "SELECT * FROM funcVenda where venda = ?";
        PreparedStatement a = connect.prepareStatement(sql);
        a.setInt(1, v.getCod());
        ResultSet b = a.executeQuery();
        Venda vi = new Venda();
        while(b.next()){
            vi.setCod(b.getInt("venda"));
            vi.setDataVenda(b.getDate("dataVenda"));
        }

        a.close();
        b.close();

        return vi.getDataVenda();    
    }

    public List<Venda> ChamarListaCompleta() throws SQLException{
        String sql = "SELECT * FROM Venda";
        PreparedStatement a = connect.prepareStatement(sql);
        ResultSet b = a.executeQuery();
        
        List<Venda> lista = new ArrayList<>();
        
        while(b.next()){
            Venda v = new Venda();
            v.setCod(Integer.valueOf(b.getString("codVenda")));
            v.setIdVendedor(getFunc(v));
            v.setValorVenda(Double.valueOf(b.getString("valorTotal")));
            v.setDataVenda(getData(v));
            lista.add(v);
        }
        return lista;
    }
    public List<Venda> ChamarLista(int cod) throws SQLException{
        String sql = "SELECT * FROM Venda WHERE codVenda = ?";
        PreparedStatement a = connect.prepareStatement(sql);
        a.setInt(1, cod);
        ResultSet b = a.executeQuery();
        
        List<Venda> lista = new ArrayList<>();
        
        while(b.next()){
            Venda v = new Venda();
            v.setCod(Integer.valueOf(b.getString("codVenda")));
            v.setIdVendedor(getFunc(v));
            v.setValorVenda(Double.valueOf(b.getString("valorTotal")));
            v.setDataVenda(getData(v));
            lista.add(v);
        }
        return lista;
    }

    public void adicionarProd(Produto p, Venda v) throws SQLException{
        String sql = "INSERT INTO VendaProduto(Venda, produto)" + "VALUES(?, ?)";
        PreparedStatement a = connect.prepareStatement(sql);

        a.setInt(1, v.getCod());
        a.setInt(2, p.getCodigo());

        a.execute();
        a.close();
    }
    public void adiocionarFunc(Venda v) throws SQLException{
        String sql = "INSERT INTO FuncVenda(funcionario, venda, dataVenda)" + "VALUES(?, ?, ?)";
        PreparedStatement a = connect.prepareStatement(sql);

        a.setInt(1, v.getIdVendedor());
        a.setInt(2, v.getCod());
        a.setDate(3, v.getDataVenda());

        a.execute();
        a.close();
    }
    public void adicionarVenda(Venda v) throws SQLException{
        String sql = "INSERT INTO venda(codVenda, valorTotal)" + "VALUES(?, ?)";
        PreparedStatement a = connect.prepareStatement(sql);

        a.setInt(1, v.getCod());
        a.setDouble(2, v.getValorVenda());

        a.execute();
        a.close();
    }

    
    public void mCod(Venda v) throws SQLException{
        
        String sql = "SELECT * FROM venda";
        
        PreparedStatement a = connect.prepareStatement(sql);
        ResultSet b = a.executeQuery();
        
        while (b.next()) {
            if(b.isLast()){
                v.setCod((b.getInt("codVenda") + 1));
            }
        }
        
        
        
        a.close();
        b.close();
        
        
    }

    public void deletarVenda(int cod) throws SQLException{
        String sql = "Delete FROM funcvenda where venda = ?";
        String sql1 = "Delete FROM vendaProduto where Venda = ?";
        String sql2 = "Delete FROM venda where codVenda = ?";

        PreparedStatement a = connect.prepareStatement(sql);
        a.setInt(1, cod);
        a.execute();
        a = connect.prepareStatement(sql1);
        a.setInt(1, cod);
        a.execute();
        a = connect.prepareStatement(sql2);
        a.setInt(1, cod);
        a.execute();

        a.close();
    }
    
}
