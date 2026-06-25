// ============================================================================
// SCRIPT DE INICIALIZACIÓN MONGODB — EXAMEN FINAL
// Base de Datos NoRelacional: analitica_db
// ============================================================================

// Seleccionar la base de datos analítica
db = db.getSiblingDB('analitica_db');

// Crear la colección e insertar exactamente 10 registros analíticos (Documentos)
db.logs_accesos.insertMany([
  {
    "id_log": 1,
    "timestamp": "2026-06-24T10:15:30Z",
    "ip_address": "192.168.1.105",
    "usuario": "carlos.mendoza",
    "navegador": "Zen Browser (Firefox Gecko)",
    "accion": "Login exitoso",
    "detalles": {
      "modulo": "Autenticación",
      "duracion_ms": 120
    }
  },
  {
    "id_log": 2,
    "timestamp": "2026-06-24T10:17:12Z",
    "ip_address": "192.168.1.105",
    "usuario": "carlos.mendoza",
    "navegador": "Zen Browser (Firefox Gecko)",
    "accion": "Consulta de clientes",
    "detalles": {
      "modulo": "Ventas",
      "filtro_aplicado": "ciudad: Santa Cruz"
    }
  },
  {
    "id_log": 3,
    "timestamp": "2026-06-24T10:20:45Z",
    "ip_address": "200.58.120.44",
    "usuario": "david.mamani",
    "navegador": "Google Chrome (Blink Engine)",
    "accion": "Modificación de stock",
    "detalles": {
      "modulo": "Inventario",
      "id_producto_modificado": 12,
      "stock_anterior": 40,
      "stock_nuevo": 35
    }
  },
  {
    "id_log": 4,
    "timestamp": "2026-06-24T10:22:10Z",
    "ip_address": "192.168.1.112",
    "usuario": "ana.gomez",
    "navegador": "Safari (WebKit Engine)",
    "accion": "Ver candidatos",
    "detalles": {
      "modulo": "Recursos Humanos",
      "seccion": "Búsquedas activas"
    }
  },
  {
    "id_log": 5,
    "timestamp": "2026-06-24T10:25:01Z",
    "ip_address": "200.58.120.44",
    "usuario": "david.mamani",
    "navegador": "Google Chrome (Blink Engine)",
    "accion": "Consulta de auditoría",
    "detalles": {
      "modulo": "Seguridad",
      "tabla_consultada": "log_auditoria"
    }
  },
  {
    "id_log": 6,
    "timestamp": "2026-06-24T10:30:15Z",
    "ip_address": "192.168.1.125",
    "usuario": "maria.flores",
    "navegador": "Google Chrome (Blink Engine)",
    "accion": "Generar Reporte BI",
    "detalles": {
      "modulo": "Marketing",
      "tipo_grafico": "Ingresos por Categoría"
    }
  },
  {
    "id_log": 7,
    "timestamp": "2026-06-24T10:35:40Z",
    "ip_address": "192.168.1.105",
    "usuario": "carlos.mendoza",
    "navegador": "Zen Browser (Firefox Gecko)",
    "accion": "Simulación de Venta",
    "detalles": {
      "modulo": "E-Commerce",
      "monto_simulado": 450.50
    }
  },
  {
    "id_log": 8,
    "timestamp": "2026-06-24T10:40:02Z",
    "ip_address": "192.168.1.125",
    "usuario": "maria.flores",
    "navegador": "Google Chrome (Blink Engine)",
    "accion": "Exportar Excel",
    "detalles": {
      "modulo": "Ventas",
      "filas_exportadas": 32
    }
  },
  {
    "id_log": 9,
    "timestamp": "2026-06-24T10:45:18Z",
    "ip_address": "192.168.1.112",
    "usuario": "ana.gomez",
    "navegador": "Safari (WebKit Engine)",
    "accion": "Actualizar perfil",
    "detalles": {
      "modulo": "Recursos Humanos",
      "campo_modificado": "email"
    }
  },
  {
    "id_log": 10,
    "timestamp": "2026-06-24T10:50:33Z",
    "ip_address": "186.2.33.90",
    "usuario": "jose.perez",
    "navegador": "Microsoft Edge (Blink Engine)",
    "accion": "Cierre de Caja",
    "detalles": {
      "modulo": "Finanzas",
      "monto_total_cierre": 12500.00
    }
  }
]);

print("✅ MongoDB inicializado correctamente con 10 registros analíticos.");
