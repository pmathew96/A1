import java.io.File;
import java.lang.String;

class A1{
	
	public static void PrintingNumberOfFilesByType(File currentdirectory){
		File[] filelist;
		filelist = currentdirectory.listFiles();
		String[]TYPES = new String[]{"jpg", "png", "gif", "mp4", "mp3", "exe", "psd", "html", "xml"};
		int[] filesizecounterarray = new int[9];
		int[] filetypecounterarray = new int[9];	//Function that takes in the current directory
		int flag, i;						//and prints the number of files by type
		String filename, filesize;					//and prints the combined size of all files
		for (File file:filelist){			//of that type
			filename = (file.getAbsolutePath().toLowerCase());
			for (i = 0; i < 9; i++){
				flag = filename.indexOf (TYPES[i]);
				if (flag != 1){
					filetypecounterarray[i]++;
					filesizecounterarray[i]+=file.length();
					break;
				}
			}
		}
		System.out.println("Number of files by type:");
		for(i = 0; i < 9; i++){
			filesize = String.format("%,d", filesizecounterarray[i]);
			System.out.println(TYPES[i]+" - "+filetypecounterarray[i]+" "+filesize);
		}
	}
	
	public static int ComputingTotalFilesInDirectory (File currentfile, int filessofar){
		File[] currentdirectoryfiles;					//Function that takes in the current
		int numberoffiles = filessofar;			//directory and the files so for in previous directories
		currentdirectoryfiles = currentfile.listFiles();//and returns the number of files in the current
		for (File file:currentdirectoryfiles){//directory, plus the files in previous directories
			if (file.isFile()){
				numberoffiles++;
			}
			else{
				numberoffiles += ComputingTotalFilesInDirectory (file, numberoffiles);
			}
		}
		return numberoffiles;
	}
	
	public static void PrintingToScreen (File currentfile, int filesdirectlyunder, int totalfiles ){
		if(filesdirectlyunder == 0 && currentfile.isFile()){
			System.out.println (totalfiles+" "+ filesdirectlyunder+" f "+currentfile.getAbsolutePath());
		}
		else {
			System.out.println (totalfiles+" "+filesdirectlyunder+" d "+currentfile.getAbsolutePath());
		}
	}
	
	public static void PrintingFilesInDirectory(File maindirectory) { 
		int numberoffiles, totalfiles;					//Function that accepts
		File[] filelist, subdirectoryfiles;				//a file/directory name and	
		filelist = maindirectory.listFiles();			//prints all the files that
		for (File currentfile:filelist){				//it contains and the number
			if (currentfile.isFile()){					//of files as well
				PrintingToScreen (currentfile, 0, 0);
			}
			else{
				numberoffiles = 0;
				subdirectoryfiles = currentfile.listFiles();
				for(File file:subdirectoryfiles){
					if (file.isFile()){
						numberoffiles++;
					}
				}
				totalfiles = ComputingTotalFilesInDirectory (currentfile, 0);
				PrintingToScreen (currentfile, numberoffiles, totalfiles);
				PrintingFilesInDirectory (currentfile);
			}
		}
	}
	
	public static void main(String[] args){
		File f = new File(".");
		PrintingFilesInDirectory(f);
		PrintingNumberOfFilesByType(f);
		System.out.println("Program executed in "+System.currentTimeMillis()+" milliseconds");
	}
}