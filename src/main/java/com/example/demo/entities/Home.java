package com.example.demo.entities;

import jakarta.persistence.*;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "home")
public class Home {
    @Id
	@Column(name = "id")
    private Long id;

	@Column(name = "houseCode")
    private String houseCode;

	@Column(name = "nombre")
	private String nombre;

    public Home(){}

	public Home(String houseCode,String nombre) {
		super();
		this.houseCode = houseCode;
		this.nombre = nombre;

	}
	
	@Override
	public String toString() {
		return "Home [id=" + id + ", houseCode=" + houseCode + ", name=" + nombre + "]";
	}

	public Long getId() {return id;}

	public void setId(Long id) {this.id = id;}

	public String getHouseCode() {return houseCode;}

	public void setHouseCode(String houseCode) {this.houseCode = houseCode;}

	public String getNombre() {return nombre;}

	public void setNombre(String nombre) {this.nombre = nombre;}
}