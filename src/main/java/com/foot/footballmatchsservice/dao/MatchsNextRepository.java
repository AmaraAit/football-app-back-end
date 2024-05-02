package com.foot.footballmatchsservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foot.footballmatchsservice.entities.MatchsNext;
@Repository
public interface MatchsNextRepository extends JpaRepository<MatchsNext, Long>{

}
