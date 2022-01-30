package com.agjurado.roboticmowers.application

import com.agjurado.roboticmowers.domain.Mower
import com.agjurado.roboticmowers.domain.event.MowerFinishedWork
import com.agjurado.roboticmowers.domain.event.EventPublisher
import org.springframework.stereotype.Service

@Service
class MowerActivator(
    private val eventPublisher: EventPublisher,
) {

    fun startMowing(mower: Mower) {
        mower.mow()
            .also(::raiseEvent)
    }

    private fun raiseEvent(mower: Mower) {
        eventPublisher.publish(
            MowerFinishedWork(this, mower.getCurrentLocation())
        )
    }

}
