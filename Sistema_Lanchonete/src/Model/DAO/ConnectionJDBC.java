package Model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBC {
    
    private static Connection connection;

    public static Connection getConnection() throws SQLException{
        if (connection == null){
            try{
            Class.forName("com.mysql.cj.jdbc.Driver"); //tentei o padrão da aula mas, aparentemente, era uma versão antiga 
            //System.out.println("Conectando ao banco de dados");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lanchonetejava",  "root", "061626");
            //System.out.println("Banco de dados: " + connection.getCatalog());
            } catch(ClassNotFoundException e){
                throw new SQLException();
            }
        }
        return connection;
    }    

}
