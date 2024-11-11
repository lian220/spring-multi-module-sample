package lian.sample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.ServletComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.scheduling.annotation.EnableAsync


@SpringBootApplication
@EnableJpaAuditing
@ServletComponentScan
@EnableAsync(proxyTargetClass = true)
class AppApplication

fun main(args: Array<String>) {
  runApplication<AppApplication>(*args)
}