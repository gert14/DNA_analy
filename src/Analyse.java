/**
 * 
 * @author Gerhart MEnde
 * 
 */

// class for the logic-part of the programm
public class Analyse implements AnalyseI{
	
	public int tyberius(){
		//find patterns for tyberius syndrome
		int c = 0;
		int br = 0;
		int cty = 0;
		for (int i=12; i<DNA_analyse.w.result.length()-16; ++i){
			if (DNA_analyse.w.result.charAt(i)=='<') {
				// to skipping the '<br>'-tags
				i+=3;
				br=c>0?br+1:0; //if there is a <br>-tag inside a pattern
				continue;
				}
			if (DNA_analyse.w.result.charAt(i)=='g') ++c;
			else if (c>=3){
				++cty;
				DNA_analyse.w.result = DNA_analyse.w.result.substring(0,i-c-4*br) 
						+ "<font color = #ff0000>" 
						+ DNA_analyse.w.result.substring(i-c-4*br,i) 
						+ "</font>" 
						+ DNA_analyse.w.result.substring(i,DNA_analyse.w.result.length());
				i+=29+4*br;
				c = 0;
				br = 0;
			}
			else {
				c = 0;
				br = 0;
			}
		}
		return cty;
	}
	
	public int brown_eyes(){
		//find patterns for brown eyes
		int cbe = 0;
		int ok = 0;
		int br = 0;
		for (int i=16; i<DNA_analyse.w.result.length()-16; ++i){
			if (DNA_analyse.w.result.charAt(i)=='<') {
				i+=3;
				br=ok>0?br+1:0;
				continue;
			}
			if (DNA_analyse.w.result.charAt(i)=='c' && ok!=0 &&ok!=3){
				ok=1;
				continue;
			}
			switch (ok){
			// ok counts the number of agreeing letters to complete the pattern
			case 0:
				if (DNA_analyse.w.result.charAt(i)=='c') ++ok;
				else {
					ok=0;
					br=0;
				}
				break;
			case 1:
				if (DNA_analyse.w.result.charAt(i)=='a') ++ok;
				else {
					ok=0;
					br=0;
				}
				break;
			case 2:
				if (DNA_analyse.w.result.charAt(i)=='g') ++ok;
				else {
					ok=0;
					br=0;
				}
				break;
			case 3:
				if (DNA_analyse.w.result.charAt(i)=='c' && DNA_analyse.w.result.charAt(i+1)!='c' 
				 || DNA_analyse.w.result.charAt(i)=='g' && DNA_analyse.w.result.charAt(i+1)!='g'){
					++ok;
				}
				else {
					ok=0;
					br=0;
				}
				break;
			case 4:
				if (DNA_analyse.w.result.charAt(i)!='t' && DNA_analyse.w.result.charAt(i+1)!='t'){
					++cbe;
					DNA_analyse.w.result = DNA_analyse.w.result.substring(0,i-br*4-4) 
							+ "<font color = #ff0000>" 
							+ DNA_analyse.w.result.substring(i-br*4-4,i+2) 
							+ "</font>" 
							+ DNA_analyse.w.result.substring(i+2,DNA_analyse.w.result.length());
					i+=30;
					ok=0;
					br=0;
				}
				else {
					ok=0;
					br=0;
				}
				break;
			}
		}
		return cbe;
	}
	
