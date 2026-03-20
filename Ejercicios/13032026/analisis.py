
ruta_raw ="./data/raw/crudo.txt"
ruta_processed = "./data/processed/datos_limpios.txt"

datos_falsos = ["  hola\n", "MUNDO  \n", "  pYthon \n", "  PHYTHON \n", "  CULQWUEICOSA \n"]

with open(ruta_raw, "w") as f:
    f.writelines(datos_falsos)

datos_limpios = []
with open(ruta_raw, "r") as f:
    for linea in f.readlines():
        # Limpieza a bajo nivel
        palabra_limpia = linea.strip().lower() + "\n"
        datos_limpios.append(palabra_limpia)

with open(ruta_processed, "w") as f:
    f.writelines(datos_limpios)

print("Pipeline ejecutado. Revisa la carpeta 'processed' en tu Drive.")
