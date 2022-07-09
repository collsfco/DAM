(:Francisco Colls:)
"1) Obtener el nombre de los puertos de Italia.",

for $a in doc("puertos.xml")/puertos/puerto
where $a/pais="Italia"
return <puerto> {$a/nombre}</puerto>