<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Buscar Patentes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body class="container mt-4">

    <h2>Buscar Patente</h2>

    <div class="row">
        <div class="col-md-5 mb-3">
            <input type="text" id="searchPublicationNumber" class="form-control" placeholder="Publication Number">
        </div>
        <div class="col-md-5 mb-3">
            <input type="text" id="searchApplicant" class="form-control" placeholder="Applicant">
        </div>
        <div class="col-md-2">
            <button class="btn btn-primary" onclick="searchPatents()">Pesquisar</button>
        </div>
    </div>

    <table class="table table-bordered mt-4">
        <thead>
            <tr>
                <th>Número da publicação</th>
                <th>№ do pedido internacional</th>
                <th>Data de publicação</th>
                <th>Requerente</th>
                <th>Título</th>
            </tr>
        </thead>
        <tbody id="patentTableBody">
        </tbody>
    </table>

    <script src="/js/patent.js"></script>

</body>
</html>
