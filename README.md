# PrintStream

## FileWriteDataOutputStream

L'obiettivo di questo esercizio è la gestione I/O con scrittura su disco di valori **Double**, **Integer** e
**String** di lunghezza variabile in forma nativa.

Creare la classe `FileWriteDataOutputStream` con relativo JUnit di test `FileWriteDataOutputStreamTest`.

### Output

Generare un file dove per ogni riga sono inserite le informazioni della fattura, non in formato carattere ma in formato nativo (a parte la descrizione del prodotto che sarà leggibile normalmente).

> **Nota:** Segure questo formato per le righe:
 Prezzo _(Double)_ - Unità _(Integer)_ - Descr.Prodotto _(String)_.

### Consigli

I **_dati_** da scrivere possono essere definiti come 3 array:

```java
double[] prices = {19.99, 9.99, 15.99, 3.99, 4.99};
int[] units = {12, 8, 13, 29, 50};
String[] descs = {"T-shirt", "Mug", "Duke", "Pin", "Key-Chain"};
```

Lo **_stream di output_** sarà definito come:

```java
DataOutputStream out = new DataOutputStream(new
FileOutputStream("fatturaNonChar.dat"));
```

Utilizzare i metodi `writeDouble()`, `writeInt()` e `writeChar()` per scrivere i dati di ogni riga.

Terminare ogni riga con il/i caratteri di fine linea (LF+CR) ottenuti con:

```java
System.getProperty("line.separator")
```

## Files

**invoice.txt**
