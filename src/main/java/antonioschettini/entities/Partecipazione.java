package antonioschettini.entities;

import antonioschettini.enums.StatoPartecipazione;
import jakarta.persistence.*;

@Entity
@Table(name = "partecipazione")
public class Partecipazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "stato", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatoPartecipazione stato;

    // Relazione manytoone molte partecipazioni posso appartenere ad una persona
    @ManyToOne
    @JoinColumn(name = "persona_id", nullable = false)
    private Persona persona;

    //Relazione manytone molte partecipazioni possono appartenere allo stesso evento
    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    public Partecipazione() {
    }

    public Partecipazione(Persona persona, Evento evento, StatoPartecipazione stato) {
        this.persona = persona;
        this.evento = evento;
        this.stato = stato;
    }

    public long getId() {
        return id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public StatoPartecipazione getStato() {
        return stato;
    }

    public void setStato(StatoPartecipazione stato) {
        this.stato = stato;
    }

    @Override
    public String toString() {
        return "Partecipazione{" +
                "id=" + id +
                ", persona=" + (persona != null ? persona.getCognome() : "Nessuna") +
                ", evento=" + (evento != null ? evento.getTitolo() : "Nessuno") +
                ", stato=" + stato +
                '}';
    }
}
