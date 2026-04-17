import pandas as pd

#def cargar_gastos_hormiga(ruta_csv: str = "data/row/gastos_hormiga.csv") -> pd.DataFrame:
#	"""Carga el CSV de gastos hormiga y lo convierte en DataFrame."""
#	return pd.read_csv(ruta_csv)

df_cliente = pd.DataFrame({'id_cliente': [1, 2, 3], 'nombre': ['Ana', 'Luis', 'Marta']})

df_ventas = pd.DataFrame({'id_venta': [101, 102, 103], 'id_cliente': [1, 1, 3], 'monto': [50, 150, 200]})

print(df_cliente.info())
print(df_ventas.info())

df_master = pd.merge(df_cliente,df_ventas, on='id_cliente', how='left')

print(df_master)


df_cliente = pd.DataFrame(
  {
    "name": "name 1",
    "avatar": "avatar 1",
    "correo": "correo 1",
    "id": "1"
  },
  {
    "name": "name 2",
    "avatar": "avatar 2",
    "correo": "correo 2",
    "id": "2"
  },
  {
    "name": "name 3",
    "avatar": "avatar 3",
    "correo": "correo 3",
    "id": "3"
  },
  {
    "name": "name 4",
    "avatar": "avatar 4",
    "correo": "correo 4",
    "id": "4"
  },
  {
    "name": "name 5",
    "avatar": "avatar 5",
    "correo": "correo 5",
    "id": "5"
  },
  {
    "name": "name 6",
    "avatar": "avatar 6",
    "correo": "correo 6",
    "id": "6"
  },
  {
    "name": "name 7",
    "avatar": "avatar 7",
    "correo": "correo 7",
    "id": "7"
  },
  {
    "name": "name 8",
    "avatar": "avatar 8",
    "correo": "correo 8",
    "id": "8"
  },
  {
    "name": "name 9",
    "avatar": "avatar 9",
    "correo": "correo 9",
    "id": "9"
  },
  {
    "name": "name 10",
    "avatar": "avatar 10",
    "correo": "correo 10",
    "id": "10"
  },
  {
    "name": "name 11",
    "avatar": "avatar 11",
    "correo": "correo 11",
    "id": "11"
  }
     )