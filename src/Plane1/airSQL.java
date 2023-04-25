package Plane1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class airSQL {
    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;

    public void connect() {
        con = DBC.DBConnect();
    }

    public void aReg(Airline air) {
        String sql = "INSERT INTO AIRLINE VALUES (?,?,?,?,?,?)";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, air.getaName());
            pstmt.setString(2, air.getaType());
            pstmt.setString(3, air.getaCompany());
            pstmt.setDouble(4, air.getaTime());
            pstmt.setString(5, air.getdCode());
            pstmt.setString(6, air.getaCode());
            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("입력 성공!");
            }
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void aSelect() {
        String sql = "SELECT * FROM AIRLINE";
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("항공편 : " + rs.getString(1) + " | "
                        + "비행기종 : " + rs.getString(2) + " | "
                        + "항공사 : " + rs.getString(3) + " | "
                        + "비행시간 : " + rs.getInt(4) + " | "
                        + "출발공항 코드 : " + rs.getString(5) + " | "
                        + "도착공항 코드 : " + rs.getString(6)
                );

            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void cReg(Airline air) {
        String sql = "INSERT INTO CODE VALUES (?,?,?,?)";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, air.getCode());
            pstmt.setString(2, air.getNation());
            pstmt.setString(3, air.getCity());
            pstmt.setInt(4, air.gettZone());
            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("입력 성공!");
            }
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cSelect() {
        String sql = "SELECT * FROM CODE";
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("공항 코드 : " + rs.getString(1) + " | "
                        + "국가 : " + rs.getString(2) + " | "
                        + "도시 : " + rs.getString(3) + " | "
                        + "타임존 : " + rs.getInt(4)
                );

            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int genrNum() {
        int rNum = 0;
        String sql = "SELECT MAX(rNum) FROM ARESERVATION";
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                rNum = rs.getInt(1);
            }
            pstmt.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rNum;
    }

    public void sReg(Airline air) {
        String sql = "INSERT INTO ASCHEDULE VALUES (?,?,?,?)";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, air.getsNum());
            pstmt.setString(2, air.getsStart());
            pstmt.setInt(3, air.getsPrice());
            pstmt.setString(4, air.getaName());
            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("입력 성공!");
            }
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean sSelect() {
        String sql = "SELECT b.SNUM,a.ANAME,a.ACOMPANY,a.DCODE,a.ACODE,b.SSTART,a.ATIME,b.SPRICE" +
                " FROM AIRLINE a, ASCHEDULE b WHERE a.ANAME=b.ANAME";
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("일정번호 : " + rs.getString(1) + " | "
                        + "항공편 : " + rs.getString(2) + " | "
                        + "항공사 : " + rs.getString(3) + " | "
                        + "출발지 : " + rs.getString(4) + " | "
                        + "도착지 : " + rs.getString(5) + " | "
                        + "출발시간 : " + rs.getString(6) + " | "
                        + "비행시간 : " + rs.getDouble(7) + " | "
                        + "가격 : " + rs.getInt(8)
                );
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public void sSelect(Airline air) {
        String sql = "SELECT b.SNUM,a.ANAME,a.ACOMPANY,a.DCODE,a.ACODE,b.SSTART,a.ATIME,b.SPRICE" +
                " FROM AIRLINE a, ASCHEDULE b, CODE c " +
                "WHERE a.ANAME=b.ANAME AND c.CODE=a.ACODE AND b.SNUM=? ";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, air.getsNum());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("일정번호 : " + rs.getString(1) + " | "
                        + "항공편 : " + rs.getString(2) + " | "
                        + "항공사 : " + rs.getString(3) + " | "
                        + "출발지 : " + rs.getString(4) + " | "
                        + "도착지 : " + rs.getString(5) + " | "
                        + "출발시간 : " + rs.getString(6) + " | "
                        + "비행시간 : " + rs.getDouble(7) + " | "
                        + "가격 : " + rs.getInt(8)
                );
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean sSelect(String region) {
        boolean check = true;

        String sql = "SELECT DISTINCT c.NATION, c.CITY FROM AIRLINE a, ASCHEDULE b, CODE c "
                + "WHERE a.ANAME=b.ANAME AND c.CODE=a.ACODE AND substr(b.sNum,6,2)=? AND a.DCODE='ICN'";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, region);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("국가 : " + rs.getString(1) + " | "
                        + "도시 : " + rs.getString(2)
                );
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return check;
    }

    public void sSelect(Airline air, String region) {
        String sql = "SELECT b.SNUM,a.ANAME,a.ACOMPANY,a.DCODE,a.ACODE,b.SSTART,a.ATIME,b.SPRICE" +
                " FROM AIRLINE a, ASCHEDULE b, CODE c " +
                "WHERE a.ANAME=b.ANAME AND c.CODE=a.ACODE AND c.CITY=? AND substr(b.SSTART,1,5)=? ";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, region);
            pstmt.setString(2, air.getsStart());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("일정번호 : " + rs.getString(1) + " | "
                        + "항공편 : " + rs.getString(2) + " | "
                        + "항공사 : " + rs.getString(3) + " | "
                        + "출발지 : " + rs.getString(4) + " | "
                        + "도착지 : " + rs.getString(5) + " | "
                        + "출발시간 : " + rs.getString(6) + " | "
                        + "비행시간 : " + rs.getDouble(7) + " | "
                        + "가격 : " + rs.getInt(8)
                );
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void sBSelect(Airline air, String region) {
        String sql = "SELECT b.SNUM,a.ANAME,a.ACOMPANY,a.DCODE,a.ACODE,b.SSTART,a.ATIME,b.SPRICE" +
                " FROM AIRLINE a, ASCHEDULE b, CODE c " +
                "WHERE a.ANAME=b.ANAME AND c.CODE=a.DCODE AND c.CITY=? AND substr(b.SSTART,1,5)=?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, region);
            pstmt.setString(2, air.getsStart());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("일정번호 : " + rs.getString(1) + " | "
                        + "항공편 : " + rs.getString(2) + " | "
                        + "항공사 : " + rs.getString(3) + " | "
                        + "출발지 : " + rs.getString(4) + " | "
                        + "도착지 : " + rs.getString(5) + " | "
                        + "출발시간 : " + rs.getString(6) + " | "
                        + "비행시간 : " + rs.getDouble(7) + " | "
                        + "가격 : " + rs.getInt(8)
                );
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void rReg(Airline air) {
        String sql = "INSERT INTO ARESERVATION VALUES (?,SYSDATE,?,?,0)";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, air.getmId());
            pstmt.setString(2, air.getsNum());
            pstmt.setInt(3, air.getrNum());

            int result = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void rSelect() {
        String sql = "SELECT c.MID,c.RTIME,b.ANAME,b.SSTART,a.DCODE,a.ACODE,b.SPRICE,c.RCHECK" +
                " FROM AIRLINE a, ASCHEDULE b, ARESERVATION c WHERE a.ANAME=b.ANAME AND B.SNUM=C.SNUM";
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("아이디 : " + rs.getString(1) + " | "
                        + "예약시간 : " + rs.getString(2) + " | "
                        + "항공편 : " + rs.getString(3) + " | "
                        + "출발시간 : " + rs.getString(4) + " | "
                        + "출발지 : " + rs.getString(5) + " | "
                        + "목적지 : " + rs.getString(6) + " | "
                        + "가격 : " + rs.getInt(7) + " | "
                        + "결제여부 : " + rs.getInt(8)
                );
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void rSelect(Airline air, int pay) {
        String sql = "SELECT c.RNUM, c.RTIME,b.ANAME,b.SSTART,a.DCODE,a.ACODE,b.SPRICE" +
                " FROM AIRLINE a, ASCHEDULE b, ARESERVATION c " +
                "WHERE a.ANAME=b.ANAME AND B.SNUM=C.SNUM AND c.RCHECK=?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, pay);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("예약번호 : " + rs.getString(1) + " | "
                        + "예약시간 : " + rs.getString(2) + " | "
                        + "항공편 : " + rs.getString(3) + " | "
                        + "출발시간 : " + rs.getString(4) + " | "
                        + "출발지 : " + rs.getString(5) + " | "
                        + "목적지 : " + rs.getString(6) + " | "
                        + "가격 : " + rs.getInt(7)
                );
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void rDelete(Airline air) {
        String sql = "DELETE FROM ARESERVATION WHERE RNUM=?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, air.getrNum());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void rBalance(Member mem) {
        String sql = "SELECT tbalance FROM BANKTEAM WHERE tAccount=?";
        int bal = 0;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, mem.gettAccount());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                bal=rs.getInt(1);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean rCheck(Airline air, int i) {
    boolean check=false;
    String sql = "SELECT * FROM ARESERVATION WHERE RCHECK=? AND MID=?";
        try {
            pstmt=con.prepareStatement(sql);
            pstmt.setInt(1,i);
            pstmt.setString( 2 , air.getmId());
            rs = pstmt.executeQuery();
            check=rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    return check;
    }


    public void rUpdate(Airline air, int i) {
        String sql = "UPDATE ARESERVATION SET RCHECK=? WHERE mId=? AND RCHECK=?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,i+1);
            pstmt.setString(2, air.getmId());
            pstmt.setInt(3,i);
            int result = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void pCreate(Airline air) {
        String sql="INSERT INTO PACCOUNT VALUES (?,?,?)";
    }


    public void rTotal(Airline air) {
        String sql="SELECT SUM(SPRICE),COUNT(*) FROM ASCHEDULE a, ARESERVATION b" +
                " WHERE a.SNUM=b.SNUM AND b.RCHECK=0 AND b.MID=?";
        try {
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1,air.getmId());
            rs= pstmt.executeQuery();
            while (rs.next()){
                System.out.println(rs.getInt(2)+"개의 예약내역, "
                        +rs.getInt(1)+"원을 아래의 계좌에 입금 바랍니다");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkBalance(Airline air) {
        boolean check=false;
        String sql="SELECT * FROM PACCOUNT a, BANKTEAM b WHERE a.ATOTAL=b.TBALANCE AND a.TACCOUNT=b.TACCOUNT AND a.TNAME=?";
        try {
            pstmt=con.prepareStatement(sql);

            pstmt.setString(1,air.getmId());
            rs= pstmt.executeQuery();
            check=rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }


    public void pSelect(Airline air) {
        String sql = "SELECT TNAME,TACCOUNT FROM BANKTEAM WHERE TNAME=?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, air.getmId());
//            pstmt.executeUpdate();
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("통장명 : "+ rs.getString(1) + ", 계좌번호 : "
                        +rs.getString(2));

            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}



