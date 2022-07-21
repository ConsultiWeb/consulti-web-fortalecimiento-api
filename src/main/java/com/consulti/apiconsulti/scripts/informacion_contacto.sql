-- Table: consulti.informacion_contacto

-- DROP TABLE consulti.informacion_contacto;

CREATE TABLE IF NOT EXISTS consulti.informacion_contacto
(
    id uuid NOT NULL,
    user_id uuid NOT NULL,
    tipo_contacto_id uuid NOT NULL,
    valor character varying(100) COLLATE pg_catalog."default" NOT NULL,
    fecha_creacion timestamp without time zone NOT NULL,
    user_creacion character varying(100) COLLATE pg_catalog."default" NOT NULL,
    fecha_modificacion timestamp without time zone,
    user_modificacion character varying(100) COLLATE pg_catalog."default",
    estado character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT informacion_contacto_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE consulti.informacion_contacto
    OWNER to postgres;

ALTER TABLE ONLY consulti.informacion_contacto
    ADD CONSTRAINT informacion_contacto_user_id_fk FOREIGN KEY (user_id) REFERENCES consulti.user(id);

ALTER TABLE ONLY consulti.informacion_contacto
    ADD CONSTRAINT informacion_contacto_tipo_contacto_id_fk FOREIGN KEY (tipo_contacto_id) REFERENCES consulti.tipo_contacto(id);