from numpy import int64
import pandas as pd

def carga_clientes():
    df_archivo = pd.read_csv('data/row/dataset_limpieza_practica.csv')
    df_archivo.info()
    #print(df_archivo.head())
    return df_archivo

def carga_ventas():
    df_ventas = pd.read_csv('data/row/ventas.csv')
    df_ventas.info()
    #print(df_archivo.head())

    return df_ventas





