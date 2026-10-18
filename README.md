# Trabajo Práctico - Programación Orientada a Objetos
## Etapa 1

### Integrantes:
- Aranguren Alejo - 1215686
- Raggio Gonzalo - 1214922
- Schipani Tiziano - 1214840

### Descripción de la propuesta:
En este proyecto nos enfocamos en el desarrollo de la propuesta C, la cual destaca un sistema de movilidad compartida que incluye bicicletas (comunes y eléctricas) y monopatines eléctricos distribuidos en estaciones por la ciudad, facilitando el movimiento de los ciudadanos.

Para esto identificamos cuatro actores: usuarios, operador del sistema, técnico de mantenimiento y administrador. 

El sistema abarca el registro de usuarios y gestión de su saldo (o medios de pago), el retiro y devolución de vehículos en las estaciones controlando su capacidad, y el cálculo de la tarifa según el tiempo de uso, tipo de vehículo y viajes vigentes. Además, contempla el control de estado de las unidades, el reporte de fallas para generar órdenes de reparación con los técnicos, la redistribución de vehículos entre estaciones cuando están vacías o llenas, y la emisión de reportes de uso y viajes para el administrador.

### Estructura del proyecto:
- `/docs`: Documentación general (enunciado elegido, requisitos y supuestos, informe de diseño y plan de pruebas).
  - `/docs/uml`: Diagramas UML (archivos fuente y exportaciones de imágenes).
- `/src`: Código fuente de la aplicación en Java, organizado en los siguientes paquetes:
  - `administracion`: Clases de administración general del sistema (`Administrador`).
  - `controladores`: Controladores para la orquestación de casos de uso (`ControladorCuenta`, `ControladorMantenimiento`, `ControladorReporte`, `ControladorViajes`).
  - `mantenimiento`: Gestión de personal operativo, técnico, órdenes de trabajo y fallas (`Empleado`, `Operador`, `Tecnico`, `OrdenMantenimiento`, `ReporteFalla`).
  - `usuarios`: Gestión de usuarios, cuentas y movimientos de saldo (`Usuario`, `Cuenta`, `Movimiento`).
  - `vehiculos`: Jerarquía de vehículos, estados y estaciones de anclaje (`Vehiculo`, `BicicletaComun`, `BicicletaElectrica`, `MonopatinElectrico`, `Estacion`, `Estado`).
  - `viajes`: Gestión de trayectos, puntos de recorrido y promociones aplicables (`Viaje`, `PuntoRecorrido`, `Promocion`, `PrimerViaje`, `PlanMensual`).
- `/test`: Casos de pruebas unitarias.