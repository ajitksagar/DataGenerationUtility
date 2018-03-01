package org.sentiance.dataupdator;

import org.sentiance.datagenerator.DataGenerationHelper;

/**
 * Thread to update the data in path1 from args[2] passed in the main class.
 * 
 * @author Ajit.Kshirsagar
 *
 */

public class LocationUpdatorThread extends Thread {

	private String location_path;
	private long unitFileSize;
	private long toBeUpdatedSize;
	
	
	public LocationUpdatorThread(String location_path,long unitFileSize,long toBeUpdatedSize){
		
		this.location_path = location_path;
		this.unitFileSize = unitFileSize;
		this.toBeUpdatedSize = toBeUpdatedSize;
		
	}
	
	@Override
	public void run() {
		super.run();
		
		//	Calling data updation method from helper class
		
		DataGenerationHelper dataLocationUpdation = new DataGenerationHelper();
		
		dataLocationUpdation.dataUpdation(location_path, unitFileSize, toBeUpdatedSize);
		
	}
}
