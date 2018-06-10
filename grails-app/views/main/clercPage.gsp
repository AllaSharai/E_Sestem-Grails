<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="main.title.label"/></title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>

<body>

<div id="list-document" class="content scaffold-list" role="main" style="overflow-y: auto">
    <h1><g:message code="document.list.label"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <table class="table table-striped">
        <tr>
            <th>Document Id</th>
            <th>User name</th>
            <th>Document title</th>
            <th>Document text</th>
            <th>Document status</th>
        </tr>
        <g:each in="${documentList}" var="document">

            <tr>
                <td>${document.id}</td>
                <td>${document.owner.firstName} ${document.owner.lastName}</td>
                <td><a href="/main/getPdf?id=${document.id}">${document.title}</a></td>
                <td>${document.text}</td>
                <td>Approved: <input type="checkbox" <g:if test="${document.approved}">checked</g:if>></td>
            </tr>
        </g:each>
    </table>
    <button onclick="button()" class="save"><g:message code="document.save.label"/></button>
    <br>
    <br>
    <br>
    <script>

        function button() {
            var approvedDocs = [];
            var unapprovedDocs = [];
            var docs = document.getElementsByTagName('tr');

            for(var i = 1; i < docs.length; i++) {
                var id = docs[i].children[0].innerText;
                var status = docs[i].children[4].children[0].checked;
                status ? approvedDocs.push(id) : unapprovedDocs.push(id);
            }

            $.ajax({
                url: "updateDocumentsStatus",
                type: "POST",
                data: { approvedDocs : JSON.stringify(approvedDocs),
                        unapprovedDocs : JSON.stringify(unapprovedDocs)},
                success: function( data ) {
                    alert( "All document data successfully saved");
                }
            });

        }
    </script>
    %{--<f:table collection="${documentList}"--}%
             %{--properties="['owner', 'title', 'text', 'approved']"/>--}%

    %{--<div class="pagination">--}%
        %{--<g:paginate total="${documentCount ?: 0}"/>--}%
    %{--</div>--}%
</div>

</body>
</html>