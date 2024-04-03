package lapr.project.utils;

public class Distance {

    public static double calculateDistance(String lati, String loni,String latf, String lonf){





        int earthRadius = 6371000;

        double nLati= toRadian(Float.parseFloat(lati));
        double nLoni= toRadian(Float.parseFloat(loni));
        double nLatf= toRadian(Float.parseFloat(latf));
        double nLonf= toRadian(Float.parseFloat(lonf));

        double dLat= nLatf-nLati;

        double dLon= nLonf-nLoni;

        double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(nLati)* Math.cos(nLatf) * Math.sin(dLon/2) * Math.sin(dLon/2);

        double b = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));


        return earthRadius*b;
    }


    public static double toRadian(float degree){

        return (degree*Math.PI)/180;


    }


}
