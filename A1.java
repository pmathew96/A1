import java.io.File;

class A1{
	
	public static void PrintingToScreen (File currentfile, int numberoffiles ){
		if(numberoffiles == 0){
			System.out.println (numberoffiles+" f "+currentfile.getAbsolutePath());
		}
		else {
			System.out.println (numberoffiles+" d "+currentfile.getAbsolutePath());
		}
	}
	
	public static void PrintingFilesInDirectory(File maindirectory) { 
		int numberoffiles;								//Function that accepts
		File[] filelist, subdirectoryfiles;				//a file/directory name and	
		filelist = maindirectory.listFiles();			//prints all the files that
		for (File currentfile:filelist){				//it contains and the number
			if (currentfile.isFile()){					//of files as well
				PrintingToScreen (currentfile, 0);
			}
			else{
				numberoffiles = 0;
				subdirectoryfiles = currentfile.listFiles();
				for(File file:subdirectoryfiles){
					if (file.isFile()){
						numberoffiles++;
					}
				}
				PrintingToScreen (currentfile, numberoffiles);
				PrintingFilesInDirectory (currentfile);
			}
		}
	}
	public static void main(String[] args){
		File f = new File(".");
		PrintingFilesInDirectory(f);
	}
}