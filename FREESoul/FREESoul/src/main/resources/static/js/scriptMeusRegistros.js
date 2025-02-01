$(document).ready(function () {
    var idPaciente = getCookie("idPaciente"); // Obtém o ID do paciente salvo no cookie

    if (idPaciente) {
        $.ajax({
            url: '/api/meusregistros/' + idPaciente, // Faz a requisição à API
            type: 'GET',
            success: function (response) {
                $('#tabelaRegistros').empty(); // Limpa a tabela antes de preencher

                if (response && response.length > 0) {
                    response.forEach(function (diario) {
                        $('#tabelaRegistros').append(
                            '<tr>' +
                            '<td>' + (diario.humor || 'Não informado') + '</td>' +
                            '<td>' + diario.texto + '</td>' +
                            '<td>' +
                            '<a href="/excluir-diario?id=' + diario.id + '" class="btn btn-danger btn-sm">Excluir</a> ' +
                            '<a href="/alterar-diario?id=' + diario.id + '" class="btn btn-warning btn-sm">Alterar</a>' +
                            '</td>' +
                            '</tr>'
                        );
                    });
                } else {
                    $('#tabelaRegistros').append(
                        '<tr><td colspan="3" class="text-center">Nenhum registro encontrado.</td></tr>'
                    );
                }
            },
            error: function () {
                $('#tabelaRegistros').empty().append(
                    '<tr><td colspan="3" class="text-center">Erro ao carregar registros.</td></tr>'
                );
            }
        });
    } else {
        $('#tabelaRegistros').empty().append(
            '<tr><td colspan="3" class="text-center">Usuário não identificado.</td></tr>'
        );
    }
});

// Função para obter o ID do paciente salvo no cookie
function getCookie(name) {
    var cookies = document.cookie.split(';');
    for (var i = 0; i < cookies.length; i++) {
        var cookie = cookies[i].trim();
        if (cookie.startsWith(name + '=')) {
            return cookie.split('=')[1];
        }
    }
    return null;
}
