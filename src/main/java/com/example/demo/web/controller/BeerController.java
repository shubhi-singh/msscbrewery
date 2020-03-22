package com.example.demo.web.controller;

import com.example.demo.web.model.BeerDto;
import com.example.demo.web.services.BeerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {
    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId)
    {
        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }
    @PostMapping
    public  ResponseEntity handlePost(@RequestBody BeerDto beerDto)
    {
        BeerDto savedDto = beerService.saveNewBeer(beerDto);
        HttpHeaders header = new HttpHeaders();
        header.add("Location","/api/v1/beer/" + savedDto.getId().toString());
        return new ResponseEntity(header, HttpStatus.CREATED);
    }
    @PutMapping("/{beerId}")
    public ResponseEntity handleUpdate(@PathVariable("beerId") UUID beerId, @RequestBody BeerDto beerDto)
    {
        beerService.updateBeer(beerId, beerDto);
        return new ResponseEntity((HttpStatus.NO_CONTENT));
    }
    @DeleteMapping("/{beerId}")
    public ResponseEntity deleteBeer(@PathVariable("beerId") UUID beerId)
    {
        beerService.deleteById(beerId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
