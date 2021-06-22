package aeee.gasPrice.core.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@PropertySource("classpath:properties/infura.properties")
class APIConfiguration {
}