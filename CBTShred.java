import java.io.*;
import java.text.*;
import java.time.*;
import java.util.*;

public class CBTShred{
	private String fileName;
	private String className;
	private String parse1 = "";
	private String[] parse2;
	private String formattedCode = "";
	private String fullName = "";
	private int period;

	public CBTShred(String fileName, String className, String fullName, int period) throws FileNotFoundException{
		this.fileName = fileName;
		this.className = className;
		this.fullName = fullName;
		this.period = period;
	}
	File file = new File("shred.txt");
	Scanner input = new Scanner(file);
	//Strip
	public String[] strip(){
		while(input.hasNextLine()){
			parse1 += input.nextLine().substring(2) + "\n";
		}
		parse2 = parse1.split("\n");

		return parse2;
	}



	//FORMAT
	public String format(){
		formattedCode += commentHeader();
		int bracketCount = 0;
		for(int i = 0; i < strip().length; i++){

			if(strip()[i].contains("public class")) {
				String classToReplace = strip()[i].split(" ")[3];
				formattedCode += strip()[i].replace(classToReplace, className) + "\n";
			}

			else{
				if(strip()[i].contains("{")){
					bracketCount += 1;
				}
				if(strip()[i].contains("}")){
					bracketCount -= 1;
				}


				if(strip()[i].contains("{")){
					for(int x = 0; x < bracketCount - 1; x++){
						formattedCode += "    ";
					}
					formattedCode += strip()[i] + "\n";
				}
				else{
					for(int x = 0; x < bracketCount; x++){
						formattedCode += "    ";
					}
					formattedCode += strip()[i] + "\n";
				}
				/*if(i >= 2){
					if(strip()[i-2].contains("public static void main")) {
						formattedCode += "\n" + codeHeader();
					}
				}
				*/
			}
		}
		return formattedCode;
	}

public void writeToFile(String fileToWrite) throws IOException {
	BufferedWriter javaFile = new BufferedWriter(new FileWriter(fileToWrite));
	javaFile.write(format());
	javaFile.close();
}


private String codeHeader() {
	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
	Date date = new Date();

	String codeHead = " System.out.println(\"" + fullName.split(" ")[1] + ", " + fullName.split(" ")[0] + "\")\n" +
										" System.out.println(\"" + dateFormat.format(date) + "\")\n" +
										" System.out.println(\"Period: " + period + "\\n\")\n";
	return codeHead;
}


	private String commentHeader() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
		Date date = new Date();
		String comment = " /*\n" +
											" " + fullName.split(" ")[1] + ", " + fullName.split(" ")[0] + "\n" +
											" " + dateFormat.format(date) + "\n" +
											" Period " + period + "\n" +
											" " + className + "\n" +
											" */\n";
		return comment;
	}
}
