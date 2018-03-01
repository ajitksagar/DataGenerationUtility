package org.sentiance.datagenerator;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Random;

import org.apache.commons.text.RandomStringGenerator;

/**
 * This class acts as a helper class having dataGeneration and dataUpdation method.
 * 
 * @author Ajit.Kshirsagar
 *
 */
public class DataGenerationHelper {

	/**
	 * This method generates the files with Random AlphaNumeric data in the given location.
	 * @param location
	 * @param totalFiles
	 * @param smallSize
	 * @param eachFileSize
	 */
	
	public void dataGeneration(String location,int totalFiles, double smallSize, double eachFileSize) {
		
		
		for(int index=1; index<=totalFiles+1;index++)
		{
			// Creating the unique file by appending the epoc time to the file
			String dateTime = String.valueOf(System.currentTimeMillis());
			File file = new File(location + File.separator + "file" + "_" + dateTime);
			
			try(FileWriter fw = new FileWriter(file))
			{
				
				// String generator to generate the random AlphaNumeric data.
				RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();

				// Creating files with Random alphanumeric data of eachFileSize in MB 
				if(index != totalFiles)
				{
					while(true)
					{
						// Writing Random AlphaNumeric string of min.size=5 and max.size=50
						fw.write(generator.generate(new Random().nextInt(50)+5)+ "\n");
						fw.flush();
						if(file.length() >= (eachFileSize*1024*1024))
							break;
					}
				}
				else
				{
					// Creating small file with remaining size and Random alphanumeric data
					while(true)
					{
						// Writing Random AlphaNumeric string of min.size=5 and max.size=50
						fw.write(generator.generate(new Random().nextInt(50)+5)+ "\n");
						fw.flush();
						if(file.length() >= (smallSize*1024*1024))
							break;
					}
					fw.close();

					// Appending _small to the file to identify the small size file.
					file.renameTo(new File(location + File.separator + "file_"+dateTime+"_small"));
					
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		
	}
	
	/**
	 * This method updates the size of the directory with given toBeUpdatedSize by generating the
	 * files of unitFileSize length with Random AlphaNumeric data.
	 * @param location_path
	 * @param unitFileSize
	 * @param toBeUpdatedSize
	 */
	public void dataUpdation(String location_path,long unitFileSize,long toBeUpdatedSize){
		
		
		try 
		{	
			// Getting the name of small file.
			String[] smallFile = new File(location_path).list(new FilenameFilter() {
			    public boolean accept(File directory, String fileName) {
			        return fileName.contains("_small");
			    }
			});
			
			//There should not be multiple small files in a directory.
			if(smallFile.length != 1)
				throw new Exception("Multiple small size files are found !!!");
			
			File fileWith_small = new File(location_path + File.separator + smallFile[0]);
			
			long smallFileSize = fileWith_small.length();
			
			System.out.println(smallFile[0]);
			
			//Random string generator for AlphaNumeric Strings
			RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();
			
			// Updating the small file to the unitFileSize
			try(FileWriter fw = new FileWriter(fileWith_small))
			{
				while(true)
				{	
					// Writing Random AlphaNumeric string of min.size=5 and max.size=50
					fw.write(generator.generate(new Random().nextInt(50)+5)+ "\n");
					fw.flush();
					if(fileWith_small.length() >= unitFileSize)
						break;
				}
				// Renaming the file by removing _small from the filename.
				String strName = location_path + File.separator + fileWith_small.getName();
				strName = strName.replace("_small", "");
				fw.close();
				fileWith_small.renameTo(new File(strName));
			}
			catch(IOException e)
			{
				throw e;
			}
			
			// Remaining size from which new files to be created in a directory
			toBeUpdatedSize = toBeUpdatedSize - (unitFileSize - smallFileSize);
			
			
			double totalFilesFraction = (toBeUpdatedSize/(float)unitFileSize);
			
			// Total number of same size files to be created.
			int totalSameSizeFiles = (int)totalFilesFraction;
			
			// Size of remaining small file
			long newSmallFileSize = (long)((totalFilesFraction - totalSameSizeFiles)*unitFileSize);
			
	
			for(int index=1; index<=totalSameSizeFiles+1;index++)
			{	
				// For unique filename appending the epoc time to the file name
				String dateTime = String.valueOf(System.currentTimeMillis());
				File file = new File(location_path + File.separator + "file_" + dateTime);			
				try(FileWriter fw = new FileWriter(file))
				{	
					// Creating files with same size(i.e. unitFileSize).	
					if(index != totalSameSizeFiles)
					{
						while(true)
						{	
							// Writing Random AlphaNumeric string of min.size=5 and max.size=50
							fw.write(generator.generate(new Random().nextInt(50)+5)+ "\n");
							fw.flush();
							if(file.length() >= (unitFileSize))
								break;
						}
					}
					else
					{	
						// Creating  the small size file with remaining bytes.	
						while(true)
						{	
							// Writing Random AlphaNumeric string of min.size=5 and max.size=50
							fw.write(generator.generate(new Random().nextInt(50)+5)+ "\n");
							fw.flush();
							if(file.length() >= (newSmallFileSize))
								break;
						}
						fw.close();
						//  To identify small file Appending _small to the filename.
						file.renameTo(new File(location_path + File.separator + "file_"+dateTime+"_small"));
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	
}
