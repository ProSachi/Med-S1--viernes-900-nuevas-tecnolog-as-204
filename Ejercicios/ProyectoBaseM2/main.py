import carga
import limpieza
import merge
import analisis

print('Proceso de Carga')
df_trabajo = carga.carga_clientes()
print('Proceso de Carga, finalizado correctamente')
print('Proceso de Limpieza, en ejecución...')
limpieza.limpiar_clientes(df_trabajo)
print('Proceso de limpieza, finalizado correctamente, resultado guardado en la ruta de processed')


print('Proceso de Carga de Ventas')
df_ventas = carga.carga_ventas()
print('Proceso de Carga, finalizado correctamente')
print('Proceso de Limpieza de ventas, en ejecución...')
limpieza.limpieza_Ventas(df_ventas)

df_ventas_cliente = merge.merge()

print("Este es el dataframe mergeado")
df_ventas_cliente.info()


analisis.analisis(df_ventas_cliente)