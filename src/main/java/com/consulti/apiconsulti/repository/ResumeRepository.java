package com.consulti.apiconsulti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import com.consulti.apiconsulti.entity.Resume;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, UUID> {

}
