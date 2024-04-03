create or replace TRIGGER audit_trail_trigger
    AFTER 
    UPDATE OR DELETE OR INSERT 
    ON cargoManifest_Container
    FOR EACH ROW
DECLARE
    contador Integer;
   transactionStatus VARCHAR2(10);
BEGIN
   -- determine the transaction type
        transactionstatus := CASE
         WHEN UPDATING THEN 1
         WHEN DELETING THEN 2
         WHEN INSERTING THEN 3
   END;

   select COUNT(*) Into contador from AuditTrails;
       contador:=contador +1;


    dbms_output.put_line('id: ' ||contador);
        dbms_output.put_line('id: ' ||:NEW.changesResponsible);
    dbms_output.put_line('id: ' ||transactionstatus);
    dbms_output.put_line('id: ' ||:NEW.cargomanifestloadid);

    --dbms_output.put_line('id: ' ||:NEW.containerid);

-- insert a row into the audit table



   INSERT INTO  AUDITTRAILS 
   VALUES(contador, :NEW.changesResponsible, transactionstatus, :NEW.cargomanifestloadid,:NEW.containerid,sysdate);
END;