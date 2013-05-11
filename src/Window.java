import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Gerhart Mende
 * 
 */

//class for the userinterface and layout
public class Window {
	
	JFrame f;
	JScrollPane dnacompl;
	JLabel dna, a1_t, a2_t, a3_t, a4_t, a5_t, a6_t, a7_t;
	JToggleButton a1, a2, a3, a4, a5, a6, a7;
	String temp, raw, dnaseq, result;

	public Window(){
		
//		the main frame für the program
		f = new JFrame();
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		f.setTitle("--- DNA analyses ---");
		f.setBounds(0, 0, d.width, d.height);
		f.setVisible(true);
		JPanel p = new JPanel(); // the main panel in the main frame
		p.setBackground(Color.black);
		p.setLayout(null);
		f.add(p);
		
		//give the buttons a functionality, defined in the class ButtonPressed
		ActionListener action = new ButtonPressed();
		

		//the different buttons to load different dna sequences
		JButton load = new JButton("load example DNA");
		load.setBounds(d.width/2, 10, d.width/6-20, 30);
		load.addActionListener(action);		
		p.add(load);
		
		JButton open = new JButton("load DNA from file");
		open.setBounds(2*d.width/3+5, 10, d.width/6-20, 30);
		open.addActionListener(action);		
		p.add(open);
		
		JButton clear = new JButton("clear DNA");
		clear.setBounds(5*d.width/6+5, 10, d.width/6-20, 30);
		clear.addActionListener(action);		
		p.add(clear);
		
		JButton exit = new JButton("EXIT");
		exit.setBounds(50, d.height-100, 100, 30);
		exit.addActionListener(action);		
		p.add(exit);
		

		// the textlabels to show the dna dequence
		// text box for the DNA sequence
		dna = new JLabel();		
		dna.setVerticalAlignment(JLabel.TOP);	
		dna.setFont(new Font("sansserif", Font.BOLD, 15));
		
		// to have a scoll funtion for the DNA sequence
		JScrollPane dna_ = new JScrollPane(dna,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);		
		p.add(dna_);
		
		dna_.setBounds(d.width/2, 50, d.width/2-20, d.height/2-70);
		
		// different buttons for the different analyses and text-labels for the information about the analyses 
		a1 = new JToggleButton("tyberius syndrome");
		a1.setBounds(50,50,220,30);
		a1.addActionListener(action);
		a1.setEnabled(false);
		p.add(a1);
		
		a1_t = new JLabel();
		a1_t.setBounds(50,85,220,30);
		a1_t.setForeground(Color.white);
		p.add(a1_t);
		
		a2 = new JToggleButton("brown eyes");
		a2.setBounds(50,200,220,30);
		a2.addActionListener(action);	
		a2.setEnabled(false);
		p.add(a2);
		
		a2_t = new JLabel();
		a2_t.setBounds(50,235,220,45);
		a2_t.setForeground(Color.white);
		p.add(a2_t);
		
		a3 = new JToggleButton("occurrence of 'CTAG'");
		a3.setBounds(50,350,220,30);
		a3.addActionListener(action);
		a3.setEnabled(false);
		p.add(a3);
		
		a3_t = new JLabel();
		a3_t.setBounds(50,385,220,30);
		a3_t.setForeground(Color.white);
		p.add(a3_t);
		
		a4 = new JToggleButton("count nucleobases");
		a4.setBounds(300,50,220,30);
		a4.addActionListener(action);		
		a4.setEnabled(false);
		p.add(a4);
		
		a4_t = new JLabel();
		a4_t.setBounds(300,85,220,80);
		a4_t.setForeground(Color.white);
		p.add(a4_t);
		
		a5 = new JToggleButton("complementary");
		a5.setBounds(300,600,220,30);
		a5.addActionListener(action);
		a5.setEnabled(false);
		p.add(a5);
		
		// the second box for the complementary sequence.
		a5_t = new JLabel();
		a5_t.setVerticalAlignment(JLabel.TOP);	
		a5_t.setVisible(true);
		a5_t.setFont(new Font("sansserif", Font.BOLD, 15));
		dnacompl = new JScrollPane(a5_t,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		dnacompl.setBounds(d.width/2, d.height/2, d.width/2-20, d.height/2-70);
		dnacompl.setVisible(false);
		p.add(dnacompl);
		
		a6 = new JToggleButton("purines & pyrimidines");
		a6.setBounds(300,200,220,30);
		a6.addActionListener(action);		
		a6.setEnabled(false);
		p.add(a6);
		
		a6_t = new JLabel();
		a6_t.setBounds(300,235,220,60);
		a6_t.setForeground(Color.white);
		p.add(a6_t);
		
		a7 = new JToggleButton("Frømingen's dischrypsia");
		a7.setBounds(300,350,220,30);
		a7.addActionListener(action);
		a7.setEnabled(false);
		p.add(a7);
		
		a7_t = new JLabel();
		a7_t.setBounds(300,385,220,45);
		a7_t.setForeground(Color.white);
		p.add(a7_t);
		
		f.repaint();
	}
}
