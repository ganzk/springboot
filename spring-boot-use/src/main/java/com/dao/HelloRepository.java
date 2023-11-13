package com.dao;

import com.domain.StudentDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HelloRepository extends JpaRepository<StudentDo,Long> {


    @Query("from StudentDo where id = 1")
    StudentDo findStudent();
}
