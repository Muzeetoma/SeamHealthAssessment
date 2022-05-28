package com.seamhealth.doctors.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="address")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "homeAddress")
    private String homeAddress;

    @Column(name = "state")
    private String state;

    @Column(name = "lga")
    private String lga;

    @JsonIgnore
    @OneToOne(mappedBy = "address")
    private Doctor doctor;


}
