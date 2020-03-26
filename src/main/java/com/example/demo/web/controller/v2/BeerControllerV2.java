package com.example.demo.web.controller.v2;

import com.example.demo.web.model.BeerDto;
import com.example.demo.web.model.V2.BeerDtoV2;
import com.example.demo.web.services.BeerService;
import com.example.demo.web.services.v2.BeerServiceV2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@RestController
@RequestMapping("/api/v2/beer")
public class BeerControllerV2 {
    private final BeerServiceV2 beerServiceV2;

    public BeerControllerV2(BeerServiceV2 beerService) {
        this.beerServiceV2 = beerService;
    }

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDtoV2> getBeer(@PathVariable("beerId") UUID beerId)
    {
        return new ResponseEntity<>(beerServiceV2.getBeerById(beerId), HttpStatus.OK);
    }
    @PostMapping
    public  ResponseEntity handlePost(@RequestBody BeerDtoV2 beerDto)
    {
        BeerDtoV2 savedDto = beerServiceV2.saveNewBeer(beerDto);
        HttpHeaders header = new HttpHeaders();
        header.add("Location","/api/v1/beer/" + savedDto.getId().toString());
        return new ResponseEntity(header, HttpStatus.CREATED);
    }
    @PutMapping("/{beerId}")
    public ResponseEntity handleUpdate(@PathVariable("beerId") UUID beerId, @RequestBody BeerDtoV2 beerDto)
    {
        beerServiceV2.updateBeer(beerId, beerDto);
        return new ResponseEntity((HttpStatus.NO_CONTENT));
    }
    @DeleteMapping("/{beerId}")
    public ResponseEntity deleteBeer(@PathVariable("beerId") UUID beerId)
    {
        beerServiceV2.deleteById(beerId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
