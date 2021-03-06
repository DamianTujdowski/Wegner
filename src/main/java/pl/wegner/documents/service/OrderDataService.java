package pl.wegner.documents.service;

import org.springframework.stereotype.Service;
import pl.wegner.documents.model.entities.OrderArchivalData;
import pl.wegner.documents.model.entities.OrderData;
import pl.wegner.documents.repository.OrderDataRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
public class OrderDataService {

    private OrderDataRepository repository;

    public OrderDataService(OrderDataRepository repository) {
        this.repository = repository;
    }

    public OrderData findById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Order data with id %d does not exist", id)
                ));
    }

    public OrderData generateData(OrderArchivalData archivalData) {
        return mapToOrderData(archivalData);
    }

    private OrderData mapToOrderData(OrderArchivalData archivalData) {
        return OrderData.builder()
                .fileName(archivalData.getFileName())
                .platesDimensions(archivalData.getPlatesDimensions())
                .platesQuantity(archivalData.getPlatesQuantity())
                .lpi(archivalData.getLpi())
                .inks(archivalData.getInks())
                .plateThickness(archivalData.getPlateThickness())
                .side(archivalData.getSide())
                .build();
    }

    public OrderData save(OrderData data) {
        return repository.save(data);
    }

    @Transactional
    public OrderData edit(OrderData data) {
        OrderData edited = repository.findById(data.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Order data with id %d does not exist", data.getId())
                ));
        edited.setFileName(data.getFileName());
        edited.setPlatesDimensions(data.getPlatesDimensions());
        edited.setPlatesQuantity(data.getPlatesQuantity());
        edited.setLpi(data.getLpi());
        edited.setInks(data.getInks());
        edited.setPlateThickness(data.getPlateThickness());
        edited.setSide(data.getSide());
        edited.setNotes(data.getNotes());
        edited.setProductionOrderId(data.getProductionOrderId());
        return edited;
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
}
