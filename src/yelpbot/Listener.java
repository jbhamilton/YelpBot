/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package yelpbot;
import java.util.*;

/**
 *
 * @author Brad
 */
public class Listener implements Runnable {
    Thread thread1;
    Thread thread2;
    Thread thread3;
    Thread thread4;
    int input = 5;
    public Listener(Thread t1,Thread t2,Thread t3,Thread t4)
    {        
        this.thread1=t1;
        this.thread2=t2;
        this.thread3=t3;
        this.thread4=t4;
    }
    @Override
    public void run() 
    { 
        Scanner scan = new Scanner(System.in);
        System.out.println("Now Listening for commands");
        while(input!=0)
        {
            int input = dataCheck(scan);
            if(input==1)
            {
                thread1.interrupt();
                System.out.println("Thread 1 interrupt sent");
            }//end if
            if(input==2)
            {
                thread2.interrupt();
                System.out.println("Thread 2 interrupt sent");
            }//end if
            if(input==3)
            {
                thread3.interrupt();
                System.out.println("Thread 3 interrupt sent");
            }//end if
            if(input==4)
            {
                thread4.interrupt();
                System.out.println("Thread 4 interrupt sent");
            }//end if
        }//end while
    }//end run
    
    public static int dataCheck(Scanner scan)
	{
	
		//local declarations	
		int userChoice;
		final int badData = -1;
		
		//checks to make sure the user entered an integer(if not, prints an error message, and starts the loop back over)
		try
		{
			userChoice = scan.nextInt();
		}//try
		catch(InputMismatchException someExceptioin)
		{
			//prints the error message and throws away the bad input
			System.out.println("\nINVALID INPUT.");
			scan.nextLine();
		
			return badData;
		}//catch
		catch(NoSuchElementException e)
		{
			return badData;
		}//catch
	
		//if the user enters good data, return it
		return userChoice;
	
	}//dataCheck()
    }//end Listener




