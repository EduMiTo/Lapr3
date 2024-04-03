package lapr.project.model;

public class Container {
    private final int containerId;
    private final int x;
    private final int y;
    private final int z;
    private final int dimx;
    private final int dimy;
    private final int dimz;
    private final int refri;
    private final String ext;
    private final String mid;
    private final String inte;

    private final float tamExt;
    private final float tamMid;
    private final float tamInt;

    private final float extCap;
    private final float midCap;
    private final float intCap;

    private final int tempCont;


    public Container(String containerId, String x, String y, String z, String dimx, String dimy, String dimz, String refri, String ext, String mid, String inte, String tamExt, String tamMid, String tamInt, String extCap, String midCap, String intCap, String tempCont) {
        this.containerId = Integer.parseInt(containerId);
        this.x = Integer.parseInt(x);
        this.y = Integer.parseInt(y);
        this.z = Integer.parseInt(z);
        this.dimx = Integer.parseInt(dimx);
        this.dimy = Integer.parseInt(dimy);
        this.dimz = Integer.parseInt(dimz);
        this.refri = Integer.parseInt(refri);
        this.ext = ext;
        this.mid = mid;
        this.inte = inte;
        this.tamExt = Float.parseFloat(tamExt);
        this.tamMid = Float.parseFloat(tamMid);
        this.tamInt = Float.parseFloat(tamInt);
        this.extCap = Float.parseFloat(extCap);
        this.midCap = Float.parseFloat(midCap);
        this.intCap = Float.parseFloat(intCap);
        this.tempCont = Integer.parseInt(tempCont);
    }

    public int getContainerId() {
        return containerId;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public int getDimx() {
        return dimx;
    }

    public int getDimy() {
        return dimy;
    }

    public int getDimz() {
        return dimz;
    }

    public int getRefri() {
        return refri;
    }

    public String getExt() {
        return ext;
    }

    public String getMid() {
        return mid;
    }

    public String getInte() {
        return inte;
    }

    public float getTamExt() {
        return tamExt;
    }

    public float getTamMid() {
        return tamMid;
    }

    public float getTamInt() {
        return tamInt;
    }

    public float getExtCap() {
        return extCap;
    }

    public float getMidCap() {
        return midCap;
    }

    public float getIntCap() {
        return intCap;
    }

    public int getTempCont() {
        return tempCont;
    }

}
