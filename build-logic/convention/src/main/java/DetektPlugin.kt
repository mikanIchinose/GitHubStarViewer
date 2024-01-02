import com.github.mikanIchinose.githubstarviewer.extension.library
import com.github.mikanIchinose.githubstarviewer.extension.libs
import com.github.mikanIchinose.githubstarviewer.extension.plugin
import com.github.mikanIchinose.githubstarviewer.extension.setupDetekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class DetektPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            // apply plugin
            pluginManager.apply(libs.plugin("detekt").pluginId)

            // setup
            setupDetekt(extensions.getByType<DetektExtension>())

            // add detekt dependencies
            dependencies {
                "detektPlugins"(libs.library("detekt-formatting"))
            }
        }
    }
}
