package mendiola.abel.ruleta.services;

import mendiola.abel.ruleta.models.entities.Apuesta;
import mendiola.abel.ruleta.models.entities.Ruleta;
import org.apache.tomcat.util.digester.Rule;

import java.util.List;
import java.util.Optional;

public interface RuletaDAO
{
    public Ruleta save(Ruleta entidad);

    public Ruleta openRuleta(Ruleta ruleta, Long id);

    public Apuesta saveApuesta(Apuesta entidad, Long id);

    public Iterable<Apuesta> buscarApuestasPorRuleta(Long ruletaId);

    List<Ruleta> fetchRuletasList();
}
