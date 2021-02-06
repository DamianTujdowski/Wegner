package pl.wegner.documents.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.wegner.documents.model.entities.ProductionOrder;
import pl.wegner.documents.repository.ProductionOrderRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ProductionOrderService {

    private ProductionOrderRepository orderRepository;

    public ProductionOrderService(ProductionOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public ProductionOrder findById(long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Production order with id %d does not exist" + id));
    }

    public List<ProductionOrder> findAll(int page, int size) {
        return orderRepository.findAllBy(PageRequest.of(page, size));
    }

    public ProductionOrder save(ProductionOrder order) {
        return orderRepository.save(order);
    }

    public ProductionOrder edit(ProductionOrder order) {
        ProductionOrder edited = orderRepository.findById(order.getId())
                .orElseThrow(() -> new EntityNotFoundException("Production order with id %d does not exist" + order.getId()));
        edited.setName(order.getName());
        edited.setDate(order.getDate());
        edited.setOrderData(order.getOrderData());
        return edited;
    }

    public void delete(long id) {
        orderRepository.deleteById(id);
    }
}