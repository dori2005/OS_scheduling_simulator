import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.*;

class DrawGantt extends JFrame {   //그저 GUI 관심 ㄴㄴ
   private JPanel contentPane;
   private JLabel label_in[][];
   private JLabel ganttLabel[][];
   private JLabel timeLabel;
   private int n, totaltime;
   String lastpro;
   public DrawGantt(int n, int totaltime, String gName) {
      this.n = n;
      this.totaltime = totaltime;
      lastpro = null;
      
      setTitle(gName + " - Scheduling");
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setBackground(Color.WHITE);
      contentPane.setLayout(null);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      
      JPanel panel = new JPanel();
      panel.setBackground(Color.GRAY);
      panel.setBounds(12, 133, 368, 17*(n+2)+2);
      contentPane.add(panel);
      GridBagLayout gbl_panel = new GridBagLayout();
      gbl_panel.columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0, 0};
      gbl_panel.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0};
      gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
      gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
      panel.setLayout(gbl_panel);
      
      //이 아래로는 표를 그리기 위한 Label
      JLabel label = new JLabel("프로그램 명");
      label.setHorizontalAlignment(SwingConstants.CENTER);
      label.setFont(new Font("맑은 고딕", Font.BOLD, 10));
      label.setBackground(Color.white);
      label.setOpaque(true);
      GridBagConstraints gbc_label = new GridBagConstraints();
      gbc_label.fill = GridBagConstraints.BOTH;
      gbc_label.insets = new Insets(0, 0, 2, 2);
      gbc_label.gridx = 0;
      gbc_label.gridy = 0;
      panel.add(label, gbc_label);
      
      JLabel label_1 = new JLabel("도착 시간");
      label_1.setHorizontalAlignment(SwingConstants.CENTER);
      label_1.setFont(new Font("맑은 고딕", Font.BOLD, 10));
      label_1.setOpaque(true);
      label_1.setBackground(Color.WHITE);
      GridBagConstraints gbc_label_1 = new GridBagConstraints();
      gbc_label_1.fill = GridBagConstraints.BOTH;
      gbc_label_1.insets = new Insets(0, 0, 2, 2);
      gbc_label_1.gridx = 1;
      gbc_label_1.gridy = 0;
      panel.add(label_1, gbc_label_1);
      
      JLabel label_2 = new JLabel("서비스 시간");
      label_2.setHorizontalAlignment(SwingConstants.CENTER);
      label_2.setFont(new Font("맑은 고딕", Font.BOLD, 10));
      label_2.setOpaque(true);
      label_2.setBackground(Color.WHITE);
      GridBagConstraints gbc_label_2 = new GridBagConstraints();
      gbc_label_2.fill = GridBagConstraints.BOTH;
      gbc_label_2.insets = new Insets(0, 0, 2, 2);
      gbc_label_2.gridx = 2;
      gbc_label_2.gridy = 0;
      panel.add(label_2, gbc_label_2);
      
      JLabel label_3 = new JLabel("우선 순위");
      label_3.setHorizontalAlignment(SwingConstants.CENTER);
      label_3.setFont(new Font("맑은 고딕", Font.BOLD, 10));
      label_3.setOpaque(true);
      label_3.setBackground(Color.WHITE);
      GridBagConstraints gbc_label_3 = new GridBagConstraints();
      gbc_label_3.fill = GridBagConstraints.BOTH;
      gbc_label_3.insets = new Insets(0, 0, 2, 2);
      gbc_label_3.gridx = 3;
      gbc_label_3.gridy = 0;
      panel.add(label_3, gbc_label_3);
      
      JLabel label_4 = new JLabel("대기 시간");
      label_4.setHorizontalAlignment(SwingConstants.CENTER);
      label_4.setFont(new Font("맑은 고딕", Font.BOLD, 10));
      label_4.setOpaque(true);
      label_4.setBackground(Color.WHITE);
      GridBagConstraints gbc_label_4 = new GridBagConstraints();
      gbc_label_4.fill = GridBagConstraints.BOTH;
      gbc_label_4.insets = new Insets(0, 0, 2, 2);
      gbc_label_4.gridx = 4;
      gbc_label_4.gridy = 0;
      panel.add(label_4, gbc_label_4);
      
      JLabel label_5 = new JLabel("반환 시간");
      label_5.setHorizontalAlignment(SwingConstants.CENTER);
      label_5.setFont(new Font("맑은 고딕", Font.BOLD, 10));
      label_5.setOpaque(true);
      label_5.setBackground(Color.WHITE);
      GridBagConstraints gbc_label_5 = new GridBagConstraints();
      gbc_label_5.fill = GridBagConstraints.BOTH;
      gbc_label_5.insets = new Insets(0, 0, 2, 2);
      gbc_label_5.gridx = 5;
      gbc_label_5.gridy = 0;
      panel.add(label_5, gbc_label_5);
      
      JLabel label_6 = new JLabel("응답 시간");
      label_6.setHorizontalAlignment(SwingConstants.CENTER);
      label_6.setFont(new Font("맑은 고딕", Font.BOLD, 10));
      label_6.setOpaque(true);
      label_6.setBackground(Color.WHITE);
      GridBagConstraints gbc_label_6 = new GridBagConstraints();
      gbc_label_6.fill = GridBagConstraints.BOTH;
      gbc_label_6.insets = new Insets(0, 0, 2, 2);
      gbc_label_6.gridx = 6;
      gbc_label_6.gridy = 0;
      panel.add(label_6, gbc_label_6);
      
      JLabel label_7 = new JLabel("색상");
      label_7.setHorizontalAlignment(SwingConstants.CENTER);
      label_7.setFont(new Font("맑은 고딕", Font.BOLD, 10));
      label_7.setOpaque(true);
      label_7.setBackground(Color.WHITE);
      GridBagConstraints gbc_label_7 = new GridBagConstraints();
      gbc_label_7.fill = GridBagConstraints.BOTH;
      gbc_label_7.insets = new Insets(0, 0, 2, 0);
      gbc_label_7.gridx = 7;
      gbc_label_7.gridy = 0;
      panel.add(label_7, gbc_label_7);

      //위로는 카테고리 아래로는 표 내용
      
      label_in = new JLabel [n+1][8];
      
      for(int i = 0; i < n+1; i++) {
         for(int j = 0; j < 8; j++) {
            label_in[i][j] = new JLabel(" ");
            label_in[i][j].setHorizontalAlignment(SwingConstants.CENTER);
            label_in[i][j].setFont(new Font("맑은 고딕", Font.PLAIN, 10));
            label_in[i][j].setOpaque(true);
            label_in[i][j].setBackground(Color.WHITE);
            GridBagConstraints gbc_label_in = new GridBagConstraints();
            gbc_label_in.fill = GridBagConstraints.BOTH;
            gbc_label_in.insets = new Insets(0, 0, (i==n)?0:2, (j==7)?0:2);
            gbc_label_in.gridx = j;
            gbc_label_in.gridy = i+1;
            panel.add(label_in[i][j], gbc_label_in);
         }
      }
      label_in[n][0].setText("평균");
      label_in[n][0].setFont(new Font("맑은 고딕", Font.BOLD, 10));
      
      timeLabel = new JLabel(0 + "  초");
      timeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
      timeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 24));
      timeLabel.setBounds(12, 76, 120, 60);
      contentPane.add(timeLabel);
      
      
      ganttLabel = new JLabel[2][totaltime+1];
      for(int l = 0; l < 2; l++) {
    	  for(int i = 0; i <= totaltime; i++) {
    		  ganttLabel[l][i] = new JLabel("");
    		  ganttLabel[l][i].setHorizontalAlignment(SwingConstants.CENTER);
    		  ganttLabel[l][i].setBounds(12+(700/totaltime)*i-(350/totaltime)*l, 38+(15*l), 700/totaltime, 20);
    		  ganttLabel[l][i].setFont(new Font("맑은 고딕", Font.PLAIN, 6));
    		  contentPane.add(ganttLabel[l][i]); 
    	  }	
      }
      
      setBounds(100, 100, 750, 400);
      setVisible(true);
   }
   public void upDateGantt(Program runP, Vector<Program> readyQv, Vector<Program> clearQv, int time) {
      int j = 0;
      int cqn = clearQv.size(), rqn = readyQv.size();
      
      int sumWT = 0, sumRT = 0, sumRST = 0;
      
      ganttLabel[1][time].setText(""+time);
      if(runP != null) {   //표 맨위 실행중인 프로그램 출력
    	  label_in[0][0].setText(runP.programName+"*");
    	  label_in[0][1].setText(""+runP.inTime);
    	  label_in[0][2].setText(""+runP.leftTime);
          double p = ((int)(runP.priority*100))/100.0;
    	  label_in[0][3].setText(""+p);
    	  label_in[0][4].setText(""+runP.waitTime);
    	  label_in[0][5].setText(""+runP.returnTime);
    	  label_in[0][6].setText(""+runP.responTime);
    	  label_in[0][7].setBackground(runP.color);
    	  label_in[0][7].setOpaque(true);
    	  if(!(lastpro == runP.programName))
    		  ganttLabel[0][time].setText(runP.programName);
    	  ganttLabel[0][time].setBackground(runP.color);
    	  ganttLabel[0][time].setOpaque(true);
    	  lastpro = runP.programName;
    	  sumWT += runP.waitTime;   //watingtime 합
    	  sumRT += runP.returnTime;   //runtime 합
    	  sumRST += runP.responTime;   //respontime 합
    	  j++;   //건너뛸 label 줄수
      }
      else if(time != totaltime){
    	  lastpro = null;
    	  ganttLabel[0][time].setBackground(Color.gray);
    	  ganttLabel[0][time].setOpaque(true);
      }
      for(int i = 0; i < rqn; i++) {
         label_in[i+j][0].setText(readyQv.get(i).programName);
         label_in[i+j][1].setText(""+readyQv.get(i).inTime);
         label_in[i+j][2].setText(""+readyQv.get(i).leftTime);
         double p = ((int)(readyQv.get(i).priority*100))/100.0;
         label_in[i+j][3].setText(""+p);
         label_in[i+j][4].setText(""+readyQv.get(i).waitTime);
         label_in[i+j][5].setText(""+readyQv.get(i).returnTime);
         label_in[i+j][6].setText(""+((readyQv.get(i).responTime==-1)?0:readyQv.get(i).responTime));
         label_in[i+j][7].setBackground(readyQv.get(i).color);
         label_in[i+j][7].setOpaque(true);
         sumWT += readyQv.get(i).waitTime;
         sumRT += readyQv.get(i).returnTime;
         sumRST += (readyQv.get(i).responTime==-1)?0:readyQv.get(i).responTime;
      }
      for(int i = 0; i < cqn; i++) {   //완료된 프로그램 정보 출력
         label_in[rqn+i+j][0].setText("-"+clearQv.get(i).programName);
         label_in[rqn+i+j][1].setText(""+clearQv.get(i).inTime);
         label_in[rqn+i+j][2].setText(""+clearQv.get(i).leftTime);
         double p = ((int)(clearQv.get(i).priority*100))/100.0;
         label_in[rqn+i+j][3].setText(""+p);
         label_in[rqn+i+j][4].setText(""+clearQv.get(i).waitTime);
         label_in[rqn+i+j][5].setText(""+clearQv.get(i).returnTime);
         label_in[rqn+i+j][6].setText(""+clearQv.get(i).responTime);
         label_in[rqn+i+j][7].setBackground(clearQv.get(i).color);
         label_in[rqn+i+j][7].setOpaque(true);
         sumWT += clearQv.get(i).waitTime;
         sumRT += clearQv.get(i).returnTime;
         sumRST += clearQv.get(i).responTime;
      }
      label_in[n][4].setText(""+(int)(10*sumWT/(double)n)/10.0);
      label_in[n][5].setText(""+(int)(10*sumRT/(double)n)/10.0);
      label_in[n][6].setText(""+(int)(10*sumRST/(double)n)/10.0);

      timeLabel.setText(time + "  초");
      System.out.println(time);

      this.revalidate();
   }
}