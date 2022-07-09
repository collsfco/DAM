(:Francisco Colls:)
"3) El nombre y el recorrido de los cruceros que se organizan cada mes, con un precio base superior seiscientos euros ordenados por su nombre.",

for $a in doc("cruceros.xml")//crucero
where $a/frecuencia="cada_mes" and $a/precio/base > 600
order by $a/nombre
return <crucero>{$a/nombre}{$a/recorrido}</crucero>