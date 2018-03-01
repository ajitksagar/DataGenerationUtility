package org.sentiance.datagenerator;

import java.io.File;

/**
 * This is a main class for DataGeneration task which receives the command line arguments and
 * calls the three different threads to generate the data parallely for given size in MB. 
 * The input should be passed in the following way: 
 * args[0] = Path for master dataset. --> D:\\folder 
 * args[1] = Size of file --> 1.6 
 * args[3] =  <path1,size1,path2,size2,path3,size3> --> locations,10,sensors,15,devices,12.
 * 
 * To understand the example better I'm refering as follows:
 * 1st path -> Locations
 * 2nd path -> sensors
 * 3rd path -> Devices
 *  
 * @author Ajit.Kshirsagar
 */

public class DataGenerator 
{
	public static void main(String[] args) 
	{	
		
		// Receiving command line arguments into variables
		String datasetPath= args[0];
		Double fileSize = Double.parseDouble(args[1]);
		Double locationDatasetSize = Double.parseDouble(args[2].split(",")[1]);
		Double sensorDatasetSize = Double.parseDouble(args[2].split(",")[3]);
		Double deviceDatasetSize = Double.parseDouble(args[2].split(",")[5]);
		
		String folder1 = args[2].split(",")[0];
		String folder2 = args[2].split(",")[2];
		String folder3 = args[2].split(",")[4];
		
		File source = new File(datasetPath);
		if(!source.exists())
			source.mkdir();
		
		// Creating the directories in master dataset path
		
		File location = new File(datasetPath +File.separator+folder1);
		location.mkdir();
		

		File sensor = new File(datasetPath + File.separator+folder2);
		sensor.mkdir();
		

		File devices = new File(datasetPath + File.separator+folder3);
		devices.mkdir();
		
		// Calculating the total number of files to be generated with Unit size given in args[1] for folder1(locations).
		int totalFiles1 = (int)(locationDatasetSize/fileSize);
		
		//Calculating the size of small file.
		double smallFileSize1 = locationDatasetSize - (fileSize*totalFiles1);
		
		
		LocationThread locThread = new LocationThread(location.getAbsolutePath(), totalFiles1, smallFileSize1, fileSize);
		
		// Calculating the total number of files to be generated with Unit size given in args[1] for folder2(sensors).
		int totalFiles2 = (int)(sensorDatasetSize/fileSize);

		//Calculating the size of small file.
		double smallFileSize2 = sensorDatasetSize - (fileSize*totalFiles2);
		
		
		SensorThread sThread = new SensorThread(sensor.getAbsolutePath(), totalFiles2, smallFileSize2, fileSize);
		
		// Calculating the total number of files to be generated with Unit size given in args[1] for folder3(devices).
		int totalFiles3 = (int)(deviceDatasetSize/fileSize);
		
		//Calculating the size of small file.
		double smallFileSize3 = deviceDatasetSize - (fileSize*totalFiles3);
		
				
		DeviceThread dThread = new DeviceThread(devices.getAbsolutePath(), totalFiles3, smallFileSize3, fileSize);
		
		// Calling the threads to generate data parallely
		
		locThread.start();
		dThread.start();
		sThread.start();
	}

}
