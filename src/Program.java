import java.awt.Color;

public class Program {
   //도착 시간, 남은 실행(서비스) 시간, 대기 시간, 반환 시간, 응답 시간
   public int inTime, leftTime, waitTime, returnTime, responTime;
   public String programName;   //프로그램 명(ID)
   public double priority;      //우선순위 : 숫자가 작을수록 우선순위up
   public Color color;
}
