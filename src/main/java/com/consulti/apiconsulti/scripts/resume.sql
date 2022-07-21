
ALTER TABLE consulti.resume ADD user_id uuid

ALTER TABLE ONLY consulti.resume
    ADD CONSTRAINT resume_user_id_fk FOREIGN KEY (user_id) REFERENCES consulti.user(id);
