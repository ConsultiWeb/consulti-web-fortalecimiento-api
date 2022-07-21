package com.consulti.apiconsulti.repository;

import com.consulti.apiconsulti.entity.HistoricoContacto;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoContactoRepository extends JpaRepository<HistoricoContacto, UUID> {
    HistoricoContacto findByEmail(String email);
    Boolean existsByEmail( String email);
    Boolean existsByIdentificacion(String identificacion);
    Boolean existsByIdAndEmail(UUID id, String email);
}
