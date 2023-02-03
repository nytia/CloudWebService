package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static Connection getCo() throws  Exception{
        return DriverManager.getConnection("jdbc:postgresql://containers-us-west-83.railway.app:6703/railway", "postgres", "DU2aWcaF3zvBXeC6kYua");

    }
}