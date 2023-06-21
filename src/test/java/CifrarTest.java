import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import br.com.cifrar.AClass;
import br.com.cifrar.Coder1;
import br.com.cifrar.Coder2;
import br.com.cifrar.Coder3;

public class CifrarTest {

	@Test
	public void testCifrarCoder1Coder2ReturnarC1C2() {
		ArrayList<String> msg = new ArrayList<>();
		msg.add("Hello");
		Coder1 mockCoder1 = mock(Coder1.class);
		Coder2 mockCoder2 = mock(Coder2.class);
		when(mockCoder1.m1(msg)).thenReturn(true);
		when(mockCoder2.m2(msg)).thenReturn(true);
		AClass aClass = new AClass(mockCoder1, mockCoder2, mock(Coder3.class));
		
		String result = aClass.cifrar(msg);

		assertEquals("C1-C2", result);
	}

	@Test
	public void testCifrarDoisOuMaisHi() {
		ArrayList<String> msg = new ArrayList<>();
		msg.add("HI");
		msg.add("Hola!");
		msg.add("HI");
		Coder3 mockCoder3 = mock(Coder3.class);
		when(mockCoder3.m3()).thenReturn(2);
		AClass aClass = new AClass(mock(Coder1.class), mock(Coder2.class), mockCoder3);

		String result = aClass.cifrar(msg);

		assertEquals("2 OR MORE HIs", result);
	}

	@Test
	public void testCifrarMaiorDoQueUmRetornarPrimeiroElementoRepetido() {
		ArrayList<String> msg = new ArrayList<>();
		msg.add("Hello");
		Coder3 mockCoder3 = mock(Coder3.class);
		when(mockCoder3.m3()).thenReturn(2);
		AClass aClass = new AClass(mock(Coder1.class), mock(Coder2.class), mockCoder3);
		
		String result = aClass.cifrar(msg);

		assertEquals("-Hello-Hello", result);
	}

	@Test
	public void testCifrarCasoDefaultRetornaPrimeiroElemento() {
		ArrayList<String> msg = new ArrayList<>();
		msg.add("Test");
		AClass aClass = new AClass(mock(Coder1.class), mock(Coder2.class), mock(Coder3.class));

		String result = aClass.cifrar(msg);

		assertEquals("Test", result);
	}
	
	@Test
	public void testCifrarMensagemNula() {
		ArrayList<String> msg = null;
		AClass aClass = new AClass(mock(Coder1.class), mock(Coder2.class), mock(Coder3.class));

		String result = aClass.cifrar(msg);

		assertEquals("INVALID", result);
	}
	
	@Test
	public void testCifrarPalavaStop() {
		ArrayList<String> msg = new ArrayList<>();
		msg.add("STOP");
		AClass aClass = new AClass(mock(Coder1.class), mock(Coder2.class), mock(Coder3.class));

		String result = aClass.cifrar(msg);

		assertEquals("INVALID", result);
	}
	
	@Test
	public void testCifrarListaVazia() {
		ArrayList<String> msg = new ArrayList<>();
		AClass aClass = new AClass(mock(Coder1.class), mock(Coder2.class), mock(Coder3.class));

		String result = aClass.cifrar(msg);

		assertEquals("INVALID", result);
	}
}
