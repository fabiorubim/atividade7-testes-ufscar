import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.resolvedor.Cliente;
import br.com.resolvedor.ClienteRepo;
import br.com.resolvedor.Resolvedor;

public class ResolvedorTest {

    @Test
    public void testDefinirPromocaoMaiorIgualTresClientesGanha25PorcentoDesconto() throws Exception {
    	ClienteRepo mockClienteRepo = Mockito.mock(ClienteRepo.class);
         
        Resolvedor resolvedor = new Resolvedor(mockClienteRepo);
        int[] cods = {1, 2, 3};

        Cliente cliente1 = new Cliente(1, "João");
        Cliente cliente2 = new Cliente(2, "Pedro");
        Cliente cliente3 = new Cliente(3, "Ana");

        when(mockClienteRepo.getCliente(1)).thenReturn(cliente1);
        when(mockClienteRepo.getCliente(2)).thenReturn(cliente2);
        when(mockClienteRepo.getCliente(3)).thenReturn(cliente3);

        ArrayList<Cliente> resultado = resolvedor.definirPromocao(cods);

        for (Cliente cliente : resultado) {
            assertEquals(25, cliente.getDesconto());
        }
    }

    @Test
    public void testDefinirPromocaoUmClienteGanha15PorcentoDesconto() throws Exception {
    	ClienteRepo mockClienteRepo = Mockito.mock(ClienteRepo.class);
    	
        Resolvedor resolvedor = new Resolvedor(mockClienteRepo);
        int[] cods = {1};

        Cliente cliente1 = new Cliente(1, "José");

        when(mockClienteRepo.getCliente(1)).thenReturn(cliente1);

        ArrayList<Cliente> resultado = resolvedor.definirPromocao(cods);

        assertEquals(15, resultado.get(0).getDesconto());
    }

    @Test
    public void testDefinirPromocaoPrimeiroClienteGanha15PorcentoDescontoSegundoClienteGanha10PorcentoDesconto() throws Exception {
    	ClienteRepo mockClienteRepo = Mockito.mock(ClienteRepo.class);
    	
        Resolvedor resolvedor = new Resolvedor(mockClienteRepo);
        int[] cods = {1, 2};

        Cliente cliente1 = new Cliente(1, "Ana");
        Cliente cliente2 = new Cliente(2, "Pedro");

        when(mockClienteRepo.getCliente(1)).thenReturn(cliente1);
        when(mockClienteRepo.getCliente(2)).thenReturn(cliente2);

        ArrayList<Cliente> resultado = resolvedor.definirPromocao(cods);

        assertEquals(15, resultado.get(0).getDesconto());
        assertEquals(10, resultado.get(1).getDesconto());
    }

    @Test
    public void testDefinirPromocaoCodigosVazios() {
    	ClienteRepo mockClienteRepo = Mockito.mock(ClienteRepo.class);
    	
        Resolvedor resolvedor = new Resolvedor(mockClienteRepo);
        int[] cods = {};

        assertThrows(IllegalArgumentException.class, () -> resolvedor.definirPromocao(cods));
    }

    @Test
    public void testDefinirPromocaoCodigoInvalido() throws Exception {
    	ClienteRepo mockClienteRepo = Mockito.mock(ClienteRepo.class);
    	
        Resolvedor resolvedor = new Resolvedor(mockClienteRepo);
        int[] cods = {1};

        when(mockClienteRepo.getCliente(1)).thenReturn(null);

        assertThrows(Exception.class, () -> resolvedor.definirPromocao(cods));
    }

}
