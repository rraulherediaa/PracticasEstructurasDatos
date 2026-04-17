/**
 * Clase Paciente para el sistema de hospital
 * Implementa Comparable para ser usada en SkipList
 */
public class Paciente implements Comparable<Paciente> {
    private int id;
    private String nombre;
    private int prioridad; // 1-5, donde 1 es urgencia máxima
    private String especialidad;

    public Paciente(int id, String nombre, int prioridad, String especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.especialidad = especialidad;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    @Override
    public int compareTo(Paciente other) {
        // Comparar por prioridad primero (menor número = mayor urgencia)
        int prioridadCompare = Integer.compare(this.prioridad, other.prioridad);
        if (prioridadCompare != 0) {
            return prioridadCompare;
        }
        // Si misma prioridad, comparar por ID
        return Integer.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        return String.format("P%d-%s(Pri:%d)", id, nombre, prioridad);
    }
}
