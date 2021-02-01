package pl.wegner.documents.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.wegner.documents.model.entities.Project;
import pl.wegner.documents.repository.ProjectRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ProjectService {

    private ProjectRepository repository;

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    public Project find(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Project with id %d does not exist", id)
                ));
    }

    public List<Project> findAll(int page, int size) {
        return repository.findAllBy(PageRequest.of(page, size));
    }

    public Project save(Project project) {
        return repository.save(project);
    }

    public Project edit(Project project) {
        Project edited = repository.findById(project.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Project with id %d does not exist", project.getId())
                ));
        edited.setName(project.getName());
        edited.setNumber(project.getNumber());
        edited.setClient(project.getClient());
        edited.setPrintHouse(project.getPrintHouse());
        edited.setRollerSize(project.getRollerSize());
        edited.setDimensions(project.getDimensions());
        edited.setPlateThickness(project.getPlateThickness());
        edited.setSide(project.getSide());
        edited.setInks(project.getInks());
        edited.setNotes(project.getNotes());
        edited.setStage(project.getStage());
        edited.setChanges(project.getChanges());
        return edited;
    }


    public void delete(long id) {
        repository.deleteById(id);
    }
}