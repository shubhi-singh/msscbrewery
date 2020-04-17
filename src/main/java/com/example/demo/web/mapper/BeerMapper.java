package com.example.demo.web.mapper;

import com.example.demo.domain.Beer;
import com.example.demo.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {
    BeerDto beerToBeerDto(Beer beer);
    Beer beerDtotoBeer(BeerDto beerDto);
}
