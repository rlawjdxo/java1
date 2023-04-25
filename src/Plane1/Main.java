package Plane1;


import java.util.Scanner;

import static Plane1.util.calender;
import static Plane1.util.plane;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        memSQL sql = new memSQL();
        Member mem = new Member();
        airSQL aSql = new airSQL();
        Airline air = new Airline();
        Client cl = new Client();
        BankSQL bSql = new BankSQL();


        boolean run = true;
        // DB 접속 메소드
        sql.connect();
        while (run) {
            plane();
            System.out.println("────────────────────────────────────────");
            System.out.println("1. 회원 가입 2. 로그인 3. ID/PW 찾기 4. 종료");
            System.out.println("────────────────────────────────────────");
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
                        aSql.connect();
                        if (sql.idPwCheck(mem)) {
                            // 관리자 확인 메소드
                            boolean run2 = true;
                            if (sql.adminCheck(mem)) {
                                while (run2) {
                                    System.out.println();
                                    System.out.println("─────────────────────────────────────────────────────────");
                                    System.out.println("1. 회원 조회 2. 회원 수정 3. 예약 조회 4. 코드 등록 5. 코드 조회");
                                    System.out.println("6. 편명 등록 7. 편명 조회 8. 일정 등록 9. 일정 조회 0. 로그 아웃");
                                    System.out.println("─────────────────────────────────────────────────────────");
                                    System.out.print("메뉴 선택 : ");
                                    int menu2 = sc.nextInt();
                                    switch (menu2) {
                                        case 1:
                                            // 회원 조회 메소드
                                            sql.mSelect();
                                            break;
                                        case 2:
                                            System.out.print("아이디 입력 : ");
                                            mem.setmId(sc.next());
                                            // ID 확인 메소드
                                            if (sql.idCheck(mem)) {
                                                System.out.println("─────────────────────────────────────────────────────────────");
                                                System.out.println("1. 패스워드 2. 이름 3. 전화번호 4. 여권번호 5. 생년월일 6. 관리자권한");
                                                System.out.println("─────────────────────────────────────────────────────────────");
                                                System.out.print("메뉴 선택 : ");
                                                int umenu = sc.nextInt();
                                                switch (umenu) {
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
                                            aSql.rSelect();
                                            break;

                                        case 4:
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
                                        case 5:
                                            // 코드 조회 메소드
                                            aSql.cSelect();
                                            break;
                                        case 6:
                                            System.out.print("항공편 입력 : ");
                                            air.setaName(sc.next());
                                            System.out.print("비행기종 입력 : ");
                                            air.setaType(sc.next());
                                            System.out.print("항공사 입력 : ");
                                            air.setaCompany(sc.next());
                                            System.out.print("비행시간 입력 : ");
                                            air.setaTime(sc.nextDouble());
                                            System.out.print("출발공항 코드 입력 : ");
                                            air.setdCode(sc.next());
                                            System.out.print("도착공항 코드 입력 : ");
                                            air.setaCode(sc.next());
                                            // 항공편 입력 메소드
                                            aSql.aReg(air);
                                            break;
                                        case 7:
                                            // 항공편 조회 메소드
                                            aSql.aSelect();
                                            break;
                                        case 8:
                                            System.out.print("일정 번호 입력 : ");
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
                                        case 0:
                                            run2 = false;
                                            System.out.println("로그아웃 성공!");
                                            break;
                                        default:
                                            System.out.println("잘못 입력했습니다");
                                            break;
                                    }
                                }
                            } else {
                                air.setmId(mem.getmId());
                                while (run2) {
                                    int confirm = 0;
                                    int count = 0;
                                    int back = 0;
                                    String select = null;
                                    System.out.println();
                                    System.out.println("─────────────────────────────────────────────────────");
                                    System.out.println("1. 조회하기 2. 추천일정 3. 장바구니 4. 마이페이지 5. 로그아웃");
                                    System.out.println("─────────────────────────────────────────────────────");
                                    System.out.print("메뉴 선택 : ");
                                    int menu3 = sc.nextInt();
                                    switch (menu3) {
                                        case 1:
                                            calender();
                                            System.out.println("출발일을 입력해 주세요(**/**)");
                                            System.out.print("현재 5월만 입력 가능합니다 >> ");
                                            air.setsStart(sc.next());
                                            System.out.println("여행 지역을 입력해 주세요");
                                            System.out.println("1. 유럽 2. 미주 3. 아시아 4. 국내");
                                            int region = sc.nextInt();
                                            // 각 케이스는 지역 선택 외에는 동일하게 진행 되는 부분이라 메소드 제작
                                            switch (region) {
                                                case 1:
                                                        if(aSql.sSelect()){
                                                            break;
                                                        }

                                                        // 일정 테이블에서 유럽만 선택해서 중복을 제거하고 국가,도시만 출력
                                                        aSql.sSelect("EU");
                                                        System.out.println("도시명을 입력해 주세요");
                                                        System.out.print("선택 : ");
                                                        select = sc.next();
                                                        // 일정 테이블에서 날짜와 국가,도시 정보로 일정 출력
                                                        aSql.sSelect(air, select);
                                                        System.out.println("목록에서 일정번호를 선택해서 입력해 주세요");
                                                        air.setsNum(sc.next());
                                                        // 일정번호로 일정 출력
                                                        aSql.sSelect(air);
                                                        System.out.println("저장하시겠습니까? 1. 네 2. 아니요");
                                                        System.out.print("선택 : ");
                                                        confirm = sc.nextInt();

                                                        if (confirm == 1) {
                                                            System.out.println("인원 수를 입력해 주세요");
                                                            System.out.print("선택 : ");
                                                            count = sc.nextInt();
                                                            for (int i = 0; i < count; i++) {
                                                                // rNum 생성메소드
                                                                air.setrNum(aSql.genrNum() + 1);
                                                                // 아이디와 일정 번호로 예약 테이블에 입력
                                                                // rTime은 SYSDATE 이용
                                                                aSql.rReg(air);
                                                            }

                                                            System.out.println("돌아오는 일정도 조회하시겠습니까? 1. 네 2. 아니요");
                                                            System.out.print("선택 : ");
                                                            back = sc.nextInt();
                                                            if (back == 1) {
                                                                calender();
                                                                System.out.println("출발일을 입력해 주세요(**-**)");
                                                                System.out.print("현재 5월만 입력 가능합니다 >> ");
                                                                air.setsStart(sc.next());
                                                                // 일정 테이블에서 날짜와 국가,도시 정보로 귀국 일정 출력
                                                                aSql.sBSelect(air, select);
                                                                System.out.println("목록에서 일정번호를 선택해서 입력해 주세요");
                                                                air.setsNum(sc.next());
                                                                // 일정번호로 일정 출력
                                                                aSql.sSelect(air);
                                                                System.out.println("저장하시겠습니까? 1. 네 2. 아니요");
                                                                System.out.print("선택 : ");
                                                                confirm = sc.nextInt();
                                                                if (confirm == 1) {
                                                                    System.out.println("인원 수를 입력해 주세요");
                                                                    System.out.print("선택 : ");
                                                                    count = sc.nextInt();
                                                                    for (int i = 0; i < count; i++) {
                                                                        // rNum 생성메소드
                                                                        air.setrNum(aSql.genrNum() + 1);
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
                                                    aSql.sSelect("AM");
                                                    System.out.println("도시명을 입력해 주세요");
                                                    System.out.print("선택 : ");
                                                    select = sc.next();
                                                    // 일정 테이블에서 날짜와 국가,도시 정보로 일정 출력
                                                    aSql.sSelect(air, select);
                                                    System.out.println("목록에서 일정번호를 선택해서 입력해 주세요");
                                                    air.setsNum(sc.next());
                                                    // 일정번호로 일정 출력
                                                    aSql.sSelect(air);
                                                    System.out.println("저장하시겠습니까? 1. 네 2. 아니요");
                                                    System.out.print("선택 : ");
                                                    confirm = sc.nextInt();
                                                    if (confirm == 1) {
                                                        System.out.println("인원 수를 입력해 주세요");
                                                        System.out.print("선택 : ");
                                                        count = sc.nextInt();
                                                        for (int i = 0; i < count; i++) {
                                                            // rNum 생성메소드
                                                            air.setrNum(aSql.genrNum() + 1);
                                                            // 아이디와 일정 번호로 예약 테이블에 입력
                                                            // rTime은 SYSDATE 이용
                                                            aSql.rReg(air);
                                                        }
                                                        System.out.println("돌아오는 일정도 조회하시겠습니까? 1. 네 2. 아니요");
                                                        System.out.print("선택 : ");
                                                        back = sc.nextInt();
                                                        if (back == 1) {
                                                            calender();
                                                            System.out.println("출발일을 입력해 주세요(**-**)");
                                                            System.out.print("현재 5월만 입력 가능합니다 >> ");
                                                            air.setsStart(sc.next());
                                                            // 일정 테이블에서 날짜와 국가,도시 정보로 귀국 일정 출력
                                                            aSql.sBSelect(air, select);
                                                            System.out.println("목록에서 일정번호를 선택해서 입력해 주세요");
                                                            air.setsNum(sc.next());
                                                            // 일정번호로 일정 출력
                                                            aSql.sSelect(air);
                                                            System.out.println("저장하시겠습니까? 1. 네 2. 아니요");
                                                            System.out.print("선택 : ");
                                                            confirm = sc.nextInt();
                                                            if (confirm == 1) {
                                                                System.out.println("인원 수를 입력해 주세요");
                                                                System.out.print("선택 : ");
                                                                count = sc.nextInt();
                                                                for (int i = 0; i < count; i++) {
                                                                    // rNum 생성메소드

                                                                air.setrNum(aSql.genrNum() + 1);
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
                                                    aSql.sSelect("AS");
                                                    System.out.println("도시명을 입력해 주세요");
                                                    System.out.print("선택 : ");
                                                    select = sc.next();
                                                    // 일정 테이블에서 날짜와 국가,도시 정보로 일정 출력
                                                    aSql.sSelect(air, select);
                                                    System.out.println("목록에서 일정번호를 선택해서 입력해 주세요");
                                                    air.setsNum(sc.next());
                                                    // 일정번호로 일정 출력
                                                    aSql.sSelect(air);
                                                    System.out.println("저장하시겠습니까? 1. 네 2. 아니요");
                                                    System.out.print("선택 : ");
                                                    confirm = sc.nextInt();
                                                    if (confirm == 1) {
                                                        System.out.println("인원 수를 입력해 주세요");
                                                        System.out.print("선택 : ");
                                                        count = sc.nextInt();
                                                        for (int i = 0; i < count; i++) {
                                                            // rNum 생성메소드
                                                            air.setrNum(aSql.genrNum() + 1);
                                                            // 아이디와 일정 번호로 예약 테이블에 입력
                                                            // rTime은 SYSDATE 이용
                                                            aSql.rReg(air);
                                                        }
                                                        System.out.println("돌아오는 일정도 조회하시겠습니까? 1. 네 2. 아니요");
                                                        System.out.print("선택 : ");
                                                        back = sc.nextInt();
                                                        if (back == 1) {
                                                            calender();
                                                            System.out.println("출발일을 입력해 주세요(**-**)");
                                                            System.out.print("현재 5월만 입력 가능합니다 >> ");
                                                            air.setsStart(sc.next());
                                                            // 일정 테이블에서 날짜와 국가,도시 정보로 귀국 일정 출력
                                                            aSql.sBSelect(air, select);
                                                            System.out.println("목록에서 일정번호를 선택해서 입력해 주세요");
                                                            air.setsNum(sc.next());
                                                            // 일정번호로 일정 출력
                                                            aSql.sSelect(air);
                                                            System.out.println("저장하시겠습니까? 1. 네 2. 아니요");
                                                            System.out.print("선택 : ");
                                                            confirm = sc.nextInt();
                                                            if (confirm == 1) {
                                                                System.out.println("인원 수를 입력해 주세요");
                                                                System.out.print("선택 : ");
                                                                count = sc.nextInt();
                                                                for (int i = 0; i < count; i++) {
                                                                    // rNum 생성메소드
                                                                    air.setrNum(aSql.genrNum() + 1);
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
                                                    aSql.sSelect("DO");
                                                    System.out.println("도시명을 입력해 주세요");
                                                    System.out.print("선택 : ");
                                                    select = sc.next();
                                                    // 일정 테이블에서 날짜와 국가,도시 정보로 일정 출력
                                                    aSql.sSelect(air, select);
                                                    System.out.println("목록에서 일정번호를 선택해서 입력해 주세요");
                                                    air.setsNum(sc.next());
                                                    // 일정번호로 일정 출력
                                                    aSql.sSelect(air);
                                                    System.out.println("저장하시겠습니까? 1. 네 2. 아니요");
                                                    System.out.print("선택 : ");
                                                    confirm = sc.nextInt();
                                                    if (confirm == 1) {
                                                        System.out.println("인원 수를 입력해 주세요");
                                                        System.out.print("선택 : ");
                                                        count = sc.nextInt();
                                                        for (int i = 0; i < count; i++) {
                                                            // rNum 생성메소드
                                                            air.setrNum(aSql.genrNum() + 1);
                                                            // 아이디와 일정 번호로 예약 테이블에 입력
                                                            // rTime은 SYSDATE 이용
                                                            aSql.rReg(air);
                                                        }
                                                        System.out.println("돌아오는 일정도 조회하시겠습니까? 1. 네 2. 아니요");
                                                        System.out.print("선택 : ");
                                                        back = sc.nextInt();
                                                        if (back == 1) {
                                                            calender();
                                                            System.out.println("출발일을 입력해 주세요(**-**)");
                                                            System.out.print("현재 5월만 입력 가능합니다 >> ");
                                                            air.setsStart(sc.next());
                                                            // 일정 테이블에서 날짜와 국가,도시 정보로 귀국 일정 출력
                                                            aSql.sBSelect(air, select);
                                                            System.out.println("목록에서 일정번호를 선택해서 입력해 주세요");
                                                            air.setsNum(sc.next());
                                                            // 일정번호로 일정 출력
                                                            aSql.sSelect(air);
                                                            System.out.println("저장하시겠습니까? 1. 네 2. 아니요");
                                                            System.out.print("선택 : ");
                                                            confirm = sc.nextInt();
                                                            if (confirm == 1) {
                                                                System.out.println("인원 수를 입력해 주세요");
                                                                System.out.print("선택 : ");
                                                                count = sc.nextInt();
                                                                for (int i = 0; i < count; i++) {
                                                                    // rNum 생성메소드
                                                                    air.setrNum(aSql.genrNum() + 1);
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
                                            System.out.println("이 달의 추천 여행지");
                                            // 전체 DB 에서 지역별로 구분해서 상위 3개의 여행지를 출력
//                                            air.recommend();
                                            calender();
                                            System.out.println("출발일을 입력해 주세요(**-**)");
                                            System.out.print("현재 5월만 입력 가능합니다 >> ");
                                            air.setsStart(sc.next());
                                            System.out.println("도시명을 입력해 주세요");
                                            System.out.print("선택 : ");
                                            select = sc.next();
                                            // 일정 테이블에서 날짜와 국가,도시 정보로 일정 출력
                                            aSql.sSelect(air, select);
                                            System.out.println("목록에서 일정번호를 선택해서 입력해 주세요");
                                            air.setsNum(sc.next());
                                            // 일정번호로 일정 출력
                                            aSql.sSelect(air);
                                            System.out.println("저장하시겠습니까? 1. 네 2. 아니요");
                                            System.out.print("선택 : ");
                                            confirm = sc.nextInt();
                                            if (confirm == 1) {
                                                System.out.println("인원 수를 입력해 주세요");
                                                System.out.print("선택 : ");
                                                count = sc.nextInt();
                                                for (int i = 0; i < count; i++) {
                                                    // rNum 생성메소드
                                                    air.setrNum(aSql.genrNum() + 1);
                                                    // 아이디와 일정 번호로 예약 테이블에 입력
                                                    // rTime은 SYSDATE 이용
                                                    aSql.rReg(air);
                                                }
                                                System.out.println("돌아오는 일정도 조회하시겠습니까? 1. 네 2. 아니요");
                                                System.out.print("선택 : ");
                                                back = sc.nextInt();
                                                if (back == 1) {
                                                    calender();
                                                    System.out.println("출발일을 입력해 주세요(**-**)");
                                                    System.out.print("현재 5월만 입력 가능합니다 >> ");
                                                    air.setsStart(sc.next());
                                                    // 일정 테이블에서 날짜와 국가,도시 정보로 귀국 일정 출력
                                                    aSql.sBSelect(air, select);
                                                    System.out.println("목록에서 일정번호를 선택해서 입력해 주세요");
                                                    air.setsNum(sc.next());
                                                    // 일정번호로 일정 출력
                                                    aSql.sSelect(air);
                                                    System.out.println("저장하시겠습니까? 1. 네 2. 아니요");
                                                    System.out.print("선택 : ");
                                                    confirm = sc.nextInt();
                                                    if (confirm == 1) {
                                                        System.out.println("인원 수를 입력해 주세요");
                                                        System.out.print("선택 : ");
                                                        count = sc.nextInt();
                                                        for (int i = 0; i < count; i++) {
                                                            // rNum 생성메소드
                                                            air.setrNum(aSql.genrNum() + 1);
                                                            // 아이디와 일정 번호로 예약 테이블에 입력
                                                            // rTime은 SYSDATE 이용
                                                            aSql.rReg(air);
                                                        }
                                                    }
                                                }
                                            }
                                            break;
                                        case 3:
                                            System.out.println();
                                            System.out.println("────────────────────────────────────────────────────────");
                                            System.out.println("1. 여행지 2. 항공사 3. 비행기 4. 예약정보로 검색하기 5. 돌아가기");
                                            System.out.println("────────────────────────────────────────────────────────");
                                            System.out.print("메뉴 선택 : ");
                                            int menu6 = sc.nextInt();
                                            break;
                                        case 4 :
                                            System.out.println("1. 장바구니 2. 결제대기");
                                            System.out.print("선택 : ");
                                            int pay = sc.nextInt();
                                            if (pay == 1) {
                                                System.out.println(" [ 장바구니 ]");
                                                // 멤버 아이디에 해당하는 예약정보중 RCHECK가 0인 내역(장바구니)만 출력
                                                if (aSql.rCheck(air, 0)) {
                                                    aSql.rSelect(air, 0);
                                                    System.out.println("1. 계좌발급 2. 삭제");
                                                    System.out.print("선택 : ");
                                                    int payAccount = sc.nextInt();
                                                    if (payAccount == 1) {
//                                                    // 계좌명 생성 메소드
                                                        if (aSql.rCheck(air, 1)) {
                                                            System.out.println("결재 대기 중인 내역이 있습니다");
                                                        } else {
                                                            //계좌명 생성 메소드
                                                            cl.settName(mem.getmId());
                                                            //계좌 번호 생성 메소드
                                                            cl.settAccount(bSql.bPAccount());
                                                            air.settAccount(cl.gettAccount());

                                                            // 계좌 생성 메소드 : 계좌 생성
                                                            bSql.bPCreate(cl);
                                                            // 입금 금액 생성 및 출력 메소드 : RCHECK가 0인 내역의 합계
                                                            aSql.rTotal(air);
                                                            // PACCOUNT 테이블에 데이터 추가
                                                            aSql.pCreate(air);
                                                            // 계좌 출력 메소드
                                                            bSql.pSelect(cl);
                                                            // RCHECK 0을 1로 변경
                                                            aSql.rUpdate(air, 1);
                                                        }
                                                    } else if (payAccount == 2) {
                                                        System.out.print("예약번호 선택 : ");
                                                        air.setrNum(sc.nextInt());
                                                        // 예약번호 받아서 삭제
                                                        aSql.rDelete(air);

                                                    }
                                                }else {
                                                    System.out.println("예약내역이 없습니다.");
                                                }
                                            } else if (pay == 2) {
                                                // 입금 확인 메소드 :
                                                System.out.println(" [ 결제대기 ]");
                                                if (aSql.rCheck(air, 1)) {
                                                    // 입금이 됐는지 확인
                                                    if(aSql.checkBalance(air)){
                                                        //RCHECK 1을 2로 변경
                                                        aSql.rUpdate(air,1);
                                                    }
                                                }
                                                // 멤버 아이디에 해당하는 예약정보중 RCHECK가 1인 내역(결제대기)만 출력
                                                aSql.rSelect(air, 1);
                                                // 결제 계좌 출력
                                                aSql.pSelect(air);
                                            }
                                            break;

                                        case 5:
                                            boolean run4 = true;
                                            while (run4) {
                                                System.out.println("────────────────────────────────────────────");
                                                System.out.println("1. 정보 조회 2. 정보 수정 3. 예약 내역 4. 돌아가기");
                                                System.out.println("────────────────────────────────────────────");
                                                System.out.print("메뉴 선택 : ");
                                                int menu4 = sc.nextInt();
                                                switch (menu4) {
                                                    case 1:
                                                        sql.mSelect(mem);
                                                        break;
                                                    case 2:
                                                        System.out.println("─────────────────────────────────────────────────");
                                                        System.out.println("1. 패스워드 2. 이름 3. 전화번호 4. 여권번호 5. 생년월일");
                                                        System.out.println("─────────────────────────────────────────────────");
                                                        System.out.print("메뉴 선택 : ");
                                                        int menu5 = sc.nextInt();
                                                        switch (menu5) {
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
                                                            default:
                                                                System.out.println("잘못 입력했습니다");
                                                        }
                                                        break;
                                                    case 3:
                                                        // RCHECK가 1인 내역이 있는지 체크
                                                        if (aSql.rCheck(air, 1)) {
                                                            // 입금이 됐는지 확인
                                                            if(aSql.checkBalance(air)){
                                                                //RCHECK 1을 2로 변경
                                                                aSql.rUpdate(air,1);
                                                            }
                                                        }
                                                        // 멤버 아이디에 해당하는 예약정보중 RCHECK가 2인 내역(결제완료)만 출력
                                                        aSql.rSelect(air, 2);
                                                        break;
                                                    case 4:
                                                        run4 = false;
                                                        break;
                                                    default:
                                                        System.out.println("잘못 입력했습니다");
                                                        break;
                                                }
                                            }
                                            break;
                                        case 6:
                                            run2 = false;
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
                    run = false;
                    sql.conClose();
                    break;
                default:
                    System.out.println("잘못 입력했습니다");
                    break;
            }
        }
    }
}
