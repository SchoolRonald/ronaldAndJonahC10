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
		super();
	}
	
	
	// loads in data file and stores student object into list
	public void loadDataFile(String fileName) throws FileNotFoundException, NumberFormatException{
		//empty out students for the next file input 
		students.clear();
		
		//scanner and reader needs to both be null
		FileReader reader = null;
		Scanner in = null;

		// open file and catch error if no file
		try {
			
			//create a new reader and scanner for the file
			reader = new FileReader(fileName);
			in = new Scanner(reader);
			
		} catch (FileNotFoundException e) {
			
			// throws exception and sends message
			System.out.println("\nCan't open file: " + fileName);
			
			//return if there isn't a file that was found
			return;
			
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
				
				//reads the student's score as an integer after it being a string
				score = Integer.parseInt(scoreLine);
				
			} catch (NumberFormatException e) {
				
				// throws exception and sends message and closes file
				System.out.println("\nIncorrect format for " + name + " not a valid score: " + scoreLine);
				System.out.println("\n");
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
		for (Student s : students) {
			System.out.println("Student Score List");
			System.out.println(s);
		}

	}
	
	// calls loadDataFile and printInOrder for correct files
	public void processFiles() throws FileNotFoundException, NumberFormatException{
		// calls functions for all three different files
		
		//loops through all the files with an iterated for loop
		for (String file : files) {
			
			//try and catch blocks around loadDataFile and printInOrder
			try {
				loadDataFile(file);
				printInOrder();
			
			//catches filenotfound exception
			} catch (FileNotFoundException e) {
				// throws exception and sends message
				System.out.println("\n\n Can't open file: " + file);
			}
		}
	}
	
	//main
	public static void main(String[] args) {
		// initialize ScoreTrakker
		ScoreTrakker trakker = new ScoreTrakker();
		
		//create a try or catch statement to process files
		try {
			trakker.processFiles();
			
		} 
		
		//there should be catch where it prints out that the user got nothing
		catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			
		}
		
		//there should be catch where it prints out that the user got nothing 
		catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		}
	}
}
