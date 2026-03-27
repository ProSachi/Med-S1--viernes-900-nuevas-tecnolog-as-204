import pandas as pd 

df = pd.read_csv('./data/row/venta_cruda.txt', sep=',')

print(df.head(5))

#df.info()
