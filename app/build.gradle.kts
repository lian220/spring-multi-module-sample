description = "app module"

dependencies {
  implementation("org.springframework.boot:spring-boot-devtools")
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.0.5")
  runtimeOnly("com.mysql:mysql-connector-j")
  implementation(project(":core"))
  implementation(project(":domain"))
}