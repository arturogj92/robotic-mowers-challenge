package com.agjurado.roboticmowers.domain.event


import com.agjurado.roboticmowers.domain.Location
import org.springframework.context.ApplicationEvent
import java.util.*

sealed class Event(source: Any) : ApplicationEvent(source) {
    val id: String = UUID.randomUUID().toString()
}

data class MowerFinishedWork(val _source: Any, val endingLocation: Location) : Event(_source)




