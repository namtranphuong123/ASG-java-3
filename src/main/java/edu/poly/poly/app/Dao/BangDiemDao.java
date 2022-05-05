/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.poly.app.Dao;

import edu.poly.poly.app.helpers.DatabaseHelper;
import edu.poly.poly.app.model.BangDiem;
import edu.poly.poly.app.model.SinhVien;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.rowset.serial.SerialBlob;

/**
 *
 * @author ASUS
 */
public class BangDiemDao {
    public boolean insert(BangDiem bd) throws Exception {        
        String sql = "INSERT INTO [dbo].[BangDiem]([MaSV],[TiengAnh],[TinHoc],[GDTC]) values(?,?,?,?)";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);
            ){
            pstmt.setString(1, bd.getMaSV());
            pstmt.setFloat(2, bd.getTiengAnh());
            pstmt.setFloat(3, bd.getTinHoc());
            pstmt.setFloat(4, bd.getGDTC());
            
          
            return pstmt.executeUpdate() > 0;
        }
    }
    public boolean update(BangDiem bd) throws Exception {
        
        String sql = "UPDATE [dbo].[BangDiem]" +
                    " SET [TiengAnh] = ?,[TinHoc] = ?,[GDTC] = ?" +
                    " WHERE [MaSV] = ?";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);
            ){
            pstmt.setString(4, bd.getMaSV());
            pstmt.setFloat(1, bd.getTiengAnh());
            pstmt.setFloat(2, bd.getTinHoc());
            pstmt.setFloat(3, bd.getGDTC());
            
          
            return pstmt.executeUpdate() > 0;
        }
    }
       public BangDiem findById(String MaSV) throws Exception {

        String sql = "Select * from BangDiem where maSV =?";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, MaSV);
            try (ResultSet rs = pstmt.executeQuery();) {
                if (rs.next()) {
                    BangDiem sv = createBangDiem(rs);
                    return sv;
                }
            }
            return null;
        }
    }
    public boolean deleteByMaSV(String MaSV) throws Exception {
        
        String sql = "delete from BangDiem " +
                    " WHERE [MaSV] = ?";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);
            ){
            pstmt.setString(1, MaSV);
            
          
            return pstmt.executeUpdate() > 0;
        }
    }
    public BangDiem findByMaSV(String MaSV) throws Exception {
        
        String sql = "select * from BangDiem WHERE [MaSV] = ?";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);
            ){
            pstmt.setString(1, MaSV);
            
            try(ResultSet rs = pstmt.executeQuery();){
                if (rs.next()){
                    BangDiem bd = createBangDiem(rs);
                    
                    return bd;
                }
            }
            return null;
        }
    }
    public List<BangDiem> findAll() throws Exception {
        
        String sql = "select from BangDiem";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);
            ){ 
            
            try(ResultSet rs = pstmt.executeQuery();){
                List<BangDiem> list = new ArrayList<>();
                while (rs.next()){
                    BangDiem bd = createBangDiem(rs);
                    list.add(bd);
                }
                return list;
            }     
        }
    }
    public List<BangDiem> findTop(int top) throws Exception {
        top = 10;
        String sql = " select top "+ top +" *, (TiengAnh + TinHoc +GDTC)/3 as DTB " +
                    " from BangDiem order by DTB desc";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);
            ){ 
            
            try(ResultSet rs = pstmt.executeQuery();){
                List<BangDiem> list = new ArrayList<>();
                while (rs.next()){
                    BangDiem bd = createBangDiem(rs);
                    list.add(bd);
                }
                return list;
            }     
        }
    }

    private BangDiem createBangDiem(final ResultSet rs) throws SQLException {
        BangDiem bd = new BangDiem();
        bd.setID(rs.getInt("ID"));
        bd.setMaSV(rs.getString("MaSV"));
        bd.setTiengAnh(rs.getFloat("TiengAnh"));
        bd.setTinHoc(rs.getFloat("TinHoc"));
        bd.setGDTC(rs.getFloat("GDTC"));
        return bd;
    }
}
