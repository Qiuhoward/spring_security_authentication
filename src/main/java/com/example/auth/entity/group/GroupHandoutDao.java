package com.example.auth.entity.group;

import com.example.auth.entity.group.GroupHandout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupHandoutDao extends JpaRepository<GroupHandout,Integer> {

}
