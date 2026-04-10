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

class ColaAlumnos
  def initialize
    @alumnos = []
  end

  def enqueue(alumno)
    @alumnos << alumno
    puts "Alumno encolado: #{alumno.nombre}"
  end

  def dequeue
    if @alumnos.empty?
      puts "La cola está vacía. No hay alumnos para desencolar."
      nil
    else
      alumno = @alumnos.shift
      puts "Alumno desencolado: #{alumno.nombre}"
      alumno
    end
  end

  def peek
    if @alumnos.empty?
      puts "La cola está vacía. No hay alumnos en el frente."
      nil
    else
      @alumnos.first
    end
  end

  def mostrar_cola
    if @alumnos.empty?
      puts "No hay alumnos en la cola."
    else
      puts "\n=== COLA DE ALUMNOS ==="
      puts "Total: #{@alumnos.size} alumnos\n"
      puts "FRENTE DE LA COLA:"
      @alumnos.each_with_index do |alumno, index|
        puts "[#{index}] #{alumno}"
      end
      puts "======================\n"
    end
  end

  def esta_vacia?
    @alumnos.empty?
  end

  def tamanio
    @alumnos.size
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
end

# Inicializar cola con alumnos de ejemplo
cola = ColaAlumnos.new
alumnos_iniciales = [
  Alumno.new("Juan Pérez", "12345", 85, "Estructura de Datos"),
  Alumno.new("María García", "12346", 72, "Estructura de Datos"),
  Alumno.new("Carlos López", "12347", 45, "Estructura de Datos"),
  Alumno.new("Ana Martínez", "12348", 90, "Estructura de Datos"),
  Alumno.new("Pedro Sánchez", "12349", 60, "Estructura de Datos")
]

alumnos_iniciales.each { |alumno| cola.enqueue(alumno) }

loop do
  puts "\n--- MENÚ COLA DE ALUMNOS ---"
  puts "1. Mostrar cola de alumnos"
  puts "2. Encolar alumno (agregar al final)"
  puts "3. Desencolar alumno (quitar del frente)"
  puts "4. Ver frente de la cola"
  puts "5. Ver si está vacía"
  puts "6. Ver promedio general"
  puts "7. Ver estadísticas"
  puts "8. Salir"

  print "Elige una opción: "
  opcion = gets.chomp.to_i

  case opcion
  when 1
    cola.mostrar_cola

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
      cola.enqueue(alumno)
    end

  when 3
    cola.dequeue

  when 4
    frente = cola.peek
    if frente
      puts "Frente de la cola: #{frente}"
    end

  when 5
    if cola.esta_vacia?
      puts "La cola está VACÍA"
    else
      puts "La cola NO está vacía (#{cola.tamanio} alumnos)"
    end

  when 6
    promedio = cola.promedio_general
    puts "Promedio general: #{promedio}"

  when 7
    puts "\n=== ESTADÍSTICAS ==="
    puts "Total alumnos: #{cola.tamanio}"
    puts "Aprobados (>= 51): #{cola.alumnos_aprobados}"
    puts "Reprobados (< 51): #{cola.alumnos_reprobados}"
    puts "Promedio general: #{cola.promedio_general}"
    puts "=====================\n"

  when 8
    puts "¡Hasta luego!"
    break

  else
    puts "Opción no válida."
  end
end
