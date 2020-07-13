package com.epitech.auth.models;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "contact")
public class Contact {
    public Contact(String n, String p){
        name = n;
        phone = p;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column
    private String name;

    @NotBlank
    @Column
    private String phone;

    public String getName(){return this.name;}
    public void setName(String name) {this.name = name;}

    public String getPhone(){return this.phone; }
    public void setPhone(String phone) {this.phone = phone;}
}
