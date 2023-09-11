package com.trendyol.shipment;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Basket {

    private List<Product> products;

    public ShipmentSize getShipmentSize() {
        Map<ShipmentSize, Long> shipmentCounts = products.stream()
                .collect(Collectors.groupingBy(Product::getSize, Collectors.counting()));

        return checkIfProductSizeIsNotMoreThanThreeReturnMaxSize(shipmentCounts);
    }

    private ShipmentSize getMaxSizeFromTheBasket(Map<ShipmentSize, Long> shipmentCounts) {
        return shipmentCounts.entrySet().stream()
                .max(Map.Entry.comparingByKey())
                .map(Map.Entry::getKey)
                .orElse(ShipmentSize.X_LARGE);
    }

    private ShipmentSize checkIfItHasXLargeProductAndLessThanThreeProductInBasket(Map<ShipmentSize, Long> shipmentCounts) {
        for (Map.Entry<ShipmentSize, Long> entry : shipmentCounts.entrySet()) {
            if (entry.getValue() >= 3) {
                return ShipmentSize.nextSize(entry.getKey()).orElseThrow(() -> new RuntimeException("Shipment size not found."));
            }
        }
        return getMaxSizeFromTheBasket(shipmentCounts);
    }

    private ShipmentSize checkIfProductSizeIsNotMoreThanThreeReturnMaxSize(Map<ShipmentSize, Long> shipmentCounts) {
        if (products.size() < 3) {
            return shipmentCounts.entrySet().stream()
                    .max(Map.Entry.comparingByKey())
                    .map(Map.Entry::getKey)
                    .orElse(ShipmentSize.X_LARGE);
        }
        return checkIfItHasXLargeProductAndLessThanThreeProductInBasket(shipmentCounts);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


}
