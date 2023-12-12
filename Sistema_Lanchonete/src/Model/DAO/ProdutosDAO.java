package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import Model.VO.Produto;

public class ProdutosDAO implements ProdutosI{
    
    private Connection connect;

    public ProdutosDAO() throws SQLException{
        connect = ConnectionJDBC.getConnection();
    }

    public Produto chamar(String nome) throws SQLException{
        Produto p =  new Produto();
        String sql = "SELECT * FROM Produto WHERE nome = ?";
        PreparedStatement a = this.connect.prepareStatement(sql);
        a.setString(1, nome);

        ResultSet b = a.executeQuery();

        while(b.next()){
            p.setNome(b.getString("nome"));
            p.setCodigo(Integer.valueOf(b.getString("codigo")));
            p.setValor(Double.valueOf(b.getString("valor")));
            p.setDescricao(b.getString("descricao"));
        }
        
        a.close();
        b.close();

        return p;
    }

    public boolean verificar(int cod) throws SQLException{
        int c = 0;
        String sql = "SELECT * FROM Produto WHERE codigo = ?";
        PreparedStatement a = connect.prepareStatement(sql);
        a.setInt(1, cod);
        ResultSet b = a.executeQuery();

        while(b.next()){
            c = b.getInt("codigo");
        }

        if(c == cod && c != 0){
            return true;
        } else {
            return false;
        }
        
    }

    public Produto chamarC(int cod) throws SQLException{
        Produto p =  new Produto();
        String sql = "SELECT * FROM Produto WHERE codigo = ?";
        PreparedStatement a = this.connect.prepareStatement(sql);
        a.setInt(1, cod);

        ResultSet b = a.executeQuery();

        while(b.next()){
            p.setNome(b.getString("nome"));
            p.setCodigo(Integer.valueOf(b.getString("codigo")));
            p.setValor(Double.valueOf(b.getString("valor")));
            p.setDescricao(b.getString("descricao"));
        }

        a.close();
        b.close();

        return p;
    }

    public List<Produto> chamaLista(String nome) throws SQLException{
        
        String sql = "SELECT * FROM Produto WHERE nome like ?";
        PreparedStatement a = this.connect.prepareStatement(sql);
        a.setString(1, nome);
        ResultSet b = a.executeQuery();
        
        List<Produto> lista = new ArrayList<>();

        while(b.next()){
            Produto p = new Produto();

            p.setNome(b.getString("nome"));
            p.setCodigo(Integer.valueOf(b.getString("codigo")));
            p.setValor(Double.valueOf(b.getString("valor")));
            p.setDescricao(b.getString("descricao"));
        
            lista.add(p);
        }
        a.close();
        b.close();

        return lista;
    }
    public List<Produto> chamaListaC(int cod) throws SQLException{
        
        String sql = "SELECT * FROM Produto WHERE codigo like ?";
        PreparedStatement a = this.connect.prepareStatement(sql);
        a.setInt(1, cod);
        ResultSet b = a.executeQuery();
        
        List<Produto> lista = new ArrayList<>();

        while(b.next()){
            Produto p = new Produto();

            p.setNome(b.getString("nome"));
            p.setCodigo(Integer.valueOf(b.getString("codigo")));
            p.setValor(Double.valueOf(b.getString("valor")));
            p.setDescricao(b.getString("descricao"));
        
            lista.add(p);
        }
        a.close();
        b.close();

        return lista;
    }

    public List<Produto> chamaListaCompleta() throws SQLException{
        String sql = "SELECT * FROM Produto";
        PreparedStatement a = this.connect.prepareStatement(sql);

        ResultSet b = a.executeQuery();
        List<Produto> lista = new ArrayList<>();
        while (b.next()) {
            Produto p = new Produto();

            p.setNome(b.getString("nome"));
            p.setCodigo(Integer.valueOf(b.getString("codigo")));
            p.setValor(Double.valueOf(b.getString("valor")));
            p.setDescricao(b.getString("descricao"));
        
            lista.add(p);
        }
        a.close();
        b.close();

        return lista;
    }

    public void adicionar(Produto p) throws SQLException{
        String sql = "INSERT INTO Produto(codigo, nome, valor, descricao)" + "VALUES(?, ?, ?, ?)";
        PreparedStatement a = this.connect.prepareStatement(sql);

        a.setInt(1, p.getCodigo());
        a.setString(2, p.getNome());
        a.setDouble(3, p.getValor());
        a.setString(4, p.getDescricao());

        a.execute();
        a.close();
    }

    public void remover(int cod) throws SQLException{
        String sql = "DELETE FROM Produto WHERE codigo = ?";
        PreparedStatement a = this.connect.prepareStatement(sql);

        a.setInt(1, cod);
        a.execute();
        a.close();
    }

    public void editar(Produto p) throws SQLException{
        String sql = "UPDATE Produto set nome=?, valor=?, descricao=?" + "WHERE codigo = ?";

        PreparedStatement a = this.connect.prepareStatement(sql);

        a.setString(1, p.getNome());
        a.setDouble(2, p.getValor());
        a.setString(3, p.getDescricao());
        a.setInt(4, p.getCodigo());

        a.execute();
        a.close();
    }
}