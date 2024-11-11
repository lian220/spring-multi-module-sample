description = "domain module"

dependencies {
  api("com.querydsl:querydsl-jpa:5.0.0:jakarta")
  kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")
  annotationProcessor(group = "com.querydsl", name = "querydsl-apt", classifier = "jakarta")
}