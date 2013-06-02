import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * 
 * @author Gerhart Mende
 * 
 */

//class for the userinterface and layout
public class Window {
	
	JFrame f;
	JScrollPane dnacompl;
	JLabel dna, a1_t, a2_t, a3_t, a4_t, a5_t, a6_t, a7_t, a9_t;
	JToggleButton a1, a2, a3, a4, a5, a6, a7, a9;
	JButton a8;
	JTextField a3_i, a9_i;
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
		a2.setBounds(50,180,220,30);
		a2.addActionListener(action);	
		a2.setEnabled(false);
		p.add(a2);
		
		a2_t = new JLabel();
		a2_t.setBounds(50,215,220,45);
		a2_t.setForeground(Color.white);
		p.add(a2_t);
		
		a3 = new JToggleButton("first occurrence of:");
		a3.setHorizontalAlignment(SwingConstants.RIGHT);
		a3.setBounds(50,440,220,30);
		a3.addActionListener(action);
		a3.setEnabled(false);
		p.add(a3);
		
		a3_t = new JLabel();
		a3_t.setBounds(50,475,470,30);
		a3_t.setForeground(Color.white);
		p.add(a3_t);
		
		a3_i = new JTextField("ctag");
		a3_i.setBounds(300,440,220,30);
		a3_i.setVisible(true);
		a3_i.setFont(new Font("sansserif", Font.BOLD, 15));
		a3_i.addKeyListener(new KeyListener() {
			char c;
			int kc;
			int cp;
			public void keyTyped(KeyEvent e) {	}
			public void keyReleased(KeyEvent e) {
				c = e.getKeyChar();
				kc = e.getKeyCode();
				if (!(c=='c' || c=='C' || c=='g' || c=='G' || c=='a' || c=='A' || c=='t' || c=='T' || c<32
						|| kc==KeyEvent.VK_HOME
						|| kc==KeyEvent.VK_END
						|| kc==KeyEvent.VK_DELETE
						|| kc==KeyEvent.VK_LEFT
						|| kc==KeyEvent.VK_RIGHT
						|| kc==KeyEvent.VK_UP
						|| kc==KeyEvent.VK_DOWN)){
					cp = a3_i.getCaretPosition();
					a3_i.setText(a3_i.getText().replaceAll(""+c, ""));
					a3_i.setCaretPosition(cp-1);
				}
			}
			public void keyPressed(KeyEvent e) {}
		});
		p.add(a3_i);
		
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
		a5.setBounds(300,310,220,30);
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
		a6.setBounds(300,180,220,30);
		a6.addActionListener(action);		
		a6.setEnabled(false);
		p.add(a6);
		
		a6_t = new JLabel();
		a6_t.setBounds(300,215,220,60);
		a6_t.setForeground(Color.white);
		p.add(a6_t);
		
		a7 = new JToggleButton("Frømingen's dischrypsia");
		a7.setBounds(50,310,220,30);
		a7.addActionListener(action);
		a7.setEnabled(false);
		p.add(a7);
		
		a7_t = new JLabel();
		a7_t.setBounds(50,345,220,45);
		a7_t.setForeground(Color.white);
		p.add(a7_t);
		
		a8 = new JButton("switch");
		a8.setBounds(d.width/2-80,d.height/2,80,30);
		a8.addActionListener(action);
		a8.setVisible(false);
		p.add(a8);
		
		a9 = new JToggleButton("all occurrence of:");
		a9.setHorizontalAlignment(SwingConstants.RIGHT);
		a9.setBounds(50,590,220,30);
		a9.addActionListener(action);
		a9.setEnabled(false);
		p.add(a9);
		
		a9_t = new JLabel();
		a9_t.setBounds(50,625,470,30);
		a9_t.setForeground(Color.white);
		p.add(a9_t);
		
		a9_i = new JTextField("");
		a9_i.setBounds(300,590,220,30);
		a9_i.setVisible(true);
		a9_i.setFont(new Font("sansserif", Font.BOLD, 15));
		a9_i.addKeyListener(new KeyListener() {
			char c;
			int kc;
			int cp;
			public void keyTyped(KeyEvent e) {	}
			public void keyReleased(KeyEvent e) {
				c = e.getKeyChar();
				kc = e.getKeyCode();
				if (!(c=='c' || c=='C' || c=='g' || c=='G' || c=='a' || c=='A' || c=='t' || c=='T' || c<32
						|| kc==KeyEvent.VK_HOME
						|| kc==KeyEvent.VK_END
						|| kc==KeyEvent.VK_DELETE
						|| kc==KeyEvent.VK_LEFT
						|| kc==KeyEvent.VK_RIGHT
						|| kc==KeyEvent.VK_UP
						|| kc==KeyEvent.VK_DOWN)){
					cp = a9_i.getCaretPosition();
					a9_i.setText(a9_i.getText().replaceAll(""+c, ""));
					a9_i.setCaretPosition(cp-1);
				}
			}
			public void keyPressed(KeyEvent e) {}
		});
		p.add(a9_i);
		
		f.repaint();
	}
}