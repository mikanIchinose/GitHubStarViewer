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
            pluginManager.apply(libs.plugin("detekt").pluginId)

            setupDetekt(extensions.getByType<DetektExtension>())

            dependencies {
                "detektPlugins"(libs.library("detekt-formatting"))
            }
        }
    }
}
