
ALTER TABLE consulti.user ADD genero character varying(100)
ALTER TABLE consulti.user ADD multimedia_id uuid
ALTER TABLE consulti.user ADD ciudad_id uuid

ALTER TABLE ONLY consulti.user
    ADD CONSTRAINT user_multimedia_id_fk FOREIGN KEY (multimedia_id) REFERENCES consulti.multimedia(id);

ALTER TABLE ONLY consulti.user
    ADD CONSTRAINT user_ciudad_id_fk FOREIGN KEY (ciudad_id) REFERENCES consulti.ciudad(id);