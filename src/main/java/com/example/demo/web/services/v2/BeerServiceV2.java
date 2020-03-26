package com.example.demo.web.services.v2;

import com.example.demo.web.model.BeerDto;
import com.example.demo.web.model.V2.BeerDtoV2;

import java.util.UUID;

public interface BeerServiceV2 {
    BeerDtoV2 getBeerById(UUID beerId);

    BeerDtoV2 saveNewBeer(BeerDtoV2 beerDto);


    void updateBeer(UUID beerId, BeerDtoV2 beerDto);

    void deleteById(UUID beerId);
}
