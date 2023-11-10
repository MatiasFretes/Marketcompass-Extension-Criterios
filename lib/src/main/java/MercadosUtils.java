import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MercadosUtils {

	public List<Mercado> buscarProductosEnMercados (List<String> productos) {

		List<String> productosUsuario = productos.stream().map(String::toLowerCase).collect(Collectors.toList());

		List<Mercado> mercadosConTodosLosProductos = new ArrayList<>();
		List<Mercado> mercadosConAlMenosUnProducto = new ArrayList<>();
		List<Mercado> mercadosJson = MercadosMocks.obtenerMercados();
		
		for (Mercado mercado : mercadosJson) {
		    List<String> productosMercado = mercado.getProductos().stream().map(String::toLowerCase).collect(Collectors.toList());
		    if (productosMercado.containsAll(productosUsuario))
		        mercadosConTodosLosProductos.add(mercado);
		    
		    if (!Collections.disjoint(productosMercado, productosUsuario)) 
		        mercadosConAlMenosUnProducto.add(mercado);
		}
		
		if (!mercadosConTodosLosProductos.isEmpty()) 
			return mercadosConTodosLosProductos;
		else if (!mercadosConAlMenosUnProducto.isEmpty()) 
		    return mercadosConAlMenosUnProducto;
		else 
		    return null;
	}
}
