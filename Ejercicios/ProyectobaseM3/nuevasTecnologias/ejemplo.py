import pandas as pd
import seaborn as sns 
import matplotlib as plt

datos_empresa = [
    {"sucursal": "Norte", "ingresos": 150000, "costos": 90000},
    {"sucursal": "Sur", "ingresos": 120000, "costos": 85000},
    {"sucursal": "Centro", "ingresos": 200000, "costos": 110000}
]

# --- 1. ENDPOINT HEADLESS ---
@app.get("/api/interno/sucursales/metricas")
def obtener_metricas_crudas():
    df = pd.DataFrame(datos_empresa)
    
    # Transformación analítica de negocio
    df['margen_ganancia'] = df['ingresos'] - df['costos']
    df['roi_porcentaje'] = (df['margen_ganancia'] / df['costos']) * 100
    
    # Ordenamiento de mayor a menor ROI
    df_limpio = df.sort_values(by='roi_porcentaje', ascending=False)
    
    return {
        "origen": "Python Analytics",
        "datos": df_limpio.to_dict(orient='records')
    }




# --- 2. ENDPOINT PULL ---
@app.get("/api/interno/sucursales/grafico")
def obtener_grafico_rentabilidad():
    df = pd.DataFrame(datos_empresa)
    df['margen'] = df['ingresos'] - df['costos']
    
    # Renderizado
    fig, ax = plt.subplots(figsize=(6, 4))
    sns.barplot(data=df, x='sucursal', y='margen', palette='mako', ax=ax)
    ax.set_title("Margen Neto por Sucursal")
    sns.despine()
    
    # Manejo en RAM (Protección de disco)
    buffer = io.BytesIO()
    plt.savefig(buffer, format='svg', transparent=True, bbox_inches='tight')
    plt.close() # Prevenir fugas de memoria
    
    # Codificación HTTP
    buffer.seek(0)
    img_b64 = base64.b64encode(buffer.read()).decode('utf-8')
    
    return {
        "mime_type": "image/svg+xml",
        "imagen_base64": img_b64
    }

# Ejecución: uvicorn microservicio:app --reload --port 8000
