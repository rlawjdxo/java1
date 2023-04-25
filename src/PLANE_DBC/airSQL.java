package PLANE_DBC;

import PLANE.Airline;
import PLANE.Member;

import java.sql.*;

public class airSQL {

    Connection con;

    // sql 쿼리문 전송을 위한 객체
    Statement stmt;

    ResultSet rs;

    // '?' 없이도 사용 가능
    PreparedStatement pstmt;

// DB연동을 위한 DBConnect() 메소드 사용

    public void DBConnect() {
        con = DBC.DBConnect();
    }

    public void conClose(){
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //예약조회
    public void rSelect(String s) {
        String sql = "SELECT a.MID, a.RTIME, a.RNUM, b.SNUM, b.SPRICE, b.SSTART, b.ANAME FROM ARESERVATION a, ASCHEDULE b";

        try {
            pstmt = con.prepareStatement(sql);
            rs= pstmt.executeQuery();
            while (rs.next()){
                System.out.println("예약 번호 : "+ rs.getString(3));
                System.out.println("예약 회원 ID : "+ rs.getString(1));
                System.out.println("예약 시간 : "+ rs.getString(2));
                System.out.println("일정 번호 : "+ rs.getString(4));
                System.out.println("가격 : "+ rs.getString(5) + "원");
                System.out.println("출발시간 : "+ rs.getString(6));
                System.out.println("항공편명 : "+ rs.getString(7));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 항공편 입력

    public void aReg(Airline air) {
        String sql = "INSERT INTO AIRLINE VALUES(?, ?, ?, ?, ?, ?)";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,air.getaName());
            pstmt.setString(2,air.getaType());
            pstmt.setString(3,air.getaCompany());
            pstmt.setDouble(4,air.getaTime());
            pstmt.setString(5,air.getaCode());
            pstmt.setString(6,air.getaCode());
            int result = pstmt.executeUpdate();
            if (result>0){
                System.out.println("항공편 입력 성공!");
            } else{
                System.out.println("입력 실패!");
            }
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    // 항공편 조회
    public void aSelect() {
        String sql = "SELECT * FROM AIRLINE";

        try {
            pstmt = con.prepareStatement(sql);
            rs= pstmt.executeQuery();
            while (rs.next()){
                System.out.println("항공편 : "+ rs.getString(1));
                System.out.println("비행기종 : "+ rs.getString(2));
                System.out.println("항공사 : "+ rs.getString(3));
                System.out.println("비행시간 : "+ rs.getString(4));
                System.out.println("출발지 : "+ rs.getString(5));
                System.out.println("도착지 : "+ rs.getString(6));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    //코드 입력

    public void cReg(Airline air) {
        String sql = "INSERT INTO CODE VALUES (?, ?, ?, ?)";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,air.getaCode());
            pstmt.setString(2,air.getNation());
            pstmt.setString(3,air.getCity());
            pstmt.setInt(4,air.gettZone());
            int result = pstmt.executeUpdate();
            if (result>0){
                System.out.println("코드 입력 성공!");
            } else{
                System.out.println("입력 실패!");
            }
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 코드 조회
    public void cSelect() {
        String sql = "SELECT * FROM CODE";

        try {
            pstmt = con.prepareStatement(sql);
            rs= pstmt.executeQuery();
            while (rs.next()){
                System.out.println("코드 : "+ rs.getString(1));
                System.out.println("국가 : "+ rs.getString(2));
                System.out.println("도시 : "+ rs.getString(3));
                System.out.println("타임존 : "+ rs.getString(4));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    // 일정 조회

    public void sSelect() {
        String sql = "SELECT * FROM ASCHEDULE";

        try {
            pstmt = con.prepareStatement(sql);
            rs= pstmt.executeQuery();
            while (rs.next()){
                System.out.println("일정 번호 : "+ rs.getString(1));
                System.out.println("출발시간 : "+ rs.getString(2));
                System.out.println("가격 : "+ rs.getString(3));
                System.out.println("항공편 : "+ rs.getString(4));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    // 일정 입력
    public void sReg(Airline air) {
        String sql = "INSERT INTO ASCHEDULE VALUES (?, ?, ?, ?)";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,air.getsNum());
            pstmt.setString(2,air.getsStart());
            pstmt.setInt(3,air.getsPrice());
            pstmt.setString(4,air.getaName());
            int result = pstmt.executeUpdate();
            if (result>0){
                System.out.println("일정 입력 성공!");
            } else{
                System.out.println("입력 실패!");
            }
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //지역 맞는 일정 조회
    public void sSelect(String select) {
        String sql = "SELECT NATION, CITY FROM ASCHEDULE A , CODE B, AIRLINE C WHERE substr(SNUM,6,2) =? AND A.ANAME = C.ANAME AND B.CODE = C.ACODE ";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,select);
            rs= pstmt.executeQuery();
            while (rs.next()){
                System.out.println("국가 : "+rs.getString(1));
                System.out.println("도시 : "+rs.getString(2));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 날짜와 국가 도시정보 일정 출력
    public void sSelect(Airline air, String select) {
        String sql = "SELECT SNUM, SSTART, NATION, CITY FROM ASCHEDULE A , CODE B, AIRLINE C WHERE SNUM LIKE '%?%' AND A.ANAME = C.ANAME AND B.CODE = C.ACODE ";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,select);
            rs= pstmt.executeQuery();
            while (rs.next()){
                System.out.println("국가 : "+rs.getString(1));
                System.out.println("도시 : "+rs.getString(2));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 일정번호로 예약 시 일정 출력
    public void rSelect(Airline air) {
        String sql = "SELECT a.MID, a.RTIME, a.RNUM, b.SNUM, b.SPRICE, b.SSTART, b.ANAME FROM ARESERVATION a, ASCHEDULE b WHERE SNUM = ?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,air.getsNum());
            rs= pstmt.executeQuery();
            while (rs.next()){
                System.out.println("예약 번호 : "+ rs.getString(3));
                System.out.println("예약 회원 ID : "+ rs.getString(1));
                System.out.println("예약 시간 : "+ rs.getString(2));
                System.out.println("일정 번호 : "+ rs.getString(4));
                System.out.println("가격 : "+ rs.getString(5) + "원");
                System.out.println("출발시간 : "+ rs.getString(6));
                System.out.println("항공편명 : "+ rs.getString(7));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public int rNum() {
        String sql = "SELECT COUNT(*) FROM ARESERVATION";
        int r =0 ;
        try {
            pstmt = con.prepareStatement(sql);
            r = rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return r;
    }
    public void rReg(Airline air) {
        String sql = "INSERT INTO ARESERVATION VALRUES( ?, ? , ?, ? ,?)";

        Member mem = new Member();
        try {
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1, mem.getmId());
            pstmt.setString(2, "SYSDATE");
            pstmt.setString(3, air.getsNum());
            pstmt.setInt(4, air.getrNum());
            pstmt.setString(5, "null");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // rcheck가 2가 아니면 미결제 출력
    public void rSelect(Airline air, int C) {
        String sql = "SELECT a.MID, a.RTIME, a.RNUM, b.SNUM, b.SPRICE, b.SSTART, b.ANAME a.RCHECK FROM ARESERVATION a, ASCHEDULE b WHERE SNUM = ?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,air.getsNum());
            pstmt.setInt(8,1);
            rs= pstmt.executeQuery();
            while (rs.next()){
                System.out.println("예약 번호 : "+ rs.getString(3));
                System.out.println("예약 회원 ID : "+ rs.getString(1));
                System.out.println("예약 시간 : "+ rs.getString(2));
                System.out.println("일정 번호 : "+ rs.getString(4));
                System.out.println("가격 : "+ rs.getString(5) + "원");
                System.out.println("출발시간 : "+ rs.getString(6));
                System.out.println("항공편명 : "+ rs.getString(7));
                if(rs.getInt(8)>0){
                    System.out.println("결제 완료");
                }else{
                    System.out.println("결제 대기");
                }
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}


