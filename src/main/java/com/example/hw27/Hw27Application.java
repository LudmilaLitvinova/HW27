package com.example.hw27;

import com.example.hw27.dto.*;
import com.example.hw27.service.StoreService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Slf4j
public class Hw27Application {
    @Autowired
    private StoreService storeService;

    public static void main(String[] args) {
        SpringApplication.run(Hw27Application.class, args);
    }


    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void init() {

        ProductDto productCoffee = new ProductDto(null, "coffee2", "description2 1", 178.5);
        productCoffee = storeService.addNewProduct(productCoffee);
        ProductDto productPasta = new ProductDto(null, "pasta2", "description2 2", 70.0);
        productPasta = storeService.addNewProduct(productPasta);

        AddressDto addressDto = new AddressDto(null, "Australia", "Sydney", "Bern str", "354");

        ClientDto clientDto = new ClientDto(null, "George", "george2@gamail.com", "12344432", addressDto, null);
        clientDto = storeService.addNewClient(clientDto);

        AddressDto newAddressDto = clientDto.getAddress();
        newAddressDto.setCity("Montreal");
        newAddressDto.setHouse("1978");

        storeService.changeAddress(newAddressDto);

        OrderItemDto orderItemCoffee = new OrderItemDto(null, null, productCoffee, 2);
        OrderItemDto orderItemPasta = new OrderItemDto(null, null, productPasta, 1);
        List<OrderItemDto> orderItemDtoList = new ArrayList<>();
        orderItemDtoList.add(orderItemCoffee);
        orderItemDtoList.add(orderItemPasta);

        List<OrderItemDto> orderItemDtoList2 = new ArrayList<>();
        orderItemDtoList.add(orderItemCoffee);
        orderItemDtoList.add(orderItemPasta);

        OrderDto orderDto = new OrderDto(null, clientDto, orderItemDtoList);
        OrderDto orderDto2 = new OrderDto(null, clientDto, orderItemDtoList2);

        storeService.createNewOrder(orderDto);
        storeService.createNewOrder(orderDto2);

        log.info(storeService.getAllInformationAboutClient(clientDto.getId()).toString());
        log.info(storeService.getClientInformation(clientDto.getId()).toString());
        log.info(storeService.getClientOrdersById(clientDto.getId()).toString());

    }
}