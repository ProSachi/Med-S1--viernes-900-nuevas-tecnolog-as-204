import pandas as pd
from analisis import pacientes_limpio, medicos_limpio, prescripciones_limpio

# ─────────────────────────────────────────────
# Carga de DataFrames limpios
# ─────────────────────────────────────────────

pacientes      = pacientes_limpio()
medicos        = medicos_limpio()
prescripciones = prescripciones_limpio()


# ─────────────────────────────────────────────
# Merges
# ─────────────────────────────────────────────

def pacientes_con_medicos() -> pd.DataFrame:
    """
    Relaciona cada paciente con su médico asignado.
    FK esperada: pacientes.medicoId -> medicos.id
    """
    return pd.merge(
        pacientes,
        medicos,
        left_on="id",
        right_on="id",
        how="left",
        suffixes=("_paciente", "_medico"),
    )


def pacientes_con_prescripciones() -> pd.DataFrame:
    """
    Relaciona cada paciente con sus prescripciones.
    FK esperada: prescripciones.pacienteId -> pacientes.id
    """
    return pd.merge(
        pacientes,
        prescripciones,
        left_on="id",
        right_on="id",
        how="left",
        suffixes=("_paciente", "_prescripcion"),
    )


def medicos_con_prescripciones() -> pd.DataFrame:
    """
    Relaciona cada médico con las prescripciones que ha emitido.
    FK esperada: prescripciones.medicoId -> medicos.id
    """
    return pd.merge(
        medicos,
        prescripciones,
        left_on="id",
        right_on="id",
        how="left",
        suffixes=("_medico", "_prescripcion"),
    )


def pacientes_medicos_prescripciones() -> pd.DataFrame:
    """
    Vista completa: paciente + médico + prescripción.
    """
    base = pd.merge(
        prescripciones,
        pacientes,
        left_on="id",
        right_on="id",
        how="left",
        suffixes=("_prescripcion", "_paciente"),
    )
    return pd.merge(
        base,
        medicos,
        left_on="id",
        right_on="id",
        how="left",
        suffixes=("", "_medico"),
    )
