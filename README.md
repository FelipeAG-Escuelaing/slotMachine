# 🎰 Slot Machine

Proyecto de **Programación Orientada a Objetos** de la **Escuela Colombiana de Ingeniería Julio Garavito**, desarrollado por **Felipe Amador González** y **Jean Paolo Peña Romero**.

![Slot Machine](https://i.pinimg.com/1200x/e6/9e/72/e69e72e9927ae5f4f3f044570a677c05.jpg)

*Imagen de referencia tomada de Pinterest.*

---

## 📖 Descripción

Este proyecto implementa un simulador de máquina tragamonedas (*Slot Machine*) inspirado en el **Problem I** de la Maratón Internacional de Programación.

La aplicación permite crear y administrar una máquina tragamonedas compuesta por varias ruedas y símbolos, controlar sus diferentes configuraciones, realizar giros y consultar su estado. También cuenta con una representación gráfica que permite visualizar el funcionamiento de la máquina.

Además, el proyecto incorpora una solución computacional al problema de Slot Machine, permitiendo generar una secuencia de acciones para alcanzar una configuración de jackpot y simular visualmente dicha solución.

---

## ⚙️ Funcionalidades

El proyecto permite:

- Crear una máquina tragamonedas.
- Crear una máquina con un número determinado de ruedas y símbolos.
- Agregar y eliminar ruedas.
- Agregar y eliminar símbolos.
- Intercambiar dos ruedas.
- Bloquear y desbloquear ruedas.
- Ubicar un símbolo específico en una rueda.
- Girar una rueda.
- Girar una rueda un número determinado de pasos.
- Girar todas las ruedas.
- Establecer una configuración determinada.
- Consultar los símbolos visibles.
- Consultar la configuración actual de la máquina.
- Contar la cantidad de símbolos visibles distintos.
- Verificar si la configuración actual corresponde a un jackpot.
- Mostrar y ocultar la representación gráfica de la máquina.
- Resolver el problema de Slot Machine.
- Simular visualmente la solución encontrada.
- Finalizar la ejecución del simulador.

---

## 🧩 Diseño Orientado a Objetos

El proyecto utiliza conceptos de **Programación Orientada a Objetos** para organizar y reutilizar el código.

La representación gráfica utiliza una clase abstracta `Figure`, de la cual heredan las figuras `Circle`, `Rectangle` y `Triangle`.

También se utiliza la interfaz `Movable`, que define las operaciones necesarias para mover las figuras gráficas.

La clase `SlotMachineContest` se encarga de la solución y simulación del problema, mientras que `SlotMachine` representa y administra el funcionamiento de la máquina tragamonedas.

---

## 🧪 Pruebas

El proyecto incluye pruebas unitarias desarrolladas con **JUnit** y ejecutadas en **BlueJ**.

Las pruebas permiten verificar el correcto funcionamiento de las operaciones de la máquina, la creación de diferentes configuraciones, la validación de acciones y la solución del problema.

También se realizaron pruebas de simulación utilizando diferentes cantidades de ruedas para verificar visualmente el funcionamiento de la máquina y la obtención del jackpot.

---

## 📂 Contenido del Repositorio

Dentro de este repositorio se encuentran los productos correspondientes al proyecto:

### Proyecto BlueJ
Implementación del simulador y la solución desarrollada en Java utilizando BlueJ.

### Diagramas UML
Diagramas de clases elaborados en Astah UML.

### Diagramas de Secuencia
Diagramas de secuencia correspondientes a los métodos desarrollados.

### Pruebas
Clases de pruebas unitarias utilizadas para verificar el funcionamiento del proyecto.

### Retrospectivas
Documentos con las respuestas a las preguntas retrospectivas del proyecto.

---

## 🔗 Repositorio

Puede acceder al repositorio mediante el siguiente enlace:

➡️ [Repositorio Slot Machine](https://github.com/FelipeAG-Escuelaing/slotMachine)

---

## 🛠️ Herramientas Utilizadas

- Java
- BlueJ
- JUnit
- Astah UML
- GitHub

---

## 👨‍💻 Autores

- Felipe Amador González
- Jean Paolo Peña Romero

---

## 📄 Licencia

Proyecto desarrollado con fines exclusivamente académicos y educativos.
