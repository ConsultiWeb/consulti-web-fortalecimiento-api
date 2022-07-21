-- Table: consulti.provincia

-- DROP TABLE consulti.provincia;

CREATE TABLE IF NOT EXISTS consulti.provincia
(
    id uuid NOT NULL,
    nombre character varying(100) COLLATE pg_catalog."default" NOT NULL,
    user_creacion character varying(100) COLLATE pg_catalog."default" NOT NULL,
    user_modificacion character varying(100) COLLATE pg_catalog."default",
    estado character varying(100) COLLATE pg_catalog."default" NOT NULL,
    fecha_creacion timestamp without time zone NOT NULL,
    fecha_modificacion timestamp without time zone,
    CONSTRAINT provincia_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE consulti.provincia
    OWNER to postgres;
INSERT INTO consulti.provincia (id, nombre, user_creacion, user_modificacion, estado, fecha_creacion, fecha_modificacion) VALUES ('ab5ce98d-6445-4582-a10b-d457aa45802c', 'AZUAY', 'erickespinozat', NULL, 'Activo', '2022-01-06 15:38:40.885', NULL);
INSERT INTO consulti.provincia (id, nombre, user_creacion, user_modificacion, estado, fecha_creacion, fecha_modificacion) VALUES ('4270bc8e-cad9-458d-ab2c-56d0167fa5ad', 'BOLIVAR', 'erickespinozat', NULL, 'Activo', '2022-01-06 15:38:40.886', NULL);
INSERT INTO consulti.provincia (id, nombre, user_creacion, user_modificacion, estado, fecha_creacion, fecha_modificacion) VALUES ('1aacbdfc-cfbd-4e02-bc51-0d0b7e555255', 'CAÑAR', 'erickespinozat', NULL, 'Activo', '2022-01-06 15:38:40.887', NULL);
INSERT INTO consulti.provincia (id, nombre, user_creacion, user_modificacion, estado, fecha_creacion, fecha_modificacion) VALUES ('8ecc59e5-f92f-4778-9f7d-8878b2b4e47e', 'CARCHI', 'erickespinozat', NULL, 'Activo', '2022-01-06 15:38:40.888', NULL);
INSERT INTO consulti.provincia (id, nombre, user_creacion, user_modificacion, estado, fecha_creacion, fecha_modificacion) VALUES ('4eac4d23-459f-4d6a-a1eb-04f04d4f5f5e', 'COTOPAXI', 'erickespinozat', NULL, 'Activo', '2022-01-06 15:38:40.889', NULL);
INSERT INTO consulti.provincia (id, nombre, user_creacion, user_modificacion, estado, fecha_creacion, fecha_modificacion) VALUES ('d4011eec-1900-4a3c-a6df-df24d274d973', 'CHIMBORAZO', 'erickespinozat', NULL, 'Activo', '2022-01-06 15:38:40.89', NULL);
INSERT INTO consulti.provincia (id, nombre, user_creacion, user_modificacion, estado, fecha_creacion, fecha_modificacion) VALUES ('c8850dd0-7248-4b8a-aac3-776ad2b5b7f8', 'EL ORO', 'erickespinozat', NULL, 'Activo', '2022-01-06 15:38:40.896', NULL);
INSERT INTO consulti.provincia (id, nombre, user_creacion, user_modificacion, estado, fecha_creacion, fecha_modificacion) VALUES ('fe1213aa-c34f-44b6-a8ca-29aca789d3ff', 'GUAYAS', 'erickespinozat', NULL, 'Activo', '2022-01-06 15:38:40.899', NULL);
INSERT INTO consulti.provincia (id, nombre, user_creacion, user_modificacion, estado, fecha_creacion, fecha_modificacion) VALUES ('0444e92a-d43a-4bb1-b5c2-13565861f726', 'ESMERALDAS', 'erickespinozat', NULL, 'Activo', '2022-01-06 15:38:40.899', NULL);
INSERT INTO consulti.provincia (id, nombre, user_creacion, user_modificacion, estado, fecha_creacion, fecha_modificacion) VALUES ('4767b3b9-853d-4d59-8a11-466e02120e8f', 'MANABI', 'erickespinozat', NULL, 'Activo', '2022-01-06 15:38:40.9', NULL);
INSERT INTO consulti.provincia (id, nombre, user_creacion, user_modificacion, estado, fecha_creacion, fecha_modificacion) VALUES ('86b5e55d-b534-4404-a278-1a31e93d1538', 'IMBABURA', 'erickespinozat', NULL, 'Activo', '2022-01-06 15:38:40.9', NULL);
INSERT INTO consulti.provincia (id, nombre, user_creacion, user_modificacion, estado, fecha_creacion, fecha_modificacion) VALUES ('782ec4c0-32fd-42be-9e3b-ed18e9f6510c', 'LOJA', 'erickespinozat', NULL, 'Activo', '2022-01-06 15:38:40.9', NULL);
INSERT INTO consulti.provincia (id, nombre, user_creacion, user_modificacion, estado, fecha_creacion, fecha_modificacion) VALUES ('cd05e424-d7d0-423e-91cf-757d8456cd1c', 'LOS RIOS', 'erickespinozat', NULL, 'Activo', '2022-01-06 15:38:40.901', NULL);
INSERT INTO consulti.provincia (id, nombre, user_creacion, user_modificacion, estado, fecha_creacion, fecha_modificacion) VALUES ('e909f273-98f8-44e1-9630-e519a9508896', 'MORONA SANTIAGO', 'erickespinozat', NULL, 'Activo', '2022-01-06 15:38:40.906', NULL);
INSERT INTO consulti.provincia (id, nombre, user_creacion, user_modificacion, estado, fecha_creacion, fecha_modificacion) VALUES ('82ad8530-1af4-4092-a559-2025e974a04e', 'NAPO', 'erickespinozat', NULL, 'Activo', '2022-01-06 15:38:40.906', NULL);
INSERT INTO consulti.provincia (id, nombre, user_creacion, user_modificacion, estado, fecha_creacion, fecha_modificacion) VALUES ('78bb6b29-fc11-48d6-88a4-0fef87db6ee4', 'PASTAZA', 'erickespinozat', NULL, 'Activo', '2022-01-06 15:38:40.907', NULL);
INSERT INTO consulti.provincia (id, nombre, user_creacion, user_modificacion, estado, fecha_creacion, fecha_modificacion) VALUES ('e2b5dfb2-2d7a-4814-bc02-e1f5475fc1d2', 'TUNGURAHUA', 'erickespinozat', NULL, 'Activo', '2022-01-06 15:38:40.906', NULL);
INSERT INTO consulti.provincia (id, nombre, user_creacion, user_modificacion, estado, fecha_creacion, fecha_modificacion) VALUES ('1ed1a3c8-b01f-42a6-a696-830e5c53aeda', 'ZAMORA CHINCHIPE', 'erickespinozat', NULL, 'Activo', '2022-01-06 15:38:40.907', NULL);
INSERT INTO consulti.provincia (id, nombre, user_creacion, user_modificacion, estado, fecha_creacion, fecha_modificacion) VALUES ('f46486b8-7804-4983-bcb3-cd39697958f9', 'PICHINCHA', 'erickespinozat', NULL, 'Activo', '2022-01-06 15:38:40.907', NULL);
INSERT INTO consulti.provincia (id, nombre, user_creacion, user_modificacion, estado, fecha_creacion, fecha_modificacion) VALUES ('db353829-1bfa-4218-ad52-f477442f6a82', 'SUCUMBIOS', 'erickespinozat', NULL, 'Activo', '2022-01-06 15:38:40.912', NULL);
INSERT INTO consulti.provincia (id, nombre, user_creacion, user_modificacion, estado, fecha_creacion, fecha_modificacion) VALUES ('6225a7fc-4130-49aa-9e0e-0148c8fcf1d7', 'ORELLANA', 'erickespinozat', NULL, 'Activo', '2022-01-06 15:38:40.912', NULL);
INSERT INTO consulti.provincia (id, nombre, user_creacion, user_modificacion, estado, fecha_creacion, fecha_modificacion) VALUES ('d4278756-ee4f-4b11-8c84-dad9667ec3be', 'GALAPAGOS', 'erickespinozat', NULL, 'Activo', '2022-01-06 15:38:40.912', NULL);
INSERT INTO consulti.provincia (id, nombre, user_creacion, user_modificacion, estado, fecha_creacion, fecha_modificacion) VALUES ('62fea272-cf62-4348-9077-456c19181614', 'SANTA ELENA', 'erickespinozat', NULL, 'Activo', '2022-01-06 15:38:40.913', NULL);
INSERT INTO consulti.provincia (id, nombre, user_creacion, user_modificacion, estado, fecha_creacion, fecha_modificacion) VALUES ('5c925da5-b8a8-40cf-806a-50d4e3830d5f', 'SANTO DOMINGO DE LOS TSACHILAS', 'erickespinozat', NULL, 'Activo', '2022-01-06 15:38:40.913', NULL);