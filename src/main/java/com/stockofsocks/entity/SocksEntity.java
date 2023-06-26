package com.stockofsocks.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "socks")
@Getter
@Setter
public class SocksEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String color;

    @Column(name = "cotton_part", nullable = false)
    private int cottonPart;

    @Column(nullable = false)
    private int quantity;

    public SocksEntity() {
    }

    public SocksEntity(String color, int cottonPart) {
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = 0;
    }


}