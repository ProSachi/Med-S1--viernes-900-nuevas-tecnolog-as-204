la_lista = ["Mateo", "Mariana", "Felipe"]
print(la_lista[1])
la_lista.append("Santiago")
print(la_lista[2])

print("Nuevos elementos")
la_lista.insert(1, "Jadier")
print(la_lista[1])
print(la_lista[2])

la_lista.remove("Jadier")
la_lista.pop(3)

print(la_lista[1])
print(la_lista[2])

for l in la_lista:
    print(f"Contenido: {l}")

if la_lista > 0:
    print("La lista tiene contenido")

if la_lista:print("La lista tiene contenido")

