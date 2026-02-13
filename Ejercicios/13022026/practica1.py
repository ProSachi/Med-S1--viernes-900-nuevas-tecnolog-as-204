nombre = ""
hobby = []


nombre = input("¿Cuál es tu nombre?")
hobby.append(input("¿Dime un hobbie? "))
hobby.append(input("¿Dime otro hobbie? "))
hobby.append(input("¿Dime otro hobbie? "))

datos_usuario = {
    "nombre": nombre,
    "hobby":hobby
    }

for clave, valor in datos_usuario.items():
    print("Clave: ", clave, "Valor: ", valor)