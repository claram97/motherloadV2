package tiendas;

import java.util.HashMap;
import java.util.Map;
import jugador.Jugador;
import jugador.Posicion;
import mejoras.MejoraDinamita;
import mejoras.MejoraHullRepairNanobots;
import mejoras.MejoraTanqueExtra;
import mejoras.MejoraTeleport;
import mejoras.Usable;
import terreno.Entidad;
import terreno.TipoEntidad;

public class TiendaDeConsumibles extends Entidad {
	Posicion posicion;
	private static final char LETRA = '*';
	private static final TipoEntidad TIPO = TipoEntidad.TIENDA;
	Map<Character, Usable> usables;
	
	public TiendaDeConsumibles(Posicion posicion) {
		super(posicion, TIPO, LETRA);
		this.posicion = posicion;
		this.usables = new HashMap<>();
		inicializarConsumibles();
	}
	
	//Inicializa las mejoras en el map.
	public void inicializarConsumibles() {
		this.usables.put('R', new MejoraHullRepairNanobots());
		this.usables.put('X', new MejoraTanqueExtra());
		this.usables.put('T', new MejoraTeleport());
		this.usables.put('D', new MejoraDinamita(null));
	}
	
	//Le vende la opción dada al Jugador dado.
	public void vender(Jugador jugador, char opcion) {
		Usable objeto = usables.get(opcion);
		
		if(objeto == null) {
			return;
		}
		
		if(jugador.hacerCompra(objeto.getCosto())) {
			jugador.getInventario().agregarUsable(objeto);
		}
	}
	

	@Override
	//Permite al Jugador dado interactuar con la Tienda actual.
	public void interactuar(Jugador jugador) {
		char opcion = VistaTiendasConsola.consumibles();
		vender(jugador, opcion);
	}
}
