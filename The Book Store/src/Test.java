import java.util.*;

class Laptop {
    int ram;
    int memory;
    final long time;
    Laptop(int ram , int memory ) {
        this.ram = ram;
        this.memory = memory;
        time = new Date().getTime();
    }

    void set() {

    }
    void view() {
        System.out.println("RAM : " + ram);
        System.out.println("Memory : " + memory);
        Date d = new Date(time);
        Calendar date = Calendar.getInstance();
        date.setTime(d);
        System.out.println("Date : "+date.get(Calendar.DATE)+":"+date.get(Calendar.MONTH)+":"+date.get(Calendar.YEAR));
    }
}

public class Test {
    public static void main(String[]  args) {
        ArrayList<Laptop> laptops = new ArrayList<>();
        laptops.add(new Laptop(2,120));
        laptops.add(new Laptop(16,2048));
        laptops.add(new Laptop(6,360));
        laptops.add(new Laptop(4,240));
        laptops.add(new Laptop(12,1024));
        laptops.add(new Laptop(8,5610));

       String s = "snfosd";
       String sd = "a";
       s.compareToIgnoreCase(sd);

    }
}