package modelo_negocio;

import excepciones.*;
import modeloDatos.*;
import modeloNegocio.Empresa;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.Constantes;
import util.Mensajes;

import java.util.HashMap;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

public class EmpresaBaseTest {
    @Before
    public void setUp() throws Exception {
        Empresa.getInstance();
    }

    @After
    public void tearDown() throws Exception {
        Empresa.getInstance().setUsuarioLogeado(null);
        Empresa.getInstance().getHistorialViajeCliente(Empresa.getInstance().getClientes().get("facundo")).clear();
        Empresa.getInstance().getHistorialViajeCliente(Empresa.getInstance().getClientes().get("thiago")).clear();
        Empresa.getInstance().getHistorialViajeCliente(Empresa.getInstance().getClientes().get("nehuen")).clear();
        Empresa.getInstance().getChoferes().clear();
        Empresa.getInstance().getVehiculos().clear();
        Empresa.getInstance().getVehiculosDesocupados().clear();
        Empresa.getInstance().getChoferesDesocupados().clear();
        Empresa.getInstance().getClientes().clear();
        Empresa.getInstance().getPedidos().clear();
        Empresa.getInstance().getViajesIniciados().clear();
    }


    @Test
    public void testLoginAdmin(){
        try {
            Empresa.getInstance().login("admin","admin");
            Assert.assertTrue("Se logueo un Administrador",Empresa.getInstance().isAdmin());
        } catch (UsuarioNoExisteException e) {
            fail("No deberia tirar este error porque el admin ya deberia estar contemplado");
        } catch (PasswordErroneaException e) {
            fail("No deberia tirar este error porque la contasenia admin ya deberia estar contemplado");
        }
    }

    @Test
    public void testLoginAdminMalUsuario(){
        try {
            Empresa.getInstance().login("a","admin");
            fail("Se logueo un Administrador");
        } catch (UsuarioNoExisteException e) {
            assertEquals("El mensaje de la excepcion no es correcto ",e.getMessage(), Mensajes.USUARIO_DESCONOCIDO.getValor());
            assertEquals("El mensaje de la excepcion no es correcto ",e.getUsuarioPretendido(), "a");
        } catch (PasswordErroneaException e) {
            fail("No deberia tirar este error");
        }
    }

    @Test
    public void testLoginAdminMalContra(){
        try {
            Empresa.getInstance().login("admin","123");
            fail("Se logueo un Administrador");
        } catch (UsuarioNoExisteException e) {
            fail("No deberia tirar este error");
        } catch (PasswordErroneaException e) {
            assertEquals("El mensaje de la excepcion no es correcto ",e.getMessage(), Mensajes.PASS_ERRONEO.getValor());
            assertEquals("El mensaje de la excepcion no es correcto ",e.getUsuarioPretendido(), "admin");
            assertEquals("El mensaje de la excepcion no es correcto ",e.getPasswordPretendida(), "123");
        }
    }

    @Test
    public void testLoginUsuarioNoExiste(){
        try {
            Empresa.getInstance().login("a","123");
            fail("Se logueo un Cliente");
        } catch (UsuarioNoExisteException e) {
            assertEquals("El mensaje de la excepcion no es correcto ",e.getMessage(), Mensajes.USUARIO_DESCONOCIDO.getValor());
            assertEquals("El mensaje de la excepcion no es correcto ",e.getUsuarioPretendido(), "a");

        } catch (PasswordErroneaException e) {
            fail("No deberia tirar este error");
        }
    }


    @Test
    public void testLoginAdmin_5(){
        try {
            Empresa.getInstance().login("ADMIN","admin");
            fail("Se logueo un Administrador");
        } catch (UsuarioNoExisteException e) {
            assertEquals("El mensaje de la excepcion no es correcto ",e.getMessage(), Mensajes.USUARIO_DESCONOCIDO.getValor());
            assertEquals("El mensaje de la excepcion no es correcto ",e.getUsuarioPretendido(), "ADMIN");
        } catch (PasswordErroneaException e) {
            fail("No deberia tirar este error");
        }
    }

    @Test
    public void testLoginAdmin_6(){
        try {
            Empresa.getInstance().login("admin","ADMIN");
            fail("Se logueo un Administrador");
        } catch (UsuarioNoExisteException e) {
            fail("No deberia tirar este error");
        } catch (PasswordErroneaException e) {
            assertEquals("El mensaje de la excepcion no es correcto ",e.getMessage(), Mensajes.PASS_ERRONEO.getValor());
            assertEquals("El mensaje de la excepcion no es correcto ",e.getUsuarioPretendido(), "admin");
            assertEquals("El mensaje de la excepcion no es correcto ",e.getPasswordPretendida(), "ADMIN");
        }
    }

    @Test
    public void testLoginAdmin_7(){
        try {
            Empresa.getInstance().login("Admin","admin");
            fail("Se logueo un Administrador");
        } catch (UsuarioNoExisteException e) {
            assertEquals("El mensaje de la excepcion no es correcto ",e.getMessage(), Mensajes.USUARIO_DESCONOCIDO.getValor());
            assertEquals("El mensaje de la excepcion no es correcto ",e.getUsuarioPretendido(), "Admin");

        } catch (PasswordErroneaException e) {
            fail("No deberia tirar este error");
        }
    }

    @Test
    public void testLoginAdmin_8(){
        try {
            Empresa.getInstance().login("admin","Admin");
            fail("Se logueo un Administrador");
        } catch (UsuarioNoExisteException e) {
            fail("No deberia tirar este error");
        } catch (PasswordErroneaException e) {
            assertEquals("El mensaje de la excepcion no es correcto ",e.getMessage(), Mensajes.PASS_ERRONEO.getValor());
            assertEquals("El mensaje de la excepcion no es correcto ",e.getUsuarioPretendido(), "admin");
            assertEquals("El mensaje de la excepcion no es correcto ",e.getPasswordPretendida(), "Admin");
        }
    }



    @Test
    public void logoutAdminTest() {
        try {
            Empresa.getInstance().login("admin","admin");
            Empresa.getInstance().logout();

            assertNull("El no se deslogueo el administrador", Empresa.getInstance().getUsuarioLogeado());
        } catch (UsuarioNoExisteException | PasswordErroneaException e ) {
            fail("no deberia tirar ninguna excepcion");
        }
    }


    @Test
    public void getSalariosTest() {
        try{
            Assert.assertEquals("",0.0, Empresa.getInstance().getTotalSalarios(),0.001);
        }catch (Exception e){
            fail("No deberia lanzar ninguna excepcion");
        }
    }

    @Test
    public void validarPedidoNoExisteVehiculoTest() {
        Pedido p = new Pedido(new Cliente("a","111","a a"),1,false,false,10,Constantes.ZONA_SIN_ASFALTAR);
        Assert.assertFalse("Se supone que los vehiculos disponibles esta vacio",Empresa.getInstance().validarPedido(p));
    }
}
