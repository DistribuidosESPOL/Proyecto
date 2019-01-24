# Proyecto de Sistemas Distribuidos

Proyecto final

Integrantes:
- Anni Rosa Santacruz Hernández
- Daniel Josué Castro Peñafiel
- Melina del Rocío Tomalá Ajitimbay

Para ejecutar localmente el programa seguir estos pasos:

1. Para instalar Redis seguir el siguiente tutorial:

- Crear la carpeta de redis y acceder a ella: 
mkdir redis
cd redis

- Descargar la versión estable de redis:
wget http://download.redis.io/redis-stable.tar.gz

- Descomprimir el archivo:
tar xvzf redis-stable.tar.gz

- Ingresar a la carpeta de la versión estable de redis y compilarlo:
cd redis-stable
make

- Copiar los archivos necesarios para la ejecución de redis:
sudo cp src/redis-server /usr/local/bin/
sudo cp src/redis-cli /usr/local/bin/

-Ejecutar redis:
redis-server


2. Para instalar MySQL siguiendo este tutorial:

- Instalar MySQL:
sudo apt update
sudo apt install mysql-server

- Configurar MySQL con el script de seguridad:
sudo mysql_secure_installation

- Entrar a la consola MySQL:
sudo mysql

- Crear un nuevo usuario:
CREATE USER 'usuario'@'localhost' IDENTIFIED BY 'contrasena';

- Darle los privilegios necesarios a ese usuario:
GRANT ALL PRIVILEGES ON *.* TO 'sammy'@'localhost' WITH GRANT OPTION;

- Salir de la consola MySQL:
exit

- Para comprobar que MySQL está ejecutándose correctamente:
systemctl status mysql.service


3. Descargar el proyecto de Github y ejecutarlo desde netBeans