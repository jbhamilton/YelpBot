package yelpbot;

import org.apache.commons.logging.LogFactory;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;

public class Main {

private static ZipList zList;
private static ProxyList pList;

	
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	
	public static void main(String[] args) throws IOException 
	{
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		zList = new ZipList(9);                         //set size of zip list to # of zips
		pList = new ProxyList(852);			//set size of proxies list to # of proxies		
                DataHandler dataHandler = new DataHandler();   		
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
		System.getProperties().put("org.apache.commons.logging.simplelog.defaultlog", "trace");
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		fillZipList("MissouribyCity.txt");
		fillProxyList("NewProxyListFeb27.txt");
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	
                
                Thread thread1 = new Thread(new myRunnable());
                Thread thread2 = new Thread(new myRunnable());
                Thread thread3 = new Thread(new myRunnable());
                Thread thread4 = new Thread(new myRunnable());
                thread1.setName("thread1");
                thread2.setName("thread 2");
                thread3.setName("thread 3");
                thread4.setName("thread 4");
                thread1.start();
                thread2.start();
                thread3.start();
                thread4.start();
                Thread thread5 = new Thread(new Listener(thread1,thread2,thread3,thread4));
                thread5.start();
                boolean thread1IsAlive = true;
                boolean thread2IsAlive = true;
                boolean thread3IsAlive = true;
                boolean thread4IsAlive = true;
                do
                {
                if(thread1.isInterrupted())
                {
                    thread1IsAlive = false;

                }                    
                if(thread1IsAlive && !thread1.isAlive())
                {
                    thread1IsAlive = false;
                    System.out.println("Thread 1 is dead");
                }
                if(thread2.isInterrupted())
                    {
                        thread2IsAlive = false;
                        
                    }
                if(thread2IsAlive && !thread2.isAlive())
                {
                    thread2IsAlive = false;
                    System.out.println("Thread 2 is dead");
                }
                if(thread3.isInterrupted())
                    {
                        thread3IsAlive = false;
                        
                    }
                if(thread3IsAlive && !thread3.isAlive())
                {
                    thread3IsAlive = false;
                    System.out.println("Thread 3 is dead");
                }
                if(thread4.isInterrupted())
                    {
                        thread4IsAlive = false;
                        
                    }
                
                if(thread4IsAlive && !thread4.isAlive())
                {
                    thread4IsAlive = false;
                    System.out.println("Thread 4 is dead");
                }
                if(!thread1IsAlive && !thread2IsAlive && !thread3IsAlive && !thread4IsAlive)
                {
                    
                    dataHandler.storeData();
                    System.out.println("Data Stored");  
		    
	    
                }//end if
                }while(thread1IsAlive || thread2IsAlive || thread3IsAlive || thread4IsAlive);                   
		

	}//end main()
	
	
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	
	public static void fillZipList(String file) throws IOException
	{
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		FileInput file1 = new FileInput();
		file1.openInFile(file);
		String tempLine = file1.readLine();
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		while(tempLine != null)
	        {
			//tempLine = tempLine.substring(0,5);
			Zip newZip=new Zip(tempLine);
			zList.insert(newZip);
			
			tempLine = file1.readLine();    
	        }// end while
	        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		file1.closeFile();
                
		ZipList.pointToStart();
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
	}//end makeZipList()
	
	
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	
	public static void fillProxyList(String file) throws IOException
	{
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		Proxy proxyObj;
		
		FileInput file2 = new FileInput();
		file2.openInFile(file);
		String tempLine = file2.readLine();
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		while(tempLine != null)
	        {
			proxyObj = new Proxy(tempLine);
			
			pList.insert(proxyObj);
			
			tempLine = file2.readLine();    
	        }// end while
	        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		file2.closeFile();
		ProxyList.pointToStart();
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
	}//end makeProxyList()
	
	
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

	
}//end class
