package com.consulti.apiconsulti.model;

import java.util.UUID;

import com.consulti.apiconsulti.entity.Post;

import lombok.Data;

@Data
public class PostReqDTO extends Post {
  UUID userId;
  UUID labelId;
}
