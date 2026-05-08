import matplotlib.pyplot as plt

# 1. Creamos un gráfico de prueba
plt.figure(figsize=(8, 4))
plt.plot([1, 2, 3], [10, 20, 15], color='blue', marker='o')
plt.title("Reporte de Producción")
plt.ylabel("Unidades")

# ==========================================
# EL BLOQUE DE EXPORTACIÓN (Antes del show)
# ==========================================

# Parámetro clave transversal: bbox_inches='tight'
# Obliga a Matplotlib a calcular el "Bounding Box" (la caja de la imagen) 
# abrazando todos los textos. Si no lo pones, los títulos largos o las 
# etiquetas rotadas del eje X quedarán cortadas por la mitad en la foto final.

# Opción A: PNG (Rasterizado / Mapa de Bits)
# Ideal para: PowerPoint, Word, correos electrónicos o impresión física.
# Parámetro técnico: dpi=300 (Puntos por pulgada). Es el estándar internacional 
# para calidad de imprenta. Si omites el DPI, se guardará a 72 o 100 DPI (se verá borroso).
plt.savefig("graficos/reporte_impresion.png", format='png', dpi=300, bbox_inches='tight')

# Opción B: SVG (Vectorial / Matemático)
# Ideal para: Páginas web, aplicaciones interactivas o diseño gráfico (Illustrator/Figma).
# No usa DPI porque no está hecho de píxeles, sino de fórmulas matemáticas. 
# Puedes hacerle zoom infinito sin que se pixele.
plt.savefig("graficos/reporte_web.svg", format='svg', bbox_inches='tight')

# Opción C: PDF (Vectorial Empaquetado)
# Ideal para: Informes formales, adjuntos gerenciales o entregables académicos.
# Al igual que el SVG, el PDF guarda los gráficos de forma vectorial, garantizando
# texto nítido sin importar cuánto zoom haga el gerente.
plt.savefig("reporte_ejecutivo.pdf", format='pdf', bbox_inches='tight')

# ==========================================
# RENDERIZADO FINAL
# ==========================================
plt.show() # Ahora sí, mostramos en pantalla y vaciamos la memoria.