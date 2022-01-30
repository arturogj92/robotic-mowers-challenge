package com.agjurado.roboticmowers.application

import com.agjurado.roboticmowers.domain.Location
import com.agjurado.roboticmowers.domain.event.Event
import com.agjurado.roboticmowers.domain.event.MowerFinishedWork
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Service
class MowerFinishedWorkListener: ApplicationListener<Event> {
    override fun onApplicationEvent(event: Event) {
        when (event) {
            is MowerFinishedWork -> logEndingLocation(event.endingLocation)
        }
    }

    private fun logEndingLocation(endingLocation: Location) {
        println(endingLocation)
    }


}
