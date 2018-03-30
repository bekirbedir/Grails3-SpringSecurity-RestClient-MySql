<!DOCTYPE html>
<html>
<head>

    <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
call api cagirildi
<h1>${letter ?:0}</h1>
<h1>${response.sku}</h1>


<g:each in="${response}" var="entry">
    <g:each in="${entry.entrySet()}" var="childMap">
        <ul>
            <li>${childMap.key} : ${childMap.value}</li>
        </ul>
    </g:each>
</g:each>




</body>
</html>
