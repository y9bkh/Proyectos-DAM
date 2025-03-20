package Departamento_Profesores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MainProfesores {

	public static void main(String[] args) throws InterruptedException, IOException, SQLException, ParserConfigurationException, TransformerException {
		// TODO Auto-generated method stub
		menu();
	}

	public static void lectorcsv(ArrayList<Profesores> Clase) throws IOException {		
		Connection conexion = null;
        String url8bdd = "jdbc:mysql://localhost:3306/profesores";
        String usuario = "root";
        String password = "";

        try {
            conexion = DriverManager.getConnection(url8bdd, usuario, password);
            conexion.setAutoCommit(false);

            String sentenciaSql = "INSERT INTO profesores (Nombre, Apellido, Edad, Asignatura, Correo_Electronico) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement prepared = conexion.prepareStatement(sentenciaSql);
                 BufferedReader br = new BufferedReader(new FileReader("profesores123.csv"))) {

                br.readLine();

                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] valores = linea.split(",");
                    
                    String Nombre = valores[0].trim();
                    String Apellido = valores[1].trim();
                    String Edad = valores[2].trim();
                    String Asignatura = valores[3].trim();
                    String Correo_Electronico = valores[4].trim();


                    prepared.setString(1, Nombre);
                    prepared.setString(2, Apellido);
                    prepared.setString(3, Edad);
                    prepared.setString(4, Asignatura);
                    prepared.setString(5, Correo_Electronico);

                    prepared.executeUpdate();
                }
                conexion.commit();
                conexion.setAutoCommit(true);

                System.out.println("Datos importados");

            } catch (IOException e) {
                System.out.println("Error al importar los datos.");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Error en la conexión a la base de datos: " + e.getMessage());
        } finally {
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }		
	}

	public static void menu() throws InterruptedException, IOException, SQLException, ParserConfigurationException, TransformerException {
		Scanner pt = new Scanner(System.in);
		
		// FRONT-END DEL MENU PRINCIPAL
		ArrayList<Profesores> Clase = new ArrayList<>(); // AQUI GUARDARE TODOS LOS DATOS DE LOS ALUMNOS CREADOS
		lectorcsv(Clase);
		
		int seleccionMenu;

		// CREO UN BUCLE PARA QUE AL REALIZAR UN OPCION Y ESTA SEA FINALIZADA, PUES QUE
		// VUELVA AL MENU A NO SER QUE YO DECIDA SALIR TECLEANDO EL Nº 6
		do {
			System.out.println("\n--- {MENÚ PRINCIPAL} ---");
			System.out.println("1) Añadir más registros de Profesor");
			System.out.println("2) Listar/Mostrar Profesor");
			System.out.println("3) Eliminar Profesor");
			System.out.println("4) Modificar campos de registros");
			System.out.println("5) Descargar info en un fichero");
			System.out.println("6) Salir");
			System.out.println();

			System.out.println("Seleccione una opcion: ");
			seleccionMenu = pt.nextInt();

			// LLAMO A TODOS LOS METODOS CREADOS EN SUS RESPECTIVAS OPCIONES
			if (seleccionMenu == 1) {
				añadirProfesor(Clase);
			} else if (seleccionMenu == 2) {
				listarProfesor();
			} else if (seleccionMenu == 3) {
				eliminarProfesor(Clase);
			} else if (seleccionMenu == 4) {
				modificar();
			} else if (seleccionMenu == 5) {
				descargar();
			} else if (seleccionMenu == 6) {
				System.out.println();
				System.out.println("Fin del programa con éxito..."); // LO PODRÍA HABER REALIZADO CON UN break(); PERO
																		// VEO MÁS EFICAZ ESTA FORMA YA
				// QUE FINALIZA EL PROGRAMA DE RAÍZ
				System.out.println();
			} else {
				System.out.println("\nError de parametros...");
			}
		} while (seleccionMenu != 6);
	}

	public static void añadirProfesor(ArrayList<Profesores> Clase) throws InterruptedException {
		int id;
		String nombre;
		String apellido;
		int edad;
		String asignatura;
		String correo;

		int registros = 0;

		Scanner pt = new Scanner(System.in);
		boolean entradaValida = false;
		while (!entradaValida) {
			try {
				System.out.print("\n¿Cuántos profesores se registran?: ");
				registros = pt.nextInt();

				if (registros <= 0) {
					System.out.println("\nError: El número de registros debe ser mayor a 0.");
				} else {
					entradaValida = true;
				}
			} catch (InputMismatchException e) {
				System.out.println("\nError: Debes introducir un número entero válido.");
				pt.next();
			}
		}

		pt.nextLine();
		for (int pos = 1; pos <= registros; pos++) {
			/*
			 * System.out.print("\nId: "); id = pt.nextInt(); pt.nextLine();
			 */

			System.out.print("Nombre: ");
			nombre = pt.nextLine();

			System.out.print("Apellido: ");
			apellido = pt.nextLine();

			System.out.print("Edad: ");
			edad = pt.nextInt();
			pt.nextLine();

			System.out.print("Asignatura: ");
			asignatura = pt.nextLine();

			System.out.print("Correo: ");
			correo = pt.nextLine();

			Clase.add(new Profesores(nombre, apellido, asignatura, edad, correo));
		}

		System.out.print("Introducir datos en BBDD [T/F]: ");
		String select = pt.nextLine();
		if (select.equals("T")) {
			Connection conexion = null;
			PreparedStatement sentencia = null;

			String urlBbdd = "jdbc:mysql://localhost:3306/profesores";
			String usuario = "root";
			String password = "";

			ResultSet resultado = null;

			try {
				conexion = DriverManager.getConnection(urlBbdd, usuario, password);

				// Inserto cada profesor en la base de datos
				for (Profesores a : Clase) {
					// Revisa que "correo" sea el nombre correcto de la columna en la tabla
					// "profesores"
					String sentenciaSql = "INSERT INTO profesores (nombre, apellido, edad, asignatura, correo_electronico) VALUES (?, ?, ?, ?, ?)";
					sentencia = conexion.prepareStatement(sentenciaSql);

					sentencia.setString(1, a.getNombre());
					sentencia.setString(2, a.getApellido());
					sentencia.setInt(3, a.getEdad());
					sentencia.setString(4, a.getAsignatura());
					sentencia.setString(5, a.getCorreo()); // Asegúrate de que la columna 'correo' existe

					sentencia.executeUpdate();
				}

				System.out.println("Datos insertados correctamente.");

			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				// Cerrar los recursos en el bloque finally para evitar fugas de recursos
				try {
					if (sentencia != null)
						sentencia.close();
					if (conexion != null)
						conexion.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			}
		}
	}

	public static void eliminarProfesor(ArrayList<Profesores> Clase) throws InterruptedException {
		Scanner pt = new Scanner(System.in);

		System.out.print("Teclee el nombre del profesor a eliminar: ");
		String nombre = pt.next();

		Connection conexion = null;
		PreparedStatement sentencia = null;

		String urlBbdd = "jdbc:mysql://localhost:3306/profesores";
		String usuario = "root";
		String password = "";

		ResultSet resultado = null;

		try {
			conexion = DriverManager.getConnection(urlBbdd, usuario, password);

			String sentenciaSql = "DELETE FROM profesores WHERE Nombre = ?"; // SE USA PARA SENTENCIAS DE INSERT, DELETE
			sentencia = conexion.prepareStatement(sentenciaSql);

			sentencia.setString(1, nombre);

			sentencia.executeUpdate();

			Thread.sleep(1000);
			System.out.println("\nDatos eliminados correctamente.");

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			// Cerrar los recursos en el bloque finally para evitar fugas de recursos
			try {
				if (sentencia != null)
					sentencia.close();
				if (conexion != null)
					conexion.close();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}

	public static void listarProfesor() throws InterruptedException {
		Connection conexion = null;
		PreparedStatement sentencia = null;

		String urlBbdd = "jdbc:mysql://localhost:3306/profesores";
		String usuario = "root";
		String password = "";

		ResultSet resultado = null;

		try {
			conexion = DriverManager.getConnection(urlBbdd, usuario, password);

			String sentenciaSql = "Select * FROM profesores";
			sentencia = conexion.prepareStatement(sentenciaSql);

			resultado = sentencia.executeQuery(); // SE USA PARA SENTENCIAS DE SELECT

			Thread.sleep(500);
			System.out.println("\nCargando datos...");
			System.out.println();
			Thread.sleep(500);

			while (resultado.next()) {
				// Supongamos que la tabla tiene columnas "nombre" y "apellido"
				String nombre = resultado.getString("Nombre");
				String apellido = resultado.getString("Apellido");
				int edad = resultado.getInt("Edad");
				String asignatura = resultado.getString("Asignatura");
				String correo = resultado.getString("Correo_Electronico");

				System.out.printf("Nombre: %-10s Apellido: %-15s Edad: %-5s Asignatura: %-15s Correo: %-20s%n", // Esto
																												// se
																												// hace
																												// para
																												// ordenar
																												// por
																												// caracteres
																												// lo
																												// que
																												// va
																												// imprimir
																												// la
																												// consola,
																												// es
																												// por
																												// pura
																												// decoración
																												// de
																												// código.
						nombre, apellido, edad, asignatura, correo);
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();

		} finally {
			// Cerrar los recursos en el bloque finally para evitar fugas de recursos
			try {
				if (sentencia != null)
					sentencia.close();
				if (conexion != null)
					conexion.close();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}

	public static void modificar() throws InterruptedException {
		Scanner pt = new Scanner(System.in);

		System.out.println("Introduce el id del usuario a modificar: ");
		int id = pt.nextInt();

		System.out.print("Introduce que campo quieres cambiar (Nombre, apellido, edad, etc): ");
		String campoModificar = pt.next();

		System.out.print("Introduce el nuevo valor para " + campoModificar + ": ");
		String nuevoValor = pt.next();

		Connection conexion = null;
		PreparedStatement sentencia = null;

		String urlBbdd = "jdbc:mysql://localhost:3306/profesores";
		String usuario = "root";
		String password = "";

		try {
			conexion = DriverManager.getConnection(urlBbdd, usuario, password); // Conecto con la bbdd

			String sentenciaSql = "UPDATE profesores SET " + campoModificar + " = ? WHERE id = ?"; // Sentencia que
																									// actualizara mi
																									// bbdd
			sentencia = conexion.prepareStatement(sentenciaSql);

			sentencia.setString(1, nuevoValor);
			sentencia.setInt(2, id);

			int resultado = sentencia.executeUpdate(); // SE USA PARA SENTENCIAS DE SELECT

			if (resultado > 0) {
				Thread.sleep(500);
				System.out.println("\nActualizando datos...");
				System.out.println();
				Thread.sleep(500);
				System.out
						.println("El campo " + campoModificar + " del usuario con ID " + id + " ha sido actualizado.");
			} else {
				Thread.sleep(500);
				System.out.println("\nNo se encontró ningún usuario con el ID proporcionado.");
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();

		} finally {
			// Cerrar los recursos en el bloque finally para evitar fugas de recursos
			try {
				if (sentencia != null)
					sentencia.close();
				if (conexion != null)
					conexion.close();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

	}

	public static void descargar() throws SQLException, ParserConfigurationException, TransformerException {
	    Scanner pt = new Scanner(System.in);
	    // DOY 3 OPCIONES DE FICHERO
	    System.out.println("1) .txt");
	    System.out.println("2) .dat");
	    System.out.println("3) .xml");
	    System.out.println();

	    System.out.print("Seleccione una opcion: ");
	    int seleccionTipoInfo = pt.nextInt();
	    System.out.println();

	    if (seleccionTipoInfo == 1) {
	        System.out.print("Indica el nombre del archivo: ");
	        String archivo = pt.next();
	        System.out.println();
	        
	        // Establecer la conexión a la base de datos
	        Connection conexion = null;
	        PreparedStatement sentencia = null;
	        ResultSet resultado = null;
	        String urlBbdd = "jdbc:mysql://localhost:3306/profesores";
	        String usuario = "root";
	        String password = "";

	        try {
	            // Crear la conexión con la base de datos
	            conexion = DriverManager.getConnection(urlBbdd, usuario, password);
	            
	            // Preparar la consulta SQL
	            String sentenciaSql = "SELECT * FROM profesores"; // Seleccionamos todos los registros de la tabla 'profesores'
	            sentencia = conexion.prepareStatement(sentenciaSql);
	            
	            // Ejecutar la consulta
	            resultado = sentencia.executeQuery();
	            
	            // Crear el archivo de salida en formato .txt
	            PrintWriter pw = new PrintWriter(new FileWriter(archivo + ".txt"));
	            
	            // Escribir los encabezados en el archivo .txt
	            pw.println("Nombre\tApellido\tEdad\tAsignatura\tCorreo Electrónico");
	            
	            // Iterar sobre los resultados y escribir cada registro en el archivo
	            while (resultado.next()) {
	                String nombre = resultado.getString("Nombre");
	                String apellido = resultado.getString("Apellido");
	                int edad = resultado.getInt("Edad");
	                String asignatura = resultado.getString("Asignatura");
	                String correo = resultado.getString("Correo_Electronico");

	                // Escribir la información de cada profesor en el archivo .txt de manera ordenada
	                pw.printf("%s\t%s\t%d\t%s\t%s%n", nombre, apellido, edad, asignatura, correo);
	            }
	            
	            
	            pw.close();
	            System.out.println("Datos exportados a " + archivo + ".txt");
	        } catch (SQLException | IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (sentencia != null) sentencia.close();
	                if (conexion != null) conexion.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    } else if (seleccionTipoInfo == 2) {
	        // Opción para .dat (binario)
	        System.out.print("Indica el nombre del archivo: ");
	        String archivo = pt.next();
	        System.out.println();
	        
	        Connection conexion = null;
	        PreparedStatement sentencia = null;
	        ResultSet resultado = null;
	        String urlBbdd = "jdbc:mysql://localhost:3306/profesores";
	        String usuario = "root";
	        String password = "";	        
	        
	        
	        try {
	        	conexion = DriverManager.getConnection(urlBbdd, usuario, password);
	        	
	        	String sentenciaSql = "SELECT * FROM profesores"; // Seleccionamos todos los registros de la tabla 'profesores'
	            sentencia = conexion.prepareStatement(sentenciaSql);
	            
	            resultado = sentencia.executeQuery();
	            
	            
	            FileOutputStream fos = new FileOutputStream(archivo + ".dat");
	            ObjectOutputStream out = new ObjectOutputStream(fos);  // 'Clase' es el ArrayList de objetos Profesores
	            
	            // Lista para almacenar los objetos Profesores
	            ArrayList<Profesores> listaProfesores = new ArrayList<>();
	            
	            while (resultado.next()) {
	            	String nombre = resultado.getString("Nombre");
	                String apellido = resultado.getString("Apellido");
	                int edad = resultado.getInt("Edad");
	                String asignatura = resultado.getString("Asignatura");
	                String correo = resultado.getString("Correo_Electronico");

	                Profesores profesor = new Profesores(nombre, apellido, asignatura, edad, correo);
	                listaProfesores.add(profesor);
	                
	            }
	           
                out.writeObject(listaProfesores);
	            out.close();
                System.out.println("Datos exportados a " + archivo + ".dat");
	        } catch (IOException e) {
	            System.out.println(e);
	        }
	    } else if(seleccionTipoInfo == 3) {
	    	System.out.print("Indica el nombre del archivo: ");
			String archivo = pt.next();
			
			// Establecer la conexión a la base de datos
	        Connection conexion = null;
	        PreparedStatement sentencia = null;
	        ResultSet resultado = null;
	        String urlBbdd = "jdbc:mysql://localhost:3306/profesores";
	        String usuario = "root";
	        String password = "";

	        try {
	            // Crear la conexión con la base de datos
	            conexion = DriverManager.getConnection(urlBbdd, usuario, password);
	            
	            // Preparar la consulta SQL
	            String sentenciaSql = "SELECT * FROM profesores"; // Seleccionamos todos los registros de la tabla 'profesores'
	            sentencia = conexion.prepareStatement(sentenciaSql);
	            
	            // Ejecutar la consulta
	            resultado = sentencia.executeQuery();
	            
	            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	            DocumentBuilder db = dbf.newDocumentBuilder();
	            Document doc = db.newDocument();

	            Element raiz = doc.createElement("Clase");
				doc.appendChild(raiz);
	            
				while (resultado.next()) {
	                // Crear un elemento <Profesor> por cada fila
	                Element profesor = doc.createElement("Profesor");

	                // Crear los elementos <Nombre>, <Apellido>, <Edad>, <Asignatura>, <Correo> para cada profesor
	                Element nombre = doc.createElement("Nombre");
	                nombre.appendChild(doc.createTextNode(resultado.getString("Nombre")));
	                profesor.appendChild(nombre);

	                Element apellido = doc.createElement("Apellido");
	                apellido.appendChild(doc.createTextNode(resultado.getString("Apellido")));
	                profesor.appendChild(apellido);

	                Element edad = doc.createElement("Edad");
	                edad.appendChild(doc.createTextNode(String.valueOf(resultado.getInt("Edad"))));
	                profesor.appendChild(edad);

	                Element asignatura = doc.createElement("Asignatura");
	                asignatura.appendChild(doc.createTextNode(resultado.getString("Asignatura")));
	                profesor.appendChild(asignatura);

	                Element correo = doc.createElement("Correo");
	                correo.appendChild(doc.createTextNode(resultado.getString("Correo_Electronico")));
	                profesor.appendChild(correo);

	                // Agregar el elemento <Profesor> a la raíz <Clase>
	                raiz.appendChild(profesor);
	            }
				
				 // Escribir el documento XML en un archivo
	            TransformerFactory transformerFactory = TransformerFactory.newInstance();
	            Transformer transformer = transformerFactory.newTransformer();
	            DOMSource source = new DOMSource(doc);
	            StreamResult result = new StreamResult(new File(archivo + ".xml"));

	            transformer.transform(source, result);

	            System.out.println("Datos exportados a " + archivo + ".xml");
					
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (sentencia != null) sentencia.close();
	                if (conexion != null) conexion.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } 		    	
	    }
	}

}
