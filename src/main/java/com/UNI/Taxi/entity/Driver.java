package com.UNI.Taxi.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.apache.logging.log4j.message.Message;
import org.springframework.lang.NonNull;

@Data
@Entity
@Table(name = "Driver")
public class Driver
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;


    private String name;
    @Column(nullable =false)
    private String family;
    private Boolean published;
}
