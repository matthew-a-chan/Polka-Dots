package Genetics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * @author Jon Wu & Stephen Chern
 * 
 * used to specifically create the 0th generation's heuristics -- Basically never run
 */
public class Gen0Init {
	public static void main(String[] args) {
		
		File dataFile=new File(System.getProperty("user.home")+File.separator+"Desktop"+File.separator+
				"AI"+File.separator+"DATA.txt");
		
		try {
			FileWriter fw = new FileWriter(dataFile);
			fw.write("0");
			fw.flush();
			fw.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		for(int i=0;i<Playground.populationSize;i++){
			File newFile=new File(System.getProperty("user.home")+File.separator+"Desktop"+File.separator+
					"AI"+File.separator+"GEN0"+File.separator+"GEN0"+"-IND"+i+".txt");

			try {
				FileWriter fw = new FileWriter(newFile);
				
				for(int k=0;k<5482;k++) {

					fw.write(((float)Math.random()*.1)-1/(20f)+" ");

				}
				fw.flush();
				fw.close();

			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
}