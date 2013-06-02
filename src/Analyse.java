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
			if (DNA_analyse.w.result.charAt(i)=='g' || DNA_analyse.w.result.charAt(i)=='G') ++c;
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
			if ((DNA_analyse.w.result.charAt(i)=='c' || DNA_analyse.w.result.charAt(i)=='C') && ok!=0 &&ok!=3){
				ok=1;
				continue;
			}
			switch (ok){
			// ok counts the number of agreeing letters to complete the pattern
			case 0:
				if (DNA_analyse.w.result.charAt(i)=='c' || DNA_analyse.w.result.charAt(i)=='C') ++ok;
				else {
					ok=0;
					br=0;
				}
				break;
			case 1:
				if (DNA_analyse.w.result.charAt(i)=='a' || DNA_analyse.w.result.charAt(i)=='A') ++ok;
				else {
					ok=0;
					br=0;
				}
				break;
			case 2:
				if (DNA_analyse.w.result.charAt(i)=='g' || DNA_analyse.w.result.charAt(i)=='G') ++ok;
				else {
					ok=0;
					br=0;
				}
				break;
			case 3:
				if (((DNA_analyse.w.result.charAt(i)=='c' || DNA_analyse.w.result.charAt(i)=='C') 
				  && !(DNA_analyse.w.result.charAt(i+1)=='c' || DNA_analyse.w.result.charAt(i+1)=='C'))
				  		|| 
				 	((DNA_analyse.w.result.charAt(i)=='g' || DNA_analyse.w.result.charAt(i)=='G') 
			      && !(DNA_analyse.w.result.charAt(i+1)=='g' || DNA_analyse.w.result.charAt(i+1)=='G'))){
					++ok;
				}
				else {
					ok=0;
					br=0;
				}
				break;
			case 4:
				if (DNA_analyse.w.result.charAt(i)!='t' && DNA_analyse.w.result.charAt(i)!='T'
				  && DNA_analyse.w.result.charAt(i+1)!='t' && DNA_analyse.w.result.charAt(i+1)!='T'){
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
			case 'a': case 'A':
				++count[0];
				DNA_analyse.w.result = DNA_analyse.w.result.substring(0,i) 
					+ "<font color = #ff0000>" 
					+ DNA_analyse.w.result.substring(i,i+1) 
					+ "</font>" 
					+ DNA_analyse.w.result.substring(i+1,DNA_analyse.w.result.length());
				i+=28;
				break;
			case 'c': case 'C':
				++count[1];
				DNA_analyse.w.result = DNA_analyse.w.result.substring(0,i) 
					+ "<font color = #00ff00>" 
					+ DNA_analyse.w.result.substring(i,i+1) 
					+ "</font>" 
					+ DNA_analyse.w.result.substring(i+1,DNA_analyse.w.result.length());
				i+=28;
				break;
			case 'g': case 'G':
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
			case 'a': case 'A': case 'g': case 'G':
				++count[0];
				break;
			case 'c': case 'C': case 't': case 'T':
				++count[1];
				break;
			}
		}
		return count;
	}
	
	public String complementary(){
		//build the complementary of the dna sequence
		String comp = "<html><body>";
		int len = DNA_analyse.w.raw.length()-1;
		for (int i=len; i>=0; --i){
			if (i-len!=0 && (i-len)%60==0) comp += "<br>";
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
			case 'A':
				comp += 'T';
				break;
			case 'C':
				comp += 'G';
				break;
			case 'G':
				comp += 'C';
				break;
			case 'T':
				comp += 'A';
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
			case 'a': case 'g': case 'A': case 'G':
				if (check){
					pu_4 = false;
				}
				check = false;
				py = 0;
				++ pu;
				if (pu==4) pu_4 = true; //it have found 4 purines
				break;
			case 'c': case 't': case 'C': case 'T':
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
		
		raw = raw.replace('A', '#');
		raw = raw.replace('T', 'A');
		raw = raw.replace('#', 'T');
		raw = raw.replace('C', '#');
		raw = raw.replace('G', 'C');
		raw = raw.replace('#', 'G');
		return raw;
	}
	
	public int first_occur(String subseq){
		return DNA_analyse.w.raw.indexOf(subseq);
	}
	
	public int find_all(String pattern){
		int len = pattern.length();
		int br=0, c=0, found=0;
		for (int i=12; i<=DNA_analyse.w.result.length()-14-(len-c); ++i){
			if (DNA_analyse.w.result.charAt(i)=='<') {
				// to skip the '<br>'-tags
				i+=3;
				br=c>0?br+1:0; //if there is a <br>-tag inside a pattern
				continue;
			}
			if (DNA_analyse.w.result.charAt(i)==Character.toUpperCase(pattern.charAt(c)) ||
				DNA_analyse.w.result.charAt(i)==Character.toLowerCase(pattern.charAt(c))){
				++c;
				if (c==len){
					DNA_analyse.w.result = DNA_analyse.w.result.substring(0,i+1-len-4*br) 
							+ "<font color = #ff0000>" 
							+ DNA_analyse.w.result.substring(i+1-len-4*br,i+1) 
							+ "</font>" 
							+ DNA_analyse.w.result.substring(i+1,DNA_analyse.w.result.length());
					i+=29+4*br;
					c = 0;
					br = 0;
					++found;
				}
			}
			else if (c>0){
				i-=c;
				c = 0;
				br = 0;
			}
		}
		return found;
	}
}
