package org.sentiance.datagenerator;

/**
 * Thread to generate the data in path2 from args[3].
 * 
 * @author Ajit.Kshirsagar
 *
 */

public class SensorThread extends Thread
{

	private String location;
	private int totalFiles;
	private double smallSize;
	private double eachFileSize;
	
	public SensorThread(String location,int totalFiles, double smallSize, double eachFileSize)
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
		DataGenerationHelper dataSensorGen = new DataGenerationHelper();
		
		dataSensorGen.dataGeneration(location, totalFiles, smallSize, eachFileSize);
		
		
	}


}
