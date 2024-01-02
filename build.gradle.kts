plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.googleServices) apply false
    alias(libs.plugins.kotlinSerialization) apply false
    alias(libs.plugins.secret) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.githook)
}

githook {
    hooks {
        create("pre-commit") {
            shell = """
                killall -9 java
                echo "Running detekt check..."
                OUTPUT="/tmp/detekt-${'$'}(date +%s)"
                ./gradlew detekt > ${'$'}OUTPUT
                EXIT_CODE=${'$'}?
                if [ ${'$'}EXIT_CODE -ne 0 ]; then
                  cat ${'$'}OUTPUT
                  rm ${'$'}OUTPUT
                  echo "***********************************************"
                  echo "                 detekt failed                 "
                  echo " Please fix the above issues before committing "
                  echo "***********************************************"
                  exit ${'$'}EXIT_CODE
                fi
                rm ${'$'}OUTPUT
            """.trimIndent()
        }
    }
}
