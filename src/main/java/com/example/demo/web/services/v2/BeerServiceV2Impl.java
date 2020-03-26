package com.example.demo.web.services.v2;

import com.example.demo.web.model.BeerDto;
import com.example.demo.web.model.V2.BeerDtoV2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@Slf4j
public class BeerServiceV2Impl implements BeerServiceV2 {
    @Override
    public BeerDtoV2 getBeerById(UUID beerId) {
        return null;
    }

    @Override
    public BeerDtoV2 saveNewBeer(BeerDtoV2 beerDto) {
        return BeerDtoV2.builder()
                .id(UUID.randomUUID())
                .build();
    }

    @Override
    public void updateBeer(UUID beerId, BeerDtoV2 beerDto) {


    }

    @Override
    public void deleteById(UUID beerId) {
    log.debug("Deleting a beer..");
    }
}
