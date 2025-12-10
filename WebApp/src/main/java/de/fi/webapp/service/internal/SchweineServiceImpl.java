package de.fi.webapp.service.internal;




import de.fi.webapp.persitence.repository.SchweineRepository;
import de.fi.webapp.service.SchweineService;
import de.fi.webapp.service.SchweineServiceException;
import de.fi.webapp.service.mapper.SchweinMapper;
import de.fi.webapp.service.model.Schwein;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(rollbackFor = SchweineServiceException.class, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)

public class SchweineServiceImpl implements SchweineService {


    private final SchweineRepository schweinRepository;

    private final SchweinMapper schweinMapper;

    public SchweineServiceImpl(final SchweineRepository schweinRepository, final SchweinMapper schweinMapper) {
        this.schweinRepository = schweinRepository;
        this.schweinMapper = schweinMapper;
    }

    @Override
    public void speichern(final Schwein schwein) throws SchweineServiceException {
        try {

            schweinRepository.save(schweinMapper.convert(schwein));

        } catch (RuntimeException e) {
            throw new SchweineServiceException("Es ist ein Fehler aufgetreten",e);
        }
    }



    @Override
    public boolean aendern(final Schwein person) throws SchweineServiceException {
        try {
            if(! schweinRepository.existsById(person.getId())) {
                return false;
            }

            schweinRepository.save(schweinMapper.convert(person));

            return true;
        } catch (RuntimeException e) {
            throw new SchweineServiceException("Es ist ein Fehler aufgetreten",e);
        }
    }

    @Override
    public boolean loesche(final UUID id) throws SchweineServiceException {
        try {
            if(! schweinRepository.existsById(id)) {
                return false;
            }

            schweinRepository.deleteById(id);
            return true;
        } catch (RuntimeException e) {
            throw new SchweineServiceException("Es ist ein Fehler aufgetreten",e);
        }
    }

    @Override
    public Optional<Schwein> findeNachId(final UUID id) throws SchweineServiceException {
        try {
            return schweinRepository.findById(id).map(schweinMapper::convert);
        }catch (Exception e) {
            throw new SchweineServiceException("Upps", e);
        }
    }

    @Override
    public Iterable<Schwein> findeAlle() throws SchweineServiceException {
        try {
            return schweinMapper.convert(schweinRepository.findAll());
        } catch (Exception e) {
            throw new SchweineServiceException("Upps", e);
        }
    }


    @Override
    public boolean fuettern(final UUID id) throws SchweineServiceException {
        Optional<Schwein> schweinOptional = findeNachId(id);
        if (schweinOptional.isPresent()) {
            Schwein schwein = schweinOptional.get();
            schwein.fuettern();
            aendern(schwein);
            return true;
        }
        return false;
    }
}
