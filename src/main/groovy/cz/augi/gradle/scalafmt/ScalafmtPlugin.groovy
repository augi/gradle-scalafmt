package cz.augi.gradle.scalafmt

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.plugins.JavaBasePlugin
import org.gradle.api.plugins.JavaPluginExtension

class ScalafmtPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        Task scalafmtAll = project.tasks.create('scalafmtAll')
        scalafmtAll.description = "Formats all source sets using scalafmt."
        Task checkScalafmtAll = project.tasks.create('checkScalafmtAll')
        checkScalafmtAll.description = "Checks formatting of all source sets using scalafmt."
        PluginExtension extension = project.extensions.create('scalafmt', PluginExtension)
        project.plugins.withType(JavaBasePlugin).configureEach {
            project.extensions.getByType(JavaPluginExtension).sourceSets.configureEach { sourceSet ->
                def task = project.tasks.register(sourceSet.getTaskName("", "scalafmt"), ScalafmtTask, {
                    it.sourceSet = sourceSet
                    it.pluginExtension = extension
                    it.description = "Formats ${sourceSet.name} source set using scalafmt."
                })

                def checkTask = project.tasks.register(sourceSet.getTaskName("check", "scalafmt"), ScalafmtCheckTask, {
                    it.sourceSet = sourceSet
                    it.pluginExtension = extension
                    it.description = "Checks formatting of ${sourceSet.name} source set using scalafmt."
                })
                scalafmtAll.dependsOn task
                checkScalafmtAll.dependsOn checkTask
            }
        }
    }
}
