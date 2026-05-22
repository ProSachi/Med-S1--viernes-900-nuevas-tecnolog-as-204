import requests
import pandas as pd
import json

def analizar_dataframe_con_gemini(df, api_key, prompt):
    """
    Envía el DataFrame a la API de Gemini junto con un prompt personalizado para análisis.
    """
    # Convertimos el DataFrame a un resumen de texto (puedes ajustar esto según el tamaño del df)
    resumen = df.head(10).to_string()
    texto = f"{prompt}\n\nDatos:\n{resumen}"
    url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-flash-latest:generateContent"
    headers = {
        'Content-Type': 'application/json',
        'X-goog-api-key': api_key
    }
    data = {
        "contents": [
            {
                "parts": [
                    {"text": texto}
                ]
            }
        ]
    }
    response = requests.post(url, headers=headers, data=json.dumps(data))
    if response.status_code == 200:
        result = response.json()
        try:
            return result['candidates'][0]['content']['parts'][0]['text']
        except Exception:
            return result
    else:
        return f"Error: {response.status_code} - {response.text}"

def main():
    # Ejemplo de carga de datos
    ruta = input("Ingrese la ruta del archivo CSV a analizar: ")
    df = pd.read_csv(ruta)
    prompt = input("Ingrese el prompt para analizar el DataFrame: ")
    api_key = "AIzaSyCXExzYNm2rNEYk3Y0ZWMZSCaWUlE-wukk"
    resultado = analizar_dataframe_con_gemini(df, api_key, prompt)
    print("\nRespuesta de Gemini:\n", resultado)

if __name__ == "__main__":
    main()
