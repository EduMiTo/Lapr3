create or replace PROCEDURE US205 (mmsiCode in Varchar, output out Varchar)
IS
    cmunloadNumber Integer;
    tripNumber Integer;
    cargoscode Cargomanifestload.id%type;
    proxLoc Trip.origin%type;
    nextPort Trip.origin%type;
    contador Integer :=0;
    typeIsoCode container.isoCode%type;
    loadWeight container.weight%type;
    Cursor cargos IS
        Select id
        from cargomanifestload
        where shipmmsi=mmsiCode
        Order by id;

Begin
open cargos;
    LOOP 
        fetch cargos INTO cargoscode;
        Exit When cargos%notfound;
        dbms_output.put_line('cargo id: ' ||cargoscode);
        SELECT Count(*) INTO cmunloadNumber 
                FROM cargomanifestunload
                Where cargomanifestunload.cargomanifestloadid = cargoscode;                                          

                dbms_output.put_line(cmunloadNumber);

                SELECT COUNT(*) INTO tripNumber 
                FROM Trip
                Where Trip.cargomanifestloadid = cargoscode;

                dbms_output.put_line(tripNumber);    

                IF tripNumber = cmunloadNumber THEN
                        dbms_output.put_line('cargo finalizado id: ' ||cargoscode);

                ELSE
                    select destination INTO proxLoc
                    from Trip                    
                    where id=cmunloadNumber+1
                    AND cargomanifestloadid= cargoscode;
                    dbms_output.put_line('proximo porto: ' ||proxLoc);

                    contador:= contador + 1;

                    IF contador=1 THEN
                        nextPort:= proxLoc;
                        output:=output||'Next Port: '|| nextPort || chr(10);
                    End IF;

                    IF proxLoc=nextPort THEN

                        FOR loop 
                        IN(Select cargomanifest_container.containerid,cargomanifest_container.xContainer,cargomanifest_container.yContainer,cargomanifest_container.zContainer 
                            from cargomanifest_container 
                            inner join Trip
                            on(cargomanifest_container.cargomanifestloadid = Trip.cargomanifestloadid)
                            where cargomanifest_container.cargomanifestloadid=cargoscode
                            AND cargomanifest_container.Tripid= cmunloadNumber+1
                            AND Trip.destination= proxLoc)
                        LOOP
                            dbms_output.put_line('asdasd: ' ||loop.containerid);
                            output:=output||'Container number: '|| loop.containerid|| ' With position-> x:'|| loop.xContainer|| ' y:' || loop.yContainer || ' z:'||loop.zContainer || chr(10);

                            select isoCode, weight into typeIsoCode, loadWeight
                            from container
                            where id= loop.containerid;

                            output:=output || 'Type(In iso Code): ' || typeIsoCode || ' Load: ' || loadWeight || chr(10);

                        END LOOP;

                    END IF;

                END IF;

            END LOOP;
        Close cargos;       
END; 