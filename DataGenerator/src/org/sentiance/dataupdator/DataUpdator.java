package org.sentiance.dataupdator;

import java.io.File;

/**
 * This class is intended to update the size of the dataset with given size.
 * args[0] = matser dataset path --> D:\\folder
 * args[1] = <path1,size1,path2,size2,path3,size3> --> <locations,5,sensors,5,devices,5>
 * 
 * To understand the example better I'm refering as follows:
 * 1st path -> Locations
 * 2nd path -> sensors
 * 3rd path -> Devices
 * 
 * @author Ajit.Kshirsagar
 *
 */

public class DataUpdator {

	
	public static void main(String args[]){
		
		// Receiving command line arguments into variables
		
		String sourceFolderPath = args[0];
		
		Double locationDatasetSize = Double.parseDouble(args[1].split(",")[1]);
		Double sensorDatasetSize = Double.parseDouble(args[1].split(",")[3]);
		Double deviceDatasetSize = Double.parseDouble(args[1].split(",")[5]);
		
		String folder1 = args[1].split(",")[0];
		String folder2 = args[1].split(",")[2];
		String folder3 = args[1].split(",")[4];
		
		
		String locationPath = sourceFolderPath+File.separator+folder1;
		String sensorPath =sourceFolderPath+File.separator+folder2;
		String devicePath =sourceFolderPath+File.separator+folder3;
		
		
		// Finding the unit file size with which dataset is created in datageneration stage
		File[] listofFiles = new File(sourceFolderPath+File.separator+folder1).listFiles();
		
		long unitFileSize;
		
		if(! listofFiles[0].getName().contains("_small")){
			
			unitFileSize = listofFiles[0].length();
		}
		else {
			
			unitFileSize = listofFiles[1].length();
		}
		
		
		System.out.println("Unit file size: "+unitFileSize+" bytes");
		
		// Calculating the size to be updated for individual folders.
		long locationToBeUpdatedSize = (long)(locationDatasetSize * 1024 * 1024);
		long sensorToBeUpdatedSize = (long)(sensorDatasetSize * 1024 * 1024);
		long deviceToBeUpdatedSize = (long)(deviceDatasetSize * 1024 * 1024);
		
					
		LocationUpdatorThread locationUpdate = new LocationUpdatorThread(locationPath, unitFileSize, locationToBeUpdatedSize);
		SensorUpdatorThread sensorUpdate = new SensorUpdatorThread(sensorPath, unitFileSize, sensorToBeUpdatedSize);
		DevicesUpdatorThread devUpdate = new DevicesUpdatorThread(devicePath, unitFileSize, deviceToBeUpdatedSize);
		
		// Calling the threads for data updation.
				
		locationUpdate.start();
		sensorUpdate.start();
		devUpdate.start();
		
		
	}
}
