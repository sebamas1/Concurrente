package tp1;
import java.util.concurrent.ThreadLocalRandom;
public class Consumidor implements Runnable {
protected static int consumidos = 0;
public void run() 
{
	synchronized(Sistema.lock) 
	{
		while(Sistema.productCount<Sistema.PRODUCCION || Sistema.cola.size()>0) //lock de la clase sistema que evita que dos consumidores saquen objetos al mismo tiempo
		{
			consumir();
	    }
		
	}
	
	}
	private void consumir() 
	{
		 if(Sistema.cola.size()>0) 
			       { 
				
				Sistema.cola.removeFirst();  //saca el primer elemento
				consumidos++;
				System.out.println("ÑAM ÑAM! Soy el "+ Thread.currentThread().getName());
				     try {Thread.sleep(ThreadLocalRandom.current().nextInt(Sistema.ALFA, Sistema.BETA));
				     }
					    catch(Exception e) {}
				     System.out.println("CONSUMIDOS = "+ consumidos);
				    }
			         
			else {
				System.out.println("NO SE PUDO CONSUMIR");
				try{
					 Thread.sleep(1);
					 }
				  catch(Exception e){}
				 }
			try{
				 Sistema.lock.notifyAll();
				 Sistema.lock.wait(1000); //este wait es para que un thread no acapare el lock, ademas, lo hacemos esperar 1000 para que en caso de que se haya terminado la ejecucion, no espere indefinidamente un notify
				 }
			  catch(Exception e){}

	}
}
