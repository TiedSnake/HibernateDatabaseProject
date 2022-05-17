package com.to;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "AProduct")
public class AProduct
{

    @Id
    @Column(name = "prID")
    private int prID;
    @Column(name = "prName")
    private String prName;
    @Column(name = "ProductCategory")
    private String ProductCategory;
    @Column(name = "prPrice")
    private float prPrice;
    @Column(name = "pdate")
    private Date pdate;

    public AProduct(int prID, String prName, String ProductCategory, float prPrice)
    {
        this.prID = prID;
        this.prName = prName;
        this.ProductCategory = ProductCategory;
        this.prPrice = prPrice;
        this.pdate = new Date();
    }

    public AProduct()
    {
        this.pdate =new Date();
    }

    @Override
    public String toString()
    {
        return "AProduct{" + "prID=" + prID + ", prName=" + prName + ", ProductCategory=" + ProductCategory + ", prPrice=" + prPrice + ", pdate=" + pdate + '}';
    }

    
    public String getProductCategory()
    {
        return ProductCategory;
    }

    public void setProductCategory(String ProductCategory)
    {
        this.ProductCategory = ProductCategory;
    }

    public int getPrID()
    {
        return prID;
    }

    public void setPrID(int prID)
    {
        this.prID = prID;
    }

    public String getPrName()
    {
        return prName;
    }

    public void setPrName(String prName)
    {
        this.prName = prName;
    }

    public float getPrPrice()
    {
        return prPrice;
    }

    public void setPrPrice(float prPrice)
    {
        this.prPrice = prPrice;
    }

    public Date getPdate()
    {
        return pdate;
    }

    public void setPdate(Date pdate)
    {
        this.pdate = pdate;
    }  
}