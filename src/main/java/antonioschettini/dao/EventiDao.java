package antonioschettini.dao;

import antonioschettini.entities.Evento;
import antonioschettini.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

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
}
