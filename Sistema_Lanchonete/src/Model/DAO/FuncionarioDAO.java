package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.VO.Funcionario;

public class FuncionarioDAO implements FuncionarioI {
    
    private Connection connect;
    
    public FuncionarioDAO() throws SQLException{
        connect = ConnectionJDBC.getConnection();
    }

    @Override
    public boolean Login (String nome, String senha) throws SQLException{
        
        String sql = "SELECT * FROM Funcionario WHERE nome = ?";
        PreparedStatement a = this.connect.prepareStatement(sql);
        a.setString(1, nome);

        ResultSet b = a.executeQuery();

        Funcionario funcionario = new Funcionario();
        
        while(b.next()){
            funcionario.setIdFunc(Integer.valueOf(b.getString("idFuncionario")));
            funcionario.setNome(b.getString("nome"));
            funcionario.setSenha(b.getString("senha"));
        }
        b.close();
        a.close();
        
        if(nome.equals(funcionario.getNome()) && senha.equals(funcionario.getSenha())) {
            
            return true;
        } else {
            return false;
        }
        
        
    }

    @Override
    public Funcionario chamar(String nome) throws SQLException {

        Funcionario f = new Funcionario();

        String Sql = "SELECT * FROM Funcionario WHERE nome = ?";
        PreparedStatement a = this.connect.prepareStatement(Sql);
        a.setString(1, nome);

        ResultSet b = a.executeQuery();
        
        while(b.next()){
            f.setIdFunc(Integer.valueOf(b.getString("idFuncionario")));
            f.setNome(b.getString("nome"));
            f.setSenha(b.getString("senha"));
            f.setCargo(b.getString("cargo"));
        }
        a.close();
        b.close();
        
        return f;
    }

    @Override
    public Funcionario chamarI(int id) throws SQLException {

        Funcionario f = new Funcionario();

        String Sql = "SELECT * FROM Funcionario WHERE IdFuncionario = ?";
        PreparedStatement a = this.connect.prepareStatement(Sql);
        a.setInt(1, id);

        ResultSet b = a.executeQuery();
        
        while(b.next()){
            f.setIdFunc(Integer.valueOf(b.getString("idFuncionario")));
            f.setNome(b.getString("nome"));
            f.setSenha(b.getString("senha"));
            f.setCargo(b.getString("cargo"));
        }
        a.close();
        b.close();
        
        return f;
    }

    public List<Funcionario> chamaLista(String nome) throws SQLException{
        
        String Sql = "SELECT * FROM Funcionario where nome like ?";
        
        PreparedStatement a = this.connect.prepareStatement(Sql);
        a.setString(1, nome);

        ResultSet b = a.executeQuery();
        
        List<Funcionario> lista = new ArrayList<>();

        while (b.next()) {
            Funcionario f = new Funcionario();

            f.setIdFunc(Integer.valueOf(b.getString("idFuncionario")));
            f.setNome(b.getString("nome"));
            f.setSenha(b.getString("senha"));
            f.setCargo(b.getString("cargo"));

            lista.add(f);

        }

        a.close();
        b.close();

        return lista;
    }
    public List<Funcionario> chamaListaI(int id) throws SQLException{
        
        String Sql = "SELECT * FROM Funcionario where idFuncionario like ?";
        
        PreparedStatement a = this.connect.prepareStatement(Sql);
        a.setInt(1, id);

        ResultSet b = a.executeQuery();
        
        List<Funcionario> lista = new ArrayList<>();

        while (b.next()) {
            Funcionario f = new Funcionario();

            f.setIdFunc(Integer.valueOf(b.getString("idFuncionario")));
            f.setNome(b.getString("nome"));
            f.setSenha(b.getString("senha"));
            f.setCargo(b.getString("cargo"));

            lista.add(f);

        }

        a.close();
        b.close();

        return lista;
    }
    
    public List<Funcionario> chamaListaCompleta() throws SQLException{
        
        String Sql = "SELECT * FROM Funcionario";
        
        PreparedStatement a = this.connect.prepareStatement(Sql);

        ResultSet b = a.executeQuery();
        
        List<Funcionario> lista = new ArrayList<>();

        while (b.next()) {
            Funcionario f = new Funcionario();

            f.setIdFunc(Integer.valueOf(b.getString("idFuncionario")));
            f.setNome(b.getString("nome"));
            f.setSenha(b.getString("senha"));
            f.setCargo(b.getString("cargo"));

            lista.add(f);

        }

        a.close();
        b.close();

        return lista;
    }
    

    //somente uma tentativa falha de pegar o maior id 
    /*
    public int maiorId() throws SQLException{
     
     String sql = "SELECT max(idFuncionario) from funcionario";
        PreparedStatement a = this.connect.prepareStatement(sql);
        ResultSet b = a.executeQuery();
        
        Funcionario f = new Funcionario();
        while(b.next()){
            f.setIdFunc(Integer.valueOf(b.getString("idFuncionario")));
        }
        a.close();
        b.close();
        return f.getIdFunc();
        
    }
    */

   
    public void adicionar(Funcionario f) throws SQLException{
        String sql = "INSERT INTO Funcionario(idFuncionario, nome, senha, cargo)" + "VALUES(?, ?, ?, ?)";
        PreparedStatement a = this.connect.prepareStatement(sql);
        
        a.setInt(1, f.getIdFunc());
        a.setString(2, f.getNome());
        a.setString(3, f.getSenha());
        a.setString(4, f.getCargo());
        
        a.execute();
        a.close();
    }

    public void remover(int id)throws SQLException{
        String sql = "DELETE FROM Funcionario WHERE IdFuncionario = ?";

        PreparedStatement a = this.connect.prepareStatement(sql);
        a.setInt(1, id);

        a.execute();
        a.close();

    }

    public void editar(Funcionario f)throws SQLException{
        String sql = "UPDATE Funcionario set nome=?, Cargo=?, Senha=?" + "WHERE IdFuncionario = ?";

        PreparedStatement a = this.connect.prepareStatement(sql);
        a.setString(1, f.getNome());
        a.setString(2, f.getCargo());
        a.setString(3, f.getSenha());
        a.setInt(4, f.getIdFunc());
    
        a.execute();
        a.close();
    
    }
        
}
