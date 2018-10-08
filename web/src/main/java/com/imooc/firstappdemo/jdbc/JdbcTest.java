package com.imooc.firstappdemo.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://132.232.44.64:3306/first-app-demo?useUnicode=true&characterEncoding=UTF-8","root","123456");
        PreparedStatement pstmt = null;
        String sql = "insert into student (name,age) values(?,?)";
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            for(int i =0;i<100;i++) {
                pstmt.setString(1, "孙令福"+i);
                pstmt.setInt(2, i);
                System.out.println(i);
                pstmt.addBatch();
            }
            int[] result = pstmt.executeBatch();
            System.out.println("batch 提交");
            pstmt.close();
            conn.close();
            for (int i : result) {
                System.out.print(i + " ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
