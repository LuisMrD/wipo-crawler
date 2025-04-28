<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registrar Patente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body class="container mt-4">

    <h2>Registrar Patente</h2>

    <div id="loader" class="d-none text-center my-3">
        <div class="spinner-border text-primary" role="status">
            <span class="visually-hidden">Carregando...</span>
        </div>
    </div>

    <div class="mb-3">
        <input type="text" id="applicationNumber" class="form-control" placeholder="Enter Process Number (e.g., WO2002008676)">
        <button class="btn btn-primary mt-2" onclick="fetchPatent()">Buscar</button>
    </div>

    <form id="patentForm">
        <div class="mb-3">
            <span>Número da publicação</span>
            <input type="text" id="publicationNumber" name="publicationNumber" class="form-control" placeholder="Publication Number">
        </div>
        <div class="mb-3">
            <span>№ do pedido internacional</span>
            <input type="text" id="applicationNumberField" name="applicationNumber" class="form-control" placeholder="Application Number">
        </div>
        <div class="mb-3">
            <span>Data de publicação</span>
            <input type="text" id="publicationDate" name="publicationDate" class="form-control" placeholder="Publication Date">
        </div>
        <div class="mb-3">
            <span>Requerentes</span>
            <input type="text" id="applicant" name="applicant" class="form-control" placeholder="Applicant">
        </div>
        <div class="mb-3">
            <span>Título</span>
            <input type="text" id="title" name="title" class="form-control" placeholder="Title">
        </div>
        <button type="button" class="btn btn-success" onclick="savePatent()">Salvar</button>
    </form>

    <script src="/js/patent.js"></script>

</body>
</html>
