package com.example.kafkastutdy.repository;

import com.example.kafkastutdy.entity.member.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @EntityGraph(value = "roles")
    List<Member> findAll();

    Optional<Member> findByUserId(String userId);

    boolean existsByUserId(String userId);

}
