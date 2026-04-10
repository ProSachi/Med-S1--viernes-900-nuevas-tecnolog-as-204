import pandas as pd

def cargar_gastos_hormiga(ruta_csv: str = "data/row/gastos_hormiga.csv") -> pd.DataFrame:
	"""Carga el CSV de gastos hormiga y lo convierte en DataFrame."""
	return pd.read_csv(ruta_csv)



