<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>

    <asset:stylesheet src="application.css"/>

    <g:layoutHead/>

    <style>
    .footer {
        position: fixed;
        left: 0;
        bottom: 0;
        width: 100%;
        color: white;
        text-align: left;
    }
    </style>

</head>

<body>

<div class="navbar navbar-default navbar-static-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

          <!--  <a class="navbar-brand" href="/#">
                <asset:image src="grails.svg" alt="Grails Logo"/>
            </a> -->
        </div>

        <div class="navbar-collapse collapse" aria-expanded="false" style="height: 0.8px;">
            <ul class="nav navbar-nav navbar-left">
                <li><a href="/"><g:message code="homePage.label"/></a></li>
                <li><a href="/main/userPage"><g:message code="userPage.label"/></a></li>
                <li><a href="/main/clercPage"><g:message code="clercPage.label"/></a></li>
                <li><a href="/main/adminPage"><g:message code="adminPage.label"/></a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <sec:ifNotLoggedIn>
                    <li><a href="/login"><g:message code="loginPage.label"/></a></li>
                    <li><a href="/"><g:message code="registerPage.label"/></a></li>
                </sec:ifNotLoggedIn>
                <sec:ifLoggedIn>
                    <li><a href="/logout"><g:message code="logoutPage.label"/></a></li>
                </sec:ifLoggedIn>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                        <g:message code="languages" default="Languages"/> <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <navBar:localeDropdownListItems uri="${request.forwardURI}"/>
                    </ul>
                </li>
            </ul>


        </div>
    </div>
</div>

<g:layoutBody/>

<div class="footer" role="contentinfo">
    Alla Sharai 2018
</div>


<div id="spinner" class="spinner" style="display:none;">
    <g:message code="spinner.alt" default="Loading&hellip;"/>
</div>

<asset:javascript src="application.js"/>

</body>
</html>
