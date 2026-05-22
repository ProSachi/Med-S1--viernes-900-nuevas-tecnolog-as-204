import requests
import pandas as pd
import matplotlib.pyplot as plt
import os

# Importar función de análisis con Gemini
from exported_images.analisis import analizar_dataframe_con_gemini

def fetch_api_data(urls):
    """Realiza fetch de varias APIs y retorna los datos en una lista de DataFrames."""
    dataframes = []
    for url in urls:
        response = requests.get(url)
        if response.status_code == 200:
            try:
                df = pd.DataFrame(response.json())
                dataframes.append(df)
            except Exception as e:
                print(f"Error procesando datos de {url}: {e}")
        else:
            print(f"Error al obtener datos de {url}: {response.status_code}")
    return dataframes

def clean_data(df):
    """Limpia el DataFrame eliminando nulos y duplicados."""
    df = df.drop_duplicates()
    df = df.dropna()
    return df

def analyze_data(df):
    """Realiza un análisis simple del DataFrame."""
    print("Resumen estadístico:")
    print(df.describe())
    print("\nPrimeras filas:")
    print(df.head())

def plot_data(df, column, output_dir="exported_images"):
    """Genera y exporta una gráfica de una columna específica."""
    if not os.path.exists(output_dir):
        os.makedirs(output_dir)
    plt.figure()
    df[column].value_counts().plot(kind='bar')
    plt.title(f"Distribución de {column}")
    plt.xlabel(column)
    plt.ylabel("Frecuencia")
    img_path = os.path.join(output_dir, f"{column}_plot.png")
    plt.savefig(img_path)
    plt.close()
    print(f"Gráfica exportada a {img_path}")

def main_menu():
    urls = []
    dataframes = []
    df = None
    while True:
        print("\n--- Menú Principal ---")
        print("1. Ingresar URLs de APIs")
        print("2. Fetch de APIs")
        print("3. Limpiar datos")
        print("4. Analizar datos")
        print("5. Graficar y exportar imagen")
        print("6. Analizar con Gemini AI")
        print("7. Salir")
        opcion = input("Seleccione una opción: ")
        if opcion == '1':
            urls = input("Ingrese las URLs separadas por coma: ").split(',')
            urls = [u.strip() for u in urls]
        elif opcion == '2':
            if urls:
                dataframes = fetch_api_data(urls)
                if dataframes:
                    df = pd.concat(dataframes, ignore_index=True)
                    print("Datos obtenidos y concatenados.")
                else:
                    print("No se obtuvieron datos.")
            else:
                print("Primero ingrese las URLs.")
        elif opcion == '3':
            if df is not None:
                df = clean_data(df)
                print("Datos limpiados.")
            else:
                print("No hay datos para limpiar.")
        elif opcion == '4':
            if df is not None:
                analyze_data(df)
            else:
                print("No hay datos para analizar.")
        elif opcion == '5':
            if df is not None:
                column = input("Ingrese el nombre de la columna a graficar: ")
                if column in df.columns:
                    plot_data(df, column)
                else:
                    print("Columna no encontrada en los datos.")
            else:
                print("No hay datos para graficar.")
        elif opcion == '6':
            if df is not None:
                prompt = input("Ingrese el prompt para analizar el DataFrame con Gemini: ")
                api_key = os.environ.get("GEMINI_API_KEY")
                if not api_key:
                    print("No se encontró la variable de entorno GEMINI_API_KEY. Por favor configúrala antes de continuar.")
                    continue
                print("Enviando datos a Gemini, por favor espere...")
                resultado = analizar_dataframe_con_gemini(df, api_key, prompt)
                print("\nRespuesta de Gemini:\n", resultado)
            else:
                print("No hay datos cargados para analizar con Gemini.")
        elif opcion == '7':
            print("Saliendo...")
            
            break
        else:
            print("Opción no válida.")

if __name__ == "__main__":
    main_menu()
