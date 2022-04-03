package com.example.bettingproject.adapter.outbound.mappers;

import com.example.bettingproject.adapter.outbound.persistence.entity.MatchOddT;
import com.example.bettingproject.shared.model.MatchOddD;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MatchOddMapper {

    MatchOddMapper INSTANCE = Mappers.getMapper(MatchOddMapper.class);

    MatchOddT toEntity(MatchOddD matchOddD);

    MatchOddD toDomain(MatchOddT matchOddT);

    List<MatchOddT> toListEntity(List<MatchOddD> matchOddDs);

    List<MatchOddD> toListDomain(List<MatchOddT> matchOddTs);

}
