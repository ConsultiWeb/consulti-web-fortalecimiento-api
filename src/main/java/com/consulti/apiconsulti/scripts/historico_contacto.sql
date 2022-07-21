-- Table: consulti.historico_contacto

-- DROP TABLE consulti.historico_contacto;

CREATE TABLE IF NOT EXISTS consulti.historico_contacto
(
    id uuid NOT NULL,
    identificacion character varying(20) COLLATE pg_catalog."default",
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    nombres character varying(255) COLLATE pg_catalog."default",
    apellidos character varying(255) COLLATE pg_catalog."default",
    fecha_nacimiento timestamp without time zone,
    genero character varying(100) COLLATE pg_catalog."default",
    telefono character varying(20) COLLATE pg_catalog."default",
    ciudad_id uuid,
    fecha_creacion timestamp without time zone NOT NULL,
    user_creacion character varying(100) COLLATE pg_catalog."default" NOT NULL,
    fecha_modificacion timestamp without time zone,
    user_modificacion character varying(100) COLLATE pg_catalog."default",
    estado character varying(50) COLLATE pg_catalog."default" NOT NULL,
    esta_interesado character varying(10) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT historico_contactos_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE consulti.historico_contacto
    OWNER to postgres;