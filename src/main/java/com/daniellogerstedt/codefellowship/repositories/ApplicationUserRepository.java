package com.daniellogerstedt.codefellowship.repositories;

import org.springframework.data.repository.CrudRepository;
import com.daniellogerstedt.codefellowship.models.ApplicationUser;

public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, Long> {

    public ApplicationUser findByUsername(String username);
}
