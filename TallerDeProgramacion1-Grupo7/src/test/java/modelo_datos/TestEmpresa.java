package modelo_datos;

import static org.junit.Assert.*;

import excepciones.*;
import modeloDatos.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modeloNegocio.Empresa;
import util.Constantes;

import java.util.ArrayList;
import java.util.HashMap;

public class TestEmpresa {

    private Empresa empresa;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private Chofer chofer, choferSinViajes;
    private Pedido pedido1,pedido2;
    private Viaje viaje1, viaje2;

    @Before
    public void setUp() throws ClienteNoExisteException, ClienteConViajePendienteException, SinVehiculoParaPedidoException, ClienteConPedidoPendienteException, UsuarioYaExisteException, ChoferRepetidoException, VehiculoRepetidoException {
        empresa = Empresa.getInstance();

        cliente = new Cliente("Sofia1", "123456789", "Sofia Palladino");
        vehiculo = new Moto("AAA111");
        chofer = new ChoferTemporario("1111111", "Carlos P");
        choferSinViajes = new ChoferTemporario("222222", "Laura E");
        empresa.agregarCliente("Sofia1", "123456789", "Sofia Palladino");

        empresa.agregarChofer(chofer);
        empresa.agregarChofer(choferSinViajes);
        empresa.agregarVehiculo(vehiculo);

        pedido1 = new Pedido(cliente, 2, false, false, 10, Constantes.ZONA_STANDARD);
        pedido2 = new Pedido(cliente, 1, false, false, 5, Constantes.ZONA_STANDARD);


        viaje1 = new Viaje(pedido1, chofer, vehiculo);
        empresa.getViajesIniciados().put(cliente,viaje1);


    }


    @After
    public void tearDown() {
        empresa.getClientes().clear();
        empresa.getChoferes().clear();
        empresa.getVehiculos().clear();
        empresa.getPedidos().clear();
    }

    @Test
    public void testAgregarClienteExitoso() {
        try {
            Empresa.getInstance().agregarCliente("ClienteNuevo","123456","Cliente Nuevo");
        } catch (UsuarioYaExisteException e) {
            fail("Segun el escenario planteado el usuario ClienteNuevo existe en el sistema");
        }
        assertNotNull(Empresa.getInstance().getClientes().get("ClienteNuevo"));
        assertEquals("Cliente Nuevo", Empresa.getInstance().getClientes().get("ClienteNuevo").getNombreReal());
    }

    @Test
    public void testAgregarClienteRepetido() {
        try {
            Empresa.getInstance().agregarCliente("Sofia1", "123456789", "Sofia Palladino");
            fail("Se esperaba una excepción UsuarioYaExisteException");
        } catch (UsuarioYaExisteException e) {
            fail("El usuario Sofia1 ya existe");
        }
    }

    @Test
    public void testAgregarPedidoExitoso() {
        Cliente cliente = empresa.getClientes().get("Sofia1");

        Pedido pedido = new Pedido(cliente, 1, false, false, 10, "ZONA_PELIGROSA");

        try {
            empresa.agregarPedido(pedido);

            assertTrue("El pedido no se encontró en la lista de pedidos", empresa.getPedidos().containsKey(cliente));
            assertEquals("El pedido agregado no es el esperado", pedido, empresa.getPedidos().get(cliente));

        } catch (ClienteNoExisteException e) {
            fail("Se esperaba que el cliente existiera, pero ocurrió una excepción: " + e.getMessage());
        } catch (ClienteConViajePendienteException e) {
            fail("Se esperaba que el pedido se agregara, pero el cliente tiene un viaje pendiente: " + e.getMessage());
        } catch (SinVehiculoParaPedidoException e) {
            fail("Se esperaba que el pedido se agregara, pero no hay vehículo disponible: " + e.getMessage());
        } catch (ClienteConPedidoPendienteException e) {
            fail("Se esperaba que el pedido se agregara, pero el cliente ya tiene un pedido pendiente: " + e.getMessage());
        }
    }


