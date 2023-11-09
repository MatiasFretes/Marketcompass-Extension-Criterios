import java.util.Arrays;

public class main {

	public static void main(String[] args) {
		Distancia distancia = new Distancia();

		System.out.println(distancia.seleccionarMercado(Arrays.asList("P2")));
		
		
		Precio precio = new Precio();

		System.out.println(precio.seleccionarMercado(Arrays.asList("P2")));
	}

}
