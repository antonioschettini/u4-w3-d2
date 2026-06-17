package antonioschettini;

import antonioschettini.dao.EventiDao;
import antonioschettini.dao.LocationDao;
import antonioschettini.dao.PartecipazioneDao;
import antonioschettini.dao.PersonaDao;
import antonioschettini.entities.Evento;
import antonioschettini.entities.Location;
import antonioschettini.entities.Partecipazione;
import antonioschettini.entities.Persona;
import antonioschettini.enums.Sesso;
import antonioschettini.enums.StatoPartecipazione;
import antonioschettini.enums.TipoEvento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Application {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("u4-w3-d2-pu");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EventiDao eventiDao = new EventiDao(entityManager);
        LocationDao locationDao = new LocationDao(entityManager);
        PersonaDao personaDao = new PersonaDao(entityManager);
        PartecipazioneDao partecipazioneDao = new PartecipazioneDao(entityManager);

        try {
            // Creo e salvo una location
            Location milanoSanSiro = new Location("Stadio San Siro", "Milano");
            locationDao.save(milanoSanSiro);

            // crep e salvo una persona
            Persona riccardo = new Persona("Riccardo", "Gulin", "riccardo.gulin@epicode.com", LocalDate.of(1990, 5, 20), Sesso.M);
            personaDao.save(riccardo);

            //creo un evento
            Evento concertoGreenDay = new Evento("Green Day a San.Siro", LocalDate.of(2026, 7, 7), "Concerto imperdibile", TipoEvento.PUBBLICO, 5000, milanoSanSiro);
            eventiDao.save(concertoGreenDay);

            //creo e salvo la partecipazione
            Partecipazione biglietto = new Partecipazione(riccardo, concertoGreenDay, StatoPartecipazione.CONFERMATA);
            partecipazioneDao.save(biglietto);
            System.out.println("Salvataggi effettuati correttamente");

            //Parte due, lettura dal db
            System.out.println("Lettura dal db");
            Evento eventoletto = eventiDao.getById(1);
            System.out.println("Evento recuperato dal db: " + eventoletto);
            System.out.println("Questo evento si terrà nella citta di: " + eventoletto.getLocation().getCitta());


        } catch (Exception e) {
            System.out.println("Si è verificato un problema: " + e.getMessage());
        } finally {
            entityManagerFactory.close();
            entityManager.close();
        }


        //Esercizio day2
//        Evento matrimonioAle = new Evento("Matrimonio Alessia", LocalDate.now(), "Matrimonio Imperdibile di Alessia", TipoEvento.PRIVATO, 150);
//        Evento concertoGreenDay = new Evento("Concerto Greenday", LocalDate.of(2026, 7, 25), "GreenDay a Bari", TipoEvento.PUBBLICO, 200);
//        Evento graduateEpicode = new Evento("Graduate Epicode", LocalDate.of(2026, 8, 1), "Graduate classe Febbraio", TipoEvento.PUBBLICO, 30);

//        eventiDao.save(matrimonioAle);
//        eventiDao.save(concertoGreenDay);
//        eventiDao.save(graduateEpicode);

//        try {
//            Evento eventoTrovato = eventiDao.getById(2);
//            System.out.println("l'evento richiesto è: " + eventoTrovato);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        try {
//            eventiDao.deleteById(2);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

    }
}
