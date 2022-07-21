ALTER TABLE consulti.postulacion ADD proceso_reclutamiento_id uuid


ALTER TABLE ONLY consulti.postulacion
    ADD CONSTRAINT postulacion_proceso_reclutamiento_id_fk FOREIGN KEY (proceso_reclutamiento_id) REFERENCES consulti.proceso_reclutamiento(id);