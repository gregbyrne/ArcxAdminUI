package com.app.arcx.controllers;

import com.app.arcx.domain.AdditionalInformation;
import com.app.arcx.domain.StepsToHelpPrepareItems;
import com.app.arcx.repository.AdditionalInformationRepository;
import com.app.arcx.services.UsernameCheckService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdditionalInformationController {


    private UsernameCheckService usernameCheckService = new UsernameCheckService();

    public boolean userVerified = false;

    private final AdditionalInformationRepository repository;

    public AdditionalInformationController(AdditionalInformationRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/additional_information")
    public List<AdditionalInformation> getAdditionalInformation(@RequestHeader String userid, @RequestHeader String userip) {

        List<AdditionalInformation> response = null;

        userVerified = usernameCheckService.userCheck(userid);

        if(userVerified){
            response = repository.findAll();
        }
        return response;
    }
    @PutMapping("/additional_information")
    public ResponseEntity<String> putAdditionalInformation(@RequestHeader String userid, @RequestHeader String userip, @RequestBody AdditionalInformation AIBody ) {


        userVerified = usernameCheckService.userCheck(userid);
        HttpStatus status = null;
        String response = "";

        if(userVerified){

            repository.save(AIBody);
            response = "User Verified";
            status = HttpStatus.OK;

        }else{
            status = HttpStatus.UNAUTHORIZED;
            response = "Post Failed";
        }

        return new ResponseEntity<String>(response, status);

    }


}
