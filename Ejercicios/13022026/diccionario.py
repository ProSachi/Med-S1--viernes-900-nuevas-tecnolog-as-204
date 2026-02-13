mi_diccionario = {"Clave":"Valor", "Elemento1":1, "otra_Cosa":"La otra cosa"}

""" print(mi_diccionario.keys())
print(mi_diccionario.values())
print(mi_diccionario.items()) """
print(mi_diccionario.get("elemento1","No Encontrado"))


for clave, valor in mi_diccionario.items():
    print(f"Campo: {clave} -> Contenido: {valor}")





