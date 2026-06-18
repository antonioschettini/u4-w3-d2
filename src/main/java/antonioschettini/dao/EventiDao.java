package antonioschettini.dao;

import antonioschettini.entities.*;
import antonioschettini.enums.GenereConcerto;
import antonioschettini.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class EventiDao {
    // il dao è data access object, in questa istanza andrò ad inserire i metodi per il collegamento al db

    // passo come unico attributo e costruttore per questa istanza l'oggetto entitymanager che mi permetterà
    // di istanziare i suoi metodi
    private final EntityManager entityManager;

    public EventiDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //Metodi per collegamento a db CRUD
    public void save(Evento nuovoEvento) {
        // inizio a creare il metodo di salvataggio a db
        // 1 per prima cosa creo la transazione
        EntityTransaction transaction = this.entityManager.getTransaction();

        // inserisco un try catch qua per limitare al dao la gestione dell'errore
        try {
            //2 faccio partire la transazione
            transaction.begin();

            //3 ho bisogno che l'oggetto che proverò a salvare a db sia MANAGED prima di committarlo a db
            this.entityManager.persist(nuovoEvento);

            //4 Adesso effettuo il commit ad db per inserire il nuovo evento
            transaction.commit();

            //5 Log di sicurezza per avvenuto salvataggio
            System.out.println("l' evento " + nuovoEvento.getTitolo() + " è stato salvato correttamente nel DB");
        } catch (Exception e) {
            System.out.println("Errore durante il salvataggio dell'evento : " + e.getMessage());
        }


    }

    public Evento getById(long id) {
        // funzione per trovare un evento dall'id
        Evento eventoFromDb = this.entityManager.find(Evento.class, id); // se non trova l'id avrò un null gestiamolo con un controllo condizionale
        if (eventoFromDb == null) throw new NotFoundException(id);
        return eventoFromDb;
    }

    public void deleteById(long id) {
        try {
            // riciclo la mia funzione per cercare un evento dall'id
            Evento eventoDalDb = this.getById(id);

            //Creo la transazione
            EntityTransaction transaction = this.entityManager.getTransaction();

            //Faccio partire la transazione
            transaction.begin();

            //Effettuo il managed per la rimozione
            this.entityManager.remove(eventoDalDb);

            //effettuo il commit
            transaction.commit();

            // sout per avvenuta cancellazione
            System.out.println("L'evento " + eventoDalDb.getTitolo() + " è stato correttamente eliminato dal DB");
        } catch (NotFoundException e) {
            System.out.println("impossibile cancellare l'evento con id : " + id + " " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Errore durante la cancellazione: " + e.getMessage());
        }

    }

    // Aggiungo i nuovi metodi
    public List<Concerto> getConcertiInStreaming(boolean inStreaming) {
        TypedQuery<Concerto> query = this.entityManager.createQuery(
                "SELECT c FROM Concerto c WHERE c.inStreaming= :parametroSteaming", Concerto.class
        );
        query.setParameter("parametroSteaming", inStreaming);
        return query.getResultList();
    }

    public List<Concerto> getConcertiPerGenere(GenereConcerto genere) {
        TypedQuery<Concerto> query = this.entityManager.createQuery(
                "SELECT c FROM Concerto c WHERE c.genere=:parametroGenere", Concerto.class
        );
        query.setParameter("parametroGenere", genere);
        return query.getResultList();
    }

    //Metodi aggiunti task nuova
    public List<PartitaDiCalcio> getPartiteVinteInCasa() {
        TypedQuery<PartitaDiCalcio> query = this.entityManager.createNamedQuery("PartitaDiCalcio.vinteInCasa", PartitaDiCalcio.class);
        return query.getResultList();
    }

    public List<PartitaDiCalcio> getPartiteVinteInTrasferta() {
        TypedQuery<PartitaDiCalcio> query = this.entityManager.createNamedQuery("PartitaDiCalcio.vinteInTrasferta", PartitaDiCalcio.class);
        return query.getResultList();
    }

    public List<PartitaDiCalcio> getPartitePareggiate() {
        TypedQuery<PartitaDiCalcio> query = this.entityManager.createNamedQuery("PartitaDiCalcio.pareggiate", PartitaDiCalcio.class);
        return query.getResultList();
    }

    public List<GaraDiAtletica> getGareVinteDaAtleta(Persona atleta) {
        TypedQuery<GaraDiAtletica> query = this.entityManager.createNamedQuery("GaraDiAtletica.perVincitore", GaraDiAtletica.class);
        query.setParameter("parametroVincitore", atleta);
        return query.getResultList();
    }

    public List<GaraDiAtletica> getGareACuiPartecipaAlteta(Persona atleta) {
        TypedQuery<GaraDiAtletica> query = this.entityManager.createNamedQuery("GaraDiAtletica.perPartecipante", GaraDiAtletica.class);
        query.setParameter("parametroAtleta", atleta);
        return query.getResultList();
    }

    public List<Evento> getEventiSoldOut() {
        TypedQuery<Evento> query = this.entityManager.createNamedQuery("Evento.soldOut", Evento.class);
        return query.getResultList();
    }
}
