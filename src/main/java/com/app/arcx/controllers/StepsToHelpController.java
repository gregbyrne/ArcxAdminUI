package com.app.arcx.controllers;

import com.app.arcx.domain.AreaOfInterest;
import com.app.arcx.domain.StepsToHelpPrepare;
import com.app.arcx.repository.AreaOfInterestRepository;
import com.app.arcx.repository.StepsToHelpPrepareRepository;
import com.app.arcx.services.AreaOfInterestService;
import com.app.arcx.services.StepsToHelpPrepareService;
import com.app.arcx.services.UsernameCheckService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StepsToHelpController {

    @Resource
    StepsToHelpPrepareService stepsToHelpPrepareService;

    public boolean userVerified = false;

    private UsernameCheckService usernameCheckService = new UsernameCheckService();

    private final StepsToHelpPrepareRepository repository;

    public StepsToHelpController(StepsToHelpPrepareRepository repository) {
        this.repository = repository;
    }

    @DeleteMapping("/delete_step")
    public ResponseEntity<String> deleteStep(@RequestHeader String userid, @RequestHeader String userip, @RequestParam int step_id)
    {

        userVerified = usernameCheckService.userCheck(userid);
        HttpStatus status = null;
        String response = "";

        if(userVerified){
            stepsToHelpPrepareService.deleteStepToHelpPrepare(step_id);

            response = "User Verified and Delete Successful.";
            status = HttpStatus.OK;

        }else{
            status = HttpStatus.UNAUTHORIZED;
            response = "Delete Failed";
        }

        return new ResponseEntity<String>(response, status);
    }

    @DeleteMapping("/delete_step_item")
    public ResponseEntity<String> deleteStepItem(@RequestHeader String userid, @RequestHeader String userip, @RequestParam int step_item_id)
    {
        userVerified = usernameCheckService.userCheck(userid);
        HttpStatus status = null;
        String response = "";

        if(userVerified){
            stepsToHelpPrepareService.deleteStepToHelpPrepareItem(step_item_id);
            response = "User Verified and Delete Successful.";
            status = HttpStatus.OK;

        }else{
            status = HttpStatus.UNAUTHORIZED;
            response = "Delete Failed";
        }

        return new ResponseEntity<String>(response, status);

    }

    @GetMapping("/steps_to_help_prepare")
    public List<StepsToHelpPrepare> getSTHP(@RequestHeader String userid, @RequestHeader String userip) {

        List<StepsToHelpPrepare> response = null;
        userVerified = usernameCheckService.userCheck(userid);

        if(userVerified){
            response = repository.findAll();
        }
        return response;
    }

    @PostMapping("/steps_to_help_prepare")
    public ResponseEntity<String> postSTHP(@RequestHeader String userid, @RequestHeader String userip, @RequestBody StepsToHelpPrepare stepBody) {

        userVerified = usernameCheckService.userCheck(userid);
        HttpStatus status = null;
        String response = "";

        if(userVerified){

            List<StepsToHelpPrepare> tocount = repository.findAll();
            StepsToHelpPrepare newStep = new StepsToHelpPrepare();
            newStep.name = stepBody.name;
            newStep.setPosition(tocount.size());
            repository.save(newStep);
            response = "User Verified";
            status = HttpStatus.CREATED;

        }else{
            status = HttpStatus.UNAUTHORIZED;
            response = "Post Failed";
        }

        return new ResponseEntity<String>(response, status);

    }

    @PutMapping("/steps_to_help_prepare/")
    public ResponseEntity<String> putSTHP(@RequestHeader String userid, @RequestHeader String userip, @RequestBody StepsToHelpPrepare Stepbody ) {

        userVerified = usernameCheckService.userCheck(userid);
        HttpStatus status = null;
        String response = "";

        if(userVerified){

            repository.save(Stepbody);
            response = "User Verified";
            status = HttpStatus.OK;

        }else{
            status = HttpStatus.UNAUTHORIZED;
            response = "Put Failed";
        }

        return new ResponseEntity<String>(response, status);

    }



}
