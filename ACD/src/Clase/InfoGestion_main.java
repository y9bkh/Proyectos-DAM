package Clase;

import java.io.*;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class InfoGestion_main {

	public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException,
			ClassNotFoundException, SAXException, InterruptedException {
		// TODO Auto-generated method stub

		menu(); // Clase raíz

	}

	public static void menu() throws IOException, ParserConfigurationException, TransformerException,
			ClassNotFoundException, SAXException, InterruptedException {
		Scanner pt = new Scanner(System.in);
		// FRONT-END DEL MENU PRINCIPAL
		List<Alumno> Clase = new ArrayList<>(); // AQUI GUARDARE TODOS LOS DATOS DE LOS ALUMNOS CREADOS
		int seleccionMenu;

		// CREO UN BUCLE PARA QUE AL REALIZAR UN OPCION Y ESTA SEA FINALIZADA, PUES QUE
		// VUELVA AL MENU A NO SER QUE YO DECIDA SALIR TECLEANDO EL Nº 6
		do {
			System.out.println("\n--- {MENÚ PRINCIPAL} ---");
			System.out.println("1) Crear/Registrar Alumno");
			System.out.println("2) Eliminar Alumno");
			System.out.println("3) Listar/Mostrar Alumno");
			System.out.println("4) Guardar info en un fichero");
			System.out.println("5) Cargar desde un fichero");
			System.out.println("6) Salir");
			System.out.println();

			System.out.println("Seleccione una opcion: ");
			seleccionMenu = pt.nextInt();

			// LLAMO A TODOS LOS METODOS CREADOS EN SUS RESPECTIVAS OPCIONES
			if (seleccionMenu == 1) {
				registrarAlumno(Clase);
			} else if (seleccionMenu == 2) {
				eliminarAlumno(Clase);
			} else if (seleccionMenu == 3) {
				listarAlumnos(Clase);
			} else if (seleccionMenu == 4) {
				guardarInfo(Clase);
			} else if (seleccionMenu == 5) {
				cargarAlumnos(Clase);
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

		pt.close(); // CIERRO EL scanner PARA QUE NO HAYA CONFUSIONES.
	}

	public static void registrarAlumno(List<Alumno> Clase) {
		// CREO VAR QUE GUARDARAN LOS DATOS PARA LUEGO PASARSELO A MI arraylist
		String nombreApellidos;
		int Edad;
		String DNI;
		String Matricula;
		int registros = 0;

		Scanner pt = new Scanner(System.in); // SE CREA UN SCANNER PARA INTRODUCIR POR TECLADO LOS DATOS
		boolean entradaValida = false;
		while (!entradaValida) {
		    try {
		        System.out.print("\n¿Cuántos alumnos se registran?: ");
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
		

		pt.nextLine(); // LIMPIO EL BUFFER PARA NO CREAR CONFUSIONES
		for (int pos = 1; pos <= registros; pos++) {
			// SOLICITO PETICIONES PARA MI DOC
			System.out.print("Nombre y apellidos: ");
			nombreApellidos = pt.nextLine();

			System.out.print("\nEdad: ");
			Edad = pt.nextInt();
			pt.nextLine(); // Con esto limpio el buffer ya que cuando ejecutas un .nextInt() y despues otro
							// tipo hace el salto (pasa de la opcion de dni a matricula) por lo que debemos
							// ejecutarlo asi.

			System.out.print("\nDni con letra (8 números + 1 letra): ");
			DNI = pt.nextLine();

			System.out.println("\nMatricula (Nombre de fp + Curso): ");
			Matricula = pt.nextLine();

			Clase.add(new Alumno(nombreApellidos, Edad, DNI, Matricula)); // AÑADO AL ALUMNO CREADO A MI arraylist QUE
																			// MÁS
			

		}
	}

	public static void eliminarAlumno(List<Alumno> Clase) {
		Scanner pt = new Scanner(System.in);

		System.out.print("Teclee la posicion del alumno a eliminar (Desde la posicion 0): ");
		int posAlumno = pt.nextInt();

		if (Clase.size() < posAlumno) { // CREO UNA CONDICION DONDE SI LA posAlumno ES UN VALOR SUPERIOR QUE SALTE EL
										// ERROR PARA QUE NO SE ELIMINE Y VUELVA AL MENÚ
			System.out.println();
			System.out.println("             [Error de parametros...]");
			System.out.println();
		} else {
			Clase.remove(posAlumno); // ELIMINO ESA POSICION
		}

	}

	public static void listarAlumnos(List<Alumno> Clase) {

		if (Clase.isEmpty()) { // SI MI arraylist SE ENCUENTRA VACÍA PUES QUE HAGA LO SIGUIENTE:´
			System.out.println();
			System.out.println("             [No hay ningun alumno registrado...]");
		} else { // PERO SI NO LO ESTA QUE ME LO IMPRIMA
			for (int a = 0; a < Clase.size(); a++) {
				System.out.println(a + ")" + Clase.get(a)); // Esto llama al método toString() de la clase Alumno
				System.out.println();
			}
		}

	}

	public static void guardarInfo(List<Alumno> Clase)
			throws IOException, ParserConfigurationException, TransformerException {
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
			
			PrintWriter pw = new PrintWriter(new FileWriter(archivo + ".txt")); // CREO EL FICHERO
			pw.println(Clase);// CON ESTO SOBREESCRIBIRE SOBRE EL.
			pw.close();
		} else if (seleccionTipoInfo == 2) {
			System.out.print("Indica el nombre del archivo: ");
			String archivo = pt.next();
			System.out.println();
			
			try {
				FileOutputStream fos = new FileOutputStream(archivo + ".dat");
				ObjectOutputStream out = new ObjectOutputStream(fos);
				out.writeObject(Clase);
				out.close();
			} catch (IOException e) {
				System.out.println(e);
			}
		} else if (seleccionTipoInfo == 3) {
			System.out.print("Indica el nombre del archivo: ");
			String archivo = pt.next();
			// Declaro los objetos para crear el xml
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();

			// GENERO UN ELEMENTO RAIZ LLAMADO CLASE DONDE ESTARAN TODOS LOS ALUMNOS
			Element raiz = doc.createElement("Clase");
			doc.appendChild(raiz);

			// Grabar cada alumno del rraylist
			for (Alumno a : Clase) {

				// Creo el nodo RAIZ
				Element alumno = doc.createElement("Alumno");

				// Creo y añado el nodo Nombre al nodo raiz
				Element nombre = doc.createElement("Nombre");
				nombre.appendChild(doc.createTextNode(a.getNombreApellidos()));
				alumno.appendChild(nombre);

				Element edad = doc.createElement("Edad");
				edad.appendChild(doc.createTextNode(String.valueOf(a.getEdad()))); // Usamos el String.ValueOf(int) para
																					// expresar exactamente el valor
																					// entero en forma de String para el
																					// .xml
				alumno.appendChild(edad);

				Element dni = doc.createElement("Dni");
				dni.appendChild(doc.createTextNode(a.getDNI()));
				alumno.appendChild(dni);

				Element matricula = doc.createElement("Matricula");
				matricula.appendChild(doc.createTextNode(a.getMatricula()));
				alumno.appendChild(matricula);

				// A añadir el alumno al nodo raíz
				raiz.appendChild(alumno);
			}

			// Ahora transforma y guarda el XML después de añadir todos los alumnos
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(archivo + ".xml"));
			transformer.transform(source, result);

		}
	}

	public static void cargarAlumnos(List<Alumno> Clase) throws IOException, ParserConfigurationException,
			ClassNotFoundException, SAXException, InterruptedException {
		Scanner pt = new Scanner(System.in);
		// DOY 3 OPCIONES DE FICHERO
		System.out.println("1) Cargar archivo .txt");
		System.out.println("2) Cargar archivo .dat");
		System.out.println("3) Cargar archivo .xml");
		System.out.println();

		System.out.println("Seleccione una opcion: ");
		int seleccionTipoInfo = pt.nextInt();
		System.out.println();

		if (seleccionTipoInfo == 1) {
			    // Pedir el nombre del archivo .txt
			    System.out.print("Introduce el nombre del archivo (sin extensión): ");
			    String nombreArchivo = pt.next();
			    System.out.println();

			    BufferedReader bfReader = new BufferedReader(new FileReader(nombreArchivo + ".txt"));
			    String linea;
			    String nombreApellidos = "";
			    int edad = 0;
			    String dni = "";
			    String matricula = "";

			    while ((linea = bfReader.readLine()) != null) {
			        linea = linea.trim(); // Limpiar espacios en blanco al inicio y final de la línea

			        // Ignorar corchetes y líneas vacías, es decir, mientras haya lineas vacias y corcheas que siga recogiendo datos.
			        if (linea.equals("[") || linea.equals("]") || linea.isEmpty()) {
			            continue;
			        }

			        // Detectar el campo que se está leyendo
			        if (linea.startsWith("NombreApellidos:")) { // Comenzar 
			            nombreApellidos = linea.substring("NombreApellidos:".length()).trim();
			        } else if (linea.startsWith("Edad:")) {
			            edad = Integer.parseInt(linea.substring("Edad:".length()).trim());
			        } else if (linea.startsWith("Dni:")) {
			            dni = linea.substring("Dni:".length()).trim();
			        } else if (linea.startsWith("Matricula:")) {
			            matricula = linea.substring("Matricula:".length()).trim();

			            // Después de leer "Matricula", añadimos el alumno a la lista
			            Clase.add(new Alumno(nombreApellidos, edad, dni, matricula));

			            
			        }
			    }

			    // Cerrar el BufferedReader
			    bfReader.close();

			    System.out.println("Datos cargados exitosamente.");
		} else if (seleccionTipoInfo == 2) {
			System.out.print("Introduce el nombre del archivo: ");
			String nombreArchivo = pt.next();
			ObjectInputStream oInput = new ObjectInputStream(new FileInputStream(nombreArchivo));

			Clase = (List<Alumno>) oInput.readObject();

			for (Alumno a : Clase) {
				System.out.println();
				System.out.println(a);
			}
			oInput.close();
		} else if (seleccionTipoInfo == 3) {
			System.out.print("Introduce el nombre del archivo: ");
			String nombreArchivo = pt.next();

			// 1. Cargo el archivo
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance(); // Instancio el constructor que me
																						// creara una "herramienta" para
																						// poder leer el .xml
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder(); // Creo la herramienta que realmente lee el .xml
			Document doc = dBuilder.parse(nombreArchivo); // Le paso el nombre del .xml que va a leer

			NodeList alumnos = doc.getElementsByTagName("Alumno"); // Los nodos son "listas" que me guardan todos los
																	// elementos. Lo ultimo nos indica que busca todo lo
																	// que tenga la etiqueta de Alumno en nuestro .xml

			for (int i = 0; i < alumnos.getLength(); i++) {
				Node nodo = alumnos.item(i);
				if (nodo.getNodeType() == Node.ELEMENT_NODE) {
					Element elemento = (Element) nodo;
					String nombre = elemento.getElementsByTagName("Nombre").item(0).getTextContent();
					int edad = Integer.parseInt(elemento.getElementsByTagName("Edad").item(0).getTextContent());
					String dni = elemento.getElementsByTagName("Dni").item(0).getTextContent();
					String matricula = elemento.getElementsByTagName("Matricula").item(0).getTextContent();
					Clase.add(new Alumno(nombre, edad, dni, matricula));
				}
			}
			System.out.println("Cargando datos...");
			Thread.sleep(1000);
			System.out.println("Datos cargados...");

		}

	}

}