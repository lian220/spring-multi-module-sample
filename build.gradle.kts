plugins {
	java
	id("org.springframework.boot") version "2.7.10"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
}

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

allprojects {
	group = "lian.sample"
	version = "0.0.1-SNAPSHOT"

	repositories {
		mavenCentral()
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}
}

subprojects {
	apply {
		plugin("java")
		plugin("org.springframework.boot")
		plugin("io.spring.dependency-management")
	}

	repositories {
		mavenCentral()
	}

	dependencies {
		implementation("org.springframework.boot:spring-boot-starter")

		// log4j2 & MDC
		implementation("org.springframework.boot:spring-boot-starter-log4j2")
		implementation("org.slf4j:slf4j-api:2.0.7")

		//log & lombok
		compileOnly("org.projectlombok:lombok")
		annotationProcessor("org.projectlombok:lombok")

		developmentOnly("org.springframework.boot:spring-boot-devtools")

		//test 용
		testImplementation("org.springframework.boot:spring-boot-starter-log4j2")
		testImplementation("org.springframework.boot:spring-boot-starter-test")
	}

	configurations {
		all {
			//log4j2 충돌 방지
			exclude("org.springframework.boot", "spring-boot-starter-logging")
		}
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}
}

project(":core") {
	tasks.bootJar {enabled = false}
	tasks.jar {enabled = true}
}

project(":product") {
	tasks.bootJar {enabled = true}
	dependencies {
		implementation("org.springframework.boot:spring-boot-devtools")
		implementation("org.springframework.boot:spring-boot-starter-web")
		implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.0.5")
		runtimeOnly("org.postgresql:postgresql")
		project(":core")
	}
}


