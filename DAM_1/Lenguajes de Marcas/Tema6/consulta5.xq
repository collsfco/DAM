(:Francisco Colls:)
"5)El nombre del crucero, el del puerto de salida y el precio de un camarote exterior (que se calcula añadiendo al precio base, el precio extra por un camarote exterior) de aquellos cruceros que paran o salen de la ciudad de Bari.",

for $a in doc("cruceros.xml")//crucero
where $a//puertos_salida="Bari" or $a//puerto="Bari"
return <crucero>
    {$a/nombre}{$a//puerto_salida}
  <precio_en_camarote_exterior>
     {$a//base + $a//extra_camarote_exterior}
   </precio_en_camarote_exterior>
</crucero>


