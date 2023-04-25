package PLANE;

public class Airline {

    String aName, aType, aCompany;

    int aTime;

    String dCode, aCode;

    String code, nation, city;

    int tZone;

    public String getsNum() {
        return sNum;
    }

    public void setsNum(String sNum) {
        this.sNum = sNum;
    }

    public String getsStart() {
        return sStart;
    }

    public void setsStart(String sStart) {
        this.sStart = sStart;
    }

    public int getsPrice() {
        return sPrice;
    }

    public void setsPrice(int sPrice) {
        this.sPrice = sPrice;
    }

    // ASCHEDULE 테이블 변수
    String  sNum;
    String sStart;
    int sPrice;

    // ARESERVATION 테이블 변수
    String rTime;
    int rNum;
    int rCheck;

    public String getrTime() {
        return rTime;
    }

    public void setrTime(String rTime) {
        this.rTime = rTime;
    }

    public int getrNum() {
        return rNum;
    }

    public void setrNum(int rNum) {
        this.rNum = rNum;
    }

    public int getrCheck() {
        return rCheck;
    }

    public void setrCheck(int rCheck) {
        this.rCheck = rCheck;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int gettZone() {
        return tZone;
    }

    public void settZone(int tZone) {
        this.tZone = tZone;
    }

    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public String getaType() {
        return aType;
    }

    public void setaType(String aType) {
        this.aType = aType;
    }

    public String getaCompany() {
        return aCompany;
    }

    public void setaCompany(String aCompany) {
        this.aCompany = aCompany;
    }

    public int getaTime() {
        return aTime;
    }

    public void setaTime(int aTime) {
        this.aTime = aTime;
    }

    public String getdCode() {
        return dCode;
    }

    public void setdCode(String dCode) {
        this.dCode = dCode;
    }

    public String getaCode() {
        return aCode;
    }

    public void setaCode(String aCode) {
        this.aCode = aCode;
    }

    @Override
    public String toString() {
        return "AirIne{" +
                "aName='" + aName + '\'' +
                ", aType='" + aType + '\'' +
                ", aCompany='" + aCompany + '\'' +
                ", aTime=" + aTime +
                ", dCode='" + dCode + '\'' +
                ", aCode='" + aCode + '\'' +
                ", code='" + code + '\'' +
                ", nation='" + nation + '\'' +
                ", city='" + city + '\'' +
                ", tZone=" + tZone +
                ", sNum='" + sNum + '\'' +
                ", sStart='" + sStart + '\'' +
                ", sPrice=" + sPrice +
                ", rTime='" + rTime + '\'' +
                ", rNum=" + rNum +
                ", rCheck=" + rCheck +
                '}';
    }
}
