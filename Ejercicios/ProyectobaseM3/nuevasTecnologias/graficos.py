"""
graficos.py — Dos modelos de visualización:

  HEADLESS → Python hace el ETL y retorna list[dict] (JSON-serializable).
             Spring Boot / React consumen los datos y renderizan el gráfico.
             Función obligatoria: .to_dict(orient='records')

  PULL     → Python dibuja la imagen en RAM (BytesIO) y retorna bytes PNG.
             Evita I/O en disco en servidores concurrentes.
"""

from io import BytesIO

import matplotlib
matplotlib.use("Agg")  # backend sin pantalla, obligatorio en servidores
import matplotlib.pyplot as plt
from matplotlib.figure import Figure
import pandas as pd

from merge import (
    pacientes_con_medicos,
    pacientes_con_prescripciones,
    medicos_con_prescripciones,
    pacientes_medicos_prescripciones,
)

# ══════════════════════════════════════════════════════════
# UTILIDADES INTERNAS
# ══════════════════════════════════════════════════════════

def _fig_to_bytes(fig: Figure) -> bytes:
    """Serializa una figura matplotlib a PNG en memoria RAM."""
    buf = BytesIO()
    fig.savefig(buf, format="png", bbox_inches="tight")
    plt.close(fig)
    buf.seek(0)
    return buf.read()


def _col(df: pd.DataFrame, *candidatos: str) -> str:
    """Retorna la primera columna candidata que exista en el DataFrame."""
    for c in candidatos:
        if c in df.columns:
            return c
    raise KeyError(f"Ninguna de las columnas {candidatos} existe en el DataFrame.")


# ══════════════════════════════════════════════════════════
# MODELO HEADLESS  →  list[dict]  (JSON-serializable)
# ══════════════════════════════════════════════════════════

def headless_pacientes_por_medico() -> list[dict]:
    """Cantidad de pacientes asignados a cada médico."""
    df = pacientes_con_medicos()
    col = _col(df, "nombre_medico", "nombre")
    resumen = (
        df.groupby(col)
        .size()
        .reset_index(name="total_pacientes")
        .sort_values("total_pacientes", ascending=False)
    )
    return resumen.to_dict(orient="records")


def headless_prescripciones_por_medico() -> list[dict]:
    """Cantidad de prescripciones emitidas por cada médico."""
    df = medicos_con_prescripciones()
    col = _col(df, "nombre_medico", "nombre")
    resumen = (
        df.groupby(col)
        .size()
        .reset_index(name="total_prescripciones")
        .sort_values("total_prescripciones", ascending=False)
    )
    return resumen.to_dict(orient="records")


def headless_prescripciones_por_paciente() -> list[dict]:
    """Cantidad de prescripciones recibidas por cada paciente."""
    df = pacientes_con_prescripciones()
    col = _col(df, "nombre_paciente", "nombre")
    resumen = (
        df.groupby(col)
        .size()
        .reset_index(name="total_prescripciones")
        .sort_values("total_prescripciones", ascending=False)
    )
    return resumen.to_dict(orient="records")


def headless_medicamentos_mas_prescritos(top: int = 10) -> list[dict]:
    """Top N medicamentos más recetados."""
    df = pacientes_medicos_prescripciones()
    col = next((c for c in df.columns if "medicamento" in c.lower()), None)
    if col is None:
        return []
    resumen = (
        df.groupby(col)
        .size()
        .reset_index(name="total")
        .sort_values("total", ascending=False)
        .head(top)
    )
    return resumen.to_dict(orient="records")


# ══════════════════════════════════════════════════════════
# MODELO PULL  →  bytes PNG  (imagen renderizada en RAM)
# ══════════════════════════════════════════════════════════

def pull_pacientes_por_medico() -> bytes:
    """Barras verticales: total de pacientes por médico."""
    df = pd.DataFrame(headless_pacientes_por_medico())
    col = df.columns[0]

    fig, ax = plt.subplots(figsize=(10, 5))
    ax.bar(df[col], df["total_pacientes"], color="steelblue")
    ax.set_title("Pacientes por médico")
    ax.set_xlabel("Médico")
    ax.set_ylabel("Pacientes")
    plt.xticks(rotation=45, ha="right")
    return _fig_to_bytes(fig)


