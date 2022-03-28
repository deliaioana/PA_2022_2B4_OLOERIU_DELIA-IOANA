<html>
<head>
  <title>Your Catalog</title>
</head>
<body>
  <h1>Your Catalog</h1>
  <a href="${catalog.url}">${catalog.name}</a>
  <#list catalog.items as item>
    <li>Title: ${item.title}</li>
    <li>ID: ${item.id}</li>
    <li>Author: ${item.author}</li>
    <li>Type: ${item.type}</li>
    <li>Year: ${item.year}</li>
    <li>Location: ${item.location}</li>
    <br/><br/>
  </#list>
</body>
</html>