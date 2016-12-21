package data.gather.dao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import data.gather.model.InfoEntity;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import static data.gather.db.ConnectionUtil.getConn;

/**
 * Created by dell on 2016/12/20.
 */
public class Infohandle {

    public static int insert(InfoEntity infoEntity) {
        Connection conn = getConn();
        int i = 0;
        String sql = "INSERT INTO info(chat_count,img,url,source,TIME,title)VALUES (?,?,?,?,?,?);";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, infoEntity.getChatCount());
            pstmt.setString(2, infoEntity.getImg().toString());
            pstmt.setString(3, infoEntity.getUrl());
            pstmt.setInt(4,infoEntity.getSource());
            pstmt.setTimestamp(5,new Timestamp(infoEntity.getTime().getTime()));
            pstmt.setString(6,infoEntity.getTitle());
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
