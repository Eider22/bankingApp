# Documentación

##Endpoints y ejemplos de petición.

## Customers endpoints(titulares de las cuentas):

Verbo: POST  
Acción: Guardar customer  
	
	http://localhost:3001/customer

```json
{
		"firstName": "Juan",
		"secondName": "Pablo",
		"firstLastname": "Cardona",
		"secondLastname": "",
		"identityNumber": "1111111111",
		"dateOfBirth": "1995-09-22"
	}
```

Verbo: GET  
Acción: Obtener todos los customers  
	
	http://localhost:3001/customer

	
Verbo: PUT  
Acción: Editar customer  
	
	http://localhost:3001/customer?id=22

```json
{
	"id": "1",
	"firstName": "Alberto",
	"secondName": "",
	"firstLastname": "Marín",
	"secondLastname": "",
	"identityNumber": "1111111111",  → debe llevar el mismo número de cédula del customer existente
	"dateOfBirth": "1995-01-01"
}
```

Verbo: DELETE  
Acción: Eliminar customer  
	
	http://localhost:3001/customer/delete?id=1


Verbo: GET  
Acción: Obtener customer por identificación  
	
	http://localhost:3001/customer/findByIdentification?identification=1111111111



Verbo: POST  
Acción: Obtener customer por id    
	
	http://localhost:3001/customer/findById?id=14


## Accounts endpoints(cuentas):

Verbo: GET  
Acción: Obtener las cuentas asociadas a un customer    
	
	http://localhost:3001/account/listByCustomerId?id=1


Verbo: POST  
Acción: Crear una cuenta    
	
	http://localhost:3001/account/listByCustomerId?id=1

```json
{
	"accountNumber":"66666",
	"accountType":"1",       → 1="Ahorros", 2="Corriente"
	"customerId": 1
}
```


## Transactions endpoints(transacciones):

Verbo: GET  
Acción: Obtener las transacciones de una cuenta por su id  
	
	http://localhost:3001/transaction/listByAccountId?id=5



Verbo: POST  
Acción: Hacer un retiro(para cualquier tipo de cuenta)    
	
	http://localhost:3001/transaction/withdrawal

```json
{
	"numberAccount":"66666",
	"amount":"10000"
}
```




## NOTAS:
- Aún faltan las funcionalidades de transferir y depositar
- Actualmente cuando un customer se elimina, sus cuentas se están eliminando, pero esta funcionalidad se tuvo que dar
a través del uso de los servicios y los repositorios, pues no ha sido posible darle una correcta configuración a SQLite para que
tenga en cuenta la restricción ON DELETE CASCADE. Además esta misma funcionalidad falta implementarla para que en el momento en el que se elimina una cuenta se eliminen sus transacciones.
- Falta llevar la conexión a base de datos a otra capa.
- Finalmente el proyecto aún se debe mejorar bastante para completar las funcionalidades esperadas y reducir el acoplamiento entre capas, entre otras cosas.

