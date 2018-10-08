package com.imooc.firstappdemo.jdbc;

import java.sql.*;

public class JdbcSelectTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://132.232.44.64:3306/first-app-demo?useUnicode=true&characterEncoding=UTF-8","root","123456");
        PreparedStatement pstmt = null;
        String sql = "select * from student";
        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet resultSet = pstmt.executeQuery();
            while(resultSet.next()){
                System.out.println(resultSet.getObject(1)+" "+resultSet.getObject(2)+" "+ resultSet.getObject(3));
            }
            resultSet.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
