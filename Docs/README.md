# README - Proyecto Spotify

## 1. Resumen
**Proyecto Spotify** es una aplicación de escritorio desarrollada en **Java y JavaFX** que simula un reproductor musical moderno. El sistema permite administrar bibliotecas de canciones locales, crear y gestionar playlists, reproducir archivos MP3, visualizar letras sincronizadas tipo karaoke y aplicar diferentes estructuras de datos para el manejo eficiente de la información.

El proyecto integra conceptos de programación orientada a objetos, interfaces gráficas avanzadas y estructuras de datos implementadas manualmente.

---

## 2. Objetivos
* Desarrollar un reproductor musical utilizando JavaFX.
* Aplicar principios de Programación Orientada a Objetos.
* Implementar estructuras de datos propias para la gestión de canciones y playlists.
* Integrar letras sincronizadas para modo karaoke.
* Optimizar el rendimiento mediante el uso de caché de imágenes.
* Incorporar mecanismos de importación, exportación y cifrado de información.

---
## 3. Tecnologías utilizadas
* Java SE 11 o superior.
* JavaFX.
* FXML para el diseño de interfaces.
* Java Media (`MediaPlayer`) para reproducción de audio.
* Archivos LRC para sincronización de letras.
* LRCLIB (opcional) para descarga de letras.
* Algoritmos y estructuras de datos implementados manualmente.

---
## 4. Funcionalidades principales
* Reproducción de archivos MP3 locales.
* Gestión de biblioteca musical.
* Creación y administración de playlists.
* Descarga y visualización de letras sincronizadas.
* Modo karaoke.
* Importación de canciones.
* Caché de portadas e imágenes.
* Estadísticas de uso y exportación de información.
* Cifrado de playlists o archivos de texto.
* Visualización y manipulación mediante estructuras de datos personalizadas.

---
## 5. Estructuras de datos implementadas
El proyecto incorpora implementaciones propias de:

* Árbol Binario de Búsqueda (ABB).
* Árbol AVL.
* Pilas.
* Colas.
* Listas doblemente enlazadas para playlists.
* Nodos especializados para cada estructura.

Estas estructuras permiten reforzar el aprendizaje de algoritmos y optimizar determinadas operaciones del sistema.

---
## 6. Estructura del proyecto
```text
ProyectoSpotify/
├─ src/
│  ├─ AnimacionK.java
│  ├─ Archivomp3.java
│  ├─ CacheImagenes.java
│  ├─ CancionCell.java
│  ├─ CargarCanciones.java
│  ├─ Cifrado.java
│  ├─ DescargarLrc.java
│  ├─ EstadisticasTXT.java
│  ├─ ImportarFx.java
│  ├─ Karaoke.java
│  ├─ MetodoCola.java
│  ├─ MetodoPila.java
│  ├─ MetodosABB.java
│  ├─ MetodosAVL.java
│  ├─ MetodosNPlaylist.java
│  ├─ NodoABB.java
│  ├─ NodoAVL.java
│  ├─ NodoCola.java
│  ├─ NodoDoble.java
│  ├─ NodoPila.java
│  ├─ NodoPlaylist.java
│  ├─ Playlist.java
│  ├─ PrincipalController.java
│  ├─ ProyectoSpotify.java
│  └─ Principal.fxml
├

```

---
## 7. Requisitos
* JDK 11 o superior.
* JavaFX SDK (si no se utiliza Maven o Gradle).
* IDE compatible con JavaFX, como IntelliJ IDEA o NetBeans.
* Conexión a internet (opcional) para la descarga de letras.

---
## 8. Ejecución del proyecto
1. Abrir el proyecto en el IDE de preferencia.
2. Configurar JavaFX si es necesario.
3. Agregar los módulos requeridos:

```text
--module-path /ruta/javafx-sdk/lib
--add-modules javafx.controls,javafx.fxml,javafx.media
```

4. Ejecutar la clase principal:

```text
ProyectoSpotify.java
```

---
## 9. Descripción de componentes
| Clase                 | Responsabilidad                                  
|                       |
| `ProyectoSpotify`     | Punto de entrada de la aplicación.               
| `PrincipalController` | Controlador principal de la interfaz.           
| `Archivomp3`          | Modelo que representa una canción.               
| `CargarCanciones`     | Gestión de carga de archivos MP3.                
| `CancionCell`         | Personalización visual de canciones.             
| `CacheImagenes`       | Administración de caché para imágenes.           
| `DescargarLrc`        | Obtención de letras sincronizadas.               
| `Karaoke`             | Visualización de letras durante la reproducción. 
| `AnimacionK`          | Animaciones relacionadas con el karaoke.         
| `Cifrado`             | Procesos de cifrado y descifrado.                
| `EstadisticasTXT`     | Generación de estadísticas y reportes.           
| `MetodosABB`          | Operaciones del árbol binario de búsqueda.       
| `MetodosAVL`          | Operaciones del árbol AVL.                       
| `MetodoPila`          | Gestión de pilas.                                
| `MetodoCola`          | Gestión de colas.                                
| `Playlist`            | Administración de listas de reproducción.        
| `MetodosNPlaylist`    | Operaciones sobre playlists.                     
---
## 10. Aspectos destacados
* Implementación propia de múltiples estructuras de datos.
* Interfaz moderna desarrollada con JavaFX.
* Integración de karaoke con letras sincronizadas.
* Gestión eficiente de recursos mediante caché.
* Proyecto orientado tanto al aprendizaje académico como a la simulación de un reproductor musical real.

---

## Licencia
Este proyecto fue desarrollado con fines educativos para reforzar conocimientos sobre Java, JavaFX, estructuras de datos y desarrollo de aplicaciones de escritorio.

---

**Fin del documento**
