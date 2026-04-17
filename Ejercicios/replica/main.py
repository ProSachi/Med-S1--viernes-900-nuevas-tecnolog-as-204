from carga import cargar_gastos_hormiga
df_gastos = cargar_gastos_hormiga()
print(df_gastos)

""" print(df_gastos.info()) """

df_gastos['NuevaCol'] = df_gastos['gastos'] * 2

print(df_gastos)