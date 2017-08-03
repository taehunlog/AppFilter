package zzang.taehun.com.appfilter.utils.newXml;

/**
 * Created by taehun on 2017-08-03.
 */

public class XmlItem {
    String stationName;
    String addr;
    String tm;//거리


    public XmlItem(String stationName, String addr, String tm) {
        this.stationName = stationName;
        this.addr = addr;
        this.tm = tm;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getTm() {
        return tm;
    }

    public void setTm(String tm) {
        this.tm = tm;
    }
    public boolean checkDataCount(){
        if(stationName.length()>0 &&
                addr.length()>0 &&
                tm.length()>0){
            return true;
        }

        return false;
    }
}
