import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<String>mercados = new ArrayList<>();
		
		mercados.add("A");
		mercados.add("B");
		
		Disponibilidad disponibilidad = new Disponibilidad(mercados);

		System.out.println(disponibilidad.seleccionarMercado(Arrays.asList("P2")));
		
	}

}
