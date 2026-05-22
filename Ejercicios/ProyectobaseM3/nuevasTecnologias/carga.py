import os
import requests
import pandas as pd
from dotenv import load_dotenv

load_dotenv()

BASE_URL = os.getenv("API_BASE_URL")

def _get_dataframe(endpoint: str) -> pd.DataFrame:
    url = f"{BASE_URL}{endpoint}"
    response = requests.get(url)
    response.raise_for_status()
    return pd.DataFrame(response.json())

def get_enfermeros() -> pd.DataFrame:
    return _get_dataframe("/api/enfermeros")

def get_medicamentos() -> pd.DataFrame:
    return _get_dataframe("/api/medicamentos")

def get_medicos() -> pd.DataFrame:
    return _get_dataframe("/api/medicos")

def get_pacientes() -> pd.DataFrame:
    return _get_dataframe("/api/pacientes")

def get_prescripciones() -> pd.DataFrame:
    return _get_dataframe("/api/prescripciones")
