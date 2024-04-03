create or replace PROCEDURE CreateCMwFirstTrip (cmId IN integer, pId In Integer,smmsi IN Varchar,ori in Varchar, dest in Varchar, eDepDate in TIMESTAMP, eArrDate in TimeStamp) IS

    begin

    SET TRANSACTION NAME 'teste';
    Insert into CARGOMANIFESTLOAD (ID,PORTID,SHIPMMSI,ISCONCLUDED) values (cmId,pId,smmsi,null);
    Insert into TRIP (CARGOMANIFESTLOADID,ID,ORIGIN,DESTINATION,EXPECTEDDEPARTUREDATE,EXPECTEDARRIVALDATE) values (cmId,'1',ori,dest,eDepDate,eArrDate);


    COMMIT;

    exception
        when others then
            dbms_output.put_line('error: ');
            rollback;
            raise_application_error(-20000,'Ship is busy');

    END;