/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proje2main;

/**
 *
 * @author yildi
 */
public class Doktor extends Kullanici {
    private String doktorDepartman;
    public Doktor(String ad, String soyad, String sifre, String TC,String doktorDepartman) {
        super(ad, soyad, sifre, TC);
        this.doktorDepartman=doktorDepartman;
    }

    public String getDoktorDepartman() {
        return doktorDepartman;
    }

    public void setDoktorDepartman(String doktorDepartman) {
        this.doktorDepartman = doktorDepartman;
    }
    
}
