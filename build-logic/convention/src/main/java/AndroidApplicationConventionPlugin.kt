import com.android.build.api.dsl.ApplicationExtension
import com.febin.buildlogic.convention.ExtensionType
import com.febin.buildlogic.convention.configureBuildTypes
import com.febin.buildlogic.convention.configureKotlinAndroid
import com.febin.buildlogic.convention.libs
import org.gradle.kotlin.dsl.configure
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidApplicationConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.compose")


            }

            extensions.configure<ApplicationExtension> {
                defaultConfig {
                    applicationId = libs.findVersion("projectApplicationId").get().toString()
                    targetSdk = libs.findVersion("projectTargetSdkVersion").get().toString().toInt()

                    versionCode = libs.findVersion("projectVersionCode").get().toString().toInt()
                    versionName = libs.findVersion("projectVersionName").get().toString()
                }
                configureKotlinAndroid(this)

                configureBuildTypes(
                    commonExtension = this,
                    extensionType = ExtensionType.APPLICATION
                )
            }

        }
    }
}