/*package modelo_negocio;

import excepciones.*;
import modeloDatos.*;
import modeloNegocio.Empresa;
import util.Constantes;

import java.util.ArrayList;

public class Escenario5 {
    private ChoferPermanente chofer1;
    private ChoferPermanente chofer2;
    private ChoferTemporario chofer3;
    private ChoferTemporario chofer4;

    public Escenario5() {

    }

    public void setup() throws Exception{
        Empresa.getInstance();

        chofer1=new ChoferPermanente("1234567","Roberto",2020,0);
        chofer2=new ChoferPermanente("1234568","Alberto",2019,3);
        chofer3=new ChoferTemporario("11111111","Javier");
        chofer4=new ChoferTemporario("22222222","Sergio");

        Vehiculo auto1=new Auto("abc123",1,true);
        Vehiculo auto2=new Auto("dfg456",4,false);
        Vehiculo moto1=new Moto("pat333");

        Empresa.getInstance().agregarCliente("facundo","123","Facundo");
        Empresa.getInstance().agregarCliente("thiago","321","Thiago");
        Empresa.getInstance().agregarCliente("nehuen","4567","Nehuen");


        Empresa.getInstance().agregarChofer(chofer1);
        Empresa.getInstance().agregarChofer(chofer2);
        Empresa.getInstance().agregarChofer(chofer3);
        Empresa.getInstance().agregarChofer(chofer4);

        Empresa.getInstance().agregarVehiculo(auto1);
        Empresa.getInstance().agregarVehiculo(auto2);
        Empresa.getInstance().agregarVehiculo(moto1);

        Pedido pedido1=new Pedido(Empresa.getInstance().getClientes().get("facundo"), 3,true,true,10, Constantes.ZONA_PELIGROSA);
        Pedido pedido2=new Pedido(Empresa.getInstance().getClientes().get("thiago"),1,true,false,3,Constantes.ZONA_STANDARD);
        Pedido pedido3=new Pedido(Empresa.getInstance().getClientes().get("nehuen"),8,true,true,1,Constantes.ZONA_PELIGROSA);

        Empresa.getInstance().agregarPedido(pedido1);
        Empresa.getInstance().agregarPedido(pedido2);
        Empresa.getInstance().agregarPedido(pedido3);

        ArrayList<Chofer> chofdesocupados= new ArrayList<>();
        chofdesocupados.add(chofer1);
        chofdesocupados.add(chofer2);
        chofdesocupados.add(chofer3);
        chofdesocupados.add(chofer4);
        Empresa.getInstance().setChoferesDesocupados(chofdesocupados);


        ArrayList<Vehiculo> vehdesocupados= new ArrayList<>();
        vehdesocupados.add(auto1);
        vehdesocupados.add(auto2);
        vehdesocupados.add(moto1);
        Empresa.getInstance().setVehiculosDesocupados(vehdesocupados);
    }

    public void teardown(){
        Empresa.getInstance().getHistorialViajeCliente(Empresa.getInstance().getClientes().get("facundo")).clear();
        Empresa.getInstance().getHistorialViajeCliente(Empresa.getInstance().getClientes().get("thiago")).clear();
        Empresa.getInstance().getHistorialViajeCliente(Empresa.getInstance().getClientes().get("nehuen")).clear();
        Empresa.getInstance().getChoferes().clear();
        Empresa.getInstance().getVehiculos().clear();
        Empresa.getInstance().getVehiculosDesocupados().clear();
        Empresa.getInstance().getChoferesDesocupados().clear();
        Empresa.getInstance().getClientes().clear();
        Empresa.getInstance().getPedidos().clear();
        Empresa.getInstance().getHistorialViajeChofer(chofer1).clear();
        Empresa.getInstance().getHistorialViajeChofer(chofer2).clear();
        Empresa.getInstance().getHistorialViajeChofer(chofer3).clear();
        Empresa.getInstance().getHistorialViajeChofer(chofer4).clear();
    }
}
*/
