import com.github.mikanIchinose.githubstarviewer.extension.library
import com.github.mikanIchinose.githubstarviewer.extension.libs
import com.github.mikanIchinose.githubstarviewer.extension.plugin
import com.github.mikanIchinose.githubstarviewer.extension.setUpJunit5
import com.github.mikanIchinose.githubstarviewer.extension.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class UnitTestPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply(libs.plugin("kotlinPowerAssert").pluginId)

            setUpJunit5()

            dependencies {
                testImplementation(libs.library("kotlin-test"))
                testImplementation(libs.library("kotlinx-coroutines-test"))
            }
        }
    }
}