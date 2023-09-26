package com.example.kafkastutdy.repository;

import com.example.kafkastutdy.entity.member.MemberRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRoleRepository extends JpaRepository<MemberRole, Long> {

}
