import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import extensible.SeleccionadorPorCriterio;

public class Precio implements SeleccionadorPorCriterio{
	
	List<String> mercados;
	
	public Precio(List<String> mercados) {
		this.mercados = mercados;
	}

	@Override
	public String seleccionarMercado(List<String> productos) {
    	MercadosUtils mercadosJsonParser = new MercadosUtils();
    	List<Mercado> mercadosConProductos = new ArrayList<>();
    	try {
			mercadosConProductos = mercadosJsonParser.buscarProductosEnMercados(productos, this.mercados);	
			if (mercadosConProductos == null || mercadosConProductos.isEmpty())
				return "";
			mercadosConProductos.sort(Comparator.comparing(Mercado::getNombre));

		} catch (Exception e) {
			e.printStackTrace();
		}
    	return mercadosConProductos.get(mercadosConProductos.size() - 1).getNombre();
	}
}
