package com.consulti.apiconsulti.model;

import com.consulti.apiconsulti.entity.Resume;
import com.consulti.apiconsulti.entity.User;
import lombok.Data;

import java.util.List;
import java.util.UUID;
@Data
public class DetalleResumenDTO {
    private User user;
    private Resume resume;
}
