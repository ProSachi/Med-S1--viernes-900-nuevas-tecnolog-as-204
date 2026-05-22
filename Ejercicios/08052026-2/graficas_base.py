import pandas as pd
import matplotlib.pyplot as plt
# Seaborn se importa para mejorar la estética global automáticamente
import seaborn as sns

from datos import cargarDatos 

# Configuración estética global de Seaborn
sns.set_theme(style="whitegrid")

data = cargarDatos()

# --- INSUMOS SINTÉTICOS (Dataset Analítico) ---
# 1. Datos Temporales
df_meses = pd.DataFrame(data)
# 2. Datos Categóricos
df_asesores = pd.DataFrame({'asesor': ['Ana Gomez', 'Carlos Ruiz', 'Marta Perez'], 'ventas_totales': [5000, 3200, 4100]})
# 3. Datos Numéricos (Dispersión)
df_clientes = pd.DataFrame({'edad': [25, 34, 45, 52, 23, 60], 'gasto_usd': [150, 400, 800, 750, 100, 1200]})


print("--- GRÁFICO 1: TENDENCIA TEMPORAL (Líneas) ---")
plt.figure(figsize=(8, 4))
# marker='o' pone un punto en cada mes
plt.plot(df_meses['mes'], df_meses['ventas'], color='#2ca02c', marker='o', linewidth=2)
plt.title("Crecimiento de Ventas (Enero - Mayo)", fontsize=14, fontweight='bold')
plt.xlabel("Mes")
plt.ylabel("Millones USD")
plt.show() # Cierra y renderiza el primer gráfico


print("\n--- GRÁFICO 2: RANKING CATEGÓRICO (Barras Horizontales) ---")
# Obligatorio: Ordenar los datos antes de graficar barras para que formen una escalera
df_asesores_ordenado = df_asesores.sort_values(by='ventas_totales', ascending=True)

plt.figure(figsize=(8, 4))
# plt.barh es ideal para nombres largos en el eje Y
plt.barh(df_asesores_ordenado['asesor'], df_asesores_ordenado['ventas_totales'], color='#1f77b4')
plt.title("Top 3 Asesores del Semestre")
plt.xlabel("Total Vendido (USD)")
# plt.tight_layout() ajusta los márgenes automáticamente para que no se corten los nombres
plt.tight_layout() 
plt.show()


print("\n--- GRÁFICO 3: RELACIÓN ESTADÍSTICA (Dispersión con Seaborn) ---")
plt.figure(figsize=(8, 5))
# Seaborn (sns) hace el scatter plot más limpio
sns.scatterplot(data=df_clientes, x='edad', y='gasto_usd', s=100, color='purple')
plt.title("Comportamiento de Gasto por Edad")
plt.xlabel("Edad del Cliente")
plt.ylabel("Gasto Histórico (USD)")
plt.show()
