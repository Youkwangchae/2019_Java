package konkuk.sw;

public class Word implements Comparable<Word> {
	private String eng;
	private String kor;
	private int count=0;
	public Word(String eng, String kor) {
		this.eng = eng;
		this.kor = kor;
	}

	public String getEng() {
		return eng;
	}

	public void setEng(String eng) {
		this.eng = eng;
	}

	public String getKor() {
		return kor;
	}

	public void setKor(String kor) {
		this.kor = kor;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return eng.hashCode() + kor.hashCode();
	}

	public void increasecount() {
		this.count++;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof Word) {
			Word w = (Word)obj;
			if(w.getEng().equals(this.eng))
				return true;
			else
				return false;
		}
		else
			return super.equals(obj);
	}

	@Override
	public int compareTo(Word o) {
		// TODO Auto-generated method stub
		return o.count-count;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		return eng+" : "+kor;
	}
	
}
