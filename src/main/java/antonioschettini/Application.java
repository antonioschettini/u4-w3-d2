package antonioschettini;

import antonioschettini.dao.EventiDao;
import antonioschettini.entities.Evento;
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

        Evento matrimonioAle = new Evento("Matrimonio Alessia", LocalDate.now(), "Matrimonio Imperdibile di Alessia", TipoEvento.PRIVATO, 150);
        Evento concertoGreenDay = new Evento("Concerto Greenday", LocalDate.of(2026, 7, 25), "GreenDay a Bari", TipoEvento.PUBBLICO, 200);
        Evento graduateEpicode = new Evento("Graduate Epicode", LocalDate.of(2026, 8, 1), "Graduate classe Febbraio", TipoEvento.PUBBLICO, 30);

//        eventiDao.save(matrimonioAle);
//        eventiDao.save(concertoGreenDay);
//        eventiDao.save(graduateEpicode);

        try {
            Evento eventoTrovato = eventiDao.getById(2);
            System.out.println("l'evento richiesto è: " + eventoTrovato);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            eventiDao.deleteById(2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
