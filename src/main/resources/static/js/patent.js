function fetchPatent() {
    let applicationNumber = $("#applicationNumber").val();

        $("#loader").removeClass("d-none");

        $.ajax({
            url: "/fetch",
            type: "GET",
            data: { applicationNumber: applicationNumber },
            success: function (data) {
                $("#publicationNumber").val(data.publicationNumber);
                $("#applicationNumberField").val(data.applicationNumber);
                $("#publicationDate").val(data.publicationDate);
                $("#applicant").val(data.applicant);
                $("#title").val(data.title);
            },
            error: function (xhr, status, error) {
                alert("Erro ao buscar patente: " + error);
            },
            complete: function () {
                $("#loader").addClass("d-none");
            }
        });
}

function savePatent() {
    let patent = {
        publicationNumber: $("#publicationNumber").val(),
        applicationNumber: $("#applicationNumberField").val(),
        publicationDate: $("#publicationDate").val(),
        applicant: $("#applicant").val(),
        title: $("#title").val()
    };

    $.ajax({
        url: "/save",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(patent),
        success: function () {
            alert("Patente salva com sucesso!");
            $("#patentForm")[0].reset();
        }
    });
}

function searchPatents() {
    let publicationNumber = $("#searchPublicationNumber").val();
    let applicant = $("#searchApplicant").val();

    $.ajax({
        url: "/find",
        type: "GET",
        data: { publicationNumber: publicationNumber, applicant: applicant },
        success: function (data) {
            let tableBody = "";
            data.forEach(function (patent) {
                tableBody += `
                    <tr>
                        <td>${patent.publicationNumber}</td>
                        <td>${patent.applicationNumber}</td>
                        <td>${patent.publicationDate}</td>
                        <td>${patent.applicant}</td>
                        <td>${patent.title}</td>
                    </tr>
                `;
            });
            $("#patentTableBody").html(tableBody);
        }
    });
}
