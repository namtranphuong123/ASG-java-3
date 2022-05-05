/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.poly.app.model;

/**
 *
 * @author ASUS
 */
public class BangDiem {
    private int ID;
    private String MaSV;
    private float TiengAnh,TinHoc,GDTC;

    public BangDiem() {
    }

    public BangDiem(int ID, String MaSV, float TiengAnh, float TinHoc, float GDTC) {
        this.ID = ID;
        this.MaSV = MaSV;
        this.TiengAnh = TiengAnh;
        this.TinHoc = TinHoc;
        this.GDTC = GDTC;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMaSV() {
        return MaSV;
    }

    public void setMaSV(String MaSV) {
        this.MaSV = MaSV;
    }

    public float getTiengAnh() {
        return TiengAnh;
    }

    public void setTiengAnh(float TiengAnh) {
        this.TiengAnh = TiengAnh;
    }

    public float getTinHoc() {
        return TinHoc;
    }

    public void setTinHoc(float TinHoc) {
        this.TinHoc = TinHoc;
    }

    public float getGDTC() {
        return GDTC;
    }

    public void setGDTC(float GDTC) {
        this.GDTC = GDTC;
    }
    
    
}
