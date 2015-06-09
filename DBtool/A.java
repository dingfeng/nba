package DBtool;

public class A {
   public void finalize()
   {
	   System.out.println("A is destroyed！");
   }
   public static void main(String[] args)
   {
	   new A();
	   new A();
	   long start = System.currentTimeMillis();
	   System.gc();
	   System.out.println(System.currentTimeMillis() - start);
	   System.out.println("end...");
	   while(true);
   }
}
