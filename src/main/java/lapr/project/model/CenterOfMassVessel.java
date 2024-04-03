package lapr.project.model;

public class CenterOfMassVessel {
    public final double length;
    public final double width;
    public final double height;
    private final double proaLength;
    private final double proaHeight;
    private final double popaLength;
    private final double popaHeight;
    private final double baseEstruturalLength;
    private final double baseEstruturalHeight;
    private final double torreLength;
    private final double torreHeight;
    private static final double MATERIAL_DENSITY = 7.82d;
    private final double centerOfMassX;
    private final double centerOfMassY;

    public  final int X_CONTAINER = 8;
    public  final int Y_CONTAINER = 5;
    public  final int Z_CONTAINER = 3;

    private static final double GRAVITY = 9.81d;


    private static final int WATER_DENSITY = 1030;

    public double x4;

    public CenterOfMassVessel(String vessel, int numOfContainers) {
        double x1;
        double x2;
        double x3;
        double x5;
        double x6;
        double y1;
        double y2;
        double y3;
        double y4;
        double y5;
        double y6;
        if ("Porta-Contentores".equals(vessel)) {
            length = 400;
            width = 48;
            height = 35;
            popaLength = 30;
            popaHeight = 40;
            baseEstruturalLength = 350;
            baseEstruturalHeight = 35;
            proaLength = 20;
            proaHeight = 48;
            torreLength = 35;
            torreHeight = 27;
            x1 = getx1();
            x2 = getx2();
            x3 = getx3();
            x4 = x2;

            double containerVol= getContainerVolume();

            double containerMass= getMass(containerVol);

            double finalContainerMass = containerMass * numOfContainers;

            x5= getx5(numOfContainers);
            x6= getx6(numOfContainers);

            centerOfMassX = calculateCenterOfMassXWContainer(x1, x2, x3, x4, x5, x6, finalContainerMass);
            y1 = gety1();
            y2 = gety2();
            y3 = gety3();
            y4 = gety4();
            y5= gety5();
            y6=gety6();

            centerOfMassY = calculateCenterOfMassYWContainer(y1,y2,y3,y4,y5,y6,finalContainerMass);
        } else {
            throw new IllegalArgumentException("Wrong vessel type");
        }

    }
    public CenterOfMassVessel(String vessel) {
        double x1;
        double x2;
        double x3;
        double y1;
        double y2;
        double y3;
        double y4;
        switch (vessel) {
            case "Porta-Contentores":
                length = 400;
                width = 48;
                height = 35;
                popaLength =30;
                popaHeight=40;
                baseEstruturalLength = 350;
                baseEstruturalHeight = 35;
                proaLength = 20;
                proaHeight = 48;
                torreLength = 35;
                torreHeight = 27;
                x1= getx1();
                x2 = getx2();
                x3 = getx3();
                x4= x2;
                centerOfMassX = calculateCenterOfMassX(x1,x2,x3,x4);
                break;
            case "Tanque":
                length = 260;
                width = 40;
                height = 20;
                popaLength =22;
                popaHeight=25;
                baseEstruturalLength = 200;
                baseEstruturalHeight = 30;
                proaLength = 20;
                proaHeight = 18;
                torreLength = 19;
                torreHeight = 8;
                x1= getx1();
                x2 = getx2();
                x3 = getx3();
                x4= x1;
                centerOfMassX = calculateCenterOfMassX(x1,x2,x3,x4);
                break;
            case "Graneleiro":
                length = 370;
                width = 68;
                height = 60;
                popaLength =21;
                popaHeight=25;
                baseEstruturalLength = 250;
                baseEstruturalHeight = 30;
                proaLength = 24;
                proaHeight = 25;
                torreLength = 20;
                torreHeight = 5;
                x1= getx1();
                x2 = getx2();
                x3 = getx3();
                x4= x3;
                centerOfMassX = calculateCenterOfMassX(x1,x2,x3,x4);
                break;
            default:
                throw new IllegalArgumentException("Wrong vessel type");
        }
        y1 = gety1();
        y2 = gety2();
        y3 = gety3();
        y4 = gety4();

        centerOfMassY = calculateCenterOfMassY(y1,y2,y3,y4);
    }

    public double getx1(){
        return popaLength/2;
    }

    public double getx5(int numCont){
        return (popaLength + ((X_CONTAINER*(numCont/4.0))/2.0));
    }
    public double getx6(int numCont){
        return (length - proaLength - ((X_CONTAINER*(numCont/4.0))/2.0));
    }
//2 andares de contentores
    public double gety5(){
        return Y_CONTAINER;
    }
    public double gety6(){
        return Y_CONTAINER;
    }

    public double getx2(){
        return popaLength + (baseEstruturalLength/2);
    }
    public double getx3(){
        return popaLength + baseEstruturalLength + (proaLength/3);
    }

