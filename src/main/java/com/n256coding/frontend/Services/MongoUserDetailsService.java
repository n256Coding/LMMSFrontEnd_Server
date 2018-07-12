package com.n256coding.frontend.Services;

import com.mongodb.MongoClient;
import com.n256coding.frontend.Models.User;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MongoUserDetailsService implements UserDetailsService {

    private MongoOperations mongoOperations;

    public MongoUserDetailsService() {
        mongoOperations = new MongoTemplate(new MongoClient("127.0.0.1", 27017), "ResourceDB");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Query query = new Query(Criteria.where("userName").is(username));
        User dbUser = mongoOperations.findOne(query, User.class);
        if(dbUser != null){
            return new MongoUserDetails(dbUser);
        }
        throw new UsernameNotFoundException("Username not found");
    }
}
