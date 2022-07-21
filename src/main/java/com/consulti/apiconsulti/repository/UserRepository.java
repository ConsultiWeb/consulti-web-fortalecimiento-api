package com.consulti.apiconsulti.repository;

import java.util.UUID;
import com.consulti.apiconsulti.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
  User findByUsername(String username);

  User findByEmail(String email);

  Boolean existsByEmail( String email);

  Boolean existsByUsername(String username);

  Boolean existsByIdentificacion(String identificacion);

  Boolean existsByIdAndEmail(UUID id, String email);

  Boolean existsByIdAndUsername(UUID id, String username);

}
