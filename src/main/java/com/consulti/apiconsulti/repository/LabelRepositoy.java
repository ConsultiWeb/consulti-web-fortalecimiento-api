package com.consulti.apiconsulti.repository;

import java.util.UUID;

import com.consulti.apiconsulti.entity.Label;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepositoy extends JpaRepository<Label, UUID> {

}
