package lian.sample

import lian.sample.repository.order.OrderRepository
import org.springframework.stereotype.Service

@Service
class TestService(
  private val orderRepository: OrderRepository,
) {
}