document.addEventListener('DOMContentLoaded', () => {
  const ticketDisplay = document.getElementById('ticketDisplay');
  const consultorioDisplay = document.getElementById('consultorioDisplay');
  const body = document.getElementById('visorBody');

  window.api.onPacienteLlamado((data: any) => {
    if (!data) return;

    // Actualizar UI
    if (ticketDisplay) ticketDisplay.innerText = data.nroFicha;
    if (consultorioDisplay) consultorioDisplay.innerText = `Pase a Consultorio ${data.consultorio} - ${data.especialidad}`;

    // Efecto visual de llamado
    body?.classList.remove('blinking');
    void body?.offsetWidth; // Trigger reflow
    body?.classList.add('blinking');

    // Síntesis de Voz
    // Parse the ticket for speech. EME-CAR-001 -> "E M E. C A R. 001"
    const partes = data.nroFicha.split('-');
    let speechText = '';
    
    if (partes.length === 3) {
      // Deletrear para que la voz no lo lea como una palabra extraña
      const prefijo = partes[0].split('').join(' ');
      const especialidad = partes[1].split('').join(' ');
      const numero = partes[2];
      speechText = `Ficha, ${prefijo}, ${especialidad}, ${numero}. Pase a consultorio ${data.consultorio}, ${data.especialidad}.`;
    } else {
      speechText = `Ficha ${data.nroFicha}. Pase a consultorio ${data.consultorio}.`;
    }

    // Utilizar la API de Google Translate TTS (requiere internet pero funciona en cualquier OS)
    const url = `https://translate.google.com/translate_tts?ie=UTF-8&client=tw-ob&q=${encodeURIComponent(speechText)}&tl=es`;
    const audio = new Audio(url);
    
    audio.play().catch(e => {
      console.log('Error reproduciendo audio:', e);
      // Fallback si no hay internet: podrías usar un sonido local aquí
    });
  });
});
