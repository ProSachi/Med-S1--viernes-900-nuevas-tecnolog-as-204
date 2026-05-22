from analisis import (
    enfermeros_limpio,
    medicamentos_limpio,
    medicos_limpio,
    pacientes_limpio,
    prescripciones_limpio,
)


def probar_carga_y_limpieza():
    dfs = {
        "ENFERMEROS":     enfermeros_limpio(),
        "MEDICAMENTOS":   medicamentos_limpio(),
        "MEDICOS":        medicos_limpio(),
        "PACIENTES":      pacientes_limpio(),
        "PRESCRIPCIONES": prescripciones_limpio(),
    }

    for nombre, df in dfs.items():
        print(f"\n{'='*50}")
        print(f"  {nombre}  ({df.shape[0]} filas, {df.shape[1]} columnas)")
        print(f"{'='*50}")
        print(df.to_string(index=False))


if __name__ == "__main__":
    probar_carga_y_limpieza()
