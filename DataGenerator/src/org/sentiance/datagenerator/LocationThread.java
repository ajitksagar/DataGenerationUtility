package org.sentiance.datagenerator;

/**
 * Thread to generate the data in path1 from args[3].
 * 
 * @author Ajit.Kshirsagar
 *
 */

public class LocationThread extends Thread
{
	private String location;
	private int totalFiles;
	private double smallSize;
	private double eachFileSize;
	
	public LocationThread(String location,int totalFiles, double smallSize, double eachFileSize)
	{
		this.location = location;
		this.totalFiles = totalFiles;
		this.smallSize = smallSize;
		this.eachFileSize = eachFileSize;
	}
	
	@Override
	public void run()
	{
		super.run();
		
		//Calling data generation method from helper class
		DataGenerationHelper dataLocationGen = new DataGenerationHelper();
		
		dataLocationGen.dataGeneration(location, totalFiles, smallSize, eachFileSize);
		
	}

}
