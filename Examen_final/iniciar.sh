#!/usr/bin/env bash
# ============================================================================
# SCRIPT DE INICIALIZACIÓN — EXAMEN FINAL
# ============================================================================

cd "$(dirname "$0")"

echo "============================================================"
echo "🚀 INICIANDO INFRAESTRUCTURA DEL EXAMEN FINAL"
echo "============================================================"

# 1. Verificar e iniciar Docker Daemon si está apagado
if ! systemctl is-active --quiet docker; then
    echo "🐳 El servicio de Docker no está activo. Iniciándolo (se requerirá contraseña sudo)..."
    sudo systemctl start docker
    if [ $? -ne 0 ]; then
        echo "❌ Error: No se pudo iniciar el servicio de Docker."
        exit 1
    fi
fi

# 2. Levantar contenedores
echo "🐳 Levantando contenedores de bases de datos..."
docker compose up -d
if [ $? -ne 0 ]; then
    echo "❌ Error: Docker Compose falló al levantar los servicios."
    exit 1
fi

echo "============================================================"
echo "✅ INFRAESTRUCTURA INICIADA CORRECTAMENTE"
echo "============================================================"
echo "🐘 PostgreSQL (puerto 5433) y 🍃 MongoDB (puerto 27018) están activos."
echo "Puedes consultar el archivo README.md para ver los comandos de prueba."
echo "============================================================"
