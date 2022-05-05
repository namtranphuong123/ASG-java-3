/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.poly.app.Dao;

import edu.poly.poly.app.helpers.DatabaseHelper;
import edu.poly.poly.app.model.SinhVien;
import java.awt.Image;
import java.awt.List;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;

/**
 *
 * @author ASUS
 */
public class SinhVienDao {

    public boolean insert(SinhVien sv)
            throws Exception {
        String sql = "INSERT INTO dbo.SinhVien(MaSV,HoTen,Email,SoDT,GioiTinh,DiaChi,Hinh)"
                + " Values(?,?,?,?,?,?,?)";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, sv.getMaSV());
            pstmt.setString(2, sv.getHoTen());
            pstmt.setString(3, sv.getEmail());
            pstmt.setString(4, sv.getSoDT());
            pstmt.setInt(5, sv.getGioiTinh());
            pstmt.setString(6, sv.getDiaChi());

            if (sv.getHinh() != null) {
                Blob hinh = new SerialBlob(sv.getHinh());
                pstmt.setBlob(7, hinh);
            } else {
                Blob hinh = null;
                pstmt.setBlob(7, hinh);

            }
            return pstmt.executeUpdate() > 0;
        }
    }

 

    public boolean Update(SinhVien sv)
            throws Exception {
        String sql = "UPDATE dbo.SinhVien"
                + " SET HoTen = ?,Email = ?,SoDT = ?,GioiTinh = ?,DiaChi = ?,Hinh = ?"
                + " where MaSV = ?";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(7, sv.getMaSV());
            pstmt.setString(1, sv.getHoTen());
            pstmt.setString(2, sv.getEmail());
            pstmt.setString(3, sv.getSoDT());
            pstmt.setInt(4, sv.getGioiTinh());
            pstmt.setString(5, sv.getDiaChi());

            if (sv.getHinh() != null) {
                Blob hinh = new SerialBlob(sv.getHinh());
                pstmt.setBlob(6, hinh);
            } else {
                Blob hinh = null;
                pstmt.setBlob(6, hinh);
            }
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean delete(String MaSV)
            throws Exception {
        String sql = "Delete from sinhvien "
                + " where MaSV = ?";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, MaSV);

            return pstmt.executeUpdate() > 0;
        }
    }

    public SinhVien findById(String MaSV) throws Exception {

        String sql = "Select * from sinhvien where maSV =?";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, MaSV);
            try (ResultSet rs = pstmt.executeQuery();) {
                if (rs.next()) {
                    SinhVien sv = createSinhVien(rs);
                    return sv;
                }
            }
            return null;
        }
    }

    private SinhVien createSinhVien(final ResultSet rs) throws SQLException {
        SinhVien sv = new SinhVien();
        sv.setMaSV(rs.getString("MaSV"));
        sv.setHoTen(rs.getString("HoTen"));
        sv.setEmail(rs.getString("Email"));
        sv.setSoDT(rs.getString("SoDT"));
        sv.setDiaChi(rs.getString("DiaChi"));
        sv.setGioiTinh(rs.getInt("GioiTinh"));
        Blob blob = rs.getBlob("hinh");
        if (blob != null) {
            sv.setHinh(blob.getBytes(1, (int) blob.length()));
        }

        return sv;
    }

    public java.util.List< SinhVien> findAll() throws Exception {

        String sql = "Select * from sinhvien";
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);) {
            try (ResultSet rs = pstmt.executeQuery();) {
                java.util.List< SinhVien> list = new ArrayList<>();
                while (rs.next()) {
                    SinhVien sv = createSinhVien(rs);
                    list.add(sv);
                }
                return list;
            }
        }
    }
}
