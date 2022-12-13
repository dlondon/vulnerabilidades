package classificador;



import java.sql.Connection;


/**
 *
 * @author Cinthia Conti
 */
public abstract class BaseDAO{
    
    protected static Connection connection;
    protected static ConexaoPostGres conexao = new ConexaoPostGres();
   
}
