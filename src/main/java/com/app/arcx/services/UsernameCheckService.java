package com.app.arcx.services;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

public class UsernameCheckService {

    public boolean userCheck(String username){
        boolean verifiedUser = false;

        String allowedUsers = System.getenv("ARCX_USERNAMES");
        String[] splitUsers = allowedUsers.split(",");

        List<String> usernameList = Arrays.asList(splitUsers);

        for (String nameToCheck:  usernameList)
              {
                  try{
                      if (nameToCheck.equals(username)){
                          verifiedUser = true;
                      }

                  }catch (Exception e){
                      System.out.println(e);

                  }

             }



        return verifiedUser;
    }
}
