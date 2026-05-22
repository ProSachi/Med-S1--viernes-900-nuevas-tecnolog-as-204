from fastapi import FastAPI
from fastapi.responses import Response
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
import io
import base64

from graficos import (
    headless_pacientes_por_medico,
    headless_prescripciones_por_medico,
    headless_prescripciones_por_paciente,
    headless_medicamentos_mas_prescritos,
    pull_pacientes_por_medico,
    pull_prescripciones_por_medico,
    pull_prescripciones_por_paciente,
    pull_medicamentos_mas_prescritos,
)

app = FastAPI(title="Motor Analítico Privado")

# ════════════════════════════════════════════════════════
# ENDPOINTS HEADLESS  →  JSON  (React / Spring Boot)
# ════════════════════════════════════════════════════════

@app.get("/api/graficos/headless/pacientes-por-medico")
def headless_ep_pacientes_por_medico():
    return headless_pacientes_por_medico()


@app.get("/api/graficos/headless/prescripciones-por-medico")
def headless_ep_prescripciones_por_medico():
    return headless_prescripciones_por_medico()


@app.get("/api/graficos/headless/prescripciones-por-paciente")
def headless_ep_prescripciones_por_paciente():
    return headless_prescripciones_por_paciente()


@app.get("/api/graficos/headless/medicamentos-mas-prescritos")
def headless_ep_medicamentos_mas_prescritos(top: int = 10):
    return headless_medicamentos_mas_prescritos(top)


# ════════════════════════════════════════════════════════
# ENDPOINTS PULL  →  image/png  (bytes en RAM)
# ════════════════════════════════════════════════════════

@app.get("/api/graficos/pull/pacientes-por-medico", response_class=Response)
def pull_ep_pacientes_por_medico():
    return Response(content=pull_pacientes_por_medico(), media_type="image/png")


@app.get("/api/graficos/pull/prescripciones-por-medico", response_class=Response)
def pull_ep_prescripciones_por_medico():
    return Response(content=pull_prescripciones_por_medico(), media_type="image/png")


@app.get("/api/graficos/pull/prescripciones-por-paciente", response_class=Response)
def pull_ep_prescripciones_por_paciente():
    return Response(content=pull_prescripciones_por_paciente(), media_type="image/png")


@app.get("/api/graficos/pull/medicamentos-mas-prescritos", response_class=Response)
def pull_ep_medicamentos_mas_prescritos(top: int = 10):
    return Response(content=pull_medicamentos_mas_prescritos(top), media_type="image/png")
