/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proje2main;

import java.util.Date;
import java.sql.*;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author yildi
 */
class DatabaseCon {

    private static final String URL = "jdbc:mysql://localhost:3306/deneme";
    private static final String USER = "root";
    private static final String PASSWORD = "Melike4434@";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

public class Hasta extends Kullanici {

    private Date dogumTarihi;
    private String kanGrubu;
    private String cinsiyet;

    public Hasta(String ad, String soyad, String sifre, String TC, Date dogumTarihi, String kanGrubu, String cinsiyet) {
        super(ad, soyad, sifre, TC);
        this.dogumTarihi = dogumTarihi;
        this.kanGrubu = kanGrubu;
        this.cinsiyet = cinsiyet;
    }

    public boolean randevuEkle( String bolum,java.util.Date tarih, String saat) throws SQLException {
        String checkQuery = "SELECT COUNT(*) FROM deneme.randevu_durumu WHERE  bolum_adi = ? AND randevu_tarihi = ? AND randevu_saat = ?";
        String doctorQuery = "SELECT doktor_adi FROM deneme.doktor_bilgileri WHERE doktor_departman = ?";  // Doktor ismini çekmek için sorgu
        String insertQuery = "INSERT INTO deneme.randevu_durumu ( bolum_adi,randevu_tarihi, randevu_saat, doktor_adi,hasta_ad,hasta_soyad,hasta_TC) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = DatabaseCon.getConnection();

        try {
            // Mevcut randevuları kontrol et
            PreparedStatement pstmtCheck = conn.prepareStatement(checkQuery); // tarih'i java.sql.Date'e dönüştür
            pstmtCheck.setString(1, bolum);
             pstmtCheck.setDate(2, new java.sql.Date(tarih.getTime())); 
            pstmtCheck.setString(3, saat);
            ResultSet rs = pstmtCheck.executeQuery();

            if (rs.next() && rs.getInt(1) == 0) {
                // Doktor ismini çek
                PreparedStatement pstmtDoctor = conn.prepareStatement(doctorQuery);
                pstmtDoctor.setString(1, bolum);
                ResultSet rsDoctor = pstmtDoctor.executeQuery();
                String doktor_adi = "";
                if (rsDoctor.next()) {
                    doktor_adi = rsDoctor.getString("doktor_adi");
                    System.out.println(doktor_adi);
                
                } else {
                    JOptionPane.showMessageDialog(null, "Bu bölüm için doktor bulunamadı.");
                    return false;
                }

                // Randevuyu ekle
                PreparedStatement pstmtInsert = conn.prepareStatement(insertQuery);
                pstmtInsert.setString(1,  bolum);
                pstmtInsert.setDate(2,new java.sql.Date(tarih.getTime()));
                pstmtInsert.setString(3, saat);
                pstmtInsert.setString(4, doktor_adi);
                pstmtInsert.setString(5, super.getAd());
                pstmtInsert.setString(6, super.getSoyad());
                pstmtInsert.setString(7, super.getTC());
                int result = pstmtInsert.executeUpdate();

                if (result > 0) {
                    JOptionPane.showMessageDialog(null, "Randevu başarıyla oluşturuldu.");
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Randevu eklenemedi.");
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Bu tarih, saat ve bölüm için zaten bir randevu mevcut.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Veritabanı hatası: " + e.getMessage());
            return false;
        } finally {
            // Kaynakları serbest bırak
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


public Date getDogumTarihi() {
        return dogumTarihi;
    }

    public void setDogumTarihi(Date dogumTarihi) {
        this.dogumTarihi = dogumTarihi;
    }

    public String getKanGrubu() {
        return kanGrubu;
    }

    public void setKanGrubu(String kanGrubu) {
        this.kanGrubu = kanGrubu;
    }

    public String getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

}
