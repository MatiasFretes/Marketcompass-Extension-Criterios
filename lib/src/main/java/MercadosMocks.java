import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MercadosMocks {
	
	public static List<Mercado> obtenerMercadosTest(){
		List<Mercado> mercados = new ArrayList<>();
		mercados.add(new Mercado("A", Arrays.asList("P2")));
		mercados.add(new Mercado("B", Arrays.asList("P1", "P2")));
		
		return mercados;
	}
	
	public static List<Mercado> obtenerMercados(){
		List<Mercado> mercados = new ArrayList<>();
		
		mercados.add(new Mercado("Coto", Arrays.asList("leche", "queso", "pollo", "pescado", "manzana", "frutilla", "manaos", "cereza")));
		mercados.add(new Mercado("Carrefour", Arrays.asList("jabon", "queso", "jamon", "coca", "pan", "banana", "zanahoria")));
		mercados.add(new Mercado("Dia", Arrays.asList("pan", "queso", "jamon", "tomate", "arroz", "fideos", "ternera", "pan blanco")));
		
		return mercados;
	}
	

}
