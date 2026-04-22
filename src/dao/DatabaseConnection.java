package Project.src.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String host = "localhost";
    private static final String port = "3306";
    private static final String db_name = "quanlysieuthi";
    private static final String username = "root";
    private static final String password = "";
    private static final String url = "jdbc:mysql://" + host + ":" + port + "/" + db_name;

    public static Connection getConnection(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url, username, password);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return conn;
    }
    
}
