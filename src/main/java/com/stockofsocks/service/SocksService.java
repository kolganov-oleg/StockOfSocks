package com.stockofsocks.service;

import com.stockofsocks.Dto.Socks;
import com.stockofsocks.entity.SocksEntity;
import com.stockofsocks.exception.ExceptionHandler;
import com.stockofsocks.mapper.SocksMapper;
import com.stockofsocks.repository.SocksRepository;
import jakarta.transaction.SystemException;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


@Service
//@Component
public class SocksService {
    private final SocksMapper socksMapper;
    private final SocksRepository socksRepository;

    public SocksService(SocksMapper socksMapper, SocksRepository socksRepository) {
        this.socksMapper = socksMapper;
        this.socksRepository = socksRepository;
    }

    public void registerSocksIncome(Socks socks) {
        if (socks.getQuantity() <= 0) {
            throw new IllegalArgumentException("Invalid quantity value");
        }

        SocksEntity socksEntity = socksRepository.findByColorAndCottonPart(socks.getColor(),
                        socks.getCottonPart())
                .orElse(new SocksEntity(socks.getColor(), socks.getCottonPart()));

        socksEntity.setQuantity(socksEntity.getQuantity() + socks.getQuantity());

        socksRepository.save(socksEntity);
    }

    public void registerSocksOutcome(Socks socks) {
        if (socks.getQuantity() <= 0) {
            throw new IllegalArgumentException("Invalid quantity value");
        }

        SocksEntity socksEntity = socksRepository.findByColorAndCottonPart(socks.getColor(),
                        socks.getCottonPart())
                .orElseThrow(() -> new IllegalArgumentException("Socks not found"));

        if (socks.getQuantity() > socksEntity.getQuantity()) {
            throw new IllegalArgumentException("Not enough socks in stock");
        }

        socksEntity.setQuantity(socksEntity.getQuantity() - socks.getQuantity());

        socksRepository.save(socksEntity);
    }

    public int getSocksCount(String color, String operation, int cottonPart) {
        if (color == null || color.isEmpty()) {
            throw new IllegalArgumentException("Invalid color value");
        }

        if (operation == null || operation.isEmpty()) {
            throw new IllegalArgumentException("Invalid operation value");
        }

        if (cottonPart < 0 || cottonPart > 100) {
            throw new IllegalArgumentException("Invalid cottonPart value");
        }

        switch (operation) {
            case "moreThan":
                return socksRepository.countByColorAndCottonPartGreaterThan(color, cottonPart);
            case "lessThan":
                return socksRepository.countByColorAndCottonPartLessThan(color, cottonPart);
            case "equal":
                return socksRepository.countByColorAndCottonPartEquals(color, cottonPart);
            default:
                throw new IllegalArgumentException("Invalid operation value");
        }
    }
    public List<Socks> getAllSocks() {
        try {
            return socksMapper.toModelList(socksRepository.findAll()) ;
        } catch (DataAccessException ex) {
            throw new ExceptionHandler.SystemException("Unable to retrieve Socks data");
        }
    }
}