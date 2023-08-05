package db;

import java.util.Vector;

public class temp_app {
    public static void main(String[] args){
//        TransportMachine transportMachine = new TransportMachine(2, "ZIL", "Zil", "C", "A777AA58", "Cargo transport", 1978, false);
//        Crude.create(transportMachine);
//        TransportMachine transportMachine = new TransportMachine(2, "ZIL", "3310", "C", "A777AA58", "Cargo transport", 1974, false);
//        Crude.update(transportMachine);
        Vector<TransportMachine> transportMachines = Dao.get("select * from transport_machines");
        System.out.println(transportMachines);
    }
}
