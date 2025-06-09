package universidad.ricardo_spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RicardoSpringApplication implements CommandLineRunner {
    @Value("${pdf.upload.dir}")
    private String uploadDir;
    @Value("${DB_URL}")
    private String dbUrl;
    @Value("${DB_USERNAME}")
    private String dbUser;
    public static void main(String[] args) {
        SpringApplication.run(RicardoSpringApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Variables de entorno:");
        System.out.println("Direccion de subida: " + uploadDir);
        System.out.println("DB URL: " + dbUrl);
        System.out.println("DB username: " + dbUser);
    }
}
