import pandas as pd

def merge():
    df_cliente = pd.read_csv('data/processed/cliente_limpio.csv')
    df_venta = pd.read_csv('data/processed/ventas_limpio.csv')
    df_clientes_ventas = df_venta.merge(df_cliente, on="id_cliente", how="left")
    df_clientes_ventas.to_csv('data/processed/clientes_ventas.csv', index=False)
    return df_clientes_ventas