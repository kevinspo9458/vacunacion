# APLICACION KRUGER - INVENTARIO DEL ESTADO DE VACUNACION DE LOS EMPLEADOS




El desarrrollo del BACKEND del aplicativo se divide en 2 partes que se detallaran a continuación: Proceso de contruccion y ejecucion de la aplicacion



## Proceso de construcción

- Se realiza el analisis de los criterios de aceptacion de cada una de las historias de usuario, para proceder a realizar diseño y desarrollo de la aplicación.

- Se diseña el modelo de datos para el desarrollo de la aplicacion.(REVISAR EL MODELO EN LA CARPETA: /vacunacion/otros/)

- Se procede a generar el proyecto con SpringBoot en el Spring Initializr (https://start.spring.io/). Seleccionamos Maven, Java 8 y SpringBoot 2.7.6. Ademas se agregan las dependencias necesarias Spring Web, Data JPA, PostgreSQL Driver. 

- Se configura la conexion a la BD PostgreSQL con las credenciales en el archivo aplication.properties.

- Se define la estructura de paquetes de nuestra aplicacion : entity, repository, service y controller. Ademas, se crean los packages: util, enums.

- Se inicia creando las clases @Entity, las cuales se definen en base al modelo de datos diseñado en los pasos anteriores. Posterior se realiza el mapeo de datos.
 
- Se crean las interfaces de tipo Repository que extienden a JPARepository y nos permitiran realizar las operaciones necesarias con los datos.

- Se crean los servicios para cada una de las funcionalidades solicitadas en el package Service, en estas se implementaran la logica del negocio.
 
- Se crea la clase ValidacionUtils situada en el package util, esta contiene los metodos de las validaciones de los campos solicitados en los criterios de aceptacion.

- Se crean los endpoints para cada operacion en las clases del package controller.

- Se agrega la documentacion mediante Swagger-OpenAPI.


## Ejecución de la aplicación

- Se procede a clonar el codigo fuente del aplicativo.

```git clone https://github.com/kevinspo9458/vacunacion.git```

- Se procede a crear una Base de datos en el servidor POSTGRESQL. Ejecutando el siguiente script:

```CREATE DATABASE vacunacion;```

- Abrir el proyecto clonado en pasos anteriores y proceder a editar el archivo application.properties, se agregan los valores correspondientes al servidor POSTGRESQL que se utilizara para las pruebas

```
spring.datasource.url=jdbc:postgresql://{HOST}:5432/vacunacion
spring.datasource.username=postgres
spring.datasource.password=
```

- Se procede a ejecutar la aplicacion en el IDE.

- Dado que la propiedad de JPA esta como update el esquema de la base de datos se completara en base a los mapeos de las entidades.

-  Ejecutar el siguiente query directamente en la base de datos para ingresar los tipos de vacunas disponibles:

```INSERT INTO vacunas(nombre) VALUES ('Sputnik'), ('AstraZeneca'),('Pfizer'),('Jhonson&Jhonson');```

- Se puede ingresar a la documentacion de la aplicacion mediante el siguiente enlace:

http://localhost:8080/swagger-ui/index.html

- Finalmente, se puede proceder a revisar y realizar las pruebas de los servicios web disponibles.

