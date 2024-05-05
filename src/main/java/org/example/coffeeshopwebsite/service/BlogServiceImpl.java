package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Articles;
import org.example.coffeeshopwebsite.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;
    @Override
    public List<Articles> getAllBlog() {
        return blogRepository.findAll();
    }
}
