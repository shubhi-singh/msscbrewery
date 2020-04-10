package com.example.demo.web.controller;

import com.example.demo.web.model.BeerDto;
import com.example.demo.web.services.BeerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Validated
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {
    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeer( @NotNull @PathVariable("beerId") UUID beerId)
    {
        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }
    @PostMapping
    public  ResponseEntity handlePost(@NotNull @Valid @RequestBody BeerDto beerDto)
    {
        BeerDto savedDto = beerService.saveNewBeer(beerDto);
        HttpHeaders header = new HttpHeaders();
        header.add("Location","/api/v1/beer/" + savedDto.getId().toString());
        return new ResponseEntity(header, HttpStatus.CREATED);
    }
    @PutMapping("/{beerId}")
    public ResponseEntity handleUpdate(@NotNull @PathVariable("beerId") UUID beerId, @Valid @RequestBody BeerDto beerDto)
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
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> validationErrorHandler(ConstraintViolationException e)
    {
        List<String> errors = new ArrayList<>(e.getConstraintViolations().size());
        e.getConstraintViolations().forEach(constraintViolation->
        {
            errors.add(constraintViolation.getPropertyPath() + " ::: " + constraintViolation.getMessage());
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
