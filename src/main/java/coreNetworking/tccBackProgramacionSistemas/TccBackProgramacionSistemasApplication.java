package coreNetworking.tccBackProgramacionSistemas;

import coreNetworking.tccBackProgramacionSistemas.Modelo.Cancion;
import coreNetworking.tccBackProgramacionSistemas.Modelo.Genero;
import coreNetworking.tccBackProgramacionSistemas.Modelo.Interprete;
import coreNetworking.tccBackProgramacionSistemas.Servicio.ICancionServicio;
import coreNetworking.tccBackProgramacionSistemas.Servicio.IGeneroServicio;
import coreNetworking.tccBackProgramacionSistemas.Servicio.IInterpreteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class TccBackProgramacionSistemasApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(TccBackProgramacionSistemasApplication.class, args);
	}

	@Autowired
	private IInterpreteServicio servicioInterprete;

	@Autowired
	private IGeneroServicio servicioGenero;

	@Autowired
	private ICancionServicio servicioCancion;


	@Override
	public void run(String... args) throws Exception {

//		// Crear una nueva canción
//		Cancion nuevaCancion = new Cancion("Cancion Prueba", new Date(), 180, 0, 0, "Avici", "eletronic");
//		Cancion cancionGuardada = servicioCancion.registrar(nuevaCancion);
//		System.out.println("Canción creada: " + cancionGuardada);

//		// Leer (Listar todas las canciones)
//		List<Cancion> canciones = servicioCancion.listar();
//		System.out.println("Lista de canciones:");
//		canciones.forEach(c -> System.out.println(c));
//
//		// Leer (Listar una canción por ID)
//		int id = cancionGuardada.getIdCancion();
//		Cancion cancionPorId = servicioCancion.listarPorId(id);
//		System.out.println("Canción obtenida por ID: " + cancionPorId);
//
//		// Modificar una canción
//		cancionPorId.setNombreCancion("Cancion Modificada");
//		cancionPorId.setDuracion(200);  // Modificar duración
//		Cancion cancionActualizada = servicioCancion.modificar(cancionPorId);
//		System.out.println("Canción actualizada: " + cancionActualizada);
//
//		// Eliminar una canción por ID
//		servicioCancion.eliminar(id);
//		System.out.println("Canción eliminada con ID: " + id);
//
//		// Verificar que la canción ha sido eliminada
//		Cancion cancionEliminada = servicioCancion.listarPorId(id);
//		if (cancionEliminada == null) {
//			System.out.println("La canción ha sido eliminada correctamente.");
//		} else {
//			System.out.println("Error: la canción no fue eliminada.");
//		}

//		Genero genero=new Genero(4,"eletronic");
//		servicioGenero.registrar(genero);
//		Interprete interprete=new Interprete(3,"Avicii",new Date(1989,9,8),"Estocolmo, Suecia");
//		servicioInterprete.registrar(interprete);
//
//		List<Interprete> interpretes=new ArrayList<>();
//		interpretes.add(interprete);
//		Cancion cancion=new Cancion("The Nights"
//				, new Date(2015,5,10)
//				,311
//				,0,0,interpretes,genero);
//		Cancion cancion2=new Cancion("Wake Me Up"
//				, new Date(2013,6,28)
//				,433
//				,0,0,interpretes,genero);
//
//		servicioCancion.registrar(cancion);
//		servicioCancion.registrar(cancion2);
//		Genero genero=new Genero(10,"rap");
//		servicioGenero.registrar(genero);
//		Interprete interprete=new Interprete(30,"Tupac",new Date(1971,6,16),"New York");
//		servicioInterprete.registrar(interprete);
//
//		List<Interprete> interpretes=new ArrayList<>();
//		interpretes.add(interprete);
//		Cancion cancion=new Cancion("All Eyez on Me"
//				, new Date(1996,2,13)
//				,311
//				,0,0,interpretes,genero);
//		Cancion cancion2=new Cancion("Thug Nigga"
//				, new Date(2000,6,28)
//				,433
//				,0,0,interpretes,genero);
//
//		servicioCancion.registrar(cancion);
//		servicioCancion.registrar(cancion2);
//
		System.out.println("LISTADO DE CANCIONES ANTES DEL BORRADO:");
		servicioCancion.listar().forEach(System.out::println);

		System.out.println("BUSCAR LAS CANCIONES DE CADA INTÉRPRETE REGISTRADO:");
		for (Interprete interprete1 : servicioInterprete.listar()) {
			System.out.println("Intérprete: " + interprete1.getNombre());
			List<Cancion> cancionesList = interprete1.getCanciones();
			if (cancionesList.isEmpty()) {
				System.out.println("La lista de canciones está vacía...");
			}
			for (Cancion cancion1 : cancionesList) {
				System.out.println("Cancion: " + cancion1);
			}
		}


	}
}
