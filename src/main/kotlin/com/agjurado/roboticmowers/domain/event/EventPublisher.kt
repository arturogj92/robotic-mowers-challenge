package com.agjurado.roboticmowers.domain.event


interface EventPublisher {
    fun publish(event: Event)
}
