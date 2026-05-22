import pandas as pd
import plotly.express as px

df_ventas = pd.DataFrame({
    'Pais': ['México', 'Colombia', 'Argentina', 'Chile', 'Perú'],
    'Ventas_USD': [15000, 12000, 8000, 9500, 7000]
})

# 1. Creación del gráfico Plotly
fig = px.bar(
    df_ventas, 
    x='Pais', 
    y='Ventas_USD', 
    color='Pais', # Automáticamente da un color y crea una leyenda interactiva
    title='Reporte de Ventas por País (Interactivo)'
)

# 2. Exportación
ruta_html = "reporte_ventas.html"
fig.write_html(ruta_html, include_plotlyjs='cdn')
print(f"Reporte exportado exitosamente a {ruta_html}")
