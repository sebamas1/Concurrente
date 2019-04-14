package tp1;
import java.io.IOException;
import java.util.logging.*; // https://www.youtube.com/watch?v=W0_Man88Z3Q&t=2s
public class Log implements Runnable {
private final static Logger logger = Logger.getLogger("log.txt");
private Thread t5;
private Thread t6;
Thread t1;
Thread t2;
Thread t3;
Thread t4;
public Log(Thread t5, Thread t6)
{
	this.t5 = t5;
	this.t6 = t6;
	LogManager.getLogManager().reset(); //elimina el handler por defecto
	logger.setLevel(Level.INFO);
	try {
	FileHandler fh = new FileHandler("logThread.txt");
	fh.setLevel(Level.INFO);
	fh.setFormatter(new SimpleFormatter());
	logger.addHandler(fh);
	}
	catch(IOException e){
		System.out.println("NO SE GUARDO EL LOG");
	}
	}
public void run() 
{
	while((Productor.perdidos+Consumidor.consumidos)<Sistema.PRODUCCION) 
	{
		try {
			Thread.sleep(2000);
		}catch(Exception e) 
		{
			System.out.println("No se durmio el logger.");
		}
		logger.info("Soy el consumidor 1 y estoy en el estado "+t5.getState()+ " y el buffer tiene "+Sistema.cola.size()+" ocupados");
		logger.info("Soy el consumidor 2 y estoy en el estado "+t6.getState()+ " y el buffer tiene "+Sistema.cola.size()+" ocupados");
		}
	System.out.println("TOTAL consumidos + perdidos: "+(Productor.perdidos+Consumidor.consumidos));
	}
}
/*Estados vistos: Blocked, Runnable, Timed_waiting, Waiting
 * Blocked: el thread fue notificado pero no pudo obtener el lock
 * Runnable: segun stackoverflow, significa que esta corriendose en la JVM, pero PODRIA estar esperando por algun recurso de SO
 * Timed_waiting: es el sleep que puse yo en la clase consumidor, que mantiene el lock ocupado, y simula el tiempo que tarda en consumir el objeto
 * Waiting: significa que esta esperando a que otro thread invoque el notify
 */
