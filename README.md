# LOANSYSTEM
 Este software gestiona prestamos que seran pagados en una sola cuota luego de la fecha de termino acordada dicha cuota comprende el pago del monto prestado mas el interes correspondiente por la
cantidad de dias transcurridos.

**1 - Desarrolar la funcionalidad getLoansByClientId(long id) la cual consiste en obtener una lista de
prestamos que pertenecen a este cliente en especifico.**

**2 - Desarrollar la funcionalidad getLoans() la cual consiste en obtener todos los prestamos existentes
en una lista.**

**3 - Desarrollar la funcionalidad getLoansWithAmountGreaterThan(double amount) la cual consiste en obtener
los prestamos con el monto mayor o igual al parametro ingresado.**

**4 - Desarrollar la funcionalidad createLoan(...Params) la cual consiste en crear un prestamo
con un cliente correspondiente asi como su fecha de inicio, termino y el monto del prestamo.
El interes ha asignar se calculara dependiendo la duracion en dias del prestamo, siendo las metricas
pautadas las siguientes:**

>- de 0 a 15 dias = 10%
>- de 16 a 30 dias = 20%
>- de 31 dias en adelante = 30%

Dicha funcion a de retornar el prestamo creado con su id asignado y todos sus campos correspondientes.

>EJEMPLO:
>
>Berlis Rodriguez con numero de DNI 402-9393932-1, solicita un prestamo de 10,000.00 pesos
en la fecha 01/01/2023 con el fin de este ser pagado en la fecha 15/01/2023.
>
>El prestamo en este caso ha de tener un 10% de interes.