package PLANE_DBC;

import PLANE.Member;

import java.sql.*;

public class memSQL {

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
            System.out.println("DB 접속 종료!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




    public boolean idCheck(Member mem) {

        String sql = "SELECT * FROM AMEMBER WHERE MID=?";

        boolean check=false;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,mem.getmId());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                check = true;
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }



    public boolean idPwCheck(Member mem) {
        String sql = "SELECT * FROM AMEMBER WHERE MID=? AND MPW=?";
        boolean check=false;

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,mem.getmId() );
            pstmt.setString(2,mem.getmPw());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                check = true;
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }

    public void join(Member mem) {
        String sql = "INSERT INTO AMEMBER(MID,MPW,MNAME,MPHONE,MPASSPORT,MBIRTH) VALUES (?,?,?,?,?,?)";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, mem.getmId());
            pstmt.setString(2, mem.getmPw());
            pstmt.setString(3, mem.getmName());
            pstmt.setString(4, mem.getmPhone());
            pstmt.setString(5, mem.getmPassport());
            pstmt.setString(6, mem.getmBirth());

            int result = pstmt.executeUpdate();

            if (result > 0) {
                System.out.println("입력 성공!");
            } else {
                System.out.println("입력 실패!");
            }
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean adminCheck(Member mem) {
        String sql = "SELECT * FROM AMEMBER WHERE MID=? AND MADMIN=1";
        boolean check=false;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, mem.getmId());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                check = true;
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }

    public void mSelect(Member mem) {
        String sql = "SELECT MID,MPW,MNAME,MPHONE,MPASSPORT,MBIRTH FROM AMEMBER";

        try {
            pstmt = con.prepareStatement(sql);

            rs=pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("아이디 :" +rs.getString(1));
                System.out.println("비번 :" +rs.getString(2));
                System.out.println("이름 :" + rs.getString(3));
                System.out.println("연락처 :" + rs.getString(4));
                System.out.println("여권번호 :" + rs.getString(5));
                System.out.println("생년월인 :" + rs.getString(6).substring(0,10));
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void mUpdate(Member mem, String type, String s) {
        String sql = "UPDATE AMEMBER SET "+type+"=? WHERE MID=?";
        try {

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, s);
            pstmt.setString(2, mem.getmId());

            rs=pstmt.executeQuery();
            if (rs.next()){
                System.out.println("변경 완료");
            }

            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void mUpdate(Member mem, String type, int s) {
        String sql = "UPDATE AMEMBER SET "+type+"=? WHERE MID=?";
        try {
            pstmt=con.prepareStatement(sql);

            pstmt.setInt(1, s);
            pstmt.setString(2, mem.getmId());
            rs=pstmt.executeQuery();
            if (rs.next()){
                System.out.println("변경 완료");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // 아이디 찾기
    public void findId(Member mem) {
        String sql = "SELECT MID FROM AMEMBER WHERE MNAME=? AND MPHONE=?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, mem.getmName());
            pstmt.setString(2, mem.getmPhone());
            rs = pstmt.executeQuery();

            if(rs.next()) {
                System.out.print("ID : " + rs.getString(1));
                System.out.println();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    // 비밀번호 찾기
    public void findPw(Member mem) {
        String sql = "SELECT MPW FROM AMEMBER WHERE MID=? AND MNAME=? AND MPHONE=? AND MPASSPORT =?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, mem.getmId());
            pstmt.setString(2, mem.getmName());
            pstmt.setString(3, mem.getmPhone());
            pstmt.setString(4, mem.getmPassport());
            rs = pstmt.executeQuery();

            if(rs.next()) {
                System.out.print("PW : " + rs.getString(1));
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
