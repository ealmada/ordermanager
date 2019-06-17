# Getting Started

### Reference Documentation

H2 Embeded Database for DEMO Spring Boot 2

You can access to the db [here](http://localhost:8080/h2-console/login.do?jsessionid=647b98513ae89b08f34086845a5e3784)

1- The given model here ![here](capture.png)

`The original model has been modified due the use of spring boot. `

2- Si tenemos una tabla de pedidos con muchos registros y columnas e incluso blobs:
    <br>
    <h4>A nivel de base de datos: </h4>utilizariamos una base distribuida. 
    En oracle pensariamos en un rac con varias instancias para la base.
    O evaluar utilizar una base de datos no relacional distribuida, utilizando varios nodos.
    En ambos casos, la necesidad de crear indices y evaluar el plan de ejecucion para 
    las queries es necesario.
    <h4>Networking y capa de aplicación:</h4>
    Tener el servicio replicado, podrían utilizarse microinstancias y un load balancer para esto.
    <h4>Otras</h4>
    Crear colas y clases que manejen estas colas para procesar las solicitudes. 
    Una vez que llegue una solicitud a la instancia del servicio, 
    por ejemplo guardar un pedido, el pedido ingresara en una cola de "guardar pedidos".
    Un manejador de la cola del guardado de pedidos tendrá luego la responsabilidad de 
    guardar ese pedido cuando el recurso de la base de datos este disponible.
     
    
    
   
   