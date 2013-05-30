
public interface AnalyseI {

	public int tyberius();					// method to find Tyberius syndrome
	public int brown_eyes();				// method to find signs for brown eyes
	public int[] nucleobase();				// method to count all nucleobases
	public int[] pu_and_py();				// method to count purines than pyrimidines
	public String complementary();			// method to calculate the complementary of the whole sequence
	public int dischrypsia();				// method to find correlationwith the early onset of Frømingen's dischrypsia
	public String get_compl();				// method to put the complementary seqeuence as dna seqeuence to analyse
	public int first_occur(String subseq);	// method to find the first occurence of a given subsequence
	public int find_all(String pattern);	// method to find all occurences of a given pattern
}