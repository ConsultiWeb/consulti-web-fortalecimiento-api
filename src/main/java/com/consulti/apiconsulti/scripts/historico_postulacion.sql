-- Table: consulti.historico_postulacion

-- DROP TABLE consulti.historico_postulacion;

CREATE TABLE IF NOT EXISTS consulti.historico_postulacion
(
    id uuid NOT NULL,
    fecha_creacion timestamp without time zone,
    user_id character varying COLLATE pg_catalog."default",
    resume_id character varying COLLATE pg_catalog."default",
    CONSTRAINT historico_postulacion_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE consulti.historico_postulacion
    OWNER to postgres;