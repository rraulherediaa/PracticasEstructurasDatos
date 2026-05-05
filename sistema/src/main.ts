import { app, BrowserWindow, ipcMain } from 'electron';
import * as path from 'path';
import { GestorAtencion } from './controllers/GestorAtencion';

let mainWindow: BrowserWindow | null = null;
let visorWindow: BrowserWindow | null = null;
const gestor = new GestorAtencion();

function createWindows() {
  mainWindow = new BrowserWindow({
    width: 1200,
    height: 800,
    webPreferences: {
      preload: path.join(__dirname, 'preload.js'),
      nodeIntegration: false,
      contextIsolation: true
    },
    title: 'Hospital Rufus - Control'
  });

  mainWindow.loadFile(path.join(__dirname, '../src/index.html'));

  visorWindow = new BrowserWindow({
    width: 800,
    height: 600,
    webPreferences: {
      preload: path.join(__dirname, 'preload.js'),
      nodeIntegration: false,
      contextIsolation: true
    },
    title: 'Visor Sala de Espera'
  });

  visorWindow.loadFile(path.join(__dirname, '../src/visor.html'));
}

app.commandLine.appendSwitch('autoplay-policy', 'no-user-gesture-required');

app.whenReady().then(() => {
  createWindows();

  app.on('activate', () => {
    if (BrowserWindow.getAllWindows().length === 0) {
      createWindows();
    }
  });
});

app.on('window-all-closed', () => {
  if (process.platform !== 'darwin') {
    app.quit();
  }
});

// IPC Handlers
ipcMain.handle('get-catalogos', () => {
  return gestor.getCatalogosUI();
});

ipcMain.handle('generar-ficha', (event, datosPaciente) => {
  try {
    gestor.generarFicha(datosPaciente);
    return { success: true, cola: gestor.listarCola() };
  } catch (error: any) {
    return { success: false, error: error.message };
  }
});

ipcMain.handle('atender-siguiente', () => {
  const ficha = gestor.atenderSiguiente();
  
  if (ficha && visorWindow) {
    const randConsultorio = Math.floor(Math.random() * 5) + 1;
    visorWindow.webContents.send('llamar-paciente', {
      nroFicha: ficha.getNroFicha(),
      especialidad: ficha.getTipoServicio(),
      consultorio: randConsultorio
    });
  }

  return {
    success: !!ficha,
    fichaAtendida: ficha ? { nombre: ficha.getPaciente().getNombre() } : null,
    cola: gestor.listarCola(),
    historial: gestor.listarHistorial()
  };
});

ipcMain.handle('cancelar-siguiente', () => {
  const ficha = gestor.cancelarSiguiente();
  return {
    success: !!ficha,
    cola: gestor.listarCola()
  };
});

ipcMain.handle('get-cola', () => {
  return gestor.listarCola();
});

ipcMain.handle('get-historial', () => {
  return gestor.listarHistorial();
});

ipcMain.handle('get-pacientes', () => {
  return gestor.listarPacientes();
});

ipcMain.handle('buscar-paciente', (_event, ci) => {
  return gestor.buscarPaciente(ci);
});

ipcMain.handle('resetear-sistema', () => {
  gestor.resetearDatos();
  return { success: true };
});
