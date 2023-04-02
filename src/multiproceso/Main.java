package multiproceso;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        procesoPersonalizado();
        procesos();

        activitatProcessos();

        //cambioOutputProcess();
    }

    public static void procesoPersonalizado() throws IOException, InterruptedException {
        // Iniciar proceso 1
        ProcessBuilder builder1 = new ProcessBuilder("notepad.exe");
        Process process1 = builder1.start();
        CustomProcess customProcess1 = new CustomProcess(process1);

        ProcessBuilder builder2 = new ProcessBuilder("notepad.exe");
        Process process2 = builder2.start();
        CustomProcess customProcess2 = new CustomProcess(process2);

        // Esperar a que ambos procesos finalicen
        customProcess1.waitFor();
        customProcess2.waitFor();

        System.out.println("Ambos procesos han finalizado");
    }

    public static void procesos() throws IOException, InterruptedException {
        // Iniciar proceso 1
        ProcessBuilder builder1 = new ProcessBuilder("notepad.exe");
        Process process1 = builder1.start();

        ProcessBuilder builder2 = new ProcessBuilder("notepad.exe");
        Process process2 = builder2.start();

        // Esperar a que ambos procesos finalicen
        process1.waitFor();
        process2.waitFor();

        System.out.println("Ambos procesos han finalizado");
    }

    private static void cambioOutputProcess() throws IOException, InterruptedException {
        // Launch the notepad process
        //(new CustomProcess2("files/file1.txt","Esto es un test")).startNotepad();

        // Iniciar proceso 3
        ProcessBuilder processBuilder = new ProcessBuilder("java", "-cp", "files/project.jar", "main.Main");
        processBuilder.redirectOutput(new File("files/output.txt"));
        processBuilder.redirectError(new File("files/error.txt"));
        Process process = processBuilder.start();
        process.waitFor();

        System.out.println("Fin proceso 3");
    }

    public static void activitatProcessos() throws IOException, InterruptedException {
        // Creamos los dos primeros procesos de manera paralela
        ProcessBuilder builder1 = new ProcessBuilder("notepad.exe");
        Process process1 = builder1.start();

        ProcessBuilder builder2 = new ProcessBuilder("cmd.exe", "/c", "echo Hello world desde proceso");
        Process process2 = builder2.start();

        // Creamos un hilo para esperar la terminación de ambos procesos
        process1.waitFor();
        process2.waitFor();
        System.out.println("Los dos primeros procesos han terminado");

        // Creamos el tercer proceso después de que los dos primeros hayan terminado
        ProcessBuilder builder3 = new ProcessBuilder("notepad.exe");
        Process process3 = builder3.start();

        // Esperamos a que termine el tercer proceso
        process3.waitFor();
        System.out.println("Todos los procesos han terminado");
    }
}
