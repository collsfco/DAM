(:Francisco Colls:)
"2)Obtener el número de puertos de España.",

let $b := doc("puertos.xml")//puerto[pais='España']
return <NumeroDePuertosEnEspaña>
{count($b)}
</NumeroDePuertosEnEspaña>

,
"Solución profesora",

for $a in doc("puertos.xml")/puertos
let $b := $a/puerto[pais="España"]
return <NumeroDePuertosEnEspaña>
{count($b)}
</NumeroDePuertosEnEspaña>
