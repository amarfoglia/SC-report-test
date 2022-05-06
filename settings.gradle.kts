plugins {
    id("com.gradle.enterprise") version "3.10"
}

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
        publishOnFailure()
    }
}

rootProject.name = "sc-report"

with(File(rootProject.projectDir, ".git/hooks/commit-msg")) {
    if(!exists()) {
        parentFile.mkdirs()
        runCatching() {
            val github = "https://raw.githubusercontent.com"
            val script = java.net.URL("$github/DanySK/conventional-pre-commit/main/conventional-pre-commit.sh")
            writeText(script.readText())
            setExecutable(true)
        }.onFailure { println("Warning: the commit hook could not be downloaded!") }
    }
}