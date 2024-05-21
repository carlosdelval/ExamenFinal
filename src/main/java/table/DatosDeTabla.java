package table;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import controller.ControladorSocio;
import model.Equipo;
import model.Socio;


public class DatosDeTabla {

	/** 
	 * 
	 * @return
	 */
	public static String[] getTitulosColumnas() {
		return new String[] {"Nombre", "1º Apellido", "2º Apellido", "Fecha Nacimiento"};
	}

	/**
	 * 
	 * @return
	 */
	public static Object[][] getDatosDeTabla(Equipo e, String filtro) {
		ControladorSocio cs = new ControladorSocio();
		List<Socio> socios = new ArrayList<Socio>();
		if(filtro.equals("")) socios = e.getSocios();
		else socios = (List<Socio>)cs.findByOrder(e.getId(), filtro);
		DateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");
		// Preparo una estructura para pasar al constructor de la JTable
		Object[][] datos = new Object[socios.size()][4];
		// Cargo los datos de la lista de personas en la matriz de los datos
		for (int i = 0; i < socios.size(); i++) {
			Socio s = socios.get(i);
			datos[i][0] = s.getNombre();
			datos[i][1] = s.getApellido1();
			datos[i][2] = s.getApellido2();
			datos[i][3] = sdf.format(s.getFechaNacimiento());
		}
		
		return datos;
	}
	
	
}
