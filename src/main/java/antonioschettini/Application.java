package antonioschettini;

import antonioschettini.dao.EventiDao;
import antonioschettini.dao.LocationDao;
import antonioschettini.dao.PartecipazioneDao;
import antonioschettini.dao.PersonaDao;
import antonioschettini.entities.*;
import antonioschettini.enums.GenereConcerto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class Application {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("u4-w3-d2-pu");

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EventiDao eventiDao = new EventiDao(entityManager);
        LocationDao locationDao = new LocationDao(entityManager);
        PersonaDao personaDao = new PersonaDao(entityManager);
        PartecipazioneDao partecipazioneDao = new PartecipazioneDao(entityManager);


        // Creazione e salvataggio iniziale dei dati a DB
//        Location olimpico = new Location("Stadio Olimpico", "Roma");
//        locationDao.save(olimpico);
//
//        Persona antonio = new Persona("Antonio", "Schettini", "antonio@esempio.com", LocalDate.of(1996, 4, 12), Sesso.M);
//        Persona pippo = new Persona("Pippo", "Franco", "pippo@esempio.com", LocalDate.of(1990, 1, 1), Sesso.M);
//        personaDao.save(antonio);
//        personaDao.save(pippo);
//
//        Concerto concertoRock = new Concerto("Rock Legends", LocalDate.of(2026, 9, 10), "Serata rock", TipoEvento.PUBBLICO, 40000, olimpico, GenereConcerto.ROCK, false);
//        eventiDao.save(concertoRock);
//
//        PartitaDiCalcio derby = new PartitaDiCalcio("Derby", LocalDate.of(2026, 10, 5), "Partita di campionato", TipoEvento.PUBBLICO, 50000, olimpico, "Roma", "Lazio", "Roma", 3, 1);
//        PartitaDiCalcio pareggio = new PartitaDiCalcio("Amichevole", LocalDate.of(2026, 10, 12), "Test estivo", TipoEvento.PUBBLICO, 15000, olimpico, "Roma", "Real Madrid", null, 0, 0);
//        eventiDao.save(derby);
//        eventiDao.save(pareggio);
//
//        List<Persona> atleti = new ArrayList<>();
//        atleti.add(antonio);
//        atleti.add(pippo);
//        GaraDiAtletica corsa = new GaraDiAtletica("200m Piani", LocalDate.of(2026, 11, 1), "Gara di corsa", TipoEvento.PUBBLICO, 8, olimpico, atleti, antonio);
//        eventiDao.save(corsa);
//
//        // Creo un concerto speciale con 1 solo posto per testare facilmente il Sold Out
//        Concerto miniLive = new Concerto("Mini Live", LocalDate.of(2026, 12, 1), "Posti limitati", TipoEvento.PUBBLICO, 1, olimpico, GenereConcerto.POP, true);
//        eventiDao.save(miniLive);
//        Partecipazione bigliettoSoldOut = new Partecipazione(antonio, miniLive, StatoPartecipazione.CONFERMATA);
//        partecipazioneDao.save(bigliettoSoldOut);


        // Recupero degli elementi tramite id
        System.out.println("--- Recupero elementi dal database ---");
        Persona antonioFromDb = personaDao.getById(1L);
        Persona pippoFromDb = personaDao.getById(2L);
        Evento miniLiveFromDb = eventiDao.getById(5L);


        // Esecuzione dei test sulle nuove query
        System.out.println("--- Risultati dei test di oggi ---");

        List<Concerto> inStreaming = eventiDao.getConcertiInStreaming(true);
        System.out.println("Concerti in streaming: " + inStreaming.size());

        List<Concerto> concertiRock = eventiDao.getConcertiPerGenere(GenereConcerto.ROCK);
        System.out.println("Concerti Pop/Rock trovati: " + concertiRock.size());

        List<PartitaDiCalcio> vinteInCasa = eventiDao.getPartiteVinteInCasa();
        System.out.println("Partite vinte dalla squadra in casa: " + vinteInCasa.size());

        List<PartitaDiCalcio> pareggiate = eventiDao.getPartitePareggiate();
        System.out.println("Partite finite in pareggio: " + pareggiate.size());

        List<GaraDiAtletica> vinteDaAntonio = eventiDao.getGareVinteDaAtleta(antonioFromDb);
        System.out.println("Gare vinte da Antonio: " + vinteDaAntonio.size());

        List<GaraDiAtletica> partecipateDaPippo = eventiDao.getGareACuiPartecipaAlteta(pippoFromDb);
        System.out.println("Gare a cui prende parte Pippo: " + partecipateDaPippo.size());

        List<Evento> tuttiISoldOut = eventiDao.getEventiSoldOut();
        System.out.println("Eventi che hanno esaurito i biglietti: " + tuttiISoldOut.size());

        List<Partecipazione> bigliettiDelMiniLive = partecipazioneDao.getPartecipazioniPerEvento(miniLiveFromDb);
        System.out.println("Biglietti trovati per il mini live: " + bigliettiDelMiniLive.size());

        System.out.println("--- Fine dei test ---");


        //Esercizio day3
//        try {
//            // Creo e salvo una location
//            Location milanoSanSiro = new Location("Stadio San Siro", "Milano");
//            locationDao.save(milanoSanSiro);
//
//            // crep e salvo una persona
//            Persona riccardo = new Persona("Riccardo", "Gulin", "riccardo.gulin@epicode.com", LocalDate.of(1990, 5, 20), Sesso.M);
//            personaDao.save(riccardo);
//
//            //creo un evento (Cambiato in Concerto perché Evento adesso è astratta)
//            Concerto concertoGreenDay = new Concerto("Green Day a San.Siro", LocalDate.of(2026, 7, 7), "Concerto imperdibile", TipoEvento.PUBBLICO, 5000, milanoSanSiro, GenereConcerto.ROCK, false);
//            eventiDao.save(concertoGreenDay);
//
//            //creo e salvo la partecipazione
//            Partecipazione biglietto = new Partecipazione(riccardo, concertoGreenDay, StatoPartecipazione.CONFERMATA);
//            partecipazioneDao.save(biglietto);
//            System.out.println("Salvataggi effettuati correttamente");
//
//            //Parte due, lettura dal db
//            System.out.println("Lettura dal db");
//            Evento eventoletto = eventiDao.getById(1);
//            System.out.println("Evento recuperato dal db: " + eventoletto);
//            System.out.println("Questo evento si terrà nella citta di: " + eventoletto.getLocation().getCitta());
//
//
//        } catch (Exception e) {
//            System.out.println("Si è verificato un problema: " + e.getMessage());
//        } finally {
//            entityManagerFactory.close();
//            entityManager.close();
//        }


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

        // Chiusura finale delle risorse
        entityManager.close();
        entityManagerFactory.close();
    }
}