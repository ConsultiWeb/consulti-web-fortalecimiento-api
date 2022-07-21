package com.consulti.apiconsulti.repository;

import java.util.UUID;
import com.consulti.apiconsulti.entity.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, UUID> {

}
