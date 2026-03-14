# Especificación de Producto: Barcode Stock Control

## Requisitos Funcionales

1. **Escaneo y Registro Rápido de Stock:**
   - **Escaneo:** El usuario debe poder iniciar el escaneo de un código de barras desde la pantalla principal.
   - **Ingreso de Cantidad:** Inmediatamente después de escanear exitosamente un código, la aplicación debe mostrar una interfaz para ingresar la cantidad de stock de ese producto.
   - **Selector en Grilla:** El ingreso de la cantidad debe realizarse a través de un selector de estilo grilla (con números/opciones predefinidas) para garantizar que la carga de datos sea lo más rápida posible.
   - **Flujo Continuo:** Una vez confirmada la cantidad de stock, la aplicación debe guardar el registro y devolver al usuario automáticamente a la pantalla principal, dejándolo listo para un nuevo escaneo.

2. **Acceso al Flujo de Escaneo:**
   - **Botón Primario:** La pantalla principal debe contar con un botón primario y destacado llamado "Escanear Producto" que redirija al flujo de escaneo descrito en el punto 1.

3. **Visualización de Escaneos Anteriores:**
   - **Listado Principal:** La pantalla principal debe mostrar un listado con el historial de los códigos escaneados y sus respectivas cantidades (Ejemplo: `Código 123456789, Stock: 2`).

4. **Gestión y Exportación del Listado:**
   - **Borrar Listado:** El listado debe incluir un acceso rápido para eliminar todos los registros actuales (con un cuadro de confirmación para evitar borrados accidentales).
   - **Compartir/Exportar:** Debe existir una opción para compartir el listado. El formato de exportación será texto plano, utilizando una tupla separada por comas (`Barcode,Stock`) por cada producto, con cada registro en una nueva línea (Ejemplo: `123123123123123,4`).

5. **Persistencia Local:**
   - **Almacenamiento:** El listado de productos escaneados debe persistirse de manera local en el dispositivo, asegurando que los datos no se pierdan al cerrar y volver a abrir la aplicación.

6. **Manejo de Códigos Duplicados:**
   - **Sobrescritura de Stock:** Si el usuario escanea un código que ya existe en el listado, la pantalla de selector de stock debe mostrar un cartel o aviso visual indicando que el nuevo valor que se ingrese sobrescribirá la cantidad anterior. No se deben crear líneas duplicadas para el mismo producto.

7. **Edición y Borrado Individual:**
   - **Modificación Rápida:** En el listado principal, el usuario debe tener la posibilidad de tocar un ítem específico para editar su cantidad.
   - **Borrado por Ítem:** Se debe permitir deslizar (swipe) un ítem del listado para borrarlo individualmente.

8. **Feedback Sensorial:**
   - **Háptico y Sonoro:** La aplicación debe emitir un sonido corto y/o una vibración para confirmar el éxito o error tanto al momento de detectar un código de barras como al guardar exitosamente la cantidad de stock.

## Requisitos No Funcionales

1. **Funcionamiento Offline:**
   - La aplicación debe funcionar de manera 100% offline. Todas las validaciones, procesamiento y persistencia se realizarán de manera local en el dispositivo sin necesidad de conexión a internet.

2. **Tecnologías y Stack de Desarrollo:**
   - **Lenguaje:** Kotlin (versión actualizada).
   - **Interfaz Gráfica:** Jetpack Compose utilizando los últimos estándares de Material Design.
   - **Navegación:** Compose Navigation para el flujo entre pantallas.
   - **Arquitectura:** Debe implementar una arquitectura moderna y escalable (como Clean Architecture y/o MVVM con MVI/StateFlow), separando las capas de UI, lógica de presentación, dominio y datos.

3. **Escáner de Código de Barras:**
   - Integración nativa con **Google ML Kit Barcode Scanning** ([Documentación](https://developers.google.com/ml-kit/vision/barcode-scanning/android?hl=es-419)).
   - **Procesamiento On-Device:** El motor de reconocimiento de ML Kit debe funcionar de forma offline directamente en el dispositivo.
   - Se tomarán como referencia los siguientes repositorios oficiales de ML Kit de Google tanto para la implementación del escáner como para las buenas prácticas de UI relacionadas a la cámara:
     - [Vision Quickstart](https://github.com/googlesamples/mlkit/tree/master/android/vision-quickstart)
     - [Material Showcase](https://github.com/googlesamples/mlkit/tree/master/android/material-showcase)

4. **Persistencia de Datos:**
   - Almacenamiento local optimizado (por ejemplo, mediante Room Database o DataStore) para guardar y leer la lista de escaneos de forma rápida, eficiente y fiable.
