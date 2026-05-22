import pandas as pd
import matplotlib.pyplot as plt
# Seaborn se importa para mejorar la estética global automáticamente
import seaborn as sns
from datos import cargarDatos 
# Configuración estética global de Seaborn
sns.set_theme(style="whitegrid")
data = cargarDatos()
df = pd.DataFrame(data)
print("--- GRÁFICO 1: TENDENCIA TEMPORAL (Líneas) ---")
plt.figure(figsize=(8, 4))
plt.plot(df['name'], df['phone'], color='#2ca02c', marker='o', linewidth=2)
plt.title("Telefono de los usuario", fontsize=14, fontweight='bold')
plt.xlabel("Usuario")
plt.ylabel("Telefonos")
plt.tick_params(rotation=45) 
plt.savefig("data/reporte_usuario.png", format='png', dpi=300, bbox_inches='tight')
plt.savefig("data/reporte_usuario_web.svg", format='svg', bbox_inches='tight')
plt.savefig("data/reporte_usuario_ejecutivo.pdf", format='pdf', bbox_inches='tight')
plt.show()

