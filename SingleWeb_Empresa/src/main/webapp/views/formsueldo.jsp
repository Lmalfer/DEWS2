<link rel="stylesheet" type="text/css" href="./css/style.css">

<div class="standard">
    <h2>Consultar sueldo Empleado</h2>
</div>
<form action="gestor" method="get">
    <input type="hidden" name="opcion" value="sueldoByDni">
    <label for="dni">Introduce el DNI del empleado:</label>
    <input type="text" name="dni" id="dni" required>
    <br>
    <input type="submit" value="Consultar Sueldo">
</form>