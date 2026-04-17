import pandas as pd

def limpiar_clientes(df_archivo):
    #Limpieza de espacio y texto en formato titulo.
    print('Limpieza de espacio y texto en formato titulo')
    df_archivo['nombre'] = df_archivo['nombre'].str.strip().str.title()
    df_archivo['edad'] = df_archivo['edad'].str.strip().str.title()
    df_archivo['ciudad'] = df_archivo['ciudad'].str.strip().str.title()
    df_archivo['email'] = df_archivo['email'].str.strip().str.title()
    df_archivo['fecha_registro'] = df_archivo['fecha_registro'].str.strip().str.title()
    df_archivo['salario'] = df_archivo['salario'].str.strip().str.title()
    df_archivo['activo'] = df_archivo['activo'].str.strip().str.title()
    df_archivo['telefono'] = df_archivo['telefono'].str.strip().str.title()

    #Eliminar datos duplicados
    print('Eliminar datos duplicados')
    df_archivo = df_archivo.drop_duplicates()
    
    print('Reemplazar datos con un valor')
    #Reemplazar datos con un valor
    df_archivo['edad'] = df_archivo['edad'].replace('Veinte', 20)
    df_archivo['edad'] = df_archivo['edad'].replace('-5', 20)
    df_archivo['edad'] = df_archivo['edad'].replace('200', 20)
    df_archivo['edad'] = pd.to_numeric(df_archivo['edad'], errors='coerce').fillna(0).astype(int)

    #print(df_archivo['edad'].value_counts())

    print('Tratamiento de Nulos')
    #Tratamiento de Nulos
    df_archivo['edad'] = df_archivo['edad'].fillna(df_archivo['edad'].mean())
    df_archivo['ciudad'] = df_archivo['ciudad'].fillna('desconocida')
    df_archivo['telefono'] = df_archivo['telefono'].fillna(00000000)
    df_archivo['puntaje'] = df_archivo['puntaje'].fillna(df_archivo['puntaje'].mean())
    df_archivo['edad'] = df_archivo['edad'].replace(0, df_archivo['edad'].mean())
    print('Reemplazando el 45')
    df_archivo['salario'] = df_archivo['salario'].replace('$2,900,000', 2900000)
    df_archivo['telefono'] = df_archivo['telefono'].replace('Abc123', 0)
    df_archivo['salario'] = pd.to_numeric(df_archivo['salario'], errors='coerce').fillna(0).astype(float)

    #df_archivo.info()

#Definimos nuestra lista estricta
    ciudades_oficiales = ['Medellin', 'Bogota', 'Cali', 'Barranquilla', 'Cartagena', 'Bucaramanga',
    'Pereira', 'Cucuta', 'Manizales', 'Ibague', 'Neiva', 'Soacha', 'Pasto']
    
    # Usamos ~ (NOT) para buscar a los que violan la regla de la lista
    mascara_infractores = ~df_archivo['ciudad'].isin(ciudades_oficiales)

    # Filtramos el DataFrame usando la máscara para ver los errores
    errores_detectados = df_archivo[mascara_infractores]

    #Hacemos un print de los datos que no cumplen nuestra lista
    print('errores_detectados')
    print(errores_detectados)

    #Hacemos un diccionario para aplicar una traducción
    traduccion = { 'Bógota': 'Bogota', 'Bogotá': 'Bogota','Bogóta': 'Bogota', 'desconocida': 'Bogota'}
    
    # Aplicamos la traducción solo a la columna afectada
    df_archivo['ciudad'] = df_archivo['ciudad'].replace(traduccion)

    print('Limpieza de ciudades realizada')
    print(df_archivo['ciudad'].value_counts())

    df_archivo.info()

    # index=False evita guardar el índice de Pandas en el archivo
    df_archivo.to_csv('data/processed/cliente_limpio.csv', index=False)
    


def limpieza_Ventas(df_archivo_ventas):
    print('Limpieza de espacio y texto en formato titulo')
    df_archivo_ventas['producto'] = df_archivo_ventas['producto'].str.strip()
    # index=False evita guardar el índice de Pandas en el archivo
    df_archivo_ventas.to_csv('data/processed/ventas_limpio.csv', index=False)