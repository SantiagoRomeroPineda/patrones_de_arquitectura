/*
 * Asignatura: Patrones de Diseño de Software
 * Patrón Creacional - > Abstract Factory
 * Implementación para el Service de Empleados tipo Restfull
 */
package af.familiarest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import servicios.IServiciosEmpleados;

/**
 *
 * @author Fabrizio Bolaño
 */
public class ImplementacionRestEmpleados implements IServiciosEmpleados {
	private static  String[] EMPLEADOS = new String[]{"Juan Guillermo", "David", "Luis Fernando"};

	@Override
	public String[] getEmpleados() {
		try {
			URL url = new URL("http://0gj5e.mocklab.io/empleados");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP Error code : "
												   + conn.getResponseCode());
			}
			InputStreamReader in = new InputStreamReader(conn.getInputStream());
			BufferedReader br = new BufferedReader(in);
			String output;
			String jsonString = "";
			while ((output = br.readLine()) != null) {
				jsonString += output;
			}

			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray tsmresponse = jsonObject.getJSONArray("empleados");

			ArrayList<String> list = new ArrayList<String>();

			for (int i = 0; i < tsmresponse.length(); i++) {
				list.add((String) tsmresponse.get(i));
			}

			EMPLEADOS = list.toArray(new String[list.size()]);
			// System.out.println(list);
			conn.disconnect();
		} catch (Exception e) {
			System.out.println("Exception in NetClientGet:- " + e);
		}
		System.out.println("Tipo de Servicio: Restful");
		return EMPLEADOS;
	}

}