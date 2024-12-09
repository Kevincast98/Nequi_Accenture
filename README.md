# Prueba Tecnica accenture_nequi

## Descripción
Esta **Prueba tecnica de acerrnure** es una aplicación backend diseñada para gestionar franquicias, agencias, y productos . Proporciona endpoints RESTful para realizar operaciones CRUD, manejar relaciones entre entidades y consultar datos específicos.

## Características
- Gestión de agencias: crear, actualizar, eliminar y consultar franquicias, agencias y productos.
- Gestión de productos asociados a agencias.
- Consultas avanzadas, como productos con mayor stock por franquicia.
- Validación de datos y manejo de errores.

## Tecnologías utilizadas
- **Java**: Lenguaje principal.
- **Spring Boot**: Framework para desarrollo backend.
- **MySQL**: Base de datos relacional.
- **Maven**: Gestión de dependencias.
- **Git**: Control de versiones.
- **Docker**: Contenedores para facilitar el despliegue y la portabilidad.
- **Postman**: Herramienta para probar y documentar los endpoints REST.


## Requisitos previos
- **Java 21+** instalado.
- **Maven** instalado para manejar dependencias.


## Configuración
1. **Clona el repositorio:**
   ```bash
   git clone https://github.com/Kevincast98/Nequi_Accenture

2. **Ingresa al directorio del proyecto:**

        cd <NOMBRE_DEL_PROYECTO>

3. **Configurar la base de datos:**
    
Configura las siguientes variables de entorno para utilizar la base de datos que está alojada en AWS. Estas variables permiten que la aplicación se conecte de manera segura y eficiente con el servicio de base de datos en la nube. Asegúrate de utilizar los valores correctos proporcionados por tu administrador o el panel de configuración de AWS:

- **DB_HOST:** La dirección del host donde se encuentra la base de datos.
    ```bash
    accenture.c12o600akc3t.us-east-2.rds.amazonaws.com

- **DB_PORT:** El puerto en el que la base de datos está escuchando 
    ```bash
    Default: 3306

- **DB_NAME:** El nombre de la base de datos que se utilizará.
    ```bash
    accenture
- **DB_USER:** El usuario con permisos para acceder a la base de datos.
    ```bash
    admin
- **DB_PASSWORD:** La contraseña asociada al usuario configurado.
    ```bash
    12345678

4. **Configurar de Docker:**
El proyecto incluye un archivo docker-compose.yml que configura un contenedor para MySQL.

- **Iniciar servicios:** 
    ```bash
    docker-compose up --build

- **Detener servicios:** 
    ```bash
    docker-compose down


## Descripción de Servicios

### Franquicias


- **Crear franquicia** 
    ```bash
    Este endpoint permite crear una nueva franquicia. Se debe enviar la información de la franquicia en el cuerpo de la solicitud para crearla en el sistema.
- **Obtener franquicias** 
    ```bash
    Este endpoint permite obtener la lista de todas las franquicias disponibles en el sistema.

- **Obtener franquicia por ID** 
    ```bash
    Este endpoint permite obtener la información de una franquicia específica utilizando su identificador único.

- **Actualizar franquicia** 
    ```bash
    Este endpoint permite actualizar la información de una franquicia específica utilizando su identificador único.

- **Eliminar franquicia** 
    ```bash
    Este endpoint permite eliminar una franquicia específica utilizando su identificador único.


### Agencias

- **Crear agencia** 
    ```bash
    Este endpoint permite crear una nueva agencia asociada a una franquicia existente. Se debe enviar la información de la agencia en el cuerpo de la solicitud y proporcionar el ID de la franquicia a la que se asociara.

- **Obtener todas las agencias** 
    ```bash
    Este endpoint permite obtener la lista de todas las agencias registradas en el sistema.
- **Obtener agencia por ID** 
    ```bash
    Este endpoint permite obtener una agencia específica utilizando su ID. Si la agencia existe, se devolverán los detalles de la agencia solicitada.

- **Actualizar agencia** 
    ```bash
    Este endpoint permite actualizar una agencia existente utilizando su ID. Si se proporciona un nuevo nombre, se actualizan los campos correspondientes. Si no se proporciona un nuevo valor para algún campo, se mantiene el valor actual.

- **Eliminar agencia** 
    ```bash
    Este endpoint permite eliminar una agencia utilizando su ID. Si la agencia tiene productos asociados, no se podrá eliminar, y se devolverá un error. Si no se encuentra la agencia con el ID especificado, se devuelve un error indicando que no existe.




### Productos

- **Crear producto** 
    ```bash
    Este endpoint permite crear un nuevo producto asociado a una agencia específica. Si la agencia no existe o los datos del producto son incorrectos, se devolverá un error adecuado.

- **Obtener todos los productos** 
    ```bash
    Este endpoint permite obtener la lista de todos los productos disponibles en el sistema. Si no hay productos registrados, se devolverá un mensaje adecuado.

- **Obtener producto por ID** 
    ```bash
    Este endpoint permite obtener un producto específico usando su identificador único (ID). Si el producto no se encuentra en la base de datos, se devolverá una respuesta indicando que no se ha encontrado.

- **Actualizar producto** 
    ```bash
    Este endpoint permite actualizar una prouducto existente utilizando su ID. Si se proporciona un nuevo nombre, se actualizan los campos correspondientes. Si no se proporciona un nuevo valor para algún campo, se mantiene el valor actual.

- **Eliminar producto** 
    ```bash
    Este endpoint permite eliminar un producto utilizando su ID. Si la agencia tiene productos asociados, no se podrá eliminar, y se devolverá un error. Si no se encuentra la agencia con el ID especificado, se devuelve un error indicando que no existe.

- **Obtener productos con el mayor stock de una franquicia** 
    ```bash
    Este endpoint permite obtener los productos que tienen el mayor stock disponible para una franquicia específica. Si no se encuentra la franquicia o si no hay productos disponibles para ella, se devolverán respuestas correspondientes.
