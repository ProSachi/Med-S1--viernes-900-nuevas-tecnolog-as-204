import seaborn as sns
import pandas as pd
import matplotlib.pyplot as plt

df_crudo = {'categoria': ['A', 'B', 'C', 'D', 'E'],
        'valor': [23, 45, 56, 12, 39]}

sns.boxplot(
    data=df_crudo,       
    x='categoria',       
    y='valor',      
    hue='categoria'  
)
plt.show()
