<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="main.title.label"/></title>
</head>

<body>
<a href="#create-document" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                 default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="index"><g:message code="document.list.label"/></g:link></li>
    </ul>
</div>

<div id="create-document" class="content scaffold-create" role="main">
    <h1><g:message code="document.create.label"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.document}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.document}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form resource="${this.document}" url="[controller:'main',action:'saveDocument']" method="POST">
        <fieldset class="form">
            <f:field bean="document" property="title"/>
            <f:field bean="document" property="text" widget="textarea"/>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="saveDocument" class="save"
                            value="${message(code: 'default.button.create.label', default: 'Create')}"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>