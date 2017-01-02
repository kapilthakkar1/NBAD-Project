package com.java.nbad.project.utils;

import java.sql.*;

public class DBConnection {

    private static Connection con = null;

    /**
     *
     * @return
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public static synchronized Connection getDBConnection() throws ClassNotFoundException, SQLException {
        if (con == null) {

            //local + openshift
            /*
            String driver = "com.mysql.jdbc.Driver";
            String DB_URL = "jdbc:mysql://127.0.0.1:3307/nbad2";
            String user = "adminL2IrgFN";
            String password = "S8cqLDwrntUzjEFX";
            */
        
            /*
            // local
            String driver = "com.mysql.jdbc.Driver";
            String DB_URL = "jdbc:mysql://127.0.0.1:3306/nbad2";    
        
            String user = "root";
            String password = "test";
            */
            
            
            //openshift
            
            String driver = "com.mysql.jdbc.Driver";
            String DB_URL = "jdbc:mysql://127.12.0.130:3306/nbad2";
            String user = "adminL2IrgFN";
            String password = "S8cqLDwrntUzjEFX";
            
            Class.forName(driver);
            con = DriverManager.getConnection(DB_URL, user, password);

        }
        return con;
    }
    

}
