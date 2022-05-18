package com.app.arcx.controllers;

import com.app.arcx.domain.AdditionalInformation;
import com.app.arcx.domain.Regions;
import com.app.arcx.repository.RegionsRepository;
import com.app.arcx.services.UsernameCheckService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RegionController {


    private UsernameCheckService usernameCheckService = new UsernameCheckService();

    public boolean userVerified = false;

    private final RegionsRepository repository;

    public RegionController(RegionsRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/regions")
    public List<Regions> getRegion(@RequestHeader String userid, @RequestHeader String userip) {

        List<Regions> response = null;

        userVerified = usernameCheckService.userCheck(userid);

        if(userVerified){
            response = repository.findAll();
        }
        return response;
    }

}
