package com.lwo.akulov.graph.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fname")
    private String fName;

    @Column(name = "mname")
    private String mName;

    @Column(name = "lname")
    private String lName;

    @Column(name = "email")
    private String eMail;

    @Column(name = "isactive")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
}
