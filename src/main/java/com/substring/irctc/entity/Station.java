package com.substring.irctc.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String code;
    private String name;
    private String city;
    private String state;
}
