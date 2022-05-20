package mendiola.abel.ruleta.services;

import mendiola.abel.ruleta.exceptions.BadRequestException;
import mendiola.abel.ruleta.exceptions.NotFoundException;
import mendiola.abel.ruleta.models.entities.Ruleta;
import mendiola.abel.ruleta.repositories.RuletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RuletaDAOImpl implements RuletaDAO
{
    private RuletaRepository repository;

    @Autowired
    public RuletaDAOImpl(RuletaRepository repository)
    {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Ruleta save(Ruleta entidad) {
        return repository.save(entidad);
    }

    @Override
    public Ruleta openRuleta(Ruleta ruleta, Long id)
    {
        Ruleta ruleta2Open = repository.findById(id).get();
        ruleta2Open.setEstaAbierta(ruleta.getEstaAbierta());
        return repository.save(ruleta2Open);
    }

    @Override
    @Transactional
    public Ruleta saveApuesta(Ruleta entidad)
    {
        return repository.save(entidad);
    }

    @Override
    @Transactional
    public Optional<Ruleta> findById(Long id)
    {
        return repository.findById(id);
    }

}
