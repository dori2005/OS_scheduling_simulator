import java.util.Vector;

abstract class GanttChart {
   public int time, n, quantum;      //스케줄링 실행 시간, 총 프로그램 개수
   protected Program runP;      //현재 실행중인 프로그램을 저장할 변수
   protected Vector<Program> readyQv;   //레디큐를 저장할 벡터
   protected Vector<Program> clearQv;   //완료된 프로그램을 저장하는 벡터
   public GanttChart() {   //모든 필드 초기화
      time = 0;
      runP = null;
      readyQv = new Vector<Program>();
      clearQv = new Vector<Program>();
   }
   abstract protected void pointOfTime(Vector<Program> pv, int totaltime);
}