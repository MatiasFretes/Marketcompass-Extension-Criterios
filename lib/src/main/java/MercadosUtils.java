import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MercadosUtils {

	public List<Mercado> buscarProductosEnMercados(List<String> productos, List<String> nombreMercados) {
        List<Mercado> mercadosDisponibles = MercadosMocks.obtenerMercadosTest();
        
        List<Mercado> mercadosFiltrados = mercadosDisponibles.stream()
                .filter(mercado -> nombreMercados.contains(mercado.getNombre()))
                .collect(Collectors.toList());

        Map<Mercado, Long> cantidadProductosPorMercado = mercadosFiltrados.stream()
                .collect(Collectors.toMap(
                        mercado -> mercado,
                        mercado -> productos.stream().filter(mercado.getProductos()::contains).count()
                ));

        long maxCantidadProductos = cantidadProductosPorMercado.values().stream()
                .max(Comparator.naturalOrder())
                .orElse(0L);
        
        if(maxCantidadProductos == 0) return new ArrayList<>();

        List<Mercado> mercadosConMaxCantidadProductos = cantidadProductosPorMercado.entrySet().stream()
                .filter(entry -> entry.getValue() == maxCantidadProductos)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return mercadosConMaxCantidadProductos;
    }
}
