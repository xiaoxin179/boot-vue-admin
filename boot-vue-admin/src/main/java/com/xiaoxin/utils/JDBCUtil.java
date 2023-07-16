package com.xiaoxin.utils;

import com.xiaoxin.entity.User;

import java.sql.*;

import static sun.util.calendar.CalendarSystem.forName;

/**
 * @author:XIAOXIN
 * @date:2023/07/16
 **/
public class JDBCUtil {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/boot-vue-admin?useSSL=false&characterEncoding=utf8";
        return DriverManager.getConnection(url,"root","123456");
    }

    public static User excuteQuery1(String username,String password) throws SQLException {
        String sql = "SELECT * FROM user WHERE username=?";
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String username1 = rs.getString("username");
                String password1 = rs.getString("password");
                User user = new User(id,username1, password1);
                return user;
            }else return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            if (rs != null) {
                rs.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}
