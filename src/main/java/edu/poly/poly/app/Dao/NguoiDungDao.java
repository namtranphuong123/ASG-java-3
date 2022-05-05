/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.poly.app.Dao;

import edu.poly.poly.app.helpers.DatabaseHelper;
import edu.poly.poly.app.model.NguoiDung;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ASUS
 */
public class NguoiDungDao {

    public NguoiDung checkLogin(String tenDangNhap, String matKhau) 
    throws Exception{
        String sql = "Select tenDangNhap, matKhau, VaiTro from nguoidung" 
                + " where tendangNhap =? and matKhau =? ";
        try (
                //ket noi dtb
                Connection con = DatabaseHelper.openConnection();
                //tao ra cau thuc hien cau lẹnh
                PreparedStatement pstmt = con.prepareStatement(sql);                
            ){
            // 1 va hai de truyen vao cau lẹnh
            pstmt.setString(1, tenDangNhap);
            pstmt.setString(2, matKhau);
            // dê dam bao dong cai ssurt thì dung try
            try(ResultSet rs = pstmt.executeQuery();){
                //.next de kiem tra du lieu tra ve
                if(rs.next()){
                    NguoiDung nd = new NguoiDung();
                    nd.setTenDangNhap(tenDangNhap);
                    nd.setVaiTro(rs.getString("VaiTro"));
                    return nd;
                }
            }
        }
        return null;
    }
}
