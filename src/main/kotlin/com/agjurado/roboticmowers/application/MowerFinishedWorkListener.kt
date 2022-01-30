package com.agjurado.roboticmowers.application

import com.agjurado.roboticmowers.domain.Location
import com.agjurado.roboticmowers.domain.event.Event
import com.agjurado.roboticmowers.domain.event.MowerFinishedWork
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@Component
class MowerFinishedWorkListener: ApplicationListener<Event> {
    override fun onApplicationEvent(event: Event) {
        when (event) {
            is MowerFinishedWork -> printEndingLocation(event.endingLocation)
        }
    }

    private fun printEndingLocation(endingLocation: Location) {
        println(endingLocation)
    }


}
