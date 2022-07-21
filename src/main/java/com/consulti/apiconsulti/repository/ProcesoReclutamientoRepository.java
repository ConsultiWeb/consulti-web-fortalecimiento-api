package com.consulti.apiconsulti.repository;

import java.util.UUID;
import com.consulti.apiconsulti.entity.ProcesoReclutamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcesoReclutamientoRepository extends JpaRepository<ProcesoReclutamiento, UUID> {

}
