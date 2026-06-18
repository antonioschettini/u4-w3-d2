package antonioschettini.entities;

import antonioschettini.enums.TipoEvento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

import java.time.LocalDate;

@Entity
//@DiscriminatorValue("PARTITA")
@NamedQueries({
        @NamedQuery(
                name = "PartitaDiCalcio.vinteInCasa",
                query = "SELECT p FROM PartitaDiCalcio p WHERE p.squadraVincente = p.squadraCasa"
        ),
        @NamedQuery(
                name = "PartitaDiCalcio.vinteInTrasferta",
                query = "SELECT p FROM PartitaDiCalcio p WHERE p.squadraVincente = p.squadraOspite"
        ),
        @NamedQuery(
                name = "PartitaDiCalcio.pareggiate",
                query = "SELECT p FROM PartitaDiCalcio p WHERE p.squadraVincente IS NULL"
        )
})
public class PartitaDiCalcio extends Evento {
    @Column(name = "squadra_casa")
    private String squadraCasa;

    @Column(name = "squadra_ospite")
    private String squadraOspite;

    @Column(name = "squadra_vincente")
    private String squadraVincente;

    @Column(name = "gol_squadra_casa")
    private int numeroGolSquadraDiCasa;

    @Column(name = "gol_squadra_ospite")
    private int numeroGolSquadraOspite;

    public PartitaDiCalcio() {
        super();
    }

    public PartitaDiCalcio(String titolo, LocalDate dataEvento, String descrizione, TipoEvento tipoEvento, int numeroMassimoPartecipanti, Location location, String squadraDiCasa, String squadraOspite, String squadraVincente, int numeroGolSquadraDiCasa, int numeroGolSquadraOspite) {
        super(titolo, dataEvento, descrizione, tipoEvento, numeroMassimoPartecipanti, location);

        this.squadraCasa = squadraDiCasa;
        this.squadraOspite = squadraOspite;
        this.squadraVincente = squadraVincente;
        this.numeroGolSquadraDiCasa = numeroGolSquadraDiCasa;
        this.numeroGolSquadraOspite = numeroGolSquadraOspite;
    }

    public String getSquadraDiCasa() {
        return squadraCasa;
    }

    public void setSquadraDiCasa(String squadraCasa) {
        this.squadraCasa = squadraCasa;
    }

    public String getSquadraOspite() {
        return squadraOspite;
    }

    public void setSquadraOspite(String squadraOspite) {
        this.squadraOspite = squadraOspite;
    }

    public String getSquadraVincente() {
        return squadraVincente;
    }

    public void setSquadraVincente(String squadraVincente) {
        this.squadraVincente = squadraVincente;
    }

    public int getNumeroGolSquadraDiCasa() {
        return numeroGolSquadraDiCasa;
    }

    public void setNumeroGolSquadraDiCasa(int numeroGolSquadraDiCasa) {
        this.numeroGolSquadraDiCasa = numeroGolSquadraDiCasa;
    }

    public int getNumeroGolSquadraOspite() {
        return numeroGolSquadraOspite;
    }

    public void setNumeroGolSquadraOspite(int numeroGolSquadraOspite) {
        this.numeroGolSquadraOspite = numeroGolSquadraOspite;
    }

    @Override
    public String toString() {
        return "PartitaDiCalcio{" +
                "id=" + getId() +
                ", titolo='" + getTitolo() + '\'' +
                ", squadraDiCasa='" + squadraCasa + '\'' +
                ", squadraOspite='" + squadraOspite + '\'' +
                ", squadraVincente='" + (squadraVincente != null ? squadraVincente : "Pareggio") + '\'' +
                ", golCasa=" + numeroGolSquadraDiCasa +
                ", golOspite=" + numeroGolSquadraOspite +
                '}';
    }
}
