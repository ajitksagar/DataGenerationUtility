package org.sentiance.databackup;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/**
 * This class is created for taking backup of the dataset from srcPath to destPath.
 * args[0] = srcPath
 * args[1] = destPath
 * 
 * @author Ajit.Kshirsagar
 *
 */
public class DataBackUp {

	public static void main(String args[]) throws IOException {
		
			
		String srcPath = args[0];
		String targetPath = args[1];
		
		// To create unique folders appending the epoc time to the folder name.
		targetPath = targetPath+File.separator+"backup_"+String.valueOf(System.currentTimeMillis());
		
		File srcDir = new File(srcPath);
		File targetDir = new File(targetPath);
		
		try {
		
		
		if(!targetDir.exists())
			targetDir.mkdir();
		
		FileUtils.copyDirectory(srcDir, targetDir);
		
		System.out.println("Folder Backup Completed at "+targetPath+" !!!");
		
		} catch(Exception e)
		{
			throw e;
			
		}
	}
	
	
}
