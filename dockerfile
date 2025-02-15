# 1️⃣ JDK 17 이미지 사용
FROM eclipse-temurin:17-jdk as build

# 2️⃣ 작업 디렉토리 설정
WORKDIR /app

# 3️⃣ JAR 파일 복사 (plain 제외)
COPY build/libs/gaia-*.jar /appapp.jar/

# 4️⃣ 실행 명령어 설정
CMD ["java", "-jar", "/app/app.jar"]
