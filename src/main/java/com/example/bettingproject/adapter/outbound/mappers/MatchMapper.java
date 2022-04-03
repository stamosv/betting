package com.example.bettingproject.adapter.outbound.mappers;


import com.example.bettingproject.adapter.outbound.persistence.entity.MatchT;
import com.example.bettingproject.shared.model.MatchD;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = MatchOddMapper.class)
public interface MatchMapper {

    MatchMapper INSTANCE = Mappers.getMapper(MatchMapper.class);

    MatchT toEntity(MatchD matchD);

    MatchD toDomain(MatchT matchT);

}
