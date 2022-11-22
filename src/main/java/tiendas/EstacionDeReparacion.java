package tiendas;

import jugador.Jugador;
import jugador.Posicion;
import terreno.Entidad;
import terreno.TipoEntidad;

public class EstacionDeReparacion extends Entidad implements EstacionDeMantenimiento {
	public Posicion posicion;
	private static final int PRECIO_REPARACION = 10;
	private static final char LETRA = '!';
	private static final TipoEntidad TIPO = TipoEntidad.TIENDA;

	
	
	public EstacionDeReparacion(Posicion posicion) {
		super(posicion, TIPO, LETRA);
	}

	//Repara la nave del Jugador elegido según la opción de gasto elegido.
	public void vender(Jugador jugador, double gasto) {
		double hpPedidos = gasto/PRECIO_REPARACION;
		double hpFaltante = jugador.getNave().getMaxHP() - jugador.getNave().getHP();
		double hpReparados = hpPedidos < hpFaltante ? hpPedidos : hpFaltante;
		double gastoEfectivo = (hpReparados * PRECIO_REPARACION) < gasto ? (hpReparados * PRECIO_REPARACION) : gasto;
		
		if(jugador.hacerCompra(gastoEfectivo)) {
			jugador.getNave().repararDmg((int)hpReparados);
		}
	}

	@Override
	//Realiza la interacción entre el Jugador y la Tienda.
	public void interactuar(Jugador jugador) {
		int cantidad = VistaTiendasConsola.repair(jugador.getNave().getHP());
		vender(jugador, cantidad);
	}
}
