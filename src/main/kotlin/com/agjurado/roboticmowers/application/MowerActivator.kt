package com.agjurado.roboticmowers.application

import com.agjurado.roboticmowers.domain.Mower
import org.springframework.stereotype.Service

@Service
class MowerActivator {

    //TODO checkear que no se sale del mapa
    //TODO lanzar domain event capturarlo con un event listener y manejarlo con un event handler y que al final imprima
    //TODO subir a github, hacer readme
    //Descargarlo y ejecutarlo con java -jar
    fun startMowing(mower: Mower) {
        mower.mow()
    }

}
