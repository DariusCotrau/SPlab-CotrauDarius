package ro.uvt.adoption.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.uvt.adoption.model.Animal;
import ro.uvt.adoption.model.AnimalRequest;
import ro.uvt.adoption.model.AnimalStatus;
import ro.uvt.adoption.persistence.AnimalRepository;

@Service
public class AnimalService {
    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> findAll() {
        return animalRepository.findAll();
    }

    public Optional<Animal> findById(long id) {
        return animalRepository.findById(id);
    }

    @Transactional
    public Animal create(AnimalRequest request) {
        Animal animal = new Animal(
                request.getName(),
                request.getSpecies(),
                request.getAge(),
                request.getStatus(),
                request.getIntakeDate());
        return animalRepository.save(animal);
    }

    @Transactional
    public Optional<Animal> update(long id, AnimalRequest request) {
        return animalRepository.findById(id).map(existing -> {
            existing.setName(request.getName());
            existing.setSpecies(request.getSpecies());
            existing.setAge(request.getAge());
            existing.setStatus(resolveStatus(request.getStatus(), existing.getStatus()));
            existing.setIntakeDate(request.getIntakeDate() != null ? request.getIntakeDate() : existing.getIntakeDate());
            return animalRepository.save(existing);
        });
    }

    @Transactional
    public Optional<Animal> updateStatus(long id, AnimalStatus status) {
        return animalRepository.findById(id).map(existing -> {
            existing.setStatus(resolveStatus(status, existing.getStatus()));
            return animalRepository.save(existing);
        });
    }

    private AnimalStatus resolveStatus(AnimalStatus requested, AnimalStatus fallback) {
        return requested != null ? requested : (fallback != null ? fallback : AnimalStatus.NEW_ARRIVAL);
    }
}
