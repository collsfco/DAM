(:Francisco Colls:)
"6)El nombre del crucero, el del puerto de salida y el número total de días de duración de los cruceros que tienen su salida en un puerto de España.",
(:Se agregó como extra el orden por días de duración:)
for $nombrepuerto in doc("puertos.xml")//puerto[pais='España'],
$a in doc("cruceros.xml")//crucero
where $a//puerto_salida=$nombrepuerto/nombre
order by sum($a//etapa/dias) descending
return <crucero>{$a//nombre} {$a//puerto_salida}
    <dias_crucero>{sum($a//etapa/dias)} </dias_crucero>
</crucero>
,

(:Solución profesora:)
for $a in doc("cruceros.xml")//crucero
for $b in doc ("puertos.xml")//puerto
where $a/recorrido/puerto_salida=$b/nombre and $b/pais='España'
return <crucero>{$a/nombre,$a/recorrido/puerto_salida}
<dias_crucero>{sum($a/recorrido/etapa/dias)}</dias_crucero></crucero>