package com.spring.henallux.firstSpringProject.dataAccess.dao;

import com.spring.henallux.firstSpringProject.dataAccess.entity.OrderCustomerEntity;
import com.spring.henallux.firstSpringProject.dataAccess.entity.OrderLineEntity;
import com.spring.henallux.firstSpringProject.dataAccess.repository.OrderLineRepository;
import com.spring.henallux.firstSpringProject.dataAccess.util.ProviderConverter;
import com.spring.henallux.firstSpringProject.model.OrderLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderLineDAO implements OrderLineDataAccess {

    private final OrderLineRepository orderLineRepository;
    private final ProviderConverter providerConverter;

    @Autowired
    public OrderLineDAO(OrderLineRepository orderLineRepository, ProviderConverter providerConverter) {
        this.orderLineRepository = orderLineRepository;
        this.providerConverter = providerConverter;
    }

    public List<OrderLine> findByOrderId(OrderCustomerEntity orderId) {
        List<OrderLineEntity> entities = orderLineRepository.findByOrderID(orderId);
        return entities.stream()
                .map(providerConverter::orderLineEntityToOrderLineModel)
                .toList();
    }
}
