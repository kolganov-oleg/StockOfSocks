package com.stockofsocks.controller;

import com.stockofsocks.Dto.Socks;
import com.stockofsocks.exception.ExceptionHandler.InvalidRequestException;
import com.stockofsocks.exception.ExceptionHandler.SystemException;
import com.stockofsocks.service.SocksService;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/socks")
public class SocksController {

    private final SocksService socksService;

    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }


    @PostMapping("/income")
    public ResponseEntity<Void> registerIncome(@RequestBody Socks socks) throws SystemException {
        if (socks.getColor() == null
                || socks.getCottonPart() == null || socks.getQuantity() == null
                || socks.getColor().isEmpty() || socks.getCottonPart() < 0 || socks.getCottonPart() > 100
                || socks.getQuantity() < 1) {
            throw new InvalidRequestException("Invalid request parameters");
        }

        try {
            socksService.registerSocksIncome(socks);
            return ResponseEntity.ok().build();
        } catch (DataAccessException ex) {
            throw new SystemException("Unable to save Socks data");
        }
    }

    @PostMapping("/outcome")
    public ResponseEntity<Void> registerOutcome(@RequestBody Socks socks) throws SystemException {
        if (socks.getColor() == null || socks.getCottonPart() == null || socks.getQuantity() == null
                || socks.getColor().isEmpty() || socks.getCottonPart() < 0 || socks.getCottonPart() > 100
                || socks.getQuantity() < 1) {
            throw new InvalidRequestException("Invalid request parameters");
        }

        try {
            socksService.registerSocksOutcome(socks);
            return ResponseEntity.ok().build();
        } catch (DataAccessException ex) {
            throw new SystemException("Unable to save Socks data");
        }
    }

    @GetMapping
    public ResponseEntity<String> getSocksCount(@RequestParam("color") String color,
                                                @RequestParam("operation") String operation,
                                                @RequestParam("cottonPart") int cottonPart) throws SystemException {
        if (color == null || operation == null || cottonPart < 0 || cottonPart > 100) {
            throw new InvalidRequestException("Invalid request parameters");
        }

        try {
            int count = socksService.getSocksCount(color, operation, cottonPart);
            return ResponseEntity.ok().body(Integer.toString(count));
        } catch (DataAccessException ex) {
            throw new SystemException("Unable to retrieve Socks data");
        }
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Socks>> getAllSocks() {
        try {
            List<Socks> socksList = socksService.getAllSocks();
            return ResponseEntity.ok().body(socksList);
        } catch (DataAccessException ex) {
            throw new SystemException("Unable to retrieve Socks data");
        } catch (SystemException e) {
            throw new RuntimeException(e);
        }
    }

}