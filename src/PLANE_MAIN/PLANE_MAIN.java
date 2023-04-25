package PLANE_MAIN;

import PLANE.Airline;
import PLANE.Member;
import PLANE_DBC.airSQL;
import PLANE_DBC.memSQL;

import java.util.Scanner;

public class PLANE_MAIN {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        memSQL sql = new memSQL();
        Member mem = new Member();
        airSQL aSql = new airSQL();
        Airline air = new Airline();


        boolean run = true;
        // DB 접속 메소드
        sql.DBConnect();

        while (run) {
            System.out.println("1. 회원 가입 2. 로그인 3. ID/PW 찾기 4. 종료");
            System.out.print("메뉴 선택 : ");
            int menu = sc.nextInt();
            switch (menu) {
                case 1:
                    System.out.print("아이디 입력 : ");
                    mem.setmId(sc.next());
                    // ID 확인 메소드
                    if (sql.idCheck(mem)) {
                        System.out.println("이미 존재하는 아이디입니다");
                    } else {
                        System.out.print("패스워드 입력 : ");
                        mem.setmPw(sc.next());
                        System.out.print("이름 입력 : ");
                        mem.setmName(sc.next());
                        System.out.print("전화번호 입력 : ");
                        mem.setmPhone(sc.next());
                        System.out.print("여권번호 입력 : ");
                        mem.setmPassport(sc.next());
                        System.out.print("생년월일 입력 : ");
                        mem.setmBirth(sc.next());
                        // 회원 가입 메소드
                        sql.join(mem);
                    }
                    break;
                case 2:
                    System.out.print("아이디 입력 : ");
                    mem.setmId(sc.next());
                    // ID 확인 메소드
                    if (sql.idCheck(mem)) {
                        System.out.print("패스워드 입력 : ");
                        mem.setmPw(sc.next());
                        // PW 확인 메소드
                        if (sql.idPwCheck(mem)) {
                            // 관리자 확인 메소드
                            if (sql.adminCheck(mem)) {
                                boolean run2 = true;
                                while (run2) {
                                    System.out.println("1. 회원 조회 2. 회원 수정 3. 예약 조회 4. 항공편 등록 5. 항공편 조회 ");
                                    System.out.println("6. 코드 등록 7. 코드 조회 8. 일정 등록 9. 일정 조회 10. 로그 아웃");
                                    System.out.print("메뉴 선택 : ");
                                    int menu2 = sc.nextInt();
                                    switch (menu2) {
                                        case 1:
                                            // 회원 조회 메소드
                                            sql.mSelect(mem);
                                            break;
                                        case 2:
                                            System.out.print("아이디 입력 : ");
                                            mem.setmId(sc.next());
                                            // ID 확인 메소드
                                            if (sql.idCheck(mem)) {
                                                System.out.println("1. 패스워드 2. 이름 3. 전화번호 4. 여권번호 5. 생년월일 6. 관리자권한");
                                                System.out.print("메뉴 선택 : ");
                                                int menu3 = sc.nextInt();
                                                switch (menu3) {
                                                    case 1:
                                                        System.out.print("패스워드 : ");
                                                        mem.setmPw(sc.next());
                                                        // 회원정보 부분수정 메소드(mPw)
                                                        sql.mUpdate(mem, "mPw", mem.getmPw());
                                                        break;
                                                    case 2:
                                                        System.out.print("이름 : ");
                                                        mem.setmName(sc.next());
                                                        // 회원정보 부분수정 메소드(mName)
                                                        sql.mUpdate(mem, "mName", mem.getmName());
                                                        break;
                                                    case 3:
                                                        System.out.print("전화번호 : ");
                                                        mem.setmPhone(sc.next());
                                                        // 회원정보 부분수정 메소드(mPhone)
                                                        sql.mUpdate(mem, "mPhone", mem.getmPhone());
                                                        break;
                                                    case 4:
                                                        System.out.print("여권번호 : ");
                                                        mem.setmPassport(sc.next());
                                                        // 회원정보 부분수정 메소드(mPassport)
                                                        sql.mUpdate(mem, "mPassport", mem.getmPassport());
                                                        break;
                                                    case 5:
                                                        System.out.print("생년월일 : ");
                                                        mem.setmBirth(sc.next());
                                                        // 회원정보 부분수정 메소드(mBirth)
                                                        sql.mUpdate(mem, "mBirth", mem.getmBirth());
                                                        break;
                                                    case 6:
                                                        System.out.println("관리자 권한 부여하시겠습니까? > 1. 예 2. 아니오");
                                                        System.out.print("선택 : ");
                                                        int admin = sc.nextInt();
                                                        if (admin == 1) {
                                                            // 회원정보 부분수정 메소드(mAdmin)
                                                            sql.mUpdate(mem, "mAdmin", 1);
                                                        }
                                                        break;
                                                    default:
                                                        System.out.println("잘못 입력했습니다");
                                                }
                                            } else {
                                                System.out.println("아이디가 존재하지 않습니다");
                                            }
                                            break;
                                        case 3:
                                            // 예약 조회 메소드
                                            aSql.rSelect(air.getsNum());
                                            break;
                                        case 4:
                                            System.out.print("항공편 입력 : ");
                                            air.setaName(sc.next());
                                            System.out.print("비행기종 입력 : ");
                                            air.setaType(sc.next());
                                            System.out.print("항공사 입력 : ");
                                            air.setaCompany(sc.next());
                                            System.out.print("비행시간 입력 : ");
                                            air.setaTime(sc.nextInt());
                                            System.out.print("출발공항 코드 입력 : ");
                                            air.setdCode(sc.next());
                                            System.out.print("도착공항 코드 입력 : ");
                                            air.setaCode(sc.next());
                                            // 항공편 입력 메소드
                                            aSql.aReg(air);
                                            break;
                                        case 5:
                                            // 항공편 조회 메소드
                                            aSql.aSelect();
                                            break;
                                        case 6:
                                            System.out.print("코드 입력 : ");
                                            air.setCode(sc.next());
                                            System.out.print("국가 입력 : ");
                                            air.setNation(sc.next());
                                            System.out.print("도시 입력 : ");
                                            air.setCity(sc.next());
                                            System.out.print("타임존 입력 : ");
                                            air.settZone(sc.nextInt());
                                            // 코드 입력 메소드
                                            aSql.cReg(air);
                                            break;
                                        case 7:
                                            // 코드 조회 메소드
                                            aSql.cSelect();
                                            break;
                                        case 8:
                                            System.out.println("일정 번호 입력 : ");
                                            air.setsNum(sc.next());
                                            System.out.print("출발시간 입력 : ");
                                            air.setsStart(sc.next());
                                            System.out.print("가격 입력 : ");
                                            air.setsPrice(sc.nextInt());
                                            System.out.print("항공편 입력 : ");
                                            air.setaName(sc.next());
                                            // 일정 입력 메소드
                                            aSql.sReg(air);
                                            break;
                                        case 9:
                                            // 일정 조회 메소드
                                            aSql.sSelect();
                                            break;
                                        case 10:
                                            run2 = false;
                                            System.out.println("로그아웃 성공!");
                                            break;
                                        default:
                                            System.out.println("잘못 입력했습니다");
                                            break;
                                    }
                                }
                            } else {
                                boolean run3 = true;
                                while (run3) {
                                    System.out.println("1. 조회하기 2. 추천일정 3. 장바구니 3. 마이페이지 4. 로그아웃");
                                    System.out.print("메뉴 선택 : ");
                                    int menu3 = sc.nextInt();
                                    int confirm = 0;
                                    int count = 0;
                                    String select = null;
                                    switch (menu3) {
                                        case 1:
                                            System.out.println("출발일을 입력해 주세요(**-**)");
                                            System.out.print("현재 5월만 입력 가능합니다 >>");
                                            air.setsStart(sc.next());
                                            System.out.println("여행 지역을 입력해 주세요");
                                            System.out.println("1. 유럽 2. 미주 3. 아시아 4. 국내");
                                            int region = sc.nextInt();
                                            System.out.println("국가 및 지역을 입력해 주세요");

                                            // 각 케이스는 지역 선택 외에는 동일하게 진행 되는 부분이라 메소드 제작
                                            switch (region) {
                                                case 1:
                                                    // 일정 테이블에서 유럽만 선택(국내 출발만)해서 중복을 제거하고 국가,도시만 출력
                                                    aSql.sSelect("eu");
                                                    System.out.print("선택 : ");
                                                    select = sc.next();
                                                    // 일정 테이블에서 날짜와 국가,도시 정보로 일정 출력
                                                    aSql.sSelect(air, select);
                                                    System.out.println("목록에서 일정번호를 선택해서 입력해 주세요");
                                                    air.setsNum(sc.next());
                                                    // 일정번호로 일정 출력
                                                    aSql.rSelect(air.getsNum());
                                                    System.out.println("저장하시겠습니까? 1. 네 2. 아니요");
                                                    System.out.print("선택 : ");
                                                    confirm = sc.nextInt();
                                                    if (confirm == 1) {

                                                        System.out.println("인원 수를 입력해 주세요");
                                                        System.out.println("선택 : ");
                                                        count = sc.nextInt();
                                                        for (int i = 0; i < count; i++) {
                                                            // rNum 생성메소드
                                                            air.setrNum(aSql.rNum() + 1);
                                                            // 아이디와 일정 번호로 예약 테이블에 입력
                                                            // rTime은 SYSDATE 이용
                                                            aSql.rReg(air);
                                                        }
                                                        System.out.println("돌아오는 일정도 조회하시겠습니까? 1. 네 2. 아니요");
                                                        System.out.print("선택 : ");
                                                        int back = sc.nextInt();
                                                        if (back == 1) {
                                                            System.out.println("출발일을 입력해 주세요(**-**)");
                                                            System.out.print("현재 5월만 입력 가능합니다 >>");
                                                            air.setsStart(sc.next());
                                                            // 일정 테이블에서 날짜와 국가,도시 정보로 귀국 일정 출력
                                                            aSql.sSelect(air, select);
                                                            System.out.println("목록에서 일정번호를 선택해서 입력해 주세요");
                                                            air.setsNum(sc.next());
                                                            // 일정번호로 일정 출력
                                                            aSql.rSelect(air.getsNum());
                                                            System.out.println("저장하시겠습니까? 1. 네 2. 아니요");
                                                            System.out.print("선택 : ");
                                                            confirm = sc.nextInt();
                                                            if (confirm == 1) {

                                                                System.out.println("인원 수를 입력해 주세요");
                                                                System.out.println("선택 : ");
                                                                count = sc.nextInt();
                                                                for (int i = 0; i < count; i++) {
                                                                    // rNum 생성메소드
                                                                    air.setrNum(aSql.rNum() + 1);
                                                                    // 아이디와 일정 번호로 예약 테이블에 입력
                                                                    // rTime은 SYSDATE 이용
                                                                    aSql.rReg(air);
                                                                }
                                                            }
                                                        }
                                                    }

                                                    break;
                                                case 2:
                                                    // 일정 테이블에서 미주만 선택해서 중복을 제거하고 국가,도시만 출력
                                                    aSql.sSelect("am");
                                                    System.out.print("선택 : ");
                                                    select = sc.next();
                                                    // 일정 테이블에서 날짜와 국가,도시 정보로 일정 출력
                                                    aSql.sSelect(air, select);
                                                    System.out.println("목록에서 일정번호를 선택해서 입력해 주세요");
                                                    air.setsNum(sc.next());
                                                    // 일정번호로 일정 출력
                                                    aSql.rSelect(air.getsNum());
                                                    System.out.println("저장하시겠습니까? 1. 네 2. 아니요");
                                                    System.out.print("선택 : ");
                                                    confirm = sc.nextInt();
                                                    if (confirm == 1) {

                                                        System.out.println("인원 수를 입력해 주세요");
                                                        System.out.println("선택 : ");
                                                        count = sc.nextInt();
                                                        for (int i = 0; i < count; i++) {
                                                            // rNum 생성메소드
                                                            air.setrNum(aSql.rNum() + 1);
                                                            // 아이디와 일정 번호로 예약 테이블에 입력
                                                            // rTime은 SYSDATE 이용
                                                            aSql.rReg(air);
                                                        }
                                                        System.out.println("돌아오는 일정도 조회하시겠습니까? 1. 네 2. 아니요");
                                                        System.out.print("선택 : ");
                                                        int back = sc.nextInt();
                                                        if (back == 1) {
                                                            System.out.println("출발일을 입력해 주세요(**-**)");
                                                            System.out.print("현재 5월만 입력 가능합니다 >>");
                                                            air.setsStart(sc.next());
                                                            // 일정 테이블에서 날짜와 국가,도시 정보로 귀국 일정 출력
                                                            aSql.sSelect(air, select);
                                                            System.out.println("목록에서 일정번호를 선택해서 입력해 주세요");
                                                            air.setsNum(sc.next());
                                                            // 일정번호로 일정 출력
                                                            aSql.rSelect(air.getsNum());
                                                            System.out.println("저장하시겠습니까? 1. 네 2. 아니요");
                                                            System.out.print("선택 : ");
                                                            confirm = sc.nextInt();
                                                            if (confirm == 1) {

                                                                System.out.println("인원 수를 입력해 주세요");
                                                                System.out.println("선택 : ");
                                                                count = sc.nextInt();
                                                                for (int i = 0; i < count; i++) {
                                                                    // rNum 생성메소드
                                                                    air.setrNum(aSql.rNum() + 1);
                                                                    // 아이디와 일정 번호로 예약 테이블에 입력
                                                                    // rTime은 SYSDATE 이용
                                                                    aSql.rReg(air);
                                                                }
                                                            }
                                                        }
                                                    }
                                                    break;
                                                case 3:
                                                    // 일정 테이블에서 아시아만 선택해서 중복을 제거하고 국가,도시만 출력
                                                    aSql.sSelect("as");
                                                    System.out.print("선택 : ");
                                                    select = sc.next();
                                                    // 일정 테이블에서 날짜와 국가,도시 정보로 일정 출력
                                                    aSql.sSelect(air, select);
                                                    System.out.println("목록에서 일정번호를 선택해서 입력해 주세요");
                                                    air.setsNum(sc.next());
                                                    // 일정번호로 일정 출력
                                                    aSql.rSelect(air.getsNum());
                                                    System.out.println("저장하시겠습니까? 1. 네 2. 아니요");
                                                    System.out.print("선택 : ");
                                                    confirm = sc.nextInt();
                                                    if (confirm == 1) {

                                                        System.out.println("인원 수를 입력해 주세요");
                                                        System.out.println("선택 : ");
                                                        count = sc.nextInt();
                                                        for (int i = 0; i < count; i++) {
                                                            // rNum 생성메소드
                                                            air.setrNum(aSql.rNum() + 1);
                                                            // 아이디와 일정 번호로 예약 테이블에 입력
                                                            // rTime은 SYSDATE 이용
                                                            aSql.rReg(air);
                                                        }
                                                        System.out.println("돌아오는 일정도 조회하시겠습니까? 1. 네 2. 아니요");
                                                        System.out.print("선택 : ");
                                                        int back = sc.nextInt();
                                                        if (back == 1) {
                                                            System.out.println("출발일을 입력해 주세요(**-**)");
                                                            System.out.print("현재 5월만 입력 가능합니다 >>");
                                                            air.setsStart(sc.next());
                                                            // 일정 테이블에서 날짜와 국가,도시 정보로 귀국 일정 출력
                                                            aSql.sSelect(air, select);
                                                            System.out.println("목록에서 일정번호를 선택해서 입력해 주세요");
                                                            air.setsNum(sc.next());
                                                            // 일정번호로 일정 출력
                                                            aSql.rSelect(air.getsNum());
                                                            System.out.println("저장하시겠습니까? 1. 네 2. 아니요");
                                                            System.out.print("선택 : ");
                                                            confirm = sc.nextInt();
                                                            if (confirm == 1) {

                                                                System.out.println("인원 수를 입력해 주세요");
                                                                System.out.println("선택 : ");
                                                                count = sc.nextInt();
                                                                for (int i = 0; i < count; i++) {
                                                                    // rNum 생성메소드
                                                                    air.setrNum(aSql.rNum() + 1);
                                                                    // 아이디와 일정 번호로 예약 테이블에 입력
                                                                    // rTime은 SYSDATE 이용
                                                                    aSql.rReg(air);
                                                                }
                                                            }
                                                        }
                                                    }
                                                    break;
                                                case 4:
                                                    // 일정 테이블에서 국내만 선택해서 중복을 제거하고 국가,도시만 출력
                                                    aSql.sSelect("do");
                                                    System.out.print("선택 : ");
                                                    select = sc.next();
                                                    // 일정 테이블에서 날짜와 국가,도시 정보로 일정 출력
                                                    aSql.sSelect(air, select);
                                                    System.out.println("목록에서 일정번호를 선택해서 입력해 주세요");
                                                    air.setsNum(sc.next());
                                                    // 일정번호로 일정 출력
                                                    aSql.rSelect(air.getsNum());
                                                    System.out.println("저장하시겠습니까? 1. 네 2. 아니요");
                                                    System.out.print("선택 : ");
                                                    confirm = sc.nextInt();
                                                    if (confirm == 1) {

                                                        System.out.println("인원 수를 입력해 주세요");
                                                        System.out.println("선택 : ");
                                                        count = sc.nextInt();
                                                        for (int i = 0; i < count; i++) {
                                                            // rNum 생성메소드
                                                            air.setrNum(aSql.rNum() + 1);
                                                            // 아이디와 일정 번호로 예약 테이블에 입력
                                                            // rTime은 SYSDATE 이용
                                                            aSql.rReg(air);
                                                        }
                                                        System.out.println("돌아오는 일정도 조회하시겠습니까? 1. 네 2. 아니요");
                                                        System.out.print("선택 : ");
                                                        int back = sc.nextInt();
                                                        if (back == 1) {
                                                            System.out.println("출발일을 입력해 주세요(**-**)");
                                                            System.out.print("현재 5월만 입력 가능합니다 >>");
                                                            air.setsStart(sc.next());
                                                            // 일정 테이블에서 날짜와 국가,도시 정보로 귀국 일정 출력
                                                            aSql.sSelect(air, select);
                                                            System.out.println("목록에서 일정번호를 선택해서 입력해 주세요");
                                                            air.setsNum(sc.next());
                                                            // 일정번호로 일정 출력
                                                            aSql.rSelect(air.getsNum());
                                                            System.out.println("저장하시겠습니까? 1. 네 2. 아니요");
                                                            System.out.print("선택 : ");
                                                            confirm = sc.nextInt();
                                                            if (confirm == 1) {

                                                                System.out.println("인원 수를 입력해 주세요");
                                                                System.out.println("선택 : ");
                                                                count = sc.nextInt();
                                                                for (int i = 0; i < count; i++) {
                                                                    // rNum 생성메소드
                                                                    air.setrNum(aSql.rNum() + 1);
                                                                    // 아이디와 일정 번호로 예약 테이블에 입력
                                                                    // rTime은 SYSDATE 이용
                                                                    aSql.rReg(air);
                                                                }
                                                            }
                                                        }
                                                    }
                                                    break;
                                                default:
                                                    System.out.println("잘못 입력했습니다");
                                                    break;
                                            }
                                            break;
                                        case 2:
                                            break;
                                        case 3:
                                            // 멤버 아이디에 해당하는 예약정보중 RCHECK가 1이 아닌 내역(미결제)만 출력
                                            aSql.rSelect(air, 0);
                                            System.out.println("결제 하시겠습니까?");
                                            break;
                                        case 4:
                                            break;
                                        case 5:
                                            run3 = false;
                                            System.out.println("로그아웃 성공");
                                            break;
                                        default:
                                            System.out.println("잘못 입력했습니다");
                                            break;
                                    }
                                }
                            }
                        } else {
                            System.out.println("패스워드를 잘못 입력했습니다");
                        }
                    } else {
                        System.out.println("아이디가 존재하지 않습니다");
                    }
                    break;
                case 3:
                    System.out.println("1. 아이디 찾기 2. 비밀번호 찾기");
                    System.out.print("메뉴 선택 : ");
                    int find = sc.nextInt();
                    if (find == 1) {
                        System.out.print("이름 입력 : ");
                        mem.setmName(sc.next());
                        System.out.println("연락처 입력 : ");
                        mem.setmPhone(sc.next());
                        sql.findId(mem);
                    } else if (find == 2) {
                        System.out.print("아이디 입력 : ");
                        mem.setmId(sc.next());
                        if (sql.idCheck(mem)) {
                            System.out.print("이름 입력 : ");
                            mem.setmName(sc.next());
                            System.out.println("연락처 입력 : ");
                            mem.setmPhone(sc.next());
                            System.out.println("여권번호 입력 : ");
                            mem.setmPassport(sc.next());
                            sql.findPw(mem);
                        } else {
                            System.out.println("아이디가 존재하지 않습니다");
                        }

                    }
                    break;
                case 4:
                    System.out.println("이용해 주셔서 감사합니다");
                    sql.conClose();
                    run=false;
                    break;
                default:
                    System.out.println("잘못 입력했습니다");
                    break;
            }
        }
    }
    public static void plane () {
        System.out.println("┌──────────────────────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│                      ___                                                                 │");
        System.out.println("│                      \\\\ \\                                                                │");
        System.out.println("│                       \\\\ `\\                                                              │");
        System.out.println("│    ___                 \\\\  \\                                                             │");
        System.out.println("│   |    \\                \\\\  `\\                                                           │");
        System.out.println("│   |_____\\                \\    \\                                                          │");
        System.out.println("│   |______\\                \\    `\\                                                        │");
        System.out.println("│   |       \\                \\     \\                                                       │");
        System.out.println("│   |      __\\__---------------------------------._.                                       │");
        System.out.println("│ __|---~~~__o_o_o_o_o_o_o_o_o_o_o_o_o_o_o_o_o_o_[][\\__                                    │");
        System.out.println("│   |___                         /~      )                \\__                              │");
        System.out.println("│        ~~~---..._______________/      ,/_________________/                               │");
        System.out.println("│                               /      /                                                   │");
        System.out.println("│                             /     ,/                                                     │");
        System.out.println("│                            /     /                                                       │");
        System.out.println("│                           /    ,/                                                        │");
        System.out.println("│                          /    /                                                          │");
        System.out.println("│                         //  ,/                                                           │");
        System.out.println("│                        //  /                                                             │");
        System.out.println("│                       // ,/                                                              │");
        System.out.println("│                      //_/                                                                │");
        System.out.println("│                                                                                          │");
        System.out.println("│                                                                                          │");
        System.out.println("└──────────────────────────────────────────────────────────────────────────────────────────┘");
    }
}
