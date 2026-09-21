# 🎰 Slot Machine

Proyecto de **Programación Orientada a Objetos** de la **Escuela Colombiana de Ingeniería Julio Garavito**, desarrollado por Felipe Amador González y Jean Paolo Peña Romero.

El proyecto corresponde al desarrollo progresivo de los ciclos del proyecto inicial y está inspirado en el **Problem I - Slot Machine** de la Maratón Internacional de Programación.

![Slot Machine](https://i.pinimg.com/1200x/e6/9e/72/e69e72e9927ae5f4f3f044570a677c05.jpg)

*Imagen de referencia tomada de Pinterest.*

---

## 📖 Descripción

Este proyecto implementa un simulador de máquina tragamonedas (*Slot Machine*) desarrollado en Java.

La aplicación permite administrar ruedas y símbolos, realizar diferentes tipos de giros, consultar la configuración de la máquina, verificar el estado de jackpot y controlar su representación gráfica.

A partir del **Tercer Ciclo**, el proyecto fue extendido para crear máquinas con un número determinado de ruedas y símbolos, resolver el problema de Slot Machine mediante una secuencia de acciones y simular visualmente dicha solución.

Durante el desarrollo también se aplicaron conceptos de Programación Orientada a Objetos como **encapsulamiento, herencia, polimorfismo, interfaces y clases abstractas**.

---

## ⚙️ Funcionalidades

El simulador permite:

- Crear una máquina tragamonedas.
- Crear una máquina con `n` ruedas y `n` símbolos.
- Agregar y eliminar ruedas.
- Agregar y eliminar símbolos.
- Intercambiar ruedas.
- Bloquear y desbloquear ruedas.
- Girar una rueda específica un número determinado de pasos.
- Girar todas las ruedas.
- Establecer una configuración determinada.
- Consultar los símbolos visibles.
- Consultar la configuración actual.
- Contar los símbolos visibles distintos.
- Verificar si la configuración actual es un jackpot.
- Mostrar y ocultar la representación gráfica de la máquina.
- Resolver el problema de Slot Machine mediante `solve`.
- Simular visualmente la solución mediante `simulate`.
- Finalizar la ejecución del simulador.

---

## 🧠 Solución del problema

La clase `SlotMachineContest` contiene la lógica correspondiente a la solución del problema.

### `solve(int n)`

Crea una máquina de `n` ruedas y `n` símbolos en modo invisible y calcula una secuencia de acciones que permite llegar a una configuración ganadora.

El método retorna las acciones en forma de un arreglo:

```text
int[][]
