import java.util.ArrayList;
import java.util.Collections;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

//create a class called scoreTrakker
public class ScoreTrakker {
	private ArrayList<Student> students = new ArrayList<>();
	private String[] files = {"scores.txt", "badscore.txt", "nofile.txt" };
	
	ScoreTrakker() {
		
	}
	
	
	// loads in data file and stores student object into list
	public void loadDataFile(String fileName) throws FileNotFoundException, NumberFormatException{
		FileReader reader = null;
		Scanner in = null;
		
		// open file and catch error if no file
		try {
			reader = new FileReader(fileName);
			in = new Scanner(reader);
		} catch (FileNotFoundException e) {
			// throws exception and sends message
			throw new FileNotFoundException("Can't open file: " + fileName);
			
		}
		
		// While loop to go through the file and add students to list
		while (in.hasNextLine()) {
			// read in name line
			String name = in.nextLine();
			
			// read in int line then use parseInt
			String scoreLine = in.nextLine();
			int score = 0;
			// check if error with score
			try {
				score = Integer.parseInt(scoreLine);
			} catch (NumberFormatException e) {
				// throws exception and sends message and closes file
				in.close();
				throw new NumberFormatException("Incorrect format for  " + name + " not a valid score: " + scoreLine);
				
			}
			
			// makes student and adds to list
			Student s = new Student(name, score);
			students.add(s);
		}
		//close file
		in.close();
	}
	
	// sorts the array list and prints it
	public void printInOrder() {
		Collections.sort(students);
		System.out.println("Student Score List");
		for (Student s : students) {
			System.out.println(s);
		}
	}
	
	// calls loadDataFile and printInOrder for correct files
	public void processFiles() throws FileNotFoundException, NumberFormatException{
		// calls functions for all three different files
		loadDataFile(files[0]);
		printInOrder();
		
		loadDataFile(files[1]);
		printInOrder();
		
		loadDataFile(files[2]);
		printInOrder();
	}
	
	//main
	public static void main(String[] args) {
		// initialize ScoreTrakker
		ScoreTrakker trakker = new ScoreTrakker();
		try {
			trakker.processFiles();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		}
	}
}
