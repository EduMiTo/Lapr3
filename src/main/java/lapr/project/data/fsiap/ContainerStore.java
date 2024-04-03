package lapr.project.data.fsiap;

import lapr.project.model.Container;

import java.util.ArrayList;
import java.util.List;

public class ContainerStore {

    private final List<Container> containers;
    private int EXTERNAL_TEMP412= 20;
    private int TIME412= 9000;


    private int EXTERNAL_TEMP4131= 20;
    private int EXTERNAL_TEMP4132= 20;
    private int EXTERNAL_TEMP4133= 10;

    private int TIME413= 10800;

    private int LADO_SOL1=1;//baixo em x,y
    private int LADO_SOL2=2;//esquerda em x, y

    private int EXTERNAL_TEMP414 = 20;

    private int TIME414= 3600;

    private int GENERATOROUTPUT=75000;



    public ContainerStore() {
        this.containers = new ArrayList<>();
    }

    public double calculateEnergySpecificRestrictions(int x, int y, int z){

        Container container = getCont(x,y,z);

        float requiredTemp = container.getTempCont();

        float area = (container.getDimx()* container.getDimz()*4) + (container.getDimy()* container.getDimz()*2);

        float tamExt = container.getTamExt();
        float tamMeio = container.getTamMid();
        float tamInt= container.getTamInt();

        float iCap= container.getIntCap();
        float mCap = container.getMidCap();
        float eCap = container.getExtCap();

        float resistivity = 0;

        resistivity += (tamExt/(eCap * area));
        resistivity += (tamMeio/(mCap * area));
        resistivity += (tamInt/(iCap * area));

        return (((EXTERNAL_TEMP412 - requiredTemp)/resistivity) * TIME412);
    }

    public double calculateEnergySpecificTrip(int xR, int yR, int zR,int xN, int yN, int zN ){

        int numRefri =numberOfRefrigerated();

        double medTemp= (EXTERNAL_TEMP4131+ EXTERNAL_TEMP4132 + EXTERNAL_TEMP4133)/3.0;

        double energyR= calculateEnergySpecificTripRefri(xR,yR,zR,medTemp);

        double energyNotRefri= calculateEnergySpecificTripNotRefri(xN,yN,zN,medTemp);


        double sumRefri = energyR * numRefri * TIME413;



        double sumNotRefri = energyNotRefri * (containers.size()-numRefri) * TIME413;





        return sumRefri+sumNotRefri;
    }


    public double calculateEnergySpecificTripRefri(int xR, int yR, int zR, double medTemp){
        Container containerRefri = getCont(xR,yR,zR);

        float area = (containerRefri.getDimx()* containerRefri.getDimz()*4) + (containerRefri.getDimy()* containerRefri.getDimz()*2);


        float requiredTemp = containerRefri.getTempCont();


        float tamExtR = containerRefri.getTamExt();
        float tamMeioR = containerRefri.getTamMid();
        float tamIntR= containerRefri.getTamInt();

        float iCapR= containerRefri.getIntCap();
        float mCapR = containerRefri.getMidCap();
        float eCapR = containerRefri.getExtCap();

        float resistivityR = 0;

        resistivityR += (tamExtR/(eCapR * area));
        resistivityR += (tamMeioR/(mCapR * area));
        resistivityR += (tamIntR/(iCapR * area));

        return (((medTemp - requiredTemp)/resistivityR));

    }

    public double calculateEnergySpecificTripNotRefri(int xN, int yN, int zN, double medTemp){
        Container containerNotRefri = getCont(xN,yN,zN);

        float area = (containerNotRefri.getDimx()* containerNotRefri.getDimz()*4) + (containerNotRefri.getDimy()* containerNotRefri.getDimz()*2);



        float requiredTempNotRefri = containerNotRefri.getTempCont();

        float tamExtNotRefri = containerNotRefri.getTamExt();
        float tamMeioNotRefri = containerNotRefri.getTamMid();
        float tamIntNotRefri= containerNotRefri.getTamInt();

        float iCapNotRefri= containerNotRefri.getIntCap();
        float mCapNotRefri = containerNotRefri.getMidCap();
        float eCapNotRefri = containerNotRefri.getExtCap();

        float resistivityNotRefri = 0;

        resistivityNotRefri += (tamExtNotRefri/(eCapNotRefri * area));
        resistivityNotRefri += (tamMeioNotRefri/(mCapNotRefri * area));
        resistivityNotRefri += (tamIntNotRefri/(iCapNotRefri * area));

        return (((medTemp - requiredTempNotRefri)/resistivityNotRefri));


    }
    public double neededEnergyToSupplyContainer() {

        int[][][] matrix=buildMatrixAuxOfContainers();
        double total=0;
        for (Container container: containers){
            int n= 3-verifySidesAffected(matrix, container);
            total+=calculateEnergyNSide(container,EXTERNAL_TEMP414, TIME414,n);


        }


        return total;

    }



