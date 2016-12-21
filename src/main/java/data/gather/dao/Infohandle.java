package data.gather.dao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import data.gather.model.InfoEntity;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import static data.gather.db.ConnectionUtil.getConn;

/**
 * Created by dell on 2016/12/20.
 */
public class Infohandle {

    public static int insert(InfoEntity infoEntity) {
        Connection conn = getConn();
        int i = 0;
        String sql = "INSERT INTO info(chat_count,img,url,source,TIME,t,title)VALUES (?,?,?,?,?,?,?);";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, infoEntity.getChatCount());
            pstmt.setString(2, infoEntity.getImg().toString());
            pstmt.setString(3, infoEntity.getUrl());
            pstmt.setInt(4,infoEntity.getSource());
            pstmt.setDate(5,new Date(infoEntity.getTime().getTime()));
            pstmt.setDate(6,new Date(infoEntity.getT()));
            pstmt.setString(7,infoEntity.getTitle());
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static void main(String[] args) {
    }
}