def pull_prescripciones_por_medico() -> bytes:
    """Barras verticales: total de prescripciones emitidas por cada médico."""
    df = pd.DataFrame(headless_prescripciones_por_medico())
    col = df.columns[0]

    fig, ax = plt.subplots(figsize=(10, 5))
    ax.bar(df[col], df["total_prescripciones"], color="darkorange")
    ax.set_title("Prescripciones por médico")
    ax.set_xlabel("Médico")
    ax.set_ylabel("Prescripciones")
    plt.xticks(rotation=45, ha="right")
    return _fig_to_bytes(fig)


def pull_prescripciones_por_paciente() -> bytes:
    """Barras verticales: total de prescripciones recibidas por cada paciente."""
    df = pd.DataFrame(headless_prescripciones_por_paciente())
    col = df.columns[0]

    fig, ax = plt.subplots(figsize=(10, 5))
    ax.bar(df[col], df["total_prescripciones"], color="seagreen")
    ax.set_title("Prescripciones por paciente")
    ax.set_xlabel("Paciente")
    ax.set_ylabel("Prescripciones")
    plt.xticks(rotation=45, ha="right")
    return _fig_to_bytes(fig)


def pull_medicamentos_mas_prescritos(top: int = 10) -> bytes:
    """Barras horizontales: top N medicamentos más prescritos."""
    datos = headless_medicamentos_mas_prescritos(top)
    if not datos:
        fig, ax = plt.subplots()
        ax.text(0.5, 0.5, "Sin datos", ha="center", va="center")
        return _fig_to_bytes(fig)

    df = pd.DataFrame(datos)
    col = df.columns[0]

    fig, ax = plt.subplots(figsize=(8, 6))
    ax.barh(df[col], df["total"], color="mediumpurple")
    ax.set_title(f"Top {top} medicamentos más prescritos")
    ax.set_xlabel("Total prescripciones")
    ax.invert_yaxis()
    return _fig_to_bytes(fig)


# ══════════════════════════════════════════════════════════
# PRUEBA LOCAL
# ══════════════════════════════════════════════════════════

import json
import os

_HEADLESS = {
    "pacientes_por_medico":        headless_pacientes_por_medico,
    "prescripciones_por_medico":   headless_prescripciones_por_medico,
    "prescripciones_por_paciente": headless_prescripciones_por_paciente,
    "medicamentos_mas_prescritos": headless_medicamentos_mas_prescritos,
}

_PULL = {
    "pacientes_por_medico":        pull_pacientes_por_medico,
    "prescripciones_por_medico":   pull_prescripciones_por_medico,
    "prescripciones_por_paciente": pull_prescripciones_por_paciente,
    "medicamentos_mas_prescritos": pull_medicamentos_mas_prescritos,
}


def probar_headless():
    """Imprime el JSON que se enviaría a Spring Boot / React."""
    print("\n" + "═" * 55)
    print("  MODELO HEADLESS  →  list[dict]")
    print("═" * 55)
    for nombre, fn in _HEADLESS.items():
        print(f"\n── {nombre} ──")
        try:
            datos = fn()
            print(json.dumps(datos, ensure_ascii=False, indent=2, default=str))
        except Exception as e:
            print(f"  [ERROR] {type(e).__name__}: {e}")


def probar_pull(carpeta: str = "graficos_prueba"):
    """Guarda los PNG en una carpeta local para inspeccionarlos."""
    os.makedirs(carpeta, exist_ok=True)
    print("\n" + "═" * 55)
    print(f"  MODELO PULL  →  PNG en '{carpeta}/'")
    print("═" * 55)
    for nombre, fn in _PULL.items():
        ruta = os.path.join(carpeta, f"{nombre}.png")
        try:
            with open(ruta, "wb") as f:
                f.write(fn())
            print(f"  ✓  {ruta}")
        except Exception as e:
            print(f"  [ERROR] {nombre}: {type(e).__name__}: {e}")


if __name__ == "__main__":
    probar_headless()
    probar_pull()
