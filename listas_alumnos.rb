class Alumno
  attr_accessor :nombre, :carnet, :nota, :materia

  def initialize(nombre, carnet, nota, materia)
    @nombre = nombre
    @carnet = carnet
    @nota = nota
    @materia = materia
  end

  def to_s
    "#{@nombre} (#{@carnet}) - #{@materia}: #{@nota}"
  end
end

class ListaAlumnos
  def initialize
    @alumnos = []
  end

  def agregar_alumno(alumno)
    @alumnos << alumno
    puts "Alumno agregado: #{alumno.nombre}"
  end

  def eliminar_alumno(carnet)
    alumno = @alumnos.find { |a| a.carnet == carnet }
    if alumno
      @alumnos.delete(alumno)
      puts "Alumno eliminado: #{alumno.nombre}"
    else
      puts "Alumno con carnet #{carnet} no encontrado."
    end
  end

  def buscar_alumno(carnet)
    alumno = @alumnos.find { |a| a.carnet == carnet }
    if alumno
      puts "Alumno encontrado: #{alumno}"
    else
      puts "Alumno con carnet #{carnet} no encontrado."
    end
  end

  def mostrar_alumnos
    if @alumnos.empty?
      puts "No hay alumnos registrados."
    else
      puts "\n=== LISTA DE ALUMNOS ==="
      puts "Total: #{@alumnos.size} alumnos\n"
      @alumnos.each_with_index do |alumno, index|
        puts "[#{index}] #{alumno}"
      end
      puts "========================\n"
    end
  end

  def promedio_general
    return 0 if @alumnos.empty?
    
    total = @alumnos.sum(&:nota)
    promedio = total.to_f / @alumnos.size
    promedio.round(2)
  end

  def alumnos_aprobados
    @alumnos.select { |a| a.nota >= 51 }.size
  end

  def alumnos_reprobados
    @alumnos.select { |a| a.nota < 51 }.size
  end

  def esta_vacia?
    @alumnos.empty?
  end
end

# Inicializar lista con alumnos de ejemplo
lista = ListaAlumnos.new
alumnos_iniciales = [
  Alumno.new("Juan Pérez", "12345", 85, "Estructura de Datos"),
  Alumno.new("María García", "12346", 72, "Estructura de Datos"),
  Alumno.new("Carlos López", "12347", 45, "Estructura de Datos"),
  Alumno.new("Ana Martínez", "12348", 90, "Estructura de Datos"),
  Alumno.new("Pedro Sánchez", "12349", 60, "Estructura de Datos")
]

alumnos_iniciales.each { |alumno| lista.agregar_alumno(alumno) }

loop do
  puts "\n--- MENÚ LISTA DE ALUMNOS ---"
  puts "1. Mostrar todos los alumnos"
  puts "2. Agregar nuevo alumno"
  puts "3. Eliminar alumno por carnet"
  puts "4. Buscar alumno por carnet"
  puts "5. Ver promedio general"
  puts "6. Ver estadísticas"
  puts "7. Salir"

  print "Elige una opción: "
  opcion = gets.chomp.to_i

  case opcion
  when 1
    lista.mostrar_alumnos

  when 2
    print "Nombre del alumno: "
    nombre = gets.chomp
    print "Carnet: "
    carnet = gets.chomp
    print "Nota (0-100): "
    nota = gets.chomp.to_i
    print "Materia: "
    materia = gets.chomp

    if nota < 0 || nota > 100
      puts "Error: La nota debe estar entre 0 y 100."
    else
      alumno = Alumno.new(nombre, carnet, nota, materia)
      lista.agregar_alumno(alumno)
    end

  when 3
    print "Carnet del alumno a eliminar: "
    carnet = gets.chomp
    lista.eliminar_alumno(carnet)

  when 4
    print "Carnet del alumno a buscar: "
    carnet = gets.chomp
    lista.buscar_alumno(carnet)

  when 5
    promedio = lista.promedio_general
    puts "Promedio general: #{promedio}"

  when 6
    puts "\n=== ESTADÍSTICAS ==="
    puts "Total alumnos: #{lista.alumnos.size}"
    puts "Aprobados (>= 51): #{lista.alumnos_aprobados}"
    puts "Reprobados (< 51): #{lista.alumnos_reprobados}"
    puts "Promedio general: #{lista.promedio_general}"
    puts "=====================\n"

  when 7
    puts "¡Hasta luego!"
    break

  else
    puts "Opción no válida."
  end
end
