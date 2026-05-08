import matplotlib.pyplot as plt
import pandas as pd

data = {'categoria': ['A', 'B', 'C', 'D', 'E'],
        'valor': [23, 45, 56, 12, 39]}
df = pd.DataFrame(data)
colores=['blue', 'red', 'orange']

# 1. Preparar el lienzo (Ancho, Alto en pulgadas)
plt.figure(figsize=(10, 6))

# 2. Pintar el gráfico (usando la integración directa de Pandas)
df.plot(kind='bar', x='categoria', y='valor', color=colores)
# 3. Personalización y Contexto (Metadata)
plt.title("Título Claro y de Negocio", fontsize=14)
plt.xlabel("Etiqueta del Eje X")
plt.ylabel("Etiqueta del Eje Y")
plt.xticks(rotation=45) # Rotar textos para que no colisionen

# 4. Renderizar (Mostrar a pantalla)
plt.show()

