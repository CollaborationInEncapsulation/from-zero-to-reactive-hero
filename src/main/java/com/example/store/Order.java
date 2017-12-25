package com.example.store;

import rx.Observable;

import java.util.Objects;

public class Order {
    private final String id;
    private final String userId;
    private final Iterable<String> productsIds;

    public Order(String id, String userId, Iterable<String> productsIds) {
        this.id = Objects.requireNonNull(id);
        this.userId = Objects.requireNonNull(userId);
        this.productsIds = Objects.requireNonNull(productsIds);
    }

    public Observable<Long> getTotalPrice() {
        // TODO: use reduce to find the sum; Use ProductCatalog to find corresponded Product by its id
        throw new RuntimeException("Not implemented yet");
    }

    public String getId() {
        return this.id;
    }

    public String getUserId() {
        return this.userId;
    }

    public Iterable<String> getProductsIds() {
        return this.productsIds;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Order)) return false;
        final Order other = (Order) o;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$userId = this.getUserId();
        final Object other$userId = other.getUserId();
        if (this$userId == null ? other$userId != null : !this$userId.equals(other$userId)) return false;
        final Object this$productsIds = this.getProductsIds();
        final Object other$productsIds = other.getProductsIds();
        if (this$productsIds == null ? other$productsIds != null : !this$productsIds.equals(other$productsIds))
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $userId = this.getUserId();
        result = result * PRIME + ($userId == null ? 43 : $userId.hashCode());
        final Object $productsIds = this.getProductsIds();
        result = result * PRIME + ($productsIds == null ? 43 : $productsIds.hashCode());
        return result;
    }

    public String toString() {
        return "Order(id=" + this.getId() + ", userId=" + this.getUserId() + ", productsIds=" + this.getProductsIds() + ")";
    }
}
