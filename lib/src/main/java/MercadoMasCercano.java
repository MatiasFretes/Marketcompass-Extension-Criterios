import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import extensible.FiltradorPorCriterio;
import implementacion.MercadoCercanoImpl;
import modelo.Mercado;

public class MercadoMasCercano implements FiltradorPorCriterio{

	@Override
	public Mercado filtrar(List<Mercado> mercados) {
		try {		
			List<String> nombresDeMercados = mercados.stream().map(mercado -> mercado.getNombre()).collect(Collectors.toList());
			String coordenadaActual = MercadoCercanoImpl.obtenerUbicacionActual();
			String nombreDelMercadoMasCercano = MercadoCercanoImpl.buscarMercadoMasCercano(coordenadaActual, nombresDeMercados);
			
            Optional<Mercado> mercadoEncontrado = mercados.stream()
                .filter(mercado -> mercado.getNombre().equals(nombreDelMercadoMasCercano))
                .findFirst();

            return mercadoEncontrado.orElse(null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
