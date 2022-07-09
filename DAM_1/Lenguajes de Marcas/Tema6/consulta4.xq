(:Francisco Colls:)
"4) El nombre y el número de etapas de los cruceros que se organizan “cada_dos_meses”.",

for $a in doc("cruceros.xml")//crucero
where $a/frecuencia="cada_dos_meses" 
return <crucero> {$a/nombre} <numero_de_etapas>{count($a//etapa)}
</numero_de_etapas> </crucero>
