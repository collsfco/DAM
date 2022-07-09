(:Francisco Colls:)
"1) Obtener el nombre de los puertos de Italia.",

for $a in doc("puertos.xml")/puertos/puerto
where $a/pais="Italia"
return <puerto> {$a/nombre}</puerto>,

"2)Obtener el número de puertos de España.",

let $b := doc("puertos.xml")//puerto[pais='España']
return <NumeroDePuertosEnEspaña>
{count($b)}
</NumeroDePuertosEnEspaña>,


"3) El nombre y el recorrido de los cruceros que se organizan cada mes, con un precio base superior seiscientos euros ordenados por su nombre.",

for $a in doc("cruceros.xml")//crucero
where $a/frecuencia="cada_mes" and $a/precio/base > 600
order by $a/nombre
return <crucero>{$a/nombre}{$a/recorrido}</crucero>,

"4) El nombre y el número de etapas de los cruceros que se organizan “cada_dos_meses”.",

for $a in doc("cruceros.xml")//crucero
where $a/frecuencia="cada_dos_meses" 
return <crucero> {$a/nombre} <numero_de_etapas>{count($a//etapa)}
</numero_de_etapas> </crucero>
,

"5)El nombre del crucero, el del puerto de salida y el precio de un camarote exterior (que se calcula añadiendo al precio base, el precio extra por un camarote exterior) de aquellos cruceros que paran o salen de la ciudad de Bari.",

for $a in doc("cruceros.xml")//crucero
where $a//puertos_salida="Bari" or $a//puerto="Bari"
return <crucero>
    {$a/nombre}{$a//puerto_salida}
  <precio_en_camarote_exterior>
     {$a//base + $a//extra_camarote_exterior}
   </precio_en_camarote_exterior>
</crucero>
,
"6)El nombre del crucero, el del puerto de salida y el número total de días de duración de los cruceros que tienen su salida en un puerto de España.",
(:Se agregó como extra el orden por días de duración:)
for $nombrepuerto in doc("puertos.xml")//puerto[pais='España'],
$a in doc("cruceros.xml")//crucero
where $a//puerto_salida=$nombrepuerto/nombre
order by sum($a//etapa/dias) descending
return <crucero>{$a//nombre} {$a//puerto_salida}
    <dias_crucero>{sum($a//etapa/dias)} </dias_crucero>
</crucero> 

