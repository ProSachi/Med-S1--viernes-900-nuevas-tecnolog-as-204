from types import SimpleNamespace

import pandas as pd
from carga import (
    get_enfermeros,
    get_medicamentos,
    get_medicos,
    get_pacientes,
    get_prescripciones,
)


# ─────────────────────────────────────────────
# Utilidades
# ─────────────────────────────────────────────

def _limpiar_base(df: pd.DataFrame) -> pd.DataFrame:
    """Limpieza genérica aplicable a todos los DataFrames."""
    # Eliminar filas y columnas completamente vacías
    df = df.dropna(how="all").reset_index(drop=True)
    df = df.dropna(axis=1, how="all")

    # Convertir columnas con tipos no hashables (list, dict) a string
    # para que drop_duplicates pueda operar sobre ellas
    for col in df.columns:
        if df[col].apply(lambda v: isinstance(v, (list, dict))).any():
            df[col] = df[col].apply(str)

    # Eliminar filas duplicadas
    df = df.drop_duplicates().reset_index(drop=True)

    # Limpiar espacios en columnas de texto
    str_cols = df.select_dtypes(include="object").columns
    df[str_cols] = df[str_cols].apply(lambda col: col.str.strip())

    return df


def _convertir_fechas(df: pd.DataFrame, columnas: list[str]) -> pd.DataFrame:
    """Intenta convertir las columnas indicadas a datetime."""
    for col in columnas:
        if col in df.columns:
            df[col] = pd.to_datetime(df[col], errors="coerce")
    return df


# ─────────────────────────────────────────────
# Limpieza por entidad
# ─────────────────────────────────────────────

def limpiar_enfermeros(df: pd.DataFrame) -> pd.DataFrame:
    df = _limpiar_base(df)
    # Descartar registros sin identificador
    if "id" in df.columns:
        df = df.dropna(subset=["id"])
    return df.reset_index(drop=True)


def limpiar_medicamentos(df: pd.DataFrame) -> pd.DataFrame:
    df = _limpiar_base(df)
    if "id" in df.columns:
        df = df.dropna(subset=["id"])
    # Normalizar nombre a título si existe
    if "nombre" in df.columns:
        df["nombre"] = df["nombre"].str.title()
    return df.reset_index(drop=True)


def limpiar_medicos(df: pd.DataFrame) -> pd.DataFrame:
    df = _limpiar_base(df)
    if "id" in df.columns:
        df = df.dropna(subset=["id"])
    if "nombre" in df.columns:
        df["nombre"] = df["nombre"].str.title()
    return df.reset_index(drop=True)


def limpiar_pacientes(df: pd.DataFrame) -> pd.DataFrame:
    df = _limpiar_base(df)
    if "id" in df.columns:
        df = df.dropna(subset=["id"])
    # Posibles columnas de fecha en pacientes
    df = _convertir_fechas(df, ["fechaNacimiento", "fecha_nacimiento", "fechaIngreso"])
    if "nombre" in df.columns:
        df["nombre"] = df["nombre"].str.title()
    return df.reset_index(drop=True)


def limpiar_prescripciones(df: pd.DataFrame) -> pd.DataFrame:
    df = _limpiar_base(df)
    if "id" in df.columns:
        df = df.dropna(subset=["id"])
    # Posibles columnas de fecha en prescripciones
    df = _convertir_fechas(df, ["fecha", "fechaPrescripcion", "fecha_prescripcion"])
    return df.reset_index(drop=True)


# ─────────────────────────────────────────────
# Funciones públicas fetch + limpieza
# ─────────────────────────────────────────────

def enfermeros_limpio() -> pd.DataFrame:
    return limpiar_enfermeros(get_enfermeros())


def medicamentos_limpio() -> pd.DataFrame:
    return limpiar_medicamentos(get_medicamentos())


def medicos_limpio() -> pd.DataFrame:
    return limpiar_medicos(get_medicos())


def pacientes_limpio() -> pd.DataFrame:
    return limpiar_pacientes(get_pacientes())


def prescripciones_limpio() -> pd.DataFrame:
    return limpiar_prescripciones(get_prescripciones())


# ─────────────────────────────────────────────
# Función principal
# ─────────────────────────────────────────────

def cargar_y_limpiar() -> SimpleNamespace:
    """
    Obtiene y limpia todos los DataFrames desde la API.
    Retorna un SimpleNamespace para acceder por atributo:
        datos = cargar_y_limpiar()
        datos.pacientes / datos.medicos / ...
    """
    return SimpleNamespace(
        enfermeros=enfermeros_limpio(),
        medicamentos=medicamentos_limpio(),
        medicos=medicos_limpio(),
        pacientes=pacientes_limpio(),
        prescripciones=prescripciones_limpio(),
    )
