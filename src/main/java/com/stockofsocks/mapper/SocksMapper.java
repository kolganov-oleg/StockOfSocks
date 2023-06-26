package com.stockofsocks.mapper;


import com.stockofsocks.Dto.Socks;
import com.stockofsocks.entity.SocksEntity;
import java.lang.reflect.Type;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

@Component
public class SocksMapper {

    private final ModelMapper modelMapper;

    public SocksMapper() {
        this.modelMapper = new ModelMapper();
    }

    public SocksEntity toEntity(Socks socks) {
        return modelMapper.map(socks, SocksEntity.class);
    }

    public Socks toModel(SocksEntity socksEntity) {
        return modelMapper.map(socksEntity, Socks.class);
    }
    public List<SocksEntity> toEntityList(List<Socks> socksList) {
        Type listType = new TypeToken<List<SocksEntity>>() {}.getType();
        return modelMapper.map(socksList, listType);
    }

    public List<Socks> toModelList(List<SocksEntity> socksEntityList) {
        Type listType = new TypeToken<List<Socks>>() {}.getType();
        return modelMapper.map(socksEntityList, listType);
    }
}