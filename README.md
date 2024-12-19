# Aplicación de Gestión de Tareas y Datos Climáticos en AWS

Este proyecto fue propuesto para el curso de Sistemas Distribuidos del ciclo VIII de la carrera de Ingeniería de Sistemas de la Universidad Nacional Mayor de San Marcos.

La finalidad de este proyecto fue aplicar los conocimientos adquiridos en sistemas distribuidos, utilizando herramientas y tecnologías avanzadas para el desarrollo y despliegue de aplicaciones en la nube, específicamente en Amazon Web Services (AWS).

## Índice

1. [Características principales](#características-principales)
2. [Tecnologías utilizadas](#tecnologías-utilizadas)
3. [Prerrequisitos](#prerrequisitos)
3. [Requisitos](#requisitos)
4. [Instrucciones de uso](#instrucciones-de-uso)
   - [Paso 1: Clonar el repositorio](#clonar-el-repositorio-en-tu-máquina-local)
   - [Paso 2: Importar el proyecto](#importar-el-proyecto-en-tu-ide-de-desarrollo)
   - [Paso 3: Configurar el archivo de propiedades](#crear-un-archivo-env-o-modificar-el-archivo-aplicationproperties-con-las-credenciales-de-acceso-a-la-base-de-datos-dynamodb-y-postgresql)
   - [Paso 4: Ejecutar el proyecto](#ejecutar-el-proyecto-en-tu-ide-de-desarrollo)
   - [Paso 5: Acceder a la aplicación](#acceder-a-la-aplicación-en-tu-navegador-web)
5. [Capturas de pantalla](#capturas-de-pantalla)
   - [Página principal](#página-principal)
   - [Crear tarea](#crear-tarea)
   - [Tareas](#tareas)
   - [Datos climáticos](#datos-climáticos)
   - [Cargar Datos Climáticos](#cargar-datos-climáticos)
6. [Consideraciones](#consideraciones)

## Características principales
La aplicación permite a los usuarios gestionar tareas mediante una interfaz donde se puede crear una tarea, proporcionando un título, descripción y los entregables relacionados. Además, se hace uso de WebSockets para mantener una conexión en tiempo real entre el cliente y el servidor.

A través de esta tecnología, la aplicación consume datos provenientes de una base de datos NoSQL (DynamoDB), y también recupera datos climáticos desde el catálogo de datos del Grupo Banco Mundial. Los datos climáticos son presentados en tiempo real a los usuarios para obtener información relevante sobre el clima de diferentes ubicaciones del mundo.

## Tecnologías utilizadas
- **Spring Boot** para la creación del backend y las API REST.
- **WebSockets** para la comunicación en tiempo real entre el cliente y el servidor.
- **DynamoDB (NoSQL)** para la gestión de tareas y datos relacionados.
- **PostgreSQL** para almacenamiento de información relacional.
- **AWS EC2** para el despliegue de la aplicación en la nube.
- **Ubuntu** como sistema operativo para el servidor en AWS.
- **Catálogo de Datos del Grupo Banco Mundial** para la obtención de información climática.

## Prerrequisitos
Como se mencionó anteriormente, la aplicación está desplegada en AWS, por lo que es necesario contar con una cuenta de AWS para poder acceder a ella. Además, para que la aplicación funcione correctamente, es imprescindible tener una base de datos DynamoDB y PostgreSQL configurada en AWS.
Toda la información necesaria para la creación de estas bases de datos en AWS está disponible en el siguiente archivo:

[Guía para la creación de AWS](https://github.com/user-attachments/files/18193097/guia.para.la.creacion.de.aws.pdf)

Este archivo incluye instrucciones detalladas sobre cómo configurar las bases de datos en AWS y conectar la aplicación de manera adecuada.

## Requisitos
- **Java 17**
- **Maven**
- **Un IDE de desarrollo (Eclipse, IntelliJ, NetBeans, etc.)**
- **PostgreSQL**
- **Crear una cuenta de aws y crear las bases de datos DynamoDB y RDS**

## Instrucciones de uso
1. ##### Clonar el repositorio en tu máquina local.
    ````shell
    git clone https://github.com/Fabo2303/springboot-aws-dynamodb-postgresql.git
    ````
2. ##### Importar el proyecto en tu IDE de desarrollo.
3. ##### Crear un archivo .env o modificar el archivo aplication.properties con las credenciales de acceso a la base de datos DynamoDB y PostgreSQL.
    ````properties
    # modelo para el archivo .env
    SPRING_DATABASE_URL=YOUR_DATABASE_URL
    SPRING_DATABASE_USERNAME=YOUR_DATABASE_USERNAME
    SPRING_DATABASE_PASSWORD=YOUR_DATABASE_PASSWORD
    DYNAMODB_ENDPOINT=YOUR_DYNAMODB_ENDPOINT
    DYNAMODB_REGION=YOUR_DYNAMODB_REGION
    DYNAMODB_ACCESS_KEY=YOUR_DYNAMODB_ACCESS_KEY
    DYNAMODB_SECRET_KEY=YOUR_DYNAMODB_SECRET_KEY
    ````
4. ##### Ejecutar el proyecto en tu IDE de desarrollo.
5. ##### Acceder a la aplicación en tu navegador web.
    ````shell
    http://localhost:8080
    ````

## Capturas de pantalla
### Página principal
1. Ingresar a la aplicación en tu navegador web.
![Página principal](https://github.com/user-attachments/assets/2c171bbd-4e10-458d-b94f-2f88fdc255cf)

### Crear tarea
1. Seleccionar la opción "Ver tareas" en el menú.
![Menú Crear tarea](https://github.com/user-attachments/assets/7ab6cc44-bbdc-4a40-bbd4-64714f84046b)
2. Completar los campos del formulario y hacer clic en el botón "Crear".
![Crear tarea](https://github.com/user-attachments/assets/631960ca-4b3c-499f-a0a7-0d2db5926750)

### Tareas
1. Visualizar las tareas creadas.
![Tareas](https://github.com/user-attachments/assets/fbd14475-f322-44ea-89bf-a4358be3ab41)

### Datos climáticos
1. Seleccionar la opción "Ver clima" en el menú.
2. La pantalla mostrará los datos climáticos cada minuto.
![Datos climáticos](https://github.com/user-attachments/assets/312c368c-5346-4a8f-939d-c099391fd1e5)

### Cargar Datos Climáticos
1. Seleccionar la opción "Cargar datos"
2. Subir el csv con los datos climáticos
3. Hacer clic en el botón "Subir Datos Meteorológicos"
![Cargar Datos Climáticos](https://github.com/user-attachments/assets/b54a3e9f-b66c-4a43-8983-ceeb354b56a4)

## Consideraciones

Debido a los costos asociados con el uso de AWS, la aplicación no se encuentra desplegada actualmente en la nube. Sin embargo, si desea probar la aplicación en su entorno local, puede seguir los siguientes pasos: primero, deberá crear las bases de datos en AWS (tanto DynamoDB como PostgreSQL), y luego configurar el proyecto de Spring Boot en su máquina local. Al completar estos pasos, podrá ejecutar y probar la aplicación en su propio sistema.