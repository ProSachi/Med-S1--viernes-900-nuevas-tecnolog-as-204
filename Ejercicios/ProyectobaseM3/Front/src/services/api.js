const API_BASE_URL = 'http://localhost:8080/api';

/**
 * Helper to handle fetch responses and extract JSON or error message
 */
async function handleResponse(response) {
  if (!response.ok) {
    let errorMessage = `HTTP Error ${response.status}`;
    try {
      const errorData = await response.json();
      errorMessage = errorData.message || errorData.error || errorMessage;
    } catch {
      try {
        const text = await response.text();
        if (text) errorMessage = text;
      } catch {}
    }
    throw new Error(errorMessage);
  }

  // Handle 204 No Content
  if (response.status === 204) {
    return null;
  }

  return response.json();
}

export const api = {
  // --- HEALTH CHECKS ---
  async checkHealth() {
    const res = await fetch(`${API_BASE_URL}/health`);
    if (!res.ok) throw new Error('Health check failed');
    return res.text();
  },

  async getHealthStatus() {
    const res = await fetch(`${API_BASE_URL}/health/status`);
    return handleResponse(res);
  },

  // --- ENFERMEROS (Nurses) ---
  async getEnfermeros() {
    const res = await fetch(`${API_BASE_URL}/enfermeros`);
    return handleResponse(res);
  },

  async getEnfermeroById(id) {
    const res = await fetch(`${API_BASE_URL}/enfermeros/${id}`);
    return handleResponse(res);
  },

  async createEnfermero(data) {
    const res = await fetch(`${API_BASE_URL}/enfermeros`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data),
    });
    return handleResponse(res);
  },

  async updateEnfermero(id, data) {
    const res = await fetch(`${API_BASE_URL}/enfermeros/${id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data),
    });
    return handleResponse(res);
  },

  async deleteEnfermero(id) {
    const res = await fetch(`${API_BASE_URL}/enfermeros/${id}`, {
      method: 'DELETE',
    });
    return handleResponse(res);
  },

  // --- MEDICOS (Doctors) ---
  async getMedicos() {
    const res = await fetch(`${API_BASE_URL}/medicos`);
    return handleResponse(res);
  },

  async getMedicoById(id) {
    const res = await fetch(`${API_BASE_URL}/medicos/${id}`);
    return handleResponse(res);
  },

  async createMedico(data) {
    const res = await fetch(`${API_BASE_URL}/medicos`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data),
    });
    return handleResponse(res);
  },

  async updateMedico(id, data) {
    const res = await fetch(`${API_BASE_URL}/medicos/${id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data),
    });
    return handleResponse(res);
  },

  async deleteMedico(id) {
    const res = await fetch(`${API_BASE_URL}/medicos/${id}`, {
      method: 'DELETE',
    });
    return handleResponse(res);
  },

  // --- PACIENTES (Patients) ---
  async getPacientes() {
    const res = await fetch(`${API_BASE_URL}/pacientes`);
    return handleResponse(res);
  },

  async getPacienteById(id) {
    const res = await fetch(`${API_BASE_URL}/pacientes/${id}`);
    return handleResponse(res);
  },

  async createPaciente(data) {
    // Data expected: { nombre: string, apellido: string, medicoId: number }
    const res = await fetch(`${API_BASE_URL}/pacientes`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        nombre: data.nombre,
        apellido: data.apellido,
        medicoId: parseInt(data.medicoId, 10)
      }),
    });
    return handleResponse(res);
  },

  async updatePaciente(id, data) {
    const res = await fetch(`${API_BASE_URL}/pacientes/${id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        nombre: data.nombre,
        apellido: data.apellido,
        medicoId: parseInt(data.medicoId, 10)
      }),
    });
    return handleResponse(res);
  },

  async deletePaciente(id) {
    const res = await fetch(`${API_BASE_URL}/pacientes/${id}`, {
      method: 'DELETE',
    });
    return handleResponse(res);
  },

  // --- MEDICAMENTOS (Medications) ---
  async getMedicamentos() {
    const res = await fetch(`${API_BASE_URL}/medicamentos`);
    return handleResponse(res);
  },

  async getMedicamentoById(id) {
    const res = await fetch(`${API_BASE_URL}/medicamentos/${id}`);
    return handleResponse(res);
  },

  async createMedicamento(data) {
    const res = await fetch(`${API_BASE_URL}/medicamentos`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data),
    });
    return handleResponse(res);
  },

  async updateMedicamento(id, data) {
    const res = await fetch(`${API_BASE_URL}/medicamentos/${id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data),
    });
    return handleResponse(res);
  },

  async deleteMedicamento(id) {
    const res = await fetch(`${API_BASE_URL}/medicamentos/${id}`, {
      method: 'DELETE',
    });
    return handleResponse(res);
  },

  // --- PRESCRIPCIONES (Prescriptions) ---
  async getPrescripciones() {
    const res = await fetch(`${API_BASE_URL}/prescripciones`);
    return handleResponse(res);
  },

  async getPrescripcionById(id) {
    const res = await fetch(`${API_BASE_URL}/prescripciones/${id}`);
    return handleResponse(res);
  },

  async getPrescripcionesByPaciente(pacienteId) {
    const res = await fetch(`${API_BASE_URL}/prescripciones/paciente/${pacienteId}`);
    return handleResponse(res);
  },

  async getPrescripcionesByMedico(medicoId) {
    const res = await fetch(`${API_BASE_URL}/prescripciones/medico/${medicoId}`);
    return handleResponse(res);
  },

  async getPrescripcionesByMedicamento(medicamentoId) {
    const res = await fetch(`${API_BASE_URL}/prescripciones/medicamento/${medicamentoId}`);
    return handleResponse(res);
  },

  async createPrescripcion(data) {
    const res = await fetch(`${API_BASE_URL}/prescripciones`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        medicamentoId: parseInt(data.medicamentoId, 10),
        pacienteId: parseInt(data.pacienteId, 10),
        medicoId: parseInt(data.medicoId, 10),
        dosis: data.dosis,
        frecuencia: data.frecuencia,
        duracion: data.duracion,
        observaciones: data.observaciones
      }),
    });
    return handleResponse(res);
  },

  async deletePrescripcion(id) {
    const res = await fetch(`${API_BASE_URL}/prescripciones/${id}`, {
      method: 'DELETE',
    });
    return handleResponse(res);
  },

  // --- GRAFICOS (Dashboard Analytics) ---
  getGraficoPullUrl(tipo, options = {}) {
    const params = new URLSearchParams();
    if (options.top) params.set('top', options.top.toString());
    if (options.t) params.set('t', options.t.toString());

    const query = params.toString();
    return `${API_BASE_URL}/graficos/pull/${tipo}${query ? `?${query}` : ''}`;
  },

  async getHeadlessPacientesPorMedico() {
    const res = await fetch(`${API_BASE_URL}/graficos/headless/pacientes-por-medico`);
    return handleResponse(res);
  },

  async getHeadlessPrescripcionesPorMedico() {
    const res = await fetch(`${API_BASE_URL}/graficos/headless/prescripciones-por-medico`);
    return handleResponse(res);
  },

  async getHeadlessPrescripcionesPorPaciente() {
    const res = await fetch(`${API_BASE_URL}/graficos/headless/prescripciones-por-paciente`);
    return handleResponse(res);
  },

  async getHeadlessMedicamentosMasPrescritos(top = 10) {
    const res = await fetch(`${API_BASE_URL}/graficos/headless/medicamentos-mas-prescritos?top=${top}`);
    return handleResponse(res);
  },
};
