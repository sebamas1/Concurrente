package tp1;
import java.util.LinkedList;
public class Sistema {
	protected static int productCount = 0;
	protected static final int CAPACIDAD = 10;
	protected static final int PRODUCCION = 1000;
	protected static LinkedList<Object> cola; //la cola
	protected static final Object lock = new Object(); //instancio la clase object, y uso su token
	protected static final int ALFA = 100; //minimo de tiempo de consumo del consumidor
	protected static final int BETA = 200; //maximo de tiempo de consumo del consumidor
	public Sistema() 
{
	cola = new LinkedList<Object>();
	Productor p1 = new Productor(); //2 productores y 2 consumidores
	Productor p2 = new Productor();
    Productor p3 = new Productor();
    Productor p4 = new Productor();
	Consumidor c1= new Consumidor();
	Consumidor c2= new Consumidor();
	Thread t1= new Thread(p1, "Thread 1");
	Thread t2= new Thread(p2, "Thread 2");
	Thread t3= new Thread(p3, "Thread 3");
	Thread t4= new Thread(p4, "Thread 4");
	Thread t5= new Thread(c1, "Consumidor 1!");
	Thread t6= new Thread(c2, "Consumidor 2!");
	Log a = new Log(t5, t6);
	Thread t7 = new Thread(a);
	t1.start();
	t2.start();
	t3.start();
	t4.start();
	t5.start();
	t6.start();
	t7.start();
	}
}
