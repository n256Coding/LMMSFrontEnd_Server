package com.n256coding.frontend.Controllers;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.n256coding.frontend.Models.User;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin(origins = "*")
@RestController
public class RequestController {

    @RequestMapping("/user")
    public Principal user(Principal user){
        return user;
    }

    @GetMapping("/user/{username}")
    public User getUserInfo(@PathVariable String username) {
        System.out.println("Username found: "+username);
        MongoClientURI uri = new MongoClientURI("mongodb+srv://moodle:QDuCgZhqbo2QV8Ps@lmmsproject-r8wzc.mongodb.net/test?retryWrites=true");
        MongoOperations mongoOperations = new MongoTemplate(new MongoClient(uri), "ResourceDB");
        Query query = new Query(Criteria.where("userName").is(username));
        return mongoOperations.findOne(query, User.class);
    }
}
