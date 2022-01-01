import java.awt.Color;
import java.io.*;
import java.util.*;

public class SchedulingEx {
    //메인 함수에서는 파일에서 받아온 프로그램 정보(프로세스 ID, 도착시간, 서비스시간, 우선순위, 시간할당량)을 받아와서
    //도착시간 순으로 정렬한 뒤, 알맞은 스케줄링 클래스의 객체를 생성하여, 스케줄링을 시뮬레이션함.
    public static void main(String[] args) {
        new MainGUI();
    }
    //실시간 GUI 화면은 주석 처리된 아래 메인 함수를 사용하면 볼 수 있지만, 위 MainGUI구현 때문에 각 스케줄링 Frame에는
    //DeafualtCloseOperation설정을 해주지 않았기 때문에, Java창을 닫아도 프로그램이 종료되지 않을 수 있습니다.
   /*public static void main(String[] args) {
	   Vector<Program> pv = new Vector<Program>();
	   Vector<Program> tpv = new Vector<Program>();
	   File file = new File("./data.txt");   //project파일 안에있는 data.txt파일
	   Scanner scanner = null;
	   try {
		   scanner = new Scanner(file);
	   } catch (FileNotFoundException e) {
		   e.printStackTrace();
	   }

	   int n = scanner.nextInt();

	   for(int i = 0; i < n; i++) {
		   Program temp = new Program();
		   Program temp2 = new Program();
		   temp.programName = scanner.next();
		   temp.inTime = scanner.nextInt();
		   temp.leftTime = scanner.nextInt();
		   temp.priority = scanner.nextInt();
		   temp.responTime = -1;
		   Random rand = new Random();
		   float r = rand.nextFloat() / 2f + (float)0.5;
		   float g = rand.nextFloat() / 2f + (float)0.5;
		   float b = rand.nextFloat() / 2f + (float)0.5;
		   temp.color = new Color(r, g, b);
		   temp2.programName = temp.programName;
		   temp2.inTime = temp.inTime;
		   temp2.leftTime = temp.leftTime;
		   temp2.priority = temp.priority;
		   pv.add(temp);
		   tpv.add(temp2);
	   }
	   int tq = scanner.nextInt();
	   scanner.close();

	   scanner = new Scanner(System.in);

	   System.out.print("실행할 스케줄링 알고리즘을 입력하세요");
	   String scheduler = scanner.next();
	   System.out.print("스케줄링 알고리즘의 진행 시간을 입력하세요(단위 ms)");
	   int q = scanner.nextInt();
	   int totaltime;
	   switch(scheduler) {
	      case "FCFS":
	    	  FCFS fcfs = new FCFS(n, q);
	    	  totaltime = fcfs.getTotalTime(tpv);
	    	  fcfs.pointOfTime(pv, totaltime);
	    	  break;
	      case "SJF":
	    	  SJF sjf = new SJF(n, q);
	    	  totaltime = sjf.getTotalTime(tpv);
	    	  sjf.pointOfTime(pv, totaltime);
	    	  break;
	      case "SRT":
	    	  SRT srt = new SRT(n, q);
	    	  totaltime = srt.getTotalTime(tpv);
	    	  srt.pointOfTime(pv, totaltime);
	    	  break;
	      case "NPP":
	    	  NPP npp = new NPP(n, q);
	    	  totaltime = npp.getTotalTime(tpv);
	    	  npp.pointOfTime(pv, totaltime);
	    	  break;
	      case "PP":
	    	  PP pp = new PP(n, q);
	    	  totaltime = pp.getTotalTime(tpv);
	    	  pp.pointOfTime(pv, totaltime);
	    	  break;
	      case "RR":
	    	  RR rr = new RR(n, tq, q);
	    	  totaltime = rr.getTotalTime(tpv);
	    	  rr.pointOfTime(pv, totaltime);
	    	  break;
	      case "HRN":
	    	  HRN hrn = new HRN(n, q);
	    	  totaltime = hrn.getTotalTime(tpv);
	    	  hrn.pointOfTime(pv, totaltime);
	      }	
	   }*/
}