    public int neededGenerators(){
        int[][][] matrix=buildMatrixAuxOfContainers();
        float total=0;
        for (Container container: containers){
            int n= 3-verifySidesAffected(matrix, container);
            total+=calculateEnergyNSide(container,EXTERNAL_TEMP414, TIME414,n);


        }


        int numOfGenerators= (int) ((total/TIME414)/GENERATOROUTPUT)+1;



        return numOfGenerators;
    }






    public int verifySidesAffected(int[][][] matrix, Container container){
        int cont= 0;
        if (LADO_SOL1==1 && LADO_SOL2 ==2 || LADO_SOL1==2 && LADO_SOL2==1){

            if ((container.getX()-1 > 0 && matrix[container.getX()-1][container.getY()][container.getZ()]!=0 )){
                cont++;
            }
            if(( container.getY()+1 < getMaty() && matrix[container.getX()][container.getY()+1][container.getZ()]!=0) ){
                cont++;
            }
            if ( (container.getZ()+1 < getMatz() && matrix[container.getX()][container.getY()][container.getZ()+1]!=0 )){
                cont++;
            }

        }else if(LADO_SOL1==1 && LADO_SOL2 ==4 || LADO_SOL1==4 && LADO_SOL2==1){

            if ((container.getX()-1 > 0 && matrix[container.getX()-1][container.getY()][container.getZ()]!=0 )){
                cont++;
            }
            if(( container.getY()-1 > 0 && matrix[container.getX()][container.getY()-1][container.getZ()]!=0 ) ){
                cont++;
            }
            if ( (container.getZ()+1 < getMatz() && matrix[container.getX()][container.getY()][container.getZ()+1]!=0 )){
                cont++;
            }

        }else if(LADO_SOL1==2 && LADO_SOL2 ==3 || LADO_SOL1==3 && LADO_SOL2==2){

            if ((container.getY()+1 < getMaty() && matrix[container.getX()][container.getY()+1][container.getZ()]!=0 )){
                cont++;
            }
            if((container.getX()+1 < getMatx() && matrix[container.getX()+1][container.getY()][container.getZ()]!=0 ) ){
                cont++;
            }
            if ( ( container.getZ()+1 < getMatz() && matrix[container.getX()][container.getY()][container.getZ()+1]!=0 )){
                cont++;
            }

        }else if(LADO_SOL1==3 && LADO_SOL2 ==4 || LADO_SOL1==4 && LADO_SOL2==3){

            if(( container.getX()+1 < getMatx() && matrix[container.getX()+1][container.getY()][container.getZ()]!=0 ) ){
                cont++;
            }
            if(( container.getY()-1 > 0 && matrix[container.getX()][container.getY()-1][container.getZ()]!=0 ) ){
                cont++;
            }
            if ( (container.getZ()+1 < getMatz() && matrix[container.getX()][container.getY()][container.getZ()+1]!=0 )){
                cont++;
            }

        }
        return cont;
    }


    public double calculateEnergyNSide(Container container, float medTemp, int time, int numberofside){

        float area = (container.getDimx()* container.getDimz()*4) + (container.getDimy()* container.getDimz()*2);

        double tempKelvin = medTemp + 273.15;

        double requiredTempKelvin = container.getTempCont() + 273.15;

        float tamExt = container.getTamExt();
        float tamMeio = container.getTamMid();
        float tamInt= container.getTamInt();

        float iCap= container.getIntCap();
        float mCap = container.getMidCap();
        float eCap = container.getExtCap();

        float resistivity = 0;

        resistivity += (tamExt/(eCap));
        resistivity += (tamMeio/(mCap));
        resistivity += (tamInt/(iCap));

        return (numberofside * area * ((tempKelvin - requiredTempKelvin)/resistivity)*time);
    }



    public void insert(Container container){
        containers.add(container);
    }

    public Container getCont(int x, int y, int z){

        for(Container container: containers){
            if(container.getX()==x && container.getY()==y && container.getZ()==z){
                return container;
            }
        }
        return null;

    }

    public int numberOfRefrigerated(){
        int cont =0;
        for (Container container: containers){
            if (container.getRefri()==1){
                cont++;
            }
        }
        return cont;
    }

    public int getMatx(){
        int max=0;
        for (Container container: containers){
            if (container.getX()>max){
                max= container.getX();
            }
        }
        return max;
    }

    public int getMaty(){
        int max=0;
        for (Container container: containers){
            if (container.getY()>max){
                max= container.getY();
            }
        }
        return max;
    }

    public int getMatz(){
        int max=0;
        for (Container container: containers){
            if (container.getZ()>max){
                max= container.getZ();
            }
        }
        return max;
    }

    public int[][][] buildMatrixAuxOfContainers(){

        int[][][] matrix = new int[getMatx()+1][getMaty()+1][getMatz()+1];

        for (Container container: containers){
            matrix[container.getX()][container.getY()][container.getZ()]= container.getContainerId();
        }

        return matrix;
    }



}
