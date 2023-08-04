package popl.class03;

public class JavaPair extends Object {
  private int first;
  private int second;

  public JavaPair(int fst , int snd) {
    first = fst;
    second = snd;
  }

  public String toString() {
	return "Pair(" + first + ", " + second + ")";
  }
  
  static public JavaPair make(int fst, int snd) {
	return new JavaPair(fst, snd);
  }
  
  public void setFirst(int fst) {
	first = fst;
  }
  
  public int getFirst() {
    return first;
  }

  public int getSecond() {
    return second;
  }
  
}