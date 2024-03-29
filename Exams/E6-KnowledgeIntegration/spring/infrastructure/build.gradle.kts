dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-mail")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.auth0:java-jwt:4.4.0")
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
}