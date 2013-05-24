import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * 
 * @author Gerhart Mende
 * 
 */

//  class to handle the button click actions
class ButtonPressed implements ActionListener{
	private AnalyseI analyse = new Analyse();
	
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		String text = "";
		int first_occurrence_of_ctag;
		int count[] = new int[4];
		int c;
		int count_ty;
		int count_be;
		
		DNA_analyse.w.result = DNA_analyse.w.dnaseq; //reset the view to dna_sqequence for the next analyse
		
		switch(cmd)
		{	//the button ehich was pressed, as a string
		case "EXIT":
			System.exit(0);
			break;
			
		case "load example DNA":
			//the given DNA sequence as an example
			show_dna(DNA_analyse.EXAMPLE_DNA);
			reset_buttons(true);
			break;
			
		case "load DNA from file":
			// to load a DNA sequence from filesystem, as txt or dat file
			JFileChooser chooser = new JFileChooser("choose file");
			FileNameExtensionFilter filter = new FileNameExtensionFilter("text files", "txt", "dat");         
			chooser.addChoosableFileFilter(filter);
			chooser.setAcceptAllFileFilterUsed(false);
			chooser.setVisible(true);
			chooser.setFileFilter(filter);
			chooser.setCurrentDirectory(new File("."));
			int res = chooser.showOpenDialog(null);
			if (res == JFileChooser.APPROVE_OPTION) {
				Scanner input = null;
				try {
					input = new Scanner(chooser.getSelectedFile());
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				String input_file = new String();				
				while (input.hasNextLine()){
					input_file += input.nextLine();;
				}				
				input.close();
				show_dna(input_file);
				reset_buttons(true);
			}
			break;
		
		case "clear DNA":
			// clear the DNA window
			DNA_analyse.w.dna.setText("");
			reset_buttons(false);
			break;
			
		case "tyberius syndrome":
			// watch for patterns for the tyberius syndrome
			if (!DNA_analyse.w.a1.isSelected()){
				// release the button, because the button is already clicked
				DNA_analyse.w.dna.setText(DNA_analyse.w.dnaseq);
				text="";
			}
			else{
				count_ty = analyse.tyberius();	//call the function to find the special patterns
				DNA_analyse.w.dna.setText(DNA_analyse.w.result);	//display the vie of dna with
																	//highlighted founded patterns
				//display infos about the result:
				text = "<html><body>" + count_ty + " patterns found";
				if (count_ty > 0) text += "<br>RISK for tyberius syndrome.";
				else text += "<br>NO risk for tyberius syndrome.";
				text += "</body></html>";
			}
			DNA_analyse.w.a1_t.setText(text); //to set the info text under the button
			break;
			
		case "brown eyes":
			// watch for patterns to have brown eyes
			if (!DNA_analyse.w.a2.isSelected()){
				DNA_analyse.w.dna.setText(DNA_analyse.w.dnaseq);
				text="";
			}
			else{
				count_be = analyse.brown_eyes();
				DNA_analyse.w.dna.setText(DNA_analyse.w.result);
				text = "<html><body>" + count_be + " patterns found:";
				if (count_be > 0) text += "<br>The object HAS brown eyes.";
				else text += "<br>the object DOESN'T<br>have brown eyes.";
				text += "</body></html>";
			}
			DNA_analyse.w.a2_t.setText(text);
			break;
			
		case "occurrence of 'CTAG'":
			// watch for the first occurence of 'ctag'
			if (!DNA_analyse.w.a3.isSelected()){
				DNA_analyse.w.dna.setText(DNA_analyse.w.dnaseq);
				text="";
			}
			else {
				first_occurrence_of_ctag = DNA_analyse.w.raw.indexOf("ctag");
				if (first_occurrence_of_ctag>=0){
					text = "<html><body>" +  "The first occurrence of<br>'CTAG' is at position "
							+ (first_occurrence_of_ctag+1) + "</body></html>";
					c = 12 + 4*(first_occurrence_of_ctag/60);
					DNA_analyse.w.result = DNA_analyse.w.result.substring(0, first_occurrence_of_ctag+c)
							+ "<font color = #ff0000>" 
							+ DNA_analyse.w.result.substring(first_occurrence_of_ctag+c,first_occurrence_of_ctag+c+4) 
							+ "</font>" 
							+ DNA_analyse.w.result.substring(first_occurrence_of_ctag+c+4,DNA_analyse.w.result.length());
					DNA_analyse.w.dna.setText(DNA_analyse.w.result);
				}
				else{
					text = "<html><body>There is no occurrence of 'CTAG' in this DNA sequence.</body></html>";
				}
			}
			DNA_analyse.w.a3_t.setText(text);
			break;
		
		case "count nucleobases":
			// coutn every nucleobase
			if (!DNA_analyse.w.a4.isSelected()){
				DNA_analyse.w.dna.setText(DNA_analyse.w.dnaseq);
				text="";
			}
			else {
				count = analyse.nucleobase();
				DNA_analyse.w.dna.setText(DNA_analyse.w.result);
				text = "<html><body>number of each nucleobase:<br>" +
						"<font color = #ff0000>a(denine):  " + count[0] + "</font><br>" +
						"<font color = #00ff00>c(ytosine): " + count[1] + "</font><br>" +
						"<font color = #0000ff>g(uanine):  " + count[2] + "</font><br>" +
						"<font color = #ffffff>t(hymine):  " + count[3] + "</font></body></html>";
			}
			DNA_analyse.w.a4_t.setText(text);
			break;
			
		case "complementary":
			// show the complementary of the whole sequence
			if (!DNA_analyse.w.a5.isSelected()){
				text="";
				DNA_analyse.w.dnacompl.setVisible(false);
				DNA_analyse.w.a8.setVisible(false);
			}
			else {
				text = analyse.complementary();
				DNA_analyse.w.dnacompl.setVisible(true);
				DNA_analyse.w.a8.setVisible(true);
			}
			DNA_analyse.w.a5_t.setText(text);
			break;
			
		case "purines & pyrimidines":
			// count the purines and pyrimidines and compare numbers
			if (!DNA_analyse.w.a6.isSelected()){
				text="";
			}
			else {
				count = analyse.pu_and_py();
				text = "<html><body>" +
						"purines:     " + count[0] + "<br>" +
						"pyrimidines: " + count[1] + "<br>";
				if (count[0]>count[1]) text += "there are more purines<br>than pyrimidines";
				else if (count[0]<count[1]) text += "there are more pyrimidines<br>than purines";
				else text += "there are as many purines<br>as pyrimidines";
				text += "</body></html>";
			}
			DNA_analyse.w.a6_t.setText(text);
			break;
			
		case "Frømingen's dischrypsia":
			// watch for correlationwith the early onset of Frømingen's dischrypsia 
			if (!DNA_analyse.w.a7.isSelected()){
				DNA_analyse.w.dna.setText(DNA_analyse.w.dnaseq);
				text="";
			}
			else {
				c = analyse.dischrypsia();
				DNA_analyse.w.dna.setText(DNA_analyse.w.result);
				text = "<html><body>The DNA shows " + c + " evidence<br>" +
						"for the correlation with<br>Frømingen's dischrypsia.</body></html>";
			}
			DNA_analyse.w.a7_t.setText(text);
			break;
			
		case "switch":
			DNA_analyse.w.raw = analyse.get_compl();
			String temp = DNA_analyse.w.a5_t.getText();
			DNA_analyse.w.a5_t.setText(DNA_analyse.w.dnaseq);
			DNA_analyse.w.dnaseq = temp;
			DNA_analyse.w.dna.setText(temp);
			DNA_analyse.w.a1.setSelected(false);
			DNA_analyse.w.a2.setSelected(false);
			DNA_analyse.w.a3.setSelected(false);
			DNA_analyse.w.a4.setSelected(false);
			DNA_analyse.w.a6.setSelected(false);
			DNA_analyse.w.a7.setSelected(false);
			DNA_analyse.w.a1_t.setText("");
			DNA_analyse.w.a2_t.setText("");
			DNA_analyse.w.a3_t.setText("");
			DNA_analyse.w.a4_t.setText("");
			DNA_analyse.w.a6_t.setText("");
			DNA_analyse.w.a7_t.setText("");
			break;
		}
	}

