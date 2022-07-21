package com.consulti.apiconsulti.repository;

import java.util.UUID;

import com.consulti.apiconsulti.entity.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

}