	public int[] nucleobase(){
		//count the a's, c's, g's and t's, and highlighted the letters in different colors
		int[] count = new int[4]; //a, c, g, t
		for (int i=12; i<DNA_analyse.w.result.length()-14; ++i){
			if (DNA_analyse.w.result.charAt(i)=='<') { i+=3; continue; }
			switch (DNA_analyse.w.result.charAt(i)){
			case 'a':
				++count[0];
				DNA_analyse.w.result = DNA_analyse.w.result.substring(0,i) 
					+ "<font color = #ff0000>" 
					+ DNA_analyse.w.result.substring(i,i+1) 
					+ "</font>" 
					+ DNA_analyse.w.result.substring(i+1,DNA_analyse.w.result.length());
				i+=28;
				break;
			case 'c':
				++count[1];
				DNA_analyse.w.result = DNA_analyse.w.result.substring(0,i) 
					+ "<font color = #00ff00>" 
					+ DNA_analyse.w.result.substring(i,i+1) 
					+ "</font>" 
					+ DNA_analyse.w.result.substring(i+1,DNA_analyse.w.result.length());
				i+=28;
				break;
			case 'g':
				++count[2];
				DNA_analyse.w.result = DNA_analyse.w.result.substring(0,i) 
					+ "<font color = #0000ff>" 
					+ DNA_analyse.w.result.substring(i,i+1) 
					+ "</font>" 
					+ DNA_analyse.w.result.substring(i+1,DNA_analyse.w.result.length());
				i+=28;				
				break;
			case 't':
				++count[3];
				break;
			}
		}
		return count;
	}
	
	public int[] pu_and_py(){
		//count the purines ans the pyrimidines
		int[] count = new int[2]; //purines, pyrimidines
		for (int i=0; i<DNA_analyse.w.raw.length(); ++i){
			switch (DNA_analyse.w.raw.charAt(i)){
			case 'a': case 'g':
				++count[0];
				break;
			case 'c': case 't':
				++count[1];
				break;
			}
		}
		return count;
	}
	
	public String complementary(){
		//build the complementary of the dna sequence
		String comp = "<html><body>";
		int len = DNA_analyse.w.raw.length();
		for (int i=len-1; i>=0; --i){
			if (i<len-1 && (len-1-i)%60==0) comp += "<br>";
			switch (DNA_analyse.w.raw.charAt(i)){
			case 'a':
				comp += 't';
				break;
			case 'c':
				comp += 'g';
				break;
			case 'g':
				comp += 'c';
				break;
			case 't':
				comp += 'a';
				break;
			}
		}
		return (comp += "</body></html>");
	}
	
	public int dischrypsia(){
		//find patterns for Frømingen's dischrypsia
		boolean pu_4 = false;
		boolean check = false;
		int br1 = 0;
		int br2 = 0;
		int pu = 0;
		int py = 0;
		int count = 0;
		for (int i=12; i<DNA_analyse.w.result.length()-22; ++i){
			if (DNA_analyse.w.result.charAt(i)=='<') {
				br1=pu>0?1:0;
				br2=pu==0&&pu_4?1:0;
				i+=3;
				continue; 
			}
			switch (DNA_analyse.w.result.charAt(i)){
			case 'a': case 'g':
				if (check){
					pu_4 = false;
				}
				check = false;
				py = 0;
				++ pu;
				if (pu==4) pu_4 = true; //it have found 4 purines
				break;
			case 'c': case 't':
				check = true;
				pu = 0;
				if (pu_4) ++py;
				else{
					br1=0;
					br2=0;
				}
				if (py==4){
					//it have found 4 pyrimidines behind 4 purines
					++count;
					DNA_analyse.w.result = DNA_analyse.w.result.substring(0,i-7-br1*4-br2*4) 
							+ "<font color = #ff0000>"
							+ DNA_analyse.w.result.substring(i-7-br1*4-br2*4,i-3-br2*4)
							+ "</font><font color = #0000ff>"
							+ DNA_analyse.w.result.substring(i-3-br2*4,i+1)
							+ "</font>" 
							+ DNA_analyse.w.result.substring(i+1,DNA_analyse.w.result.length());
					i+=54+br1*4+br2*4;
					br1=0;
					br2=0;
				}
				break;
			}
		}
		return count;
	}
	
	public String get_compl(){
		String raw = new StringBuffer(DNA_analyse.w.raw).reverse().toString();
		raw = raw.replace('a', '#');
		raw = raw.replace('t', 'a');
		raw = raw.replace('#', 't');
		raw = raw.replace('c', '#');
		raw = raw.replace('g', 'c');
		raw = raw.replace('#', 'g');
		return raw;
	}
	
	public int first_occur(String subseq){
		return DNA_analyse.w.raw.indexOf(subseq);
	}	
}