    public double gety1(){
        return popaHeight/2;
    }
    public double gety2(){
        return baseEstruturalHeight / 2;
    }
    public double gety3(){
        return proaHeight/3;
    }
    public double gety4(){
        return height + (torreHeight / 2);
    }
    public double calculateCenterOfMassX(double x1, double x2, double x3, double x4) {
        double proaVolume = getProaVolume();
        double popaVolume = getPopaVolume();
        double baseEstruturalVolume = getBaseEstruturalVolume();
        double torreVolume = getTorreVolume();
        double proaMass = getMass(proaVolume);
        double popaMass = getMass(popaVolume);
        double baseEstruturalMass = getMass(baseEstruturalVolume);
        double torreMass = getMass(torreVolume);


        return ((popaMass * x1)  + (baseEstruturalMass * x2) + (proaMass * x3) + (torreMass * x4)) / (popaMass + baseEstruturalMass + proaMass + torreMass);
    }

    public double calculateCenterOfMassY(double y1, double y2, double y3, double y4) {
        double proaVolume = getProaVolume();
        double popaVolume = getPopaVolume();
        double baseEstruturalVolume = getBaseEstruturalVolume();
        double torreVolume = getTorreVolume();
        double proaMass = getMass(proaVolume);
        double popaMass = getMass(popaVolume);
        double baseEstruturalMass = getMass(baseEstruturalVolume);
        double torreMass = getMass(torreVolume);

        return ((popaMass * y1)  + (baseEstruturalMass * y2) + (proaMass * y3) + (torreMass * y4)) / (popaMass + baseEstruturalMass + proaMass + torreMass);
    }

    public double getMass(double volume) {
        return volume * MATERIAL_DENSITY;
    }

    public double getProaVolume() {
        return (proaHeight*proaLength)/2 * width;
    }

    public double getBaseEstruturalVolume() {
        return baseEstruturalLength * width * baseEstruturalHeight;
    }

    public double getTorreVolume() {
        return torreHeight * torreLength * width;
    }

    public double getPopaVolume() {
        return popaHeight * popaLength * width;
    }

    public double getContainerVolume() {
        return X_CONTAINER * Y_CONTAINER * Z_CONTAINER;
    }

    public double getCenterOfMassX() {
        return centerOfMassX;
    }

    public double getCenterOfMassY() {
        return centerOfMassY;
    }

    public double calculateCenterOfMassXWContainer(double x1, double x2, double x3, double x4, double x5, double x6, double mass) {
        double proaVolume = getProaVolume();
        double popaVolume = getPopaVolume();
        double baseEstruturalVolume = getBaseEstruturalVolume();
        double torreVolume = getTorreVolume();
        double proaMass = getMass(proaVolume);
        double popaMass = getMass(popaVolume);
        double baseEstruturalMass = getMass(baseEstruturalVolume);
        double torreMass = getMass(torreVolume);


        return ((popaMass * x1)  + (baseEstruturalMass * x2) + (proaMass * x3) + (torreMass * x4) + (mass * x5) + (mass * x6))  / (popaMass + baseEstruturalMass + proaMass + torreMass + mass + mass);
    }

    public double calculateCenterOfMassYWContainer(double y1, double y2, double y3, double y4, double y5, double y6, double mass) {
        double proaVolume = getProaVolume();
        double popaVolume = getPopaVolume();
        double baseEstruturalVolume = getBaseEstruturalVolume();
        double torreVolume = getTorreVolume();
        double proaMass = getMass(proaVolume);
        double popaMass = getMass(popaVolume);
        double baseEstruturalMass = getMass(baseEstruturalVolume);
        double torreMass = getMass(torreVolume);


        return ((popaMass * y1)  + (baseEstruturalMass * y2) + (proaMass * y3) + (torreMass * y4)+ (mass * y5) + (mass * y6))  / (popaMass + baseEstruturalMass + proaMass + torreMass + mass + mass);
    }


    public double sink(int number){
        double pression= pression();

        double pressionWithContainers= pressionWithContainers(number);


        return (pressionWithContainers - pression) / (WATER_DENSITY* GRAVITY);

    }

    public double getMassOfShip(){
        return (getPopaVolume()*MATERIAL_DENSITY) + (getBaseEstruturalVolume() * MATERIAL_DENSITY) + (getProaVolume() * MATERIAL_DENSITY) + (getTorreVolume() * MATERIAL_DENSITY);
    }

    public double weightOfShip(){
        return getMassOfShip()* GRAVITY;
    }

    public double getArea(){
        return length*width;
    }

    public double pression(){
        return weightOfShip()/getArea();
    }

    public double getMassOfShipWithContainers(int number){
        return (getPopaVolume()*MATERIAL_DENSITY) + (getBaseEstruturalVolume() * MATERIAL_DENSITY) + (getProaVolume() * MATERIAL_DENSITY) + (getTorreVolume() * MATERIAL_DENSITY) + (500 * number);
    }

    public double weightOfShipWithContainers(int number){
        return getMassOfShipWithContainers(number)* GRAVITY;
    }

    public double pressionWithContainers(int number){
        return weightOfShipWithContainers(number)/getArea();
    }



}
