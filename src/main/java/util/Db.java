// src/main/java/util/Db.java
package util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Db {
    private static String dbUrl;
    private static String dbUser;
    private static String dbPass;
    
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Properties props = new Properties();
            InputStream input = Db.class.getClassLoader().getResourceAsStream("db.properties");
            if (input != null) {
                props.load(input);
                dbUrl = props.getProperty("db.url");
                dbUser = props.getProperty("db.user");
                dbPass = props.getProperty("db.pass");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(dbUrl, dbUser, dbPass);
    }
}