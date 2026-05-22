from merge import (
    pacientes_con_medicos,
    pacientes_con_prescripciones,
    medicos_con_prescripciones,
    pacientes_medicos_prescripciones,
)


def probar_merges():
    merges = {
        "PACIENTES  ←→  MEDICOS":          pacientes_con_medicos,
        "PACIENTES  ←→  PRESCRIPCIONES":   pacientes_con_prescripciones,
        "MEDICOS    ←→  PRESCRIPCIONES":   medicos_con_prescripciones,
        "VISTA COMPLETA (pac + med + prx)": pacientes_medicos_prescripciones,
    }

    for nombre, fn in merges.items():
        print(f"\n{'='*55}")
        print(f"  {nombre}")
        print(f"{'='*55}")
        try:
            df = fn()
            print(f"  {df.shape[0]} filas  |  {df.shape[1]} columnas")
            print(f"  Columnas: {list(df.columns)}\n")
            print(df.to_string(index=False))
        except Exception as e:
            print(f"  [ERROR] {type(e).__name__}: {e}")


if __name__ == "__main__":
    probar_merges()
