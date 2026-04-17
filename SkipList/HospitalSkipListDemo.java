/**
 * Demostración de SkipList aplicada a sistema de hospital
 * Muestra diferentes casos de uso: búsqueda, inserción, eliminación
 */
public class HospitalSkipListDemo {
    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("   SKIP LIST - SISTEMA DE HOSPITAL");
        System.out.println("   Implementación en Java");
        System.out.println("==========================================");

        // Crear SkipList para pacientes
        SkipList<Paciente> listaPacientes = new SkipList<>(4, 0.5);

        // CASO 1: INSERCIÓN DE PACIENTES
        System.out.println("\n--- CASO 1: INSERCIÓN DE PACIENTES ---");
        System.out.println("Insertando pacientes en el sistema de urgencias:\n");
        
        Paciente[] pacientes = {
            new Paciente(101, "Juan Pérez", 3, "Cardiología"),
            new Paciente(102, "María García", 1, "Urgencias"),
            new Paciente(103, "Carlos López", 2, "Traumatología"),
            new Paciente(104, "Ana Martínez", 4, "Pediatría"),
            new Paciente(105, "Pedro Sánchez", 5, "Medicina General"),
            new Paciente(106, "Laura Rodríguez", 1, "Urgencias"),
            new Paciente(107, "Miguel Fernández", 2, "Neurología")
        };

        for (Paciente p : pacientes) {
            System.out.println("Insertando: " + p + " - " + p.getEspecialidad());
            listaPacientes.insert(p);
        }

        System.out.println("\nEstructura de SkipList después de inserciones:");
        listaPacientes.print();

        // CASO 2: BÚSQUEDA DE PACIENTES
        System.out.println("\n--- CASO 2: BÚSQUEDA DE PACIENTES ---");
        System.out.println("Buscando pacientes específicos:\n");
        
        Paciente buscar1 = new Paciente(103, "Carlos López", 2, "Traumatología");
        System.out.println("Buscando: " + buscar1);
        System.out.println("Resultado: " + (listaPacientes.search(buscar1) ? "ENCONTRADO" : "NO ENCONTRADO"));
        
        Paciente buscar2 = new Paciente(999, "Inexistente", 3, "N/A");
        System.out.println("\nBuscando: " + buscar2);
        System.out.println("Resultado: " + (listaPacientes.search(buscar2) ? "ENCONTRADO" : "NO ENCONTRADO"));

        // CASO 3: ELIMINACIÓN DE PACIENTES
        System.out.println("\n--- CASO 3: ELIMINACIÓN DE PACIENTES ---");
        System.out.println("Eliminando pacientes que ya fueron atendidos:\n");
        
        Paciente eliminar1 = new Paciente(102, "María García", 1, "Urgencias");
        System.out.println("Eliminando: " + eliminar1 + " (Paciente atendido)");
        listaPacientes.delete(eliminar1);
        
        Paciente eliminar2 = new Paciente(105, "Pedro Sánchez", 5, "Medicina General");
        System.out.println("Eliminando: " + eliminar2 + " (Paciente atendido)");
        listaPacientes.delete(eliminar2);

        System.out.println("\nEstructura de SkipList después de eliminaciones:");
        listaPacientes.print();

        // CASO 4: INSERCIÓN DE NUEVOS PACIENTES DE URGENCIA
        System.out.println("\n--- CASO 4: NUEVOS PACIENTES DE URGENCIA ---");
        System.out.println("Llegan nuevos pacientes con alta prioridad:\n");
        
        Paciente urgente1 = new Paciente(108, "Roberto Díaz", 1, "Urgencias");
        Paciente urgente2 = new Paciente(109, "Carmen Ruiz", 1, "Urgencias");
        
        System.out.println("Insertando: " + urgente1);
        listaPacientes.insert(urgente1);
        System.out.println("Insertando: " + urgente2);
        listaPacientes.insert(urgente2);

        System.out.println("\nEstructura de SkipList con nuevos urgentes:");
        listaPacientes.print();

        // CASO 5: RECORRIDO ORDENADO POR PRIORIDAD
        System.out.println("\n--- CASO 5: LISTADO ORDENADO POR PRIORIDAD ---");
        System.out.println("Pacientes en orden de atención (por prioridad):\n");
        System.out.println("Prioridad 1 (URGENTE):");
        System.out.println("Prioridad 2 (MUY URGENTE):");
        System.out.println("Prioridad 3 (URGENTE):");
        System.out.println("Prioridad 4 (NORMAL):");
        System.out.println("Prioridad 5 (POCO URGENTE):");

        System.out.println("\n==========================================");
        System.out.println("   FIN DE LA DEMOSTRACIÓN");
        System.out.println("==========================================");
    }
}
