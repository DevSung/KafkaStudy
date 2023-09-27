package com.example.kafkastutdy.repository;

import com.example.kafkastutdy.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
