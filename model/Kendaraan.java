/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author anin7
 */
public class Kendaraan {
    private int id;
    private String platNomor;
    private String jenis;
    private String merk;
    
    public Kendaraan(){}
    
    public Kendaraan(int id, String platNomor, String jenis, String merk){
        this.id = id;
        this.platNomor = platNomor;
        this.jenis = jenis;
        this.merk = merk;
    }
    
    public Kendaraan(String platNomor, String jenis, String merk){
        this.platNomor = platNomor;
        this.jenis = jenis;
        this.merk = merk;
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getPlatNomor(){
        return platNomor;
    }
    
    public void setPlatNomor(String platNomor){
        this.platNomor = platNomor;
    }
    
    public String getJenis(){
        return jenis;
    }
    
    public void setJenis(String jenis){
        this.jenis = jenis;
    }
    
    public String getMerk(){
        return merk;
    }
    
    public void setMerk(String merk){
        this.merk = merk;
    }
    
}
