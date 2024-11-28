/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package camerarentalmanagementsystem;

import java.sql.Date;

/**
 *
 * @author user
 */
public class cameraData {
    
    private Integer cameraId;
    private String brand;
    private String model;
    private Double price;
    private String status;
    private Date date;
    private String image;
    
    public cameraData(Integer cameraId, String brand, String model
            , Double price, String status, String image, Date date){
        this.cameraId = cameraId;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.status = status;
        this.date = date;
        this.image = image;
    }
    
    public Integer getCameraId(){
        return cameraId;
    }
    public String getBrand(){
        return brand;
    }
    public String getModel(){
        return model;
    }
    public Double getPrice(){
        return price;
    }
    public String getStatus(){
        return status;
    }
    public Date getDate(){
        return date;
    }
    public String getImage(){
        return image;
    }
    
}
