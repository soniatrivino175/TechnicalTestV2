"# TechnicalTestV2"

TechnicalTestV2 (Creación de examen para estudiantes)

• NOTA: Documentación, colección postman, scripts de bases de datos en la ruta (/src/main/resources/)

El proyecto usa el motor de base de datos de Mysql.
Los pasos para poner en ejecución el proyecto son los siguientes:
a)	INSTALACION DE LA BASE DE DATOS:
1.	Instalar el motor de base de datos de Mysql (la versión de base de datos que se uso fue la 8.0.7).
2.	Ejecutar el Siguiente script para la creación de la base de datos TechnicalTestV2 
CREATE DATABASE TechnicalTestV2 CHARACTER SET utf8 COLLATE utf8_general_ci;
3.	Se debe ejecutar el script de base de datos (Database.sql) sobre TechnicalTestV2
b)	Configuración de la conexión a la base de datos: 
(Ubicación archivos de configuración: ./src/main/resources):
1.	Se debe descargar el proyecto TechnicalTestV2en la máquina.
2.	application.yml: tiene el perfil activo, se puede cambiar si se tienen mas perfiles, es decir archivos de propiedad del tipo application-{perfil}.yml
3.	application-local.yml: Tiene toda la configuración de acceso a la base de datos
4.	En el archivo de application-local.yaml se puede modificar el puerto por el cual se desea que corra la aplicación
server: 
  port: {port}

5.	En el archivo application-local.yaml se deben modificar los parámetros de conexión
spring:
  datasource:
    url: jdbc:mysql://{ipServerMysql}:{portServerMysql}/TechnicalTestV2 
    username: {user}
    password: {pass}

c)	COMPILACION Y GENERACION DEL .jar 

•	Si se posee eclipse o STS se puede abrir dicho proyecto como File -> Import -> Existing Maven Project y buscar la carpeta del proyecto (Esto agregara el proyecto en el Workspace) y luego sobre el proyecto: 
a)	clic derecho Maven -> Update Project... 
b)	clic derecho Run As... -> Maven clean 
c)	clic derecho Run As... -> Maven install

•	En caso de no tener ninguno de los 2 se puede realizar la generacion del .jar si tiene instalado el comando mvn (maven) en la máquina de la siguiente manera (esto se debe hacer estando dentro del proyecto por línea de comandos):
mvn clean install -U

De juntas maneras se crea dentro del proyecto un archivo .jar dentro de target/TechnicalTestV2-0.0.1.jar.
d)	EJECUCIÓN DE LA APLICACIÓN:
Se puede ejecutar el proyecto de varias maneras:
•	Desde el STS o Eclipse dando clic derecho sobre el proyecto, Run As... -> Spring Boot App
•	Puede ejecutar mediante el comando java -jar {locationJar}/ TechnicalTestV2-0.0.1.jar o dentro de un contenedor Docker
La ubicación del jar {locationJar} queda en la carpeta target que se encuentra en la raíz del proyecto
•	También con Maven con el comando mvn spring-boot:run en la raíz del proyecto

e)	Testeo desde Postman:
Se puede importar la colección de Postman TechnicalTestV2.postman_collection.json
