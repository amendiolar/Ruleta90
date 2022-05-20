package mendiola.abel.ruleta.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "ruletas", schema = "ruleta")
public class Ruleta implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "colores")
    private String color;

    @Column(name = "numeros")
    private Integer numero;

    @Column(name = "estaAbierta")
    private Boolean estaAbierta;

    @Column(name = "valorApuestas")
    private Double valorApuesta;

    @Column(name = "fecha_alta")
    private Date fechaAlta;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    public Ruleta(Long id, String color, Integer numero, Boolean estaAbierta, Double valorApuesta) {
        this.id = id;
        this.color = color;
        this.numero = numero;
        this.estaAbierta = estaAbierta;
        this.valorApuesta = valorApuesta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ruleta)) return false;
        Ruleta ruleta = (Ruleta) o;
        return Objects.equals(id, ruleta.id) && Objects.equals(color, ruleta.color) && Objects.equals(numero, ruleta.numero) && Objects.equals(estaAbierta, ruleta.estaAbierta) && Objects.equals(valorApuesta, ruleta.valorApuesta) && Objects.equals(fechaAlta, ruleta.fechaAlta) && Objects.equals(fechaModificacion, ruleta.fechaModificacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, color, numero, estaAbierta, valorApuesta, fechaAlta, fechaModificacion);
    }

    @PrePersist
    private void antesPersistir()
    {
        this.fechaAlta = new Date();
    }

    @PreUpdate
    private void antesActualizar()
    {
        this.fechaModificacion = new Date();
    }
}
