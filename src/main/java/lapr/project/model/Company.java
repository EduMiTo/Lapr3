package lapr.project.model;

import lapr.project.data.BorderStore;
import lapr.project.data.CapitalStore;
import lapr.project.data.SeaDistStore;
import lapr.project.data.fsiap.ContainerStore;
import lapr.project.model.graph.MatrixGraph;

public class Company {


    private final BSTShip bstShip;
    private final Port2dTree port2dTree;
    private final CountryStore countryStore;
    private final PortStore portStore;
    private final SeaDistStore seaDistStore;
    private final CapitalStore capitalStore;
    private final BorderStore borderStore;
    private MatrixGraph graph ;
    private final ContainerStore containerStore;

    public Company() {
        bstShip = new AVLShip();
        port2dTree=new Port2dTree();
        countryStore=new CountryStore();
        portStore=new PortStore();
        seaDistStore = new SeaDistStore();
        capitalStore= new CapitalStore();
        borderStore = new BorderStore();
        graph= new MatrixGraph(true);
        containerStore=new ContainerStore();
    }



    public  BSTShip getBstShip() {
        return bstShip;
    }
    public Port2dTree getPort2dTree(){return port2dTree; }
    public CountryStore getCountryList(){return countryStore; }
    public PortStore getPortStore(){ return portStore; }
    public SeaDistStore getSeaDistStore(){ return seaDistStore; }
    public CapitalStore getCapitalStore(){ return capitalStore; }
    public BorderStore getBorderStore(){return borderStore;}
    public MatrixGraph getGraph(){return graph;}
    public void setGraph(MatrixGraph graph){this.graph= graph;}

    public ContainerStore getContainerStore() {
        return containerStore;
    }
}

