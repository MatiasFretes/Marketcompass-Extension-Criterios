import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MercadosUtils {

	public List<Mercado> buscarProductosEnMercados(List<String> productos) {
        List<Mercado> mercadosJson = MercadosMocks.obtenerMercadosTest();

        Map<Mercado, Long> cantidadProductosPorMercado = mercadosJson.stream()
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
