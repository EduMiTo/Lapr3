CREATE OR REPLACE PROCEDURE US304 (CargoManifestloadedId in INTEGER, ContainerID in Integer, outString out VARCHAR2) IS

    opName Varchar(255);
    
        

    BEGIN
    
    FOR loop
    in(SELECT operationsId, userusername, dateofop,CARGOMANIFEST_CONTAINERCARGOMANIFESTLOADID,CARGOMANIFEST_CONTAINERCONTAINERID
    FROM audittrails
    where cargomanifest_containercontainerid = ContainerID
    AND cargomanifest_containercargomanifestloadid=CargoManifestLoadedId
    ORDER BY dateofop)
    LOOP
    
        select operationname into opName
        From operations
        where id = loop.operationsId;
        
        
        
        outString := outString || 'Cargo manifest id: ' || loop.CARGOMANIFEST_CONTAINERCARGOMANIFESTLOADID || ' Container id: '|| loop.CARGOMANIFEST_CONTAINERCONTAINERID || ' User name: ' || loop.userusername || ' Operation name: ' || opName || ' Date of change: '|| loop.dateofop|| chr(10);
        
    
        dbms_output.put_line('date: ' ||loop.dateofop);
        dbms_output.put_line('operation: ' ||opName);
        dbms_output.put_line('CARGOMANIFEST_CONTAINERCARGOMANIFESTLOADID: ' ||loop.CARGOMANIFEST_CONTAINERCARGOMANIFESTLOADID);


        
           
    END LOOP;
    


END;