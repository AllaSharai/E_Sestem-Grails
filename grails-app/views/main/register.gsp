<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
    <title><g:message code="default.create.label" args="[entityName]" /></title>
</head>
<body>
<a href="#create-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div id="create-user" class="content scaffold-create" role="main">
    <h1><g:message code="default.create.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.user}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.user}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form resource="${this.user}" method="POST" url="[controller:'main',action:'registerUser']">
        <fieldset class="form">
            <f:field bean="user" property="username"/>
            <f:field bean="user" property="email"/>
            <f:field bean="user" property="password"/>
            <br>
            <f:field bean="user" property="firstName"/>
            <f:field bean="user" property="lastName"/>
            <f:field bean="user" property="pesel"/>
            <h2><g:message code="default.checkEmail.label"/></h2>

        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="registerUser" value="${message(code: 'default.button.register.label', default: 'Register')}" />
        </fieldset>
    </g:form>
</div>
</body>
</html>