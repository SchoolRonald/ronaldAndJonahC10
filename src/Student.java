
public class Student implements Comparable<Student> {
	// private data
	private String name;
	private int score;
	
	
	// constructor
	Student (String name, int score) {
		this.name = name;
		this.score = score;
		
	}


	@Override
	public String toString() {
		return name + " " + score;
	}


	@Override
	public int compareTo(Student other) {
		// TODO Auto-generated method stub
		return this.name.compareTo(other.name);
	}
}
