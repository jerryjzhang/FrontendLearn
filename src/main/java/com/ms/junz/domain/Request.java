package com.ms.junz.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Request {

	private int id;
	
    private String name;
    
    @Id
    @GeneratedValue
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="request_name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
