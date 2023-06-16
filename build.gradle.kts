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
		testImplementation("org.springframework.boot:spring-boot-starter-test")
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}
}

project(":product") {
	dependencies {
		implementation("org.springframework.boot:spring-boot-starter-web")
		project(":core")
	}
}


