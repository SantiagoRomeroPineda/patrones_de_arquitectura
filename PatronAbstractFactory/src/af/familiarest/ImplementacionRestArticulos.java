/*
 * Asignatura: Patrones de Diseño de Software
 * Patrón Creacional - > Abstract Factory
 * Implementación para el Service de Articulos tipo Restfull
 */
package af.familiarest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import servicios.IServiciosArticulos;


/**
 *
 * @author Fabrizio Bolaño
 */
public class ImplementacionRestArticulos implements IServiciosArticulos {
	private static  String[] ARTICULOS = new String[]{"Disco Duro", "Teclados", "Mouse"};

	@Override
	public String[] getArticulos() {
		try {
			URL url = new URL("http://0gj5e.mocklab.io/articulos");
			// try (BufferedReader reader = new BufferedReader(new
			// InputStreamReader(url.openStream(), "UTF-8"))) {
			// for (String line; (line = reader.readLine()) != null;) {
			// System.out.println(line);
			// }
			// }
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
			JSONArray tsmresponse = jsonObject.getJSONArray("articulos");

			ArrayList<String> list = new ArrayList<String>();

			for (int i = 0; i < tsmresponse.length(); i++) {
				list.add((String) tsmresponse.get(i));
			}

			ARTICULOS = list.toArray(new String[list.size()]);
			// System.out.println(list);
			conn.disconnect();
		} catch (Exception e) {
			System.out.println("Exception in NetClientGet:- " + e);
		}
		System.out.println("Tipo de Servicio: Restful");
		return ARTICULOS;
	}



}
