package lian.sample.repository.order

import lian.sample.repository.order.entity.OrderJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository: JpaRepository<OrderJpaEntity, Long> {
}