CREATE TABLE IF NOT EXISTS consulti.cargo
(
    id uuid NOT NULL,
    nombre character varying(100) COLLATE pg_catalog."default" NOT NULL,
    fecha_creacion timestamp without time zone NOT NULL,
    user_creacion character varying(100) COLLATE pg_catalog."default" NOT NULL,
    fecha_modificacion timestamp without time zone,
    user_modificacion character varying(100) COLLATE pg_catalog."default",
    estado character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT cargo_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE consulti.cargo
    OWNER to postgres;