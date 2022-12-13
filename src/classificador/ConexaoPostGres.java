package classificador;



import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


/**
 *
 * @author contdiego
 */
public class ConexaoPostGres {

    private static Connection connection;

    /**
     * Chama método criaConexao e retorna conexao aberta com o BD
     * @return Conexão aberta com o banco 
     */
    public Connection conectar() {
        try {
            return criaConexao(false);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ConexaoPostGres.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Cria conexão com o banco de dados
     * @param autocommit   
     * @return
     * @throws Exception 
     */
    private Connection criaConexao(Boolean autocommit) throws Exception {
      
          
        //System.out.println("criaConexao");
        
        /*
         String host = "10.5.112.237";
        String db = "sistram4";
        String url = "jdbc:postgresql://" + host + "/" + db;
        String user = "postgres";
        String password = "postgres";
        String postgresqlDriver = "org.postgresql.Driver";
        */
      
        
        String host = "localhost";
        String db = "classificador";
        String url = "jdbc:postgresql://" + host + "/" + db;
        String user = "postgres";
        String password = "postgres";
        String postgresqlDriver = "org.postgresql.Driver";
       

        try
        {
            Class.forName(postgresqlDriver);
        }
        catch (ClassNotFoundException e)
        {
            java.util.logging.Logger.getLogger(ConexaoPostGres.class.getName()).log(Level.SEVERE, null, e);
        }
        try
        {
            connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(autocommit);
        }
        catch (SQLException e)
        {
            java.util.logging.Logger.getLogger(ConexaoPostGres.class.getName()).log(Level.SEVERE, null, e);
        } 
        
         
       
         //Context ctx = new InitialContext();
         //DataSource ds = (DataSource) ctx.lookup("jdbc/__derrotas");
         //Connection connection = ds.getConnection();
         
          return connection;
    }

    /**
     * Fecha conexão com o BD
     */
    protected void fecharConexao()
    {
        System.out.println("fecharConexao");        
        try {
            connection.close();
        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(ConexaoPostGres.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Método para consultar dado no BD
     * @param stmt
     * @return 
     */
    protected ResultSet executarSelect(PreparedStatement stmt) {

        try {
            return stmt.executeQuery();

        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(ConexaoPostGres.class.getName()).log(Level.SEVERE, null, e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(ConexaoPostGres.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;

    }

    /**
     * Método para deletar dado no BD
     * @param stmt 
     */
    protected void executarDelete(PreparedStatement stmt) {

        try {
            stmt.executeUpdate();

        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(ConexaoPostGres.class.getName()).log(Level.SEVERE, null, e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(ConexaoPostGres.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
