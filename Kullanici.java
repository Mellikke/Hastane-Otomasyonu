/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proje2main;

import javax.swing.JFrame;
import java.util.Date;

/**
 *
 * @author yildi
 */
public class Kullanici {
    private String ad,soyad,sifre,TC;
    
 

    public Kullanici(String ad, String soyad, String sifre, String TC) {
        this.ad = ad;
        this.soyad = soyad;
        this.sifre = sifre;
        this.TC = TC;
        
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getTC() {
        return TC;
    }

    public void setTC(String TC) {
        this.TC = TC;
    }

     
    
}
