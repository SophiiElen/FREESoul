$(document).ready(function () {
    $('#buscarCpfBtn').on('click', function (e) { // Correção aqui
        e.preventDefault(); // Previne o envio padrão do formulário

        var cpf = $('#cpfInput').val().trim(); // Obtém o CPF inserido

        if (cpf) {
            console.log('teste');
            $.ajax({
                url: '/Medico/listarDiarios/' + cpf,
                type: 'GET',
                success: function (response) {
                    console.log("Resposta do servidor: ", response); // Log para depuração

                    // Limpa a tabela antes de preenchê-la
                    $('#tabelaRegistros').empty();

                    // Verifica se há registros na resposta
                    if (response && response.length > 0) {
                        response.forEach(function (diario) {
                            $('#tabelaRegistros').append(
                                '<tr>' +
                                '<td>' + (diario.humor || 'Não informado') + '</td>' +
                                '<td>' + diario.texto + '</td>' +
                                '</tr>'
                            );
                        });
                    } else {
                        $('#tabelaRegistros').append(
                            '<tr><td colspan="2" class="text-center">Nenhum registro encontrado.</td></tr>'
                        );
                    }
                },
                error: function (xhr, status, error) {
                    console.error("Erro ao buscar registros: ", error);
                    $('#tabelaRegistros').empty().append(
                        '<tr><td colspan="2" class="text-center">Erro ao buscar registros. Tente novamente.</td></tr>'
                    );
                }
            });
        } else {
            $('#tabelaRegistros').empty().append(
                '<tr><td colspan="2" class="text-center">Por favor, insira um CPF válido.</td></tr>'
            );
        }
    });
});
