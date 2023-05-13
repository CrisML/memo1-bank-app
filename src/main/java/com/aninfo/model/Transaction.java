package com.aninfo.model;

import javax.persistence.*;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private Long cbu;
    private Double amount;
    private String category;

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public void setCategory(String type){
        this.category = type;
    }

    public String getCategory(){
        return this.category;
    }

    public void setCbu(Long cbu){
        this.cbu = cbu;
    }

    public Long getCbu(){
        return this.cbu;
    }

    public void setAmount(Double money){
        this.amount = money;
    }

    public Double getAmount(){
        return this.amount;
    }

}
