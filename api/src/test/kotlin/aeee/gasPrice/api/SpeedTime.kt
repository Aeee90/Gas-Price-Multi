package aeee.gasPrice.api

import org.springframework.util.StopWatch

object SpeedTime {

    fun measure(task: String, process: () -> Unit): Long {
        val stopWatch = StopWatch()
        stopWatch.start()
        process.invoke()
        stopWatch.stop()
        return stopWatch.totalTimeMillis
    }
}
