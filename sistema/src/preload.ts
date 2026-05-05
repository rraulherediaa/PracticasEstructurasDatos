import { contextBridge, ipcRenderer } from 'electron';

contextBridge.exposeInMainWorld('api', {
  getCatalogos: () => ipcRenderer.invoke('get-catalogos'),
  generarFicha: (datosPaciente: any) => ipcRenderer.invoke('generar-ficha', datosPaciente),
  atenderSiguiente: () => ipcRenderer.invoke('atender-siguiente'),
  cancelarSiguiente: () => ipcRenderer.invoke('cancelar-siguiente'),
  getCola: () => ipcRenderer.invoke('get-cola'),
  getHistorial: () => ipcRenderer.invoke('get-historial'),
  getPacientes: () => ipcRenderer.invoke('get-pacientes'),
  buscarPaciente: (ci: string) => ipcRenderer.invoke('buscar-paciente', ci),
  resetearSistema: () => ipcRenderer.invoke('resetear-sistema'),
  onPacienteLlamado: (callback: (data: any) => void) => ipcRenderer.on('llamar-paciente', (_event, data) => callback(data))
});