	private void show_dna(String dnaseq) {
		// method to show a new DNA seqence and reset the buttons and the display, and set buttons to enabled
		//i used html for display, cause the line breaks and different colors to highlight special letters
		DNA_analyse.w.raw = dnaseq;
		DNA_analyse.w.dnaseq = new String("<html><body>");		
		for (int i=0; i<dnaseq.length(); ++i){
			if (i!=0 && i%60==0) DNA_analyse.w.dnaseq += "<br>"; 
			DNA_analyse.w.dnaseq += dnaseq.charAt(i);
		}		
		DNA_analyse.w.dnaseq += "</body></html>";
		DNA_analyse.w.dna.setText(DNA_analyse.w.dnaseq);
		DNA_analyse.w.a1.setEnabled(true);
		DNA_analyse.w.a2.setEnabled(true);
		DNA_analyse.w.a3.setEnabled(true);
		DNA_analyse.w.a4.setEnabled(true);
		DNA_analyse.w.a5.setEnabled(true);
		DNA_analyse.w.a6.setEnabled(true);
		DNA_analyse.w.a7.setEnabled(true);
	}
	
	private void reset_buttons(boolean enabled){
		// to reset all buttons and the display
		DNA_analyse.w.a1.setEnabled(enabled);
		DNA_analyse.w.a2.setEnabled(enabled);
		DNA_analyse.w.a3.setEnabled(enabled);
		DNA_analyse.w.a4.setEnabled(enabled);
		DNA_analyse.w.a5.setEnabled(enabled);
		DNA_analyse.w.a6.setEnabled(enabled);
		DNA_analyse.w.a7.setEnabled(enabled);
		
		DNA_analyse.w.a1.setSelected(false);
		DNA_analyse.w.a2.setSelected(false);
		DNA_analyse.w.a3.setSelected(false);
		DNA_analyse.w.a4.setSelected(false);
		DNA_analyse.w.a5.setSelected(false);
		DNA_analyse.w.a6.setSelected(false);
		DNA_analyse.w.a7.setSelected(false);
		DNA_analyse.w.a8.setVisible(false);
		
		DNA_analyse.w.a1_t.setText("");
		DNA_analyse.w.a2_t.setText("");
		DNA_analyse.w.a3_t.setText("");
		DNA_analyse.w.a4_t.setText("");
		DNA_analyse.w.a5_t.setText("");
		DNA_analyse.w.a6_t.setText("");
		DNA_analyse.w.a7_t.setText("");
		
		DNA_analyse.w.dnacompl.setVisible(false);
	}
}