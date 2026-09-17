package com.mirim.board.util;

import javax.xml.transform.Result;
import java.sql.*;

public class Test {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/spring_boot";

        try (Connection conn = DriverManager.getConnection(url, "root", "비밀번호")) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM posts WHERE id = ?"); // "" 안에 쿼리
            pstmt.setLong(1, 1L);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
