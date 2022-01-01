import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class MainGUI extends JFrame{
	String []scheduler = {"FCFS", "SJF", "SRT",
			"NPP", "PP", "RR", "HRN"};
	int index = 0,indexL = -1, totaltime, n, tq, q;
    Vector<Program> pv = new Vector<Program>();
	Vector<String> lv = new Vector<String>();
	JList<String> list;
	JTextField textField, textField2;
	public MainGUI() {
		
		setTitle("Main GUI");
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(null);
		
		list = new JList<String>();
		list.setBounds(10, 10, 332, 182);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				JList<String> t = (JList<String>)e.getSource();
				indexL = t.getSelectedIndex();
			}
		});
		c.add(list);
		
		JButton button = new JButton("데이터 찾기");
		button.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		button.setBounds(348, 10, 76, 23);
		button.addActionListener(new FopenButtonAction());
		c.add(button);
		
		JButton button_1 = new JButton("추가");
		button_1.setBounds(348, 169, 76, 23);
		button_1.addActionListener(new DeleteAction());
		c.add(button_1);
		
		JButton button_2 = new JButton("삭제");
		button_2.setBounds(348, 140, 76, 23);
		button_2.addActionListener(new CreateAction());
		c.add(button_2);

		textField = new JTextField();
		textField.setBounds(348, 113, 74, 23);
		textField.addActionListener(new QuantumTextAction());
		textField.setColumns(10);
		c.add(textField);
		
		JLabel lblNewLabel = new JLabel("Time Quantum");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 10));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(348, 99, 78, 15);
		c.add(lblNewLabel);
		
		textField2 = new JTextField();
		textField2.setBounds(348, 75, 74, 23);
		textField2.addActionListener(new QuantumAction());
		textField2.setColumns(10);
		c.add(textField2);
		
		JLabel lblNewLabel2 = new JLabel("시뮬레이터 시간");
		lblNewLabel2.setFont(new Font("맑은 고딕", Font.BOLD, 10));
		lblNewLabel2.setForeground(Color.BLACK);
		lblNewLabel2.setBounds(348, 61, 76, 15);
		c.add(lblNewLabel2);
		
		Panel panel = new Panel();
		panel.setBounds(10, 198, 414, 53);
		c.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("실행");
		btnNewButton.setBounds(282, 10, 97, 23);
		btnNewButton.addActionListener(new ExeButtonAction());
		panel.add(btnNewButton);
		
		JComboBox<String> comboBox = new JComboBox<String>(scheduler);
		comboBox.setBounds(52, 11, 218, 22);
		comboBox.addActionListener(new ComboAction());
		panel.add(comboBox);

		setVisible(true);
	}
	class FopenButtonAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File(System.getProperty("user.home")+"//"+"Desktop"));
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File", "txt");
			chooser.setFileFilter(filter);
			int ret = chooser.showOpenDialog(null);
			if(ret != JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.", "확인", JOptionPane.WARNING_MESSAGE);
				return;
			}
			String filePath = chooser.getSelectedFile().getPath();
			System.out.println(filePath);
			File file = new File(filePath);  //project파일 안에있는 data.txt파일
			Scanner scanner = null;
			try {
				scanner = new Scanner(file);
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			}
			n = scanner.nextInt();

			for(int i = 0; i < n; i++) {
				Program temp = new Program();
				temp.programName = scanner.next();
				temp.inTime = scanner.nextInt();
				temp.leftTime = scanner.nextInt();
				temp.priority = scanner.nextDouble();
				temp.responTime = -1;
				Random rand = new Random();
				float r = rand.nextFloat() / 2f + (float)0.5;
				float g = rand.nextFloat() / 2f + (float)0.5;
				float b = rand.nextFloat() / 2f + (float)0.5;
				temp.color = new Color(r, g, b);
				pv.add(temp);
			}
			for(int i = 0; i < n; i++) 
				lv.add(pv.get(i).programName +" "+ pv.get(i).inTime +" "+ pv.get(i).leftTime +" "+ pv.get(i).priority);
			list.setListData(lv);
			tq = scanner.nextInt();
			textField.setText(""+tq);
			scanner.close();
		}
	}
	class ComboAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JComboBox<String> cb = (JComboBox<String>)e.getSource();
			index = cb.getSelectedIndex();
		}
	}
	class QuantumTextAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JTextField t = (JTextField)e.getSource();
			int len = t.getText().length(), cip = 1;
			tq = 0;
			for(int i = 0; i < len; i++) {
				char c = t.getText().charAt(len-i-1);
				tq += (c-48)*cip;
				cip *= 10;
			}
			System.out.println(tq);
		}
	}
	class QuantumAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JTextField t = (JTextField)e.getSource();
			int len = t.getText().length(), cip = 1;
			q = 0;
			for(int i = 0; i < len; i++) {
				char c = t.getText().charAt(len-i-1);
				q += (c-48)*cip;
				cip *= 10;
			}
			System.out.println(q);
		}
	}
	class DeleteAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(indexL >= 0 || indexL < lv.size()) {
				lv.remove(indexL);
				pv.remove(indexL);
				n--;
				list.setListData(lv);
			}
		}
	}
	class CreateAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {

		}
	}
	class ProcessTextAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JTextField t = (JTextField)e.getSource();
			
		}
	}
	class ExeButtonAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
	        Vector<Program> mpv = new Vector<Program>(n);
	        Vector<Program> tpv = new Vector<Program>(n);
	        for(int i = 0; i < n; i++) {
				Program temp = new Program();
				temp.programName = pv.get(i).programName;
				temp.inTime = pv.get(i).inTime;
				temp.leftTime = pv.get(i).leftTime; 
				temp.priority = pv.get(i).priority;
				temp.responTime = -1;
				temp.color = pv.get(i).color;
				mpv.add(i, temp);

				Program temp2 = new Program();
				temp2.programName = pv.get(i).programName;
				temp2.inTime = pv.get(i).inTime;
				temp2.leftTime = pv.get(i).leftTime; 
				temp2.priority = pv.get(i).priority;
				tpv.add(i, temp2);
			}
			if (n != 0) {
				switch (scheduler[index]) {
					case "FCFS":
						FCFS fcfs = new FCFS(n, q);
						totaltime = fcfs.getTotalTime(tpv);
						fcfs.pointOfTime(mpv, totaltime);
						break;
					case "SJF":
						SJF sjf = new SJF(n, q);
						totaltime = sjf.getTotalTime(tpv);
						sjf.pointOfTime(mpv, totaltime);
						break;
					case "SRT":
						SRT srt = new SRT(n, q);
						totaltime = srt.getTotalTime(tpv);
						srt.pointOfTime(mpv, totaltime);
						break;
					case "NPP":
						NPP npp = new NPP(n, q);
						totaltime = npp.getTotalTime(tpv);
						npp.pointOfTime(mpv, totaltime);
						break;
					case "PP":
						PP pp = new PP(n, q);
						totaltime = pp.getTotalTime(tpv);
						pp.pointOfTime(mpv, totaltime);
						break;
					case "RR":
						RR rr = new RR(n, tq, q);
						totaltime = rr.getTotalTime(tpv);
						rr.pointOfTime(mpv, totaltime);
						break;
					case "HRN":
						HRN hrn = new HRN(n, q);
						totaltime = hrn.getTotalTime(tpv);
						hrn.pointOfTime(mpv, totaltime);
				}
			}
		}
	}
}