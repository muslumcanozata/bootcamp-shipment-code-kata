package com.trendyol.shipment;

import java.util.Arrays;
import java.util.Optional;

public enum ShipmentSize {

    SMALL(0),
    MEDIUM(1),
    LARGE(2),
    X_LARGE(3);

    private final int key;
    ShipmentSize(int key) {
        this.key = key;
    }

    public int getKey() {
        return this.key;
    }

    public static Optional<ShipmentSize> nextSize(ShipmentSize size) {
        if (ShipmentSize.X_LARGE.equals(size)) {
            return Optional.of(size);
        }
        return getEnumByKey(size.getKey()+1);
    }

    public static Optional<ShipmentSize> getEnumByKey(int key) {
        return Arrays.stream(ShipmentSize.values()).filter(shipmentSize -> shipmentSize.getKey() == key).findFirst();
    }
}
