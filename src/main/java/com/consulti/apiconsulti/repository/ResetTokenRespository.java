package com.consulti.apiconsulti.repository;

import java.util.UUID;

import com.consulti.apiconsulti.entity.ResetToken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResetTokenRespository extends JpaRepository<ResetToken, UUID> {
  ResetToken findByToken(String token);
}
