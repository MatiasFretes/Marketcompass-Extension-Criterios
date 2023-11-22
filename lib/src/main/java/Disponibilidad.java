import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import extensible.SeleccionadorPorCriterio;

public class Disponibilidad implements SeleccionadorPorCriterio {
	
	List<String> mercados;
	
	public Disponibilidad(List<String> mercados) {
		this.mercados = mercados;
	}

    @Override
    public String seleccionarMercado(List<String> productos) {
    	MercadosUtils mercadosUtils = new MercadosUtils();
    	List<Mercado> mercadosConProductos = new ArrayList<>();
    	try {
			mercadosConProductos = mercadosUtils.buscarProductosEnMercados(productos, this.mercados);	
			if (mercadosConProductos == null || mercadosConProductos.isEmpty())
				return "";
			mercadosConProductos.sort(Comparator.comparing(Mercado::getNombre));

		} catch (Exception e) {
			e.printStackTrace();
		}
    	return mercadosConProductos.get(0).getNombre();
    }
}