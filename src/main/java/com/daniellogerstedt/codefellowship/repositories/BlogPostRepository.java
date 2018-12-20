package com.daniellogerstedt.codefellowship.repositories;

import com.daniellogerstedt.codefellowship.models.BlogPost;
import org.springframework.data.repository.CrudRepository;

public interface BlogPostRepository extends CrudRepository<BlogPost, Long> {

}
