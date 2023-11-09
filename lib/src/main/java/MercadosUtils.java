import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MercadosUtils {

	public List<Mercado> obtenerMercadosDelJson() throws Exception{
		ObjectMapper mapper = new ObjectMapper(); 
	    return mapper.readValue(Files.readAllBytes(Paths.get("src/test/resources/mercados.json")), new TypeReference<List<Mercado>>() {});
	}
	
	public List<Mercado> buscarProductosEnMercados (List<String> productos) throws Exception {
		List<Mercado> mercadosConTodosLosProductos = new ArrayList<>();
		List<Mercado> mercadosConAlMenosUnProducto = new ArrayList<>();
		
		List<Mercado> mercados = obtenerMercadosDelJson();
		
		for (Mercado mercado : mercados) {
		    List<String> productosMercado = mercado.getProductos();
		    if (productosMercado.containsAll(productos))
		        mercadosConTodosLosProductos.add(mercado);
		    
		    if (!Collections.disjoint(productosMercado, productos)) 
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
