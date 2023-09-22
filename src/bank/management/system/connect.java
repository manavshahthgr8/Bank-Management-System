package bank.management.system;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

//We will connect to MySQL with the help of JDBC
//With the help of jar file all conection will b made
public class connect {
    Connection connection;
    Statement statement;
    public connect(){
        //Step 1 try catch //step 2 inner code after writing connection and statement
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankSystem ","root","password");
            statement= connection.createStatement(); // after this 2 code line database gets connected
        }catch (Exception E){
            E.printStackTrace();
        }
    }
    public static void main(String[] args) {

    }
}
