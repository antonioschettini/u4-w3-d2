package antonioschettini.entities;

import antonioschettini.enums.TipoEvento;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
//@DiscriminatorValue("GARA_ATLETICA")
@NamedQueries({
        @NamedQuery(
                name = "GaraDiAtletica.perVincitore",
                query = "SELECT g FROM GaraDiAtletica g WHERE g.vincitore = :parametroVincitore"
        ),
        @NamedQuery(
                name = "GaraDiAtletica.perPartecipante",
                query = "SELECT g FROM GaraDiAtletica g WHERE :parametroAtleta MEMBER OF g.atleti"
        )
})
public class GaraDiAtletica extends Evento {
    @ManyToMany // molte gare possono partecipare più persone
    @JoinTable(name = "gara_atleti",
            joinColumns = @JoinColumn(name = "gara_id"), // chiave della gara
            inverseJoinColumns = @JoinColumn(name = "atleta_id")) // chiave della persona,
    // utilizzo una tabella ponte virtuale per collegare i due dati ed avrò due colonne
    private List<Persona> atleti;

    @ManyToOne // Una gara ha solo un vincitore
    @JoinColumn(name = "vincitore_id")
    private Persona vincitore;

    public GaraDiAtletica() {
        super();
    }

    public GaraDiAtletica(String titolo, LocalDate dataEvento, String descrizione, TipoEvento tipoEvento,
                          int numeroMassimoPartecipanti, Location location, List<Persona> atleti, Persona vincitore) {
        super(titolo, dataEvento, descrizione, tipoEvento, numeroMassimoPartecipanti, location);
        this.atleti = atleti;
        this.vincitore = vincitore;
    }

    public List<Persona> getAtleti() {
        return atleti;
    }

    public void setAtleti(List<Persona> atleti) {
        this.atleti = atleti;
    }

    public Persona getVincitore() {
        return vincitore;
    }

    public void setVincitore(Persona vincitore) {
        this.vincitore = vincitore;
    }

    @Override
    public String toString() {
        return "GaraDiAtletica{" +
                "numeroAtleti=" + (atleti != null ? atleti.size() : 0) +
                ", vincitore=" + (vincitore != null ? vincitore.getCognome() : "Nessuno") +
                '}';
    }
}