package com.api.CourseApi.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.CourseApi.domain.entity.Receipt;

public interface RecieptRepository extends JpaRepository<Receipt, Integer>{

}
