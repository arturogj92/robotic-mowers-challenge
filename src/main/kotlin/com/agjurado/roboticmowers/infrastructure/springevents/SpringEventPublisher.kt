package com.agjurado.roboticmowers.infrastructure.springevents

import com.agjurado.roboticmowers.domain.event.Event
import com.agjurado.roboticmowers.domain.event.EventPublisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class SpringEventPublisher
@Autowired
constructor(private val applicationEventPublisher: ApplicationEventPublisher) : EventPublisher {

    override fun publish(event: Event) {
        applicationEventPublisher.publishEvent(event)
    }

}
