package cz.augi.gradle.scalafmt

import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.TaskAction

@CacheableTask
class ScalafmtTask extends ScalafmtFormatBase {
    @TaskAction
    def format() {
        runScalafmt()
    }
}
