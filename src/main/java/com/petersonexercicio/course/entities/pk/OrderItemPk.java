package com.petersonexercicio.course.entities.pk;

import com.petersonexercicio.course.entities.Order;
import com.petersonexercicio.course.entities.Product;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderItemPk implements Serializable {
    @Serial
    private static final long serialVersionUID= 1L;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemPk that = (OrderItemPk) o;
        return Objects.equals(order, that.order) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, product);
    }
}
