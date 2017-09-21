package com.api.CourseApi.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;


import com.api.CourseApi.domain.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer>{
    
}
