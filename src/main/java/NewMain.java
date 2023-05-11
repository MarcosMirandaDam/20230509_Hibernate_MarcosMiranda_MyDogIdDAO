import com.mycompany.zhibernate.dao.EventoDAOImplementacion;
import com.mycompany.zhibernate.dao.FormacionDAOImplementacion;
import com.mycompany.zhibernate.dao.MontaDAOImplementacion;
import com.mycompany.zhibernate.dao.PerroDAOImplementacion;
import com.mycompany.zhibernate.dao.PersonaDAOImplementacion;
import com.mycompany.zhibernate.dao.RegistroSaludDAOImplementacion;
import com.mycompany.zhibernate.modelo.Evento;
import com.mycompany.zhibernate.modelo.Formacion;
import com.mycompany.zhibernate.modelo.Monta;
import com.mycompany.zhibernate.modelo.Perro;
import com.mycompany.zhibernate.modelo.Persona;
import com.mycompany.zhibernate.modelo.RegistroSalud;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Clase principal que gestiona una app para gestionar la cria de perros, base de datos, mediante
 * hibernate, en su patrón DAO
 * @author sssoc
 */
public class NewMain {

    public static void main(String[] args) {
        // Configurar Hibernate
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        
        //creo unas personas aun sin perros en propiedad
        Persona persona1 = new Persona(1, "13158801H", "Marcos", "Miranda", "636146896", 46, null);
        Persona persona2 = new Persona(2, "32886806A", "Mariana", "Fernandez", "649187940", 45, null);
        Persona persona3 = new Persona(3, "34315837K", "Martina", "Miranda", "985695638", 6, null);
        PersonaDAOImplementacion personaDAOImplementacion = new PersonaDAOImplementacion(sessionFactory);
        personaDAOImplementacion.crear(persona1);
        personaDAOImplementacion.crear(persona2);
        personaDAOImplementacion.crear(persona3);
        
        
        // creo montas y las añado a la lista de montas
        List<Monta>listaMontas=new ArrayList<>();
        Monta monta1 = new Monta(1, "teckel", "Nicanor", 1234, "Jenny", 1233, new Date());
        MontaDAOImplementacion montaDAOImplementacion = new MontaDAOImplementacion(sessionFactory);
        montaDAOImplementacion.crear(monta1);
        listaMontas.add(monta1);
        
        // creo un registro de salud y lo añado a la lista correspondiente
        List<RegistroSalud>registrosSalud=new ArrayList<>();
        RegistroSalud registroSalud1 = new RegistroSalud(0, "La Pomar", "La Felguera", new Date(), "Vacuna Rabia", true, false, 26.90f, null);
        RegistroSaludDAOImplementacion registroSaludDAOImplementacion = new RegistroSaludDAOImplementacion(sessionFactory);
        registroSaludDAOImplementacion.crear(registroSalud1);
        registrosSalud.add(registroSalud1);
        
        // creo una formacion y la añado a la lista de formaciones correspondiente
        List<Formacion>formaciones=new ArrayList<>();
        Formacion formacionA = new Formacion(0, "Agility", "Gijon", "Marco Antonio", new Date(), null);
        FormacionDAOImplementacion formacionDAOImplementacion = new FormacionDAOImplementacion(sessionFactory);
        formacionDAOImplementacion.crear(formacionA);
        formaciones.add(formacionA);
        
        // creo un evento y le añado a la lista de eventos correspondiente
        List<Evento>eventos=new ArrayList<>();
        Evento eventoA = new Evento(0, "Festival Belleza", "Tineo", "Belleza", "Jose Angel", new Date(), null);
        EventoDAOImplementacion eventoDAOImplementacion = new EventoDAOImplementacion(sessionFactory);
        eventoDAOImplementacion.crear(eventoA);
        eventos.add(eventoA);
        
        //creo un perro y añado alguna lista... o las deseadas
        List<Perro>listaPerros=new ArrayList<>();
        Perro perro1 = new Perro(0, "Saskia", "1001", "1245", new Date(), "hembra", 1200,listaMontas, registrosSalud, eventos, formaciones);
        PerroDAOImplementacion perroDAOImplementacion = new PerroDAOImplementacion(sessionFactory);
        perroDAOImplementacion.crear(perro1);
        listaPerros.add(perro1);
        
        //-----------------------------------------------------------------------------------------------
        //OPERACIONES POSTERIORES TRAS FUNCIONAMIENTO CORRECTO PERSISTENCIA EN WORKBENCH DEL PROGRAMA
        //ASIGNO la lista de perros a Martina, mostramos sus nombres
        System.out.println("**********MOSTRANDO NOMBRES DE LOS PERROS********"); 
        PersonaDAOImplementacion personaDAOImplementacion1 = new PersonaDAOImplementacion(sessionFactory);
        persona3.setListaPerros(listaPerros);
        personaDAOImplementacion1.actualizar(persona3);
        for (Perro perro : listaPerros) {
            System.out.println("Nombres del los perros--->"+perro.getNombre());
        }
        
        System.out.println("**********MOSTRANDO LISTA DE PERSONAS********");
        List<Persona> obtenerTodos = personaDAOImplementacion1.obtenerTodos();
        for (Persona persona : obtenerTodos) {
            System.out.println(persona.toString());
        }
        
        System.out.println("**********MOSTRANDO TODOS LOS REGISTRO DE SALUD POR DESCRIPCION********"); 
        String descripcion="Vacuna Rabia";
        RegistroSaludDAOImplementacion registroSaludDAOImplementacion1 = new RegistroSaludDAOImplementacion(sessionFactory);
        List<RegistroSalud> findByDescripionContainingIgnoreCase = registroSaludDAOImplementacion1.findByDescripionContainingIgnoreCase(descripcion);
        for (RegistroSalud registroSalud : findByDescripionContainingIgnoreCase) {
            System.out.println(registroSalud.toString());
        }
        
        
        
        
        
        
        
        
        
        sessionFactory.close();
        
        
    }
    
}
