package com.app.arcx.controllers;

import com.app.arcx.domain.AreaOfInterest;
import com.app.arcx.domain.AreaOfInterestSubItems;
import com.app.arcx.repository.AreaOfInterestRepository;
import com.app.arcx.repository.AreaOfInterestSubItemsRepository;
import com.app.arcx.services.AreaOfInterestService;
import com.app.arcx.services.UsernameCheckService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AreaOfInterestSubItemsController {


    private final AreaOfInterestSubItemsRepository repository;

    private UsernameCheckService usernameCheckService = new UsernameCheckService();

    public boolean userVerified = false;

    public AreaOfInterestSubItemsController(AreaOfInterestSubItemsRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/area_of_interest_sub_items")
    public List<AreaOfInterestSubItems> getAOISubItem(@RequestHeader String userid, @RequestHeader String userip) {

        List<AreaOfInterestSubItems> response = null;

        userVerified = usernameCheckService.userCheck(userid);

        if(userVerified){
            response = repository.findAll();
            }
        return response;
    }

    @PostMapping("/area_of_interest_sub_items")
    public ResponseEntity<String> postAOISubItem(@RequestHeader String userid, @RequestHeader String userip, @RequestBody AreaOfInterestSubItems subItemBody) {

        userVerified = usernameCheckService.userCheck(userid);
        HttpStatus status = null;
        String response = "";

        if(userVerified){

            AreaOfInterestSubItems subItem = new AreaOfInterestSubItems();
            subItem.name = subItemBody.name;
            subItem.setParentid(subItemBody.getParentid());

            repository.save(subItem);
            response = "User Verified";
            status = HttpStatus.CREATED;

        }else{
            status = HttpStatus.UNAUTHORIZED;
            response = "Post Failed";
        }

        return new ResponseEntity<String>(response, status);

    }

    @DeleteMapping("/area_of_interest_sub_items/{sub_item_id}")
    public ResponseEntity<String> deleteSubItem(@RequestHeader String userid, @RequestHeader String userip, @PathVariable int sub_item_id) {

        userVerified = usernameCheckService.userCheck(userid);
        HttpStatus status = null;
        String response = "";


        if(userVerified){
            repository.deleteById(sub_item_id);

            response = "User Verified and Delete Successful.";
            status = HttpStatus.ACCEPTED;

        }else{
            status = HttpStatus.UNAUTHORIZED;
            response = "Delete Failed";
        }

        return new ResponseEntity<String>(response, status);
    }

    @PutMapping("/area_of_interest_sub_items")
    public ResponseEntity<String> putAOISubItems(@RequestHeader String userid, @RequestHeader String userip, @RequestBody AreaOfInterestSubItems subItemBody ) {

        userVerified = usernameCheckService.userCheck(userid);
        HttpStatus status = null;
        String response = "";

        if(userVerified){

            repository.save(subItemBody);
            response = "User Verified";
            status = HttpStatus.OK;

        }else{
            status = HttpStatus.UNAUTHORIZED;
            response = "Post Failed";
        }

        return new ResponseEntity<String>(response, status);

    }


}
