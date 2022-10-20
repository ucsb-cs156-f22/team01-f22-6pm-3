package edu.ucsb.cs156.spring.backenddemo.controllers;

import edu.ucsb.cs156.spring.backenddemo.services.ZipCodeQueryService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(description="ZipCode info from http://www.zippopotam.us/")
@Slf4j
@RestController
@RequestMapping("/api/zipcode")
public class ZipCodeController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    ZipCodeQueryService ZipCodeQueryService;

    @ApiOperation(value="Get information about a US zipcode", notes="zipcode data uploaded to Zippopotam")
    @GetMapping("/get")
    public ResponseEntity<String> getZipCodes(
        @ApiParam("zipcode, e.g. 93117") @RequestParam String zipcode
    ) throws JsonProcessingException {
        log.info("getZipCodes: zipcode={}", zipcode);
        String result = ZipCodeQueryService.getJSON(zipcode);
        return ResponseEntity.ok().body(result);
    }

}