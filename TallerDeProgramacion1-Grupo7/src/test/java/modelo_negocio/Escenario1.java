package modelo_negocio;

import modeloNegocio.Empresa;

public class Escenario1 {

    public void setup() throws Exception{
        Empresa.getInstance().agregarCliente("facundo","123","Facundo");
        Empresa.getInstance().agregarCliente("thiago","321","Thiago");
        Empresa.getInstance().agregarCliente("nehuen","4567","Nehuen");
    }

    public void teardown(){
        Empresa.getInstance().getClientes().clear();
    }
}