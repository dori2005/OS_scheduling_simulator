import java.util.Vector;

class RR extends GanttChart {
	int timeQuantum, lQ;
	public RR(int n, int timeQuantum, int q) {   //총 프로그램 수 저장
		this.n = n;
		this.timeQuantum = timeQuantum;
		this.lQ = 0;
		this.quantum = q;
	}
	protected void pointOfTime(Vector<Program> pv, int totaltime) {
		readyQv.removeAllElements();
		time = 0;
		runP = null;
		//간트차트를 그려주는 GUI클래스 (실행중인 프로그램, 레디큐 벡터, 완료된 프로그램 벡터, 총 프로그램수)를 인수로 받음
		DrawGantt display = new DrawGantt(n, totaltime, "Round Robin");
		//도착 전 프로그램 큐와 레디 큐가 모두 비었고, 실행중인 프로그램이 없음(프로그램이 모두 완료)면, 반복 종료
		while(!pv.isEmpty() || !readyQv.isEmpty() || runP!=null) {
			for(int i = 0; i < pv.size(); i++) {   //현 시간에 도착할 프로그램을 레디큐에 넣음
				if(pv.get(i).inTime <= time) {
					readyQv.add(pv.remove(i));
					i--;
				}
			}
			try {   //1초를 쉬고 다음코드로 넘어감
				Thread.sleep(quantum);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}


			System.out.println(""+time+(runP==null));
			if(runP == null) {      //실행중인 프로그램이 없으면
				if(readyQv.isEmpty()) {      //레디큐가 비었음, 하지만 while문이 작동했으므로, 도착 전 프로그램이 존재
					display.upDateGantt(runP, readyQv, clearQv, time);   //다음 반복으로 넘어가기전 화면 업데이트
					time++; continue;      //단순히 진행 시간만 늘려주고, 다음 반복으로 넘어감
				}
				runP = readyQv.remove(0);   //레디큐에서 대기중인 프로그램을 받아옴
				lQ = timeQuantum;
				if(runP.responTime == -1)
					runP.responTime = time - runP.inTime;   //그후 응답 시간을 구함(도착 시간 - 첫 실행 시간)
			}
			else {
				runP.leftTime--;   //시간이 1(초) 지났으므로, 실행중인 프로그램의 남은 실행시간을 1(초)줄임
				lQ--;
				if(lQ == 0) {
					if(runP.leftTime == 0) {
						runP.returnTime = time - runP.inTime ;   //반환시간을 구함
						clearQv.add(runP);
						runP = null;
						if(readyQv.isEmpty()) {
							display.upDateGantt(runP, readyQv, clearQv, time);
							time++; continue;
						}
					}
					else
						readyQv.add(runP);
					runP = readyQv.remove(0);   //레디큐에서 대기중인 프로그램을 받아옴
					lQ = timeQuantum;
					if(runP.responTime == -1)
						runP.responTime = time - runP.inTime;
				}
			}
			if(runP.leftTime == 0) {   //실행중인 프로그램이 종료되면,
				runP.returnTime = time - runP.inTime ;   //반환시간을 구함
				clearQv.add(runP);
				runP = null;
				if(readyQv.isEmpty()) {
					display.upDateGantt(runP, readyQv, clearQv, time);
					time++; continue;
				}
				runP = readyQv.remove(0);
				lQ = timeQuantum;
				if(runP.responTime == -1)
					runP.responTime = time - runP.inTime;   //그후 응답 시간을 구함(도착 시간 - 첫 실행 시간)
			}
			display.upDateGantt(runP, readyQv, clearQv, time);    //시간을 조정하기 전에 현 상황의 정보를 화면에 업데이트
			time++;      //시간을 1(초) 증가시킴
			//래디큐에 대기중인 프로그램의 대기시간을 증가시킴
			for(int i = 0; i < readyQv.size(); i++)
				readyQv.get(i).waitTime++;
		}
	}
	public int getTotalTime(Vector<Program> pv) {
		while(!pv.isEmpty() || !readyQv.isEmpty() || runP!=null) {
			for(int i = 0; i < pv.size(); i++) {   //현 시간에 도착할 프로그램을 레디큐에 넣음
				if(pv.get(i).inTime <= time) {
					readyQv.add(pv.remove(i));
					i--;
				}
			}

			if(runP == null) {      //실행중인 프로그램이 없으면
				if(readyQv.isEmpty()) {      //레디큐가 비었음, 하지만 while문이 작동했으므로, 도착 전 프로그램이 존재
					time++; continue;      //단순히 진행 시간만 늘려주고, 다음 반복으로 넘어감
				}
				runP = readyQv.remove(0);   //레디큐에서 대기중인 프로그램을 받아옴
				lQ = timeQuantum;
			}
			else {
				runP.leftTime--;   //시간이 1(초) 지났으므로, 실행중인 프로그램의 남은 실행시간을 1(초)줄임
				lQ--;
				if(lQ == 0) {
					if(runP.leftTime == 0) {
						runP = null;
						if(readyQv.isEmpty()) {
							time++; continue;
						}
					}
					else
						readyQv.add(runP);
					runP = readyQv.remove(0);   //레디큐에서 대기중인 프로그램을 받아옴
					lQ = timeQuantum;
				}
			}
			if(runP.leftTime == 0) {   //실행중인 프로그램이 종료되면,
				runP = null;
				if(readyQv.isEmpty()) {
					time++; continue;
				}
				runP = readyQv.remove(0);
				lQ = timeQuantum;
			}
			time++;      //시간을 1(초) 증가시킴\
		}
		return time-1;
	}
}