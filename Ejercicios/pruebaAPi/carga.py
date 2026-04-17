import pandas as pd

print("Desde json")
df_archivo = pd.read_json('https://69d927420576c938825a9949.mockapi.io/api/v1/get/Users')
df_archivo.info()


print("Desde csv")
df = pd.read_csv('data/raw/datos.csv')
df.info()
#print(df.head())


