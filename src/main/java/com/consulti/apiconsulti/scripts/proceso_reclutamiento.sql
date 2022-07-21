-- Table: consulti.proceso_reclutamiento

-- DROP TABLE consulti.proceso_reclutamiento;

CREATE TABLE IF NOT EXISTS consulti.proceso_reclutamiento
(
    id uuid NOT NULL,
    nombre character varying(100) COLLATE pg_catalog."default" NOT NULL,
    fecha_inicio timestamp without time zone NOT NULL,
    fecha_fin timestamp without time zone NOT NULL,
    cargo_id uuid NOT NULL,
    fecha_creacion timestamp without time zone NOT NULL,
    user_creacion character varying COLLATE pg_catalog."default" NOT NULL,
    fecha_modificacion timestamp without time zone,
    user_modificacion character varying(100) COLLATE pg_catalog."default",
    estado character varying(100) COLLATE pg_catalog."default" NOT NULL,
    esta_publicado character varying(10) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT proceso_reclutamiento_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE consulti.proceso_reclutamiento
    OWNER to postgres;

ALTER TABLE ONLY consulti.proceso_reclutamiento
    ADD CONSTRAINT proceso_reclutamiento_cargo_id_fk FOREIGN KEY (cargo_id) REFERENCES consulti.cargo(id);
