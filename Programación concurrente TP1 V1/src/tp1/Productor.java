package tp1;
public class Productor implements Runnable {
protected static int perdidos=0;
public void run() 
{
		synchronized(Sistema.lock) //lock unico de la clase sistema, que impide que 2 productores agreguen al mismo tiempo
		{
		while(Sistema.productCount<Sistema.PRODUCCION) {
		colocarProducto();
		}
}
		
}
	private void colocarProducto() 
	{
		    Sistema.productCount++;
			Object p = new Object(); //produce un NUEVO item en cada loop, en caso de no haber espacio, no se guarda p, y en la siguiente vuelta del loop, es sobreescrito
			  if(Sistema.cola.size()<Sistema.CAPACIDAD) 
			{
				Sistema.cola.offerFirst(p);  //pone "encima" al elemento como si fuera una LIFO
				}else {
					perdidos++;    
					System.out.println("UN PRODUCTO PERDIDO");
						 //cuenta de productos perdidos
					}
			System.out.println("ELEMENTOS EN LA COLA: "+Sistema.cola.size()+ " .Soy el  "+Thread.currentThread().getName());
			System.out.println("---->Se perdieron "+perdidos+" elementos.");
			try {Thread.sleep(1); //este sleep se podria sacar, solo esta para que las impresiones en consola sean un poco mas lentas y legibles
			}catch(Exception e) {}
			finally{
				Sistema.lock.notifyAll();
			    try {
			    	Sistema.lock.wait(); //este wait es para que un thread no acapare el lock
			    	}
			    catch(Exception e) {System.out.println("NO SE EJECUTO EL WAIT EN PRODUCTOR");}
			}
			
		
	}
	
}