    @Test
    public void testAgregarPedidoNoCumple() {
        Cliente cliente = empresa.getClientes().get("Sofia1");
        Pedido pedido = new Pedido(cliente, 5, true, true, 10, "ZONA_PELIGROSA");
        try {
            empresa.agregarPedido(pedido);
            assertTrue("El pedido no se encontró en la lista de pedidos", empresa.getPedidos().containsKey(cliente));
            assertEquals("El pedido agregado no es el esperado", pedido, empresa.getPedidos().get(cliente));
        } catch (ClienteNoExisteException e) {
            fail("Se esperaba que el cliente existiera, pero ocurrió una excepción: " + e.getMessage());
        } catch (ClienteConViajePendienteException e) {
            fail("Se esperaba que el pedido se agregara, pero el cliente tiene un viaje pendiente: " + e.getMessage());
        } catch (SinVehiculoParaPedidoException e) {
            fail("Se esperaba que el pedido se agregara, pero no hay vehículo disponible: " + e.getMessage());
        } catch (ClienteConPedidoPendienteException e) {
            fail("Se esperaba que el pedido se agregara, pero el cliente ya tiene un pedido pendiente: " + e.getMessage());
        }
    }

    @Test
    public void testAgregarChoferExitoso() {
        Chofer nuevoChofer = new ChoferTemporario("7777777", "Nuevo Chofer");
        try {
            Empresa.getInstance().agregarChofer(nuevoChofer);
            assertEquals("El DNI del chofer agregado no es el esperado", "7777777", Empresa.getInstance().getChoferes().get("7777777").getDni());
        } catch (ChoferRepetidoException e) {
            fail("Se esperaba que el chofer se agregara exitosamente, pero ocurrió una excepción: " + e.getMessage());
        }
    }

    @Test
    public void testAgregarChoferDuplicado() {
        try {
            Empresa.getInstance().agregarChofer(chofer); // Ya fue agregado en el setup
            fail("Se esperaba una excepción ChoferRepetidoException");
        } catch (ChoferRepetidoException e) {
            fail("El chofer con DNI 111111 ya está registrado");
        }
    }

    @Test
    public void testAgregarVehiculoExitoso() {
        Vehiculo nuevoVehiculo = new Moto("XYZ789");
        try {
            Empresa.getInstance().agregarVehiculo(nuevoVehiculo);
            assertEquals("La patente del vehículo agregado no es la esperada", "XYZ789", Empresa.getInstance().getVehiculos().get("XYZ789").getPatente());
        } catch (VehiculoRepetidoException e) {
            fail("Se esperaba que el vehículo se agregara exitosamente, pero ocurrió una excepción: " + e.getMessage());
        }
    }

    @Test
    public void testAgregarVehiculoDuplicado() {
        try {
            Empresa.getInstance().agregarVehiculo(vehiculo); // Ya fue agregado en el setup
            fail("Se esperaba una excepción VehiculoRepetidoException");
        } catch (VehiculoRepetidoException e) {
            fail("El vehiculo con patente abc123 ya está registrado");
        }
    }

    @Test
    public void testValidarPedidoConVehiculoValido() {
        Pedido pedido = new Pedido(cliente, 1,false,false,20,"ZONA_STANDARD");
        assertTrue("Debería encontrar un vehículo adecuado.", empresa.validarPedido(pedido));
    }

    @Test
    public void testValidarPedidoConVehiculoNoValidoPorPlazas() {
        Pedido pedido = new Pedido(cliente, 6,false,false,20,"ZONA_STANDARD");
        assertTrue("No debería encontrar un vehículo adecuado.", empresa.validarPedido(pedido));
    }

    @Test
    public void testValidarPedidoConVehiculoNoValidoPorMascota() {
        Pedido pedido = new Pedido(cliente, 1,true,false,20,"ZONA_STANDARD");
        assertTrue("No debería encontrar un vehículo adecuado.", empresa.validarPedido(pedido));
    }

    @Test
    public void testValidarPedidoConVehiculoNoValidoPorBaul() {
        Pedido pedido = new Pedido(cliente, 1,false,true,20,"ZONA_STANDARD");
        assertTrue("No debería encontrar un vehículo adecuado.", empresa.validarPedido(pedido));
    }

    @Test
    public void testObtenerClientes() {
        HashMap<String, Cliente> clientes = empresa.getClientes();
        assertNotNull(clientes);
        assertFalse(clientes.containsKey("Sofia1"));
    }

    @Test
    public void testCalificacionDeChoferSinViajes(){
        try {
             empresa.calificacionDeChofer(choferSinViajes);
             fail("Se esperaba que se lanzara una excepción SinViajesException");
        } catch (SinViajesException e) {
            assertNotNull("La excepción SinViajesException debería haber sido lanzada", e);
            assertEquals("El chofer no tiene viajes registrados", e.getMessage());
        }
    }



}
