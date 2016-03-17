import java.io.File;

class A1{
	
	public static void PrintingFileList(File maindirectory) { //Function that accepts
		File[] filelist;								//a file/directory name and	
		filelist = maindirectory.listFiles();			//prints all the files that
		for (File currentfile:filelist){				//it contains
			if (currentfile.isFile()){	
			System.out.println ("f "+currentfile.getAbsolutePath());
			}
			else{
				System.out.println ("d "+currentfile.getAbsolutePath());
				PrintingFileList (currentfile);
			}
		}
	}
	public static void main(String[] args){
		File f = new File(".");
		PrintingFileList(f);
	}
}