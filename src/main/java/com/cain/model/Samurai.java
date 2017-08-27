package com.cain.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Samurai {
	
	@Id
    private Integer id;
	
	@Column
    private String name;
	
	@Column
    private String sex;
	
	@Column
    private Long leadership;
	
	@Column
    private Long power;
	
	@Column
    private Date modifyDate;
    
    public Samurai() {};
    
    public Samurai(Integer id, String name, String sex, Long leadership, Long power, Date modifyDate) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.leadership = leadership;
        this.power = power;
        this.modifyDate = modifyDate;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    
    public Long getLeadership() {
        return leadership;
    }

    public void setLeadership(Long leadership) {
        this.leadership = leadership;
    }
    
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}