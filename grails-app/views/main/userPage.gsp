<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="main.title.label"/></title>
</head>

<body>
<a href="#list-document" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                               default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="createDocument"><g:message code="document.new.label"/></g:link></li>
    </ul>
</div>

<div id="list-document" class="content scaffold-list" role="main">
    <h1><g:message code="document.list.label"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <f:table collection="${documentList}"
             properties="['title', 'text', 'approved']"/>

    <div class="pagination">
        <g:paginate total="${documentCount ?: 0}"/>
    </div>
</div>
</body>
</html>