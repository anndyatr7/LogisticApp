/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author anin7
 */
public class Sopir {
    private int id;
    private String nama;
    private String noSim;
    private String noHp;
    
    public Sopir(){}
    
    public Sopir(int id, String nama, String noSim, String noHp){
        this.id = id;
        this.nama = nama;
        this.noSim = noSim;
        this.noHp = noHp;
    }
    
    public Sopir(String nama, String noSim, String noHp){
        this.nama = nama;
        this.noSim = noSim;
        this.noHp = noHp;
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getNama(){
        return nama;
    }
    
    public void setNama(String nama){
        this.nama = nama;
    }
    
    public String getNoSim(){
        return noSim;
    }
    
    public void setNoSim(String noSim){
        this.noSim = noSim;
    }
    
    public String getNoHp(){
        return noHp;
    }
    
    public void setNoHp(String noHp){
        this.noHp = noHp;
    }
    
}
