package com.stockofsocks.Dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Socks {

    private String color;

    private Integer cottonPart;

    private Integer quantity;

    public Socks() {
    }

    public Socks(String color, int cottonPart, int quantity) {
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }


}