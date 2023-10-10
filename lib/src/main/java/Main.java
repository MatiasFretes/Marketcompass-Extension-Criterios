import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import modelo.Mercado;

public class Main {

	public static void main(String[] args) {
		Mercado mercado1 = new Mercado();
		mercado1.setNombre("Dia");
		mercado1.setProductos(Arrays.asList("P1", "P2", "P3"));
		Mercado mercado2 = new Mercado();
		mercado2.setNombre("Coto");
		mercado2.setProductos(Arrays.asList("P1", "P3"));
		Mercado mercado3 = new Mercado();
		mercado3.setNombre("Carrefour");
		mercado3.setProductos(Arrays.asList("P1"));
		
		List<Mercado> mercados = new ArrayList<Mercado>();
		mercados.add(mercado1);
		mercados.add(mercado2);
		mercados.add(mercado3);
		
        MercadoMasCercano filtro = new MercadoMasCercano();
        Mercado mercadoCercano = filtro.filtrar(mercados);
        
        if (mercadoCercano != null) {
            System.out.println("La respuesta del filtro es: " + mercadoCercano);
        } else {
            System.out.println("No se encontró ningún mercado cercano.");
        }
	}

}
