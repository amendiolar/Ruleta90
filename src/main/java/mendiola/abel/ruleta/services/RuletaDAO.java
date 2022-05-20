package mendiola.abel.ruleta.services;

import mendiola.abel.ruleta.models.entities.Ruleta;

import java.util.Optional;

public interface RuletaDAO
{
    public Ruleta save(Ruleta entidad);

    public Ruleta openRuleta(Ruleta ruleta, Long id);

    public Ruleta saveApuesta(Ruleta entidad);

    public Optional<Ruleta> findById(Long id);
}
