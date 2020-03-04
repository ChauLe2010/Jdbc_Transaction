package com.codegym.connection;

import java.sql.*;

public class JDBCConnection {
    final  static String url="jdbc:mysql://localhost:3306/TestJDBC";
    final static String user="test";
    final static String pass="test";
    public static Connection getJDBCConnection(){
        Connection conn=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(url,user,pass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) {
        Connection conn=getJDBCConnection();
        if(conn!=null)
            System.out.println("Ket noi thanh cong");
        else
            System.out.println("Loi ket noi");
        try {
            Statement st1=conn.createStatement();
            Statement st2=conn.createStatement();
            String sql1="insert into Test values (3,'Hoa')";
            String sql2="delete from  Test where id=10";
            st1.executeUpdate(sql1);
            st2.executeUpdate(sql2);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
