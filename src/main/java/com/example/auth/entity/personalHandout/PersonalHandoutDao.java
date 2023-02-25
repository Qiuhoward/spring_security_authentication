package com.example.auth.entity.personalHandout;


import com.example.auth.entity.personalHandout.PersonalHandout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalHandoutDao extends JpaRepository<PersonalHandout,Integer> {

    List<PersonalHandout>findAll();
}
