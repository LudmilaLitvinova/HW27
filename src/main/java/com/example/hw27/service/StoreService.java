package com.example.hw27.service;

import com.example.hw27.dto.*;
import com.example.hw27.model.*;
import com.example.hw27.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ludmila Litvinova on 25.01
 */
@Service
@RequiredArgsConstructor
@Transactional
public class StoreService {

    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final AddressRepository addressRepository;
    private final ClientRepository clientRepository;
    private final ObjectMapper objectMapper;
    private List<Order> clientList;

    public ProductDto addNewProduct(ProductDto productDto) {
        Product product = objectMapper.convertValue(productDto, Product.class);
        productRepository.save(product);
        productDto.setId(product.getId());
        return productDto;
    }

    public ClientDto addNewClient(ClientDto clientDto) {
        Client client = objectMapper.convertValue(clientDto, Client.class);
        addressRepository.save(client.getAddress());
        AddressDto addressDto = objectMapper.convertValue(client.getAddress(), AddressDto.class);

        clientRepository.save(client);
        clientDto.setId(client.getId());
        clientDto.setAddress(addressDto);
        return clientDto;
    }

    public void changeAddress(AddressDto addressDto) {
        addressRepository.updateAddress(objectMapper.convertValue(addressDto, Address.class));
    }

    public OrderDto createNewOrder(OrderDto orderDto) {
        Order order = new Order();
        Client client = objectMapper.convertValue(orderDto.getClient(), Client.class);
        List<OrderItem> orderItems = orderDto.getOrderItems().stream().map(oi -> objectMapper.convertValue(oi, OrderItem.class))
                .collect(Collectors.toList());

        order.setClient(client);
        orderRepository.save(order);

        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(order);
            orderItemRepository.save(orderItem);
        }
        client = clientRepository.findById(orderDto.getClient().getId()).orElseThrow(
                () -> new EntityNotFoundException("Client with id=" + orderDto.getClient().getId() + "wasn't found"));
        if (client.getOrderHistory() == null) {
            clientList = new ArrayList<>();
        }
        clientList.add(order);
        client.setOrderHistory(clientList);
        clientRepository.save(client);
        order.setOrderItems(orderItems);
        orderDto.setId(order.getId());
        orderDto.setClient(objectMapper.convertValue(order.getClient(), ClientDto.class));
        orderDto.setOrderItems(order.getOrderItems().stream().map(oi -> objectMapper.convertValue(oi, OrderItemDto.class)).collect(Collectors.toList()));
        return orderDto;
    }

    public AddressDto createNewAddress(AddressDto addressDto) {
        Address address = objectMapper.convertValue(addressDto, Address.class);
        addressRepository.save(address);
        addressDto.setId(address.getId());
        return addressDto;
    }

    public ClientDto getAllInformationAboutClient(Integer clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(
                () -> new EntityNotFoundException("Client with id=" + clientId + "wasn't found"));
        ClientDto clientDto = objectMapper.convertValue(client, ClientDto.class);
        List<Order> orderList = client.getOrderHistory();
        List<OrderDto> orderDtoList = orderList.stream().map(o -> objectMapper.convertValue(o, OrderDto.class)).collect(Collectors.toList());
        clientDto.setOrderHistory(orderDtoList);
        return clientDto;

    }

    public ClientsInformationDto getClientInformation(Integer clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(
                () -> new EntityNotFoundException("Client with id=" + clientId + "wasn't found"));
        ClientsInformationDto clientsInformationDto = objectMapper.convertValue(client, ClientsInformationDto.class);
        return clientsInformationDto;
    }

    public ClientsOrdersIdDto getClientOrdersById(Integer clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(
                () -> new EntityNotFoundException("Client with id=" + clientId + "wasn't found"));
        ClientsOrdersIdDto clientsOrdersIdDto = objectMapper.convertValue(client, ClientsOrdersIdDto.class);
        List<Order> orderList = client.getOrderHistory();

        List<Integer> orderIds = orderList.stream().map(o -> o.getId()).collect(Collectors.toList());
        clientsOrdersIdDto.setOrderIds(orderIds);

        return clientsOrdersIdDto;
    }
